package androidx.camera.core.processing.concurrent;

import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Surface;
import androidx.camera.core.CompositionSettings;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.Logger;
import androidx.camera.core.SurfaceOutput;
import androidx.camera.core.SurfaceRequest;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.processing.DefaultSurfaceProcessor$$ExternalSyntheticLambda2;
import androidx.camera.core.processing.DefaultSurfaceProcessor$$ExternalSyntheticLambda4;
import androidx.camera.core.processing.ShaderProvider;
import androidx.camera.core.processing.SurfaceProcessorInternal;
import androidx.camera.core.processing.util.GLUtils;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.functions.Function3;
import org.mvel2.asm.Constants$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public class DualSurfaceProcessor implements SurfaceProcessorInternal, SurfaceTexture.OnFrameAvailableListener {
    private final Executor mGlExecutor;
    final Handler mGlHandler;
    private final DualOpenGlRenderer mGlRenderer;
    final HandlerThread mGlThread;
    private int mInputSurfaceCount;
    private final AtomicBoolean mIsReleaseRequested;
    private boolean mIsReleased;
    final Map<SurfaceOutput, Surface> mOutputSurfaces;
    private SurfaceTexture mPrimarySurfaceTexture;
    private SurfaceTexture mSecondarySurfaceTexture;

    public static class Factory {
        private static Function3<DynamicRange, CompositionSettings, CompositionSettings, SurfaceProcessorInternal> sSupplier;

        static {
            sSupplier = new DualSurfaceProcessor$Factory$$ExternalSyntheticLambda0();
        }

        public static SurfaceProcessorInternal newInstance(DynamicRange r1, CompositionSettings r2, CompositionSettings r3) {
            return sSupplier.invoke(r1, r2, r3);
        }
    }

    /* renamed from: $r8$lambda$-ZbwcPykT74fmw8U2V6XL8hhU5w, reason: not valid java name */
    public static /* synthetic */ void m618$r8$lambda$ZbwcPykT74fmw8U2V6XL8hhU5w(DualSurfaceProcessor r0, Runnable r1, Runnable r2) {
        if (r0.mIsReleased == false) goto L6;
        r1.run();
        return;
    L6:
        r2.run();
    }

    /* renamed from: $r8$lambda$H-VJUM4Do99Q9JsClAV6N0JKx5I, reason: not valid java name */
    public static /* synthetic */ void m619$r8$lambda$HVJUM4Do99Q9JsClAV6N0JKx5I() {
    }

    /* renamed from: $r8$lambda$NAKKU-Hmbm6lwk-7NskA4M9WlNg, reason: not valid java name */
    public static /* synthetic */ void m620$r8$lambda$NAKKUHmbm6lwk7NskA4M9WlNg(DualSurfaceProcessor r0, SurfaceTexture r1, Surface r2, SurfaceRequest.Result r3) {
        r0.getClass();
        r1.setOnFrameAvailableListener(null);
        r1.release();
        r2.release();
        r0.mInputSurfaceCount--;
        r0.checkReadyToRelease();
    }

    public static /* synthetic */ void $r8$lambda$PCwXS4W96kYxX8f3Nu5NwNa2AbE(DualSurfaceProcessor r1) {
        r1.mIsReleased = true;
        r1.checkReadyToRelease();
    }

    /* renamed from: $r8$lambda$SSJVT35FEA6PJGDsDjTufRx-9_M, reason: not valid java name */
    public static /* synthetic */ void m621$r8$lambda$SSJVT35FEA6PJGDsDjTufRx9_M(DualSurfaceProcessor r0, SurfaceOutput r1, SurfaceOutput.Event r2) {
        r0.getClass();
        r1.close();
        Surface r12 = r0.mOutputSurfaces.remove(r1);
        if (r12 == null) goto L6;
        r0.mGlRenderer.unregisterOutputSurface(r12);
        return;
    }

    /* renamed from: $r8$lambda$XjKCBi0iSlQAHK-EbAfakln9aNU, reason: not valid java name */
    public static /* synthetic */ void m622$r8$lambda$XjKCBi0iSlQAHKEbAfakln9aNU(DualSurfaceProcessor r2, SurfaceOutput r3) {
        Surface r0 = r3.getSurface(r2.mGlExecutor, new DualSurfaceProcessor$$ExternalSyntheticLambda5(r2, r3));
        r2.mGlRenderer.registerOutputSurface(r0);
        r2.mOutputSurfaces.put(r3, r0);
    }

    /* renamed from: $r8$lambda$eYxu-5ZhmaW5Q_3yYxnq9Fy4kTk, reason: not valid java name */
    public static /* synthetic */ void m623$r8$lambda$eYxu5ZhmaW5Q_3yYxnq9Fy4kTk(DualSurfaceProcessor r4, SurfaceRequest r5) {
        r4.mInputSurfaceCount++;
        SurfaceTexture r0 = new SurfaceTexture(r4.mGlRenderer.getTextureName(r5.isPrimary()));
        r0.setDefaultBufferSize(r5.getResolution().getWidth(), r5.getResolution().getHeight());
        Surface r1 = new Surface(r0);
        r5.provideSurface(r1, r4.mGlExecutor, new DualSurfaceProcessor$$ExternalSyntheticLambda8(r4, r0, r1));
        if (r5.isPrimary() == false) goto L6;
        r4.mPrimarySurfaceTexture = r0;
        return;
    L6:
        r4.mSecondarySurfaceTexture = r0;
        r0.setOnFrameAvailableListener(r4, r4.mGlHandler);
    }

    /* renamed from: $r8$lambda$gvtYv8V6pqQmb0lqnGgcqf1W-30, reason: not valid java name */
    public static /* synthetic */ void m624$r8$lambda$gvtYv8V6pqQmb0lqnGgcqf1W30(DualSurfaceProcessor r0, DynamicRange r1, Map r2, CallbackToFutureAdapter.Completer r3) {
        r0.getClass();
        r0.mGlRenderer.init(r1, r2);     // Catch: RuntimeException -> L5
        r3.set(null);     // Catch: RuntimeException -> L5
        return;
    L5:
        e = move-exception;
        r3.setException(e);
    }

    public static /* synthetic */ Object $r8$lambda$xmFNEnzuXLtJLpm9XXaBYQZXSMI(DualSurfaceProcessor r1, DynamicRange r2, Map r3, CallbackToFutureAdapter.Completer r4) {
        r1.getClass();
        r1.executeSafely(new DualSurfaceProcessor$$ExternalSyntheticLambda4(r1, r2, r3, r4));
        return "Init GlRenderer";
    }

    public DualSurfaceProcessor(DynamicRange r2, CompositionSettings r3, CompositionSettings r4) {
        this(r2, Collections.EMPTY_MAP, r3, r4);
    }

    public DualSurfaceProcessor(DynamicRange r3, Map<GLUtils.InputFormat, ShaderProvider> r4, CompositionSettings r5, CompositionSettings r6) {
        this.mInputSurfaceCount = 0;
        this.mIsReleased = false;
        this.mIsReleaseRequested = new AtomicBoolean(false);
        this.mOutputSurfaces = new LinkedHashMap();
        HandlerThread r0 = new HandlerThread("CameraX-GL Thread");
        this.mGlThread = r0;
        r0.start();
        Handler r1 = new Handler(r0.getLooper());
        this.mGlHandler = r1;
        this.mGlExecutor = CameraXExecutors.newHandlerExecutor(r1);
        this.mGlRenderer = new DualOpenGlRenderer(r5, r6);
        initGlRenderer(r3, r4);     // Catch: RuntimeException -> L5
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
        if (this.mInputSurfaceCount != 0) goto L14;
        Iterator<SurfaceOutput> r0 = this.mOutputSurfaces.keySet().iterator();
    L8:
        if (r0.hasNext() == false) goto L10;
        r0.next().close();
        goto L8
    L10:
        this.mOutputSurfaces.clear();
        this.mGlRenderer.release();
        this.mGlThread.quit();
        return;
    }

    private void executeSafely(Runnable r2) {
        executeSafely(r2, new DualSurfaceProcessor$$ExternalSyntheticLambda7());
    }

    private void executeSafely(Runnable r3, Runnable r4) {
        this.mGlExecutor.execute(new DualSurfaceProcessor$$ExternalSyntheticLambda6(this, r4, r3));     // Catch: RejectedExecutionException -> L4
        return;
    L4:
        e = move-exception;
        Logger.w("DualSurfaceProcessor", "Unable to executor runnable", e);
        r4.run();
    }

    private void initGlRenderer(DynamicRange r2, Map<GLUtils.InputFormat, ShaderProvider> r3) {
        CallbackToFutureAdapter.getFuture(new DualSurfaceProcessor$$ExternalSyntheticLambda2(this, r2, r3)).get();     // Catch: Throwable -> L5
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

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public void onFrameAvailable(SurfaceTexture r11) {
        if (this.mIsReleaseRequested.get() == true) goto L27;
        SurfaceTexture r0 = this.mPrimarySurfaceTexture;
        if (r0 != null) goto L8;
        return;
    L8:
        if (this.mSecondarySurfaceTexture == null) goto L28;
        r0.updateTexImage();
        this.mSecondarySurfaceTexture.updateTexImage();
        Iterator<Map.Entry<SurfaceOutput, Surface>> r1 = this.mOutputSurfaces.entrySet().iterator();
    L12:
        if (r1.hasNext() == false) goto L29;
        Map.Entry<SurfaceOutput, Surface> r02 = r1.next();
        Surface r6 = r02.getValue();
        SurfaceOutput r7 = r02.getKey();
        if (r7.getFormat() != 34) goto L12;
        this.mGlRenderer.render(r11.getTimestamp(), r6, r7, this.mPrimarySurfaceTexture, this.mSecondarySurfaceTexture);     // Catch: RuntimeException -> L17
    L17:
        e = move-exception;
        Logger.e("DualSurfaceProcessor", "Failed to render with OpenGL.", e);
        goto L12
    L29:
        return;
    L28:
        return;
    }

    @Override // androidx.camera.core.SurfaceProcessor
    public void onInputSurface(SurfaceRequest r3) {
        if (this.mIsReleaseRequested.get() == false) goto L6;
        r3.willNotProvideSurface();
        return;
    L6:
        DualSurfaceProcessor$$ExternalSyntheticLambda1 r0 = new DualSurfaceProcessor$$ExternalSyntheticLambda1(this, r3);
        Objects.requireNonNull(r3);
        executeSafely(r0, new DefaultSurfaceProcessor$$ExternalSyntheticLambda4(r3));
    }

    @Override // androidx.camera.core.SurfaceProcessor
    public void onOutputSurface(SurfaceOutput r3) {
        if (this.mIsReleaseRequested.get() == false) goto L6;
        r3.close();
        return;
    L6:
        DualSurfaceProcessor$$ExternalSyntheticLambda3 r0 = new DualSurfaceProcessor$$ExternalSyntheticLambda3(this, r3);
        Objects.requireNonNull(r3);
        executeSafely(r0, new DefaultSurfaceProcessor$$ExternalSyntheticLambda2(r3));
    }

    @Override // androidx.camera.core.processing.SurfaceProcessorInternal
    public void release() {
        if (this.mIsReleaseRequested.getAndSet(true) == false) goto L5;
        return;
    L5:
        executeSafely(new DualSurfaceProcessor$$ExternalSyntheticLambda0(this));
    }
}
