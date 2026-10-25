package androidx.camera.core.processing;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.Logger;
import androidx.camera.core.SurfaceOutput;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.utils.MatrixExt;
import androidx.camera.core.impl.utils.TransformUtils;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Consumer;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes3.dex */
final class SurfaceOutputImpl implements SurfaceOutput {
    private final float[] mAdditionalTransform;
    private final SurfaceOutput.CameraInputInfo mCameraInputInfo;
    private final ListenableFuture<Void> mCloseFuture;
    private CallbackToFutureAdapter.Completer<Void> mCloseFutureCompleter;
    private Consumer<SurfaceOutput.Event> mEventListener;
    private Executor mExecutor;
    private final int mFormat;
    private boolean mHasPendingCloseRequest;
    private final float[] mInvertedTextureTransform;
    private boolean mIsClosed;
    private final Object mLock;
    private final float[] mSecondaryAdditionalTransform;
    private final SurfaceOutput.CameraInputInfo mSecondaryCameraInputInfo;
    private final float[] mSecondaryInvertedTextureTransform;
    private Matrix mSensorToBufferTransform;
    private final Size mSize;
    private final Surface mSurface;
    private final int mTargets;

    /* renamed from: $r8$lambda$BPwK3dRtWFOkowL69-hb8JIT2RI, reason: not valid java name */
    public static /* synthetic */ Object m616$r8$lambda$BPwK3dRtWFOkowL69hb8JIT2RI(SurfaceOutputImpl r0, CallbackToFutureAdapter.Completer r1) {
        r0.mCloseFutureCompleter = r1;
        return "SurfaceOutputImpl close future complete";
    }

    public static /* synthetic */ void $r8$lambda$tN7PQI3jxt3m69zesHNhPckAd28(SurfaceOutputImpl r1, AtomicReference r2) {
        r1.getClass();
        ((Consumer) r2.get()).accept(SurfaceOutput.Event.of(0, r1));
    }

    public SurfaceOutputImpl(Surface r6, int r7, int r8, Size r9, SurfaceOutput.CameraInputInfo r10, SurfaceOutput.CameraInputInfo r11, Matrix r12) {
        this.mLock = new Object();
        float[] r1 = new float[16];
        this.mAdditionalTransform = r1;
        float[] r2 = new float[16];
        this.mSecondaryAdditionalTransform = r2;
        float[] r3 = new float[16];
        this.mInvertedTextureTransform = r3;
        float[] r0 = new float[16];
        this.mSecondaryInvertedTextureTransform = r0;
        this.mHasPendingCloseRequest = false;
        this.mIsClosed = false;
        this.mSurface = r6;
        this.mTargets = r7;
        this.mFormat = r8;
        this.mSize = r9;
        this.mCameraInputInfo = r10;
        this.mSecondaryCameraInputInfo = r11;
        this.mSensorToBufferTransform = r12;
        calculateAdditionalTransform(r1, r3, r10);
        calculateAdditionalTransform(r2, r0, r11);
        this.mCloseFuture = CallbackToFutureAdapter.getFuture(new SurfaceOutputImpl$$ExternalSyntheticLambda0(this));
    }

    private static void calculateAdditionalTransform(float[] r9, float[] r10, SurfaceOutput.CameraInputInfo r11) {
        android.opengl.Matrix.setIdentityM(r9, 0);
        if (r11 != null) goto L5;
        return;
    L5:
        MatrixExt.preVerticalFlip(r9, 0.5f);
        MatrixExt.preRotate(r9, r11.getRotationDegrees(), 0.5f, 0.5f);
        if (r11.getMirroring() == false) goto L8;
        android.opengl.Matrix.translateM(r9, 0, 1.0f, 0.0f, 0.0f);
        android.opengl.Matrix.scaleM(r9, 0, -1.0f, 1.0f, 1.0f);
    L8:
        Matrix r4 = TransformUtils.getRectToRect(TransformUtils.sizeToRectF(r11.getInputSize()), TransformUtils.sizeToRectF(TransformUtils.rotateSize(r11.getInputSize(), r11.getRotationDegrees())), r11.getRotationDegrees(), r11.getMirroring());
        RectF r5 = new RectF(r11.getInputCropRect());
        r4.mapRect(r5);
        float r42 = r5.left / r1.getWidth();
        float r6 = ((r1.getHeight() - r5.height()) - r5.top) / r1.getHeight();
        float r7 = r5.width() / r1.getWidth();
        float r52 = r5.height() / r1.getHeight();
        android.opengl.Matrix.translateM(r9, 0, r42, r6, 0.0f);
        android.opengl.Matrix.scaleM(r9, 0, r7, r52, 1.0f);
        calculateInvertedTextureTransform(r10, r11.getCameraInternal());
        android.opengl.Matrix.multiplyMM(r9, 0, r10, 0, r9, 0);
    }

