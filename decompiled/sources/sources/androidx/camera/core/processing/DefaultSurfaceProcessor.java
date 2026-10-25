package androidx.camera.core.processing;

import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Size;
import android.view.Surface;
import androidx.arch.core.util.Function;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.ImageProcessingUtil;
import androidx.camera.core.Logger;
import androidx.camera.core.SurfaceOutput;
import androidx.camera.core.SurfaceRequest;
import androidx.camera.core.impl.utils.MatrixExt;
import androidx.camera.core.impl.utils.TransformUtils;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.processing.util.GLUtils;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Triple;
import org.mvel2.asm.Constants$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public class DefaultSurfaceProcessor implements SurfaceProcessorInternal, SurfaceTexture.OnFrameAvailableListener {
    private final Executor mGlExecutor;
    final Handler mGlHandler;
    private final OpenGlRenderer mGlRenderer;
    final HandlerThread mGlThread;
    private int mInputSurfaceCount;
    private final AtomicBoolean mIsReleaseRequested;
    private boolean mIsReleased;
    final Map<SurfaceOutput, Surface> mOutputSurfaces;
    private final List<PendingSnapshot> mPendingSnapshots;
    private final float[] mSurfaceOutputMatrix;
    private final float[] mTextureMatrix;

    public static class Factory {
        private static Function<DynamicRange, SurfaceProcessorInternal> sSupplier;

        static {
            sSupplier = new DefaultSurfaceProcessor$Factory$$ExternalSyntheticLambda0();
        }

        public static SurfaceProcessorInternal newInstance(DynamicRange r1) {
            return sSupplier.apply(r1);
        }
    }

    public static abstract class PendingSnapshot {
        public abstract CallbackToFutureAdapter.Completer<Void> getCompleter();

        public abstract int getJpegQuality();

        public abstract int getRotationDegrees();
    }

    /* renamed from: $r8$lambda$6ehatn4QOFUxJsVy7XqEaXd--A8, reason: not valid java name */
    public static /* synthetic */ void m606$r8$lambda$6ehatn4QOFUxJsVy7XqEaXdA8(DefaultSurfaceProcessor r0, SurfaceRequest r1, SurfaceTexture r2, Surface r3, SurfaceRequest.Result r4) {
        r0.getClass();
        r1.clearTransformationInfoListener();
        r2.setOnFrameAvailableListener(null);
        r2.release();
        r3.release();
        r0.mInputSurfaceCount--;
        r0.checkReadyToRelease();
    }

    /* renamed from: $r8$lambda$8VpokqnQ-Vg450zGfiUT6CjueOY, reason: not valid java name */
    public static /* synthetic */ void m607$r8$lambda$8VpokqnQVg450zGfiUT6CjueOY(DefaultSurfaceProcessor r1) {
        r1.mIsReleased = true;
        r1.checkReadyToRelease();
    }

    public static /* synthetic */ void $r8$lambda$P3oBxHyw9llKTXX07rPqw1VHBwA(DefaultSurfaceProcessor r0, DynamicRange r1, Map r2, CallbackToFutureAdapter.Completer r3) {
        r0.getClass();
        r0.mGlRenderer.init(r1, r2);     // Catch: RuntimeException -> L5
        r3.set(null);     // Catch: RuntimeException -> L5
        return;
    L5:
        e = move-exception;
        r3.setException(e);
    }

    public static /* synthetic */ void $r8$lambda$Q1rrgC8aoiGLiHQv9mIX_otuWf4(DefaultSurfaceProcessor r1, SurfaceRequest r2, SurfaceRequest.TransformationInfo r3) {
        r1.getClass();
        GLUtils.InputFormat r0 = GLUtils.InputFormat.DEFAULT;
        if (r2.getDynamicRange().is10BitHdr() == true) goto L5;
    L7:
        r1.mGlRenderer.setInputFormat(r0);
        return;
    L5:
        if (r3.hasCameraTransform() == false) goto L7;
        r0 = GLUtils.InputFormat.YUV;
        goto L7
    }

    /* renamed from: $r8$lambda$WkmvDX1VoxVksXmre1pdTM-7PX8, reason: not valid java name */
    public static /* synthetic */ void m608$r8$lambda$WkmvDX1VoxVksXmre1pdTM7PX8(DefaultSurfaceProcessor r2, SurfaceOutput r3) {
        Surface r0 = r3.getSurface(r2.mGlExecutor, new DefaultSurfaceProcessor$$ExternalSyntheticLambda6(r2, r3));
        r2.mGlRenderer.registerOutputSurface(r0);
        r2.mOutputSurfaces.put(r3, r0);
    }

    public static /* synthetic */ void $r8$lambda$Yh367u_2KFarOwBqtvsSpeftiww(DefaultSurfaceProcessor r0, SurfaceOutput r1, SurfaceOutput.Event r2) {
        r0.getClass();
        r1.close();
        Surface r12 = r0.mOutputSurfaces.remove(r1);
        if (r12 == null) goto L6;
        r0.mGlRenderer.unregisterOutputSurface(r12);
        return;
    }

    public static /* synthetic */ Object $r8$lambda$eZcZsxjTSPFb419HaHACXsjhtYA(DefaultSurfaceProcessor r1, DynamicRange r2, Map r3, CallbackToFutureAdapter.Completer r4) {
        r1.getClass();
        r1.executeSafely(new DefaultSurfaceProcessor$$ExternalSyntheticLambda9(r1, r2, r3, r4));
        return "Init GlRenderer";
    }

    /* renamed from: $r8$lambda$i022ReF-OMMyB0jSZtyCUD1hM14, reason: not valid java name */
    public static /* synthetic */ void m609$r8$lambda$i022ReFOMMyB0jSZtyCUD1hM14() {
    }

    /* renamed from: $r8$lambda$ng-fDf0hLfDAuzPNpoHXLrNZrXk, reason: not valid java name */
    public static /* synthetic */ void m610$r8$lambda$ngfDf0hLfDAuzPNpoHXLrNZrXk(DefaultSurfaceProcessor r4, SurfaceRequest r5) {
        r4.mInputSurfaceCount++;
        SurfaceTexture r0 = new SurfaceTexture(r4.mGlRenderer.getTextureName());
        r0.setDefaultBufferSize(r5.getResolution().getWidth(), r5.getResolution().getHeight());
        Surface r1 = new Surface(r0);
        r5.setTransformationInfoListener(r4.mGlExecutor, new DefaultSurfaceProcessor$$ExternalSyntheticLambda7(r4, r5));
        r5.provideSurface(r1, r4.mGlExecutor, new DefaultSurfaceProcessor$$ExternalSyntheticLambda8(r4, r5, r0, r1));
        r0.setOnFrameAvailableListener(r4, r4.mGlHandler);
    }

    /* renamed from: $r8$lambda$ryx4Xt9N-nExVDrMO9XaGJjO-nM, reason: not valid java name */
    public static /* synthetic */ void m611$r8$lambda$ryx4Xt9NnExVDrMO9XaGJjOnM(DefaultSurfaceProcessor r0, Runnable r1, Runnable r2) {
        if (r0.mIsReleased == false) goto L6;
        r1.run();
        return;
    L6:
        r2.run();
    }

    public DefaultSurfaceProcessor(DynamicRange r2) {
        this(r2, Collections.EMPTY_MAP);
    }

    public DefaultSurfaceProcessor(DynamicRange r4, Map<GLUtils.InputFormat, ShaderProvider> r5) {
        this.mIsReleaseRequested = new AtomicBoolean(false);
        this.mTextureMatrix = new float[16];
        this.mSurfaceOutputMatrix = new float[16];
        this.mOutputSurfaces = new LinkedHashMap();
        this.mInputSurfaceCount = 0;
        this.mIsReleased = false;
        this.mPendingSnapshots = new ArrayList();
        HandlerThread r0 = new HandlerThread("CameraX-GL Thread");
        this.mGlThread = r0;
        r0.start();
        Handler r1 = new Handler(r0.getLooper());
        this.mGlHandler = r1;
        this.mGlExecutor = CameraXExecutors.newHandlerExecutor(r1);
        this.mGlRenderer = new OpenGlRenderer();
        initGlRenderer(r4, r5);     // Catch: RuntimeException -> L5
        return;
    L5:
        e = move-exception;
        release();
        throw e;
    }

    private void checkReadyToRelease() {
        if (this.mIsReleased == true) goto L5;
        return;
    L5:
        if (this.mInputSurfaceCount != 0) goto L19;
        Iterator<SurfaceOutput> r0 = this.mOutputSurfaces.keySet().iterator();
    L8:
        if (r0.hasNext() == false) goto L10;
        r0.next().close();
        goto L8
    L10:
        Iterator<PendingSnapshot> r02 = this.mPendingSnapshots.iterator();
    L12:
        if (r02.hasNext() == false) goto L14;
        r02.next().getCompleter().setException(new Exception("Failed to snapshot: DefaultSurfaceProcessor is released."));
        goto L12
    L14:
        this.mOutputSurfaces.clear();
        this.mGlRenderer.release();
        this.mGlThread.quit();
        return;
    }

    private void executeSafely(Runnable r2) {
        executeSafely(r2, new DefaultSurfaceProcessor$$ExternalSyntheticLambda10());
    }

    private void executeSafely(Runnable r3, Runnable r4) {
        this.mGlExecutor.execute(new DefaultSurfaceProcessor$$ExternalSyntheticLambda11(this, r4, r3));     // Catch: RejectedExecutionException -> L4
        return;
    L4:
        e = move-exception;
        Logger.w("DefaultSurfaceProcessor", "Unable to executor runnable", e);
        r4.run();
    }

    private void failAllPendingSnapshots(Throwable r3) {
        Iterator<PendingSnapshot> r0 = this.mPendingSnapshots.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().getCompleter().setException(r3);
        goto L4
    L6:
        this.mPendingSnapshots.clear();
    }

    private Bitmap getBitmap(Size r3, float[] r4, int r5) {
        float[] r42 = (float[]) r4.clone();
        MatrixExt.preRotate(r42, r5, 0.5f, 0.5f);
        MatrixExt.preVerticalFlip(r42, 0.5f);
        return this.mGlRenderer.snapshot(TransformUtils.rotateSize(r3, r5), r42);
    }

    private void initGlRenderer(DynamicRange r2, Map<GLUtils.InputFormat, ShaderProvider> r3) {
        CallbackToFutureAdapter.getFuture(new DefaultSurfaceProcessor$$ExternalSyntheticLambda0(this, r2, r3)).get();     // Catch: Throwable -> L5
        return;
    L5:
        e = e;
        if ((e instanceof ExecutionException) == false) goto L10;
        e = e.getCause();
    L10:
        if ((e instanceof RuntimeException) == true) goto L14;
        Constants$$ExternalSyntheticBUOutline0.m("Failed to create DefaultSurfaceProcessor", e);
        return;
    L14:
        throw ((RuntimeException) e);
    }

    private void takeSnapshotAndDrawJpeg(Triple<Surface, Size, float[]> r12) {
        if (this.mPendingSnapshots.isEmpty() == false) goto L5;
        return;
    L5:
        if (r12 != null) goto L41;
        failAllPendingSnapshots(new Exception("Failed to snapshot: no JPEG Surface."));
        return;
    L41:
        ByteArrayOutputStream r0 = new ByteArrayOutputStream();     // Catch: IOException -> L28
        Iterator<PendingSnapshot> r1 = this.mPendingSnapshots.iterator();     // Catch: Throwable -> L18
        int r4 = -1;
        int r6 = -1;
        Bitmap r5 = null;
        byte[] r7 = null;
    L10:
        if (r1.hasNext() == false) goto L26;
        PendingSnapshot r8 = r1.next();     // Catch: Throwable -> L18
        if (r4 != r8.getRotationDegrees()) goto L15;
        if (r5 == null) goto L15;
    L22:
        if (r6 == r8.getJpegQuality()) goto L24;
        r0.reset();     // Catch: Throwable -> L18
        r6 = r8.getJpegQuality();     // Catch: Throwable -> L18
        r5.compress(Bitmap.CompressFormat.JPEG, r6, r0);     // Catch: Throwable -> L18
        r7 = r0.toByteArray();     // Catch: Throwable -> L18
    L24:
        Surface r9 = r12.getFirst();     // Catch: Throwable -> L18
        Objects.requireNonNull(r7);     // Catch: Throwable -> L18
        ImageProcessingUtil.writeJpegBytesToSurface(r9, r7);     // Catch: Throwable -> L18
        r8.getCompleter().set(null);     // Catch: Throwable -> L18
        r1.remove();     // Catch: Throwable -> L18
    L15:
        r4 = r8.getRotationDegrees();     // Catch: Throwable -> L18
        if (r5 == null) goto L20;
        r5.recycle();     // Catch: Throwable -> L18
    L20:
        r5 = getBitmap(r12.getSecond(), r12.getThird(), r4);     // Catch: Throwable -> L18
        r6 = -1;
        goto L22
    L26:
        r0.close();     // Catch: IOException -> L28
        return;
    L18:
        th = move-exception;
        r0.close();     // Catch: Throwable -> L32
    L34:
        throw th;     // Catch: IOException -> L28
    L32:
        th = move-exception;
        th.addSuppressed(th);     // Catch: IOException -> L28
    L28:
        e = move-exception;
        failAllPendingSnapshots(e);
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public void onFrameAvailable(SurfaceTexture r10) {
        if (this.mIsReleaseRequested.get() == true) goto L36;
        r10.updateTexImage();
        r10.getTransformMatrix(this.mTextureMatrix);
        Iterator<Map.Entry<SurfaceOutput, Surface>> r0 = this.mOutputSurfaces.entrySet().iterator();
        Triple<Surface, Size, float[]> r1 = null;
    L7:
        if (r0.hasNext() == false) goto L29;
        Map.Entry<SurfaceOutput, Surface> r2 = r0.next();
        Surface r3 = r2.getValue();
        SurfaceOutput r22 = r2.getKey();
        r22.updateTransformMatrix(this.mSurfaceOutputMatrix, this.mTextureMatrix);
        if (r22.getFormat() != 34) goto L14;
        this.mGlRenderer.render(r10.getTimestamp(), this.mSurfaceOutputMatrix, r3);     // Catch: RuntimeException -> L12
    L12:
        e = move-exception;
        Logger.e("DefaultSurfaceProcessor", "Failed to render with OpenGL.", e);
        goto L7
    L14:
        boolean r6 = false;
        if (r22.getFormat() != 256) goto L17;
        boolean r4 = true;
    L18:
        Preconditions.checkState(r4, "Unsupported format: " + r22.getFormat());
        if (r1 != null) goto L21;
        r6 = true;
    L21:
        Preconditions.checkState(r6, "Only one JPEG output is supported.");
        r1 = new Triple(r3, r22.getSize(), (float[]) this.mSurfaceOutputMatrix.clone());
        goto L7
    L17:
        r4 = false;
        goto L18
    L29:
        takeSnapshotAndDrawJpeg(r1);     // Catch: RuntimeException -> L24
        return;
    L24:
        e = move-exception;
        failAllPendingSnapshots(e);
        return;
    }

    @Override // androidx.camera.core.SurfaceProcessor
    public void onInputSurface(SurfaceRequest r3) {
        if (this.mIsReleaseRequested.get() == false) goto L6;
        r3.willNotProvideSurface();
        return;
    L6:
        DefaultSurfaceProcessor$$ExternalSyntheticLambda3 r0 = new DefaultSurfaceProcessor$$ExternalSyntheticLambda3(this, r3);
        Objects.requireNonNull(r3);
        executeSafely(r0, new DefaultSurfaceProcessor$$ExternalSyntheticLambda4(r3));
    }

    @Override // androidx.camera.core.SurfaceProcessor
    public void onOutputSurface(SurfaceOutput r3) {
        if (this.mIsReleaseRequested.get() == false) goto L6;
        r3.close();
        return;
    L6:
        DefaultSurfaceProcessor$$ExternalSyntheticLambda1 r0 = new DefaultSurfaceProcessor$$ExternalSyntheticLambda1(this, r3);
        Objects.requireNonNull(r3);
        executeSafely(r0, new DefaultSurfaceProcessor$$ExternalSyntheticLambda2(r3));
    }

    @Override // androidx.camera.core.processing.SurfaceProcessorInternal
    public void release() {
        if (this.mIsReleaseRequested.getAndSet(true) == false) goto L5;
        return;
    L5:
        executeSafely(new DefaultSurfaceProcessor$$ExternalSyntheticLambda5(this));
    }
}