    private static void calculateInvertedTextureTransform(float[] r4, CameraInternal r5) {
        android.opengl.Matrix.setIdentityM(r4, 0);
        MatrixExt.preVerticalFlip(r4, 0.5f);
        if (r5 == null) goto L7;
        Preconditions.checkState(r5.getHasTransform(), "Camera has no transform.");
        MatrixExt.preRotate(r4, r5.getCameraInfo().getSensorRotationDegrees(), 0.5f, 0.5f);
        if (r5.isFrontFacing() == false) goto L7;
        android.opengl.Matrix.translateM(r4, 0, 1.0f, 0.0f, 0.0f);
        android.opengl.Matrix.scaleM(r4, 0, -1.0f, 1.0f, 1.0f);
    L7:
        android.opengl.Matrix.invertM(r4, 0, r4, 0);
    }

    @Override // androidx.camera.core.SurfaceOutput, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Object r0 = this.mLock;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (this.mIsClosed == true) goto L9;
        this.mIsClosed = true;     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
        this.mCloseFutureCompleter.set(null);
    }

    public ListenableFuture<Void> getCloseFuture() {
        return this.mCloseFuture;
    }

    @Override // androidx.camera.core.SurfaceOutput
    public int getFormat() {
        return this.mFormat;
    }

    @Override // androidx.camera.core.SurfaceOutput
    public Size getSize() {
        return this.mSize;
    }

    @Override // androidx.camera.core.SurfaceOutput
    public Surface getSurface(Executor r2, Consumer<SurfaceOutput.Event> r3) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        this.mExecutor = r2;     // Catch: Throwable -> L10
        this.mEventListener = r3;     // Catch: Throwable -> L10
        boolean r22 = this.mHasPendingCloseRequest;     // Catch: Throwable -> L10
        monitor-exit(r0);     // Catch: Throwable -> L10
        if (r22 == false) goto L9;
        requestClose();
    L9:
        return this.mSurface;
    L10:
        th = move-exception;
        throw th;
    }

    public void requestClose() {
        AtomicReference r0 = new AtomicReference();
        Object r1 = this.mLock;
        monitor-enter(r1);
    L12:
        th = move-exception;
        throw th;
    L5:
        if (this.mExecutor == null) goto L14;
        Consumer<SurfaceOutput.Event> r2 = this.mEventListener;     // Catch: Throwable -> L12
        if (r2 == null) goto L14;
        if (this.mIsClosed == true) goto L15;
        r0.set(r2);     // Catch: Throwable -> L12
        Executor r22 = this.mExecutor;     // Catch: Throwable -> L12
        this.mHasPendingCloseRequest = false;     // Catch: Throwable -> L12
    L16:
        monitor-exit(r1);     // Catch: Throwable -> L12
        if (r22 == null) goto L29;
        r22.execute(new SurfaceOutputImpl$$ExternalSyntheticLambda1(this, r0));     // Catch: RejectedExecutionException -> L20
        return;
    L20:
        e = move-exception;
        Logger.d("SurfaceOutputImpl", "Processor executor closed. Close request not posted.", e);
        return;
    L29:
        return;
    L15:
        r22 = null;
    L14:
        this.mHasPendingCloseRequest = true;     // Catch: Throwable -> L12
        goto L15
    }

    @Override // androidx.camera.core.SurfaceOutput
    public void updateTransformMatrix(float[] r2, float[] r3) {
        updateTransformMatrix(r2, r3, true);
    }

    @Override // androidx.camera.core.SurfaceOutput
    public void updateTransformMatrix(float[] r7, float[] r8, boolean r9) {
        if (r9 == false) goto L5;
        float[] r6 = this.mAdditionalTransform;
    L6:
        android.opengl.Matrix.multiplyMM(r7, 0, r8, 0, r6, 0);
        return;
    L5:
        r6 = this.mSecondaryAdditionalTransform;
        goto L6
    }
}
