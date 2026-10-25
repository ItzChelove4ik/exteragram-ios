package androidx.camera.camera2.pipe.compat;

import android.graphics.SurfaceTexture;
import android.view.Surface;
import androidx.camera.camera2.config.UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0;
import androidx.camera.camera2.pipe.compat.CameraCaptureSessionWrapper;
import androidx.camera.camera2.pipe.core.Log;
import java.util.concurrent.CountDownLatch;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.atomicfu.AtomicBoolean;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\n\u001a\u00020\u0003H\u0016J\b\u0010\u000b\u001a\u00020\u0003H\u0016J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\r"}, d2 = {"androidx/camera/camera2/pipe/compat/Camera2DeviceCloserImpl$createCaptureSession$callback$1", "Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper$StateCallback;", "onConfigured", _UrlKt.FRAGMENT_ENCODE_SET, "session", "Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper;", "onClosed", "onConfigureFailed", "onReady", "onCaptureQueueEmpty", "onSessionDisconnected", "onSessionFinalized", "onActive", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCamera2DeviceCloser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Camera2DeviceCloser.kt\nandroidx/camera/camera2/pipe/compat/Camera2DeviceCloserImpl$createCaptureSession$callback$1\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,263:1\n50#2,2:264\n50#2,2:266\n50#2,2:268\n*S KotlinDebug\n*F\n+ 1 Camera2DeviceCloser.kt\nandroidx/camera/camera2/pipe/compat/Camera2DeviceCloserImpl$createCaptureSession$callback$1\n*L\n211#1:264,2\n219#1:266,2\n227#1:268,2\n*E\n"})
/* loaded from: classes3.dex */
public final class Camera2DeviceCloserImpl$createCaptureSession$callback$1 implements CameraCaptureSessionWrapper.StateCallback {
    final /* synthetic */ CountDownLatch $sessionConfigured;
    final /* synthetic */ Surface $surface;
    final /* synthetic */ AtomicBoolean $surfaceReleased;
    final /* synthetic */ SurfaceTexture $surfaceTexture;

    public Camera2DeviceCloserImpl$createCaptureSession$callback$1(CountDownLatch r1, AtomicBoolean r2, Surface r3, SurfaceTexture r4) {
        this.$sessionConfigured = r1;
        this.$surfaceReleased = r2;
        this.$surface = r3;
        this.$surfaceTexture = r4;
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraCaptureSessionWrapper.StateCallback
    public void onActive(CameraCaptureSessionWrapper r1) {
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraCaptureSessionWrapper.StateCallback
    public void onCaptureQueueEmpty(CameraCaptureSessionWrapper r1) {
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraCaptureSessionWrapper.StateCallback
    public void onClosed(CameraCaptureSessionWrapper r3) {
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L6;
        android.util.Log.d("CXCP", "Empty capture session closed");
    L6:
        if (this.$surfaceReleased.compareAndSet(false, true) == false) goto L9;
        this.$surface.release();
        this.$surfaceTexture.release();
        return;
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraCaptureSessionWrapper.StateCallback
    public void onConfigureFailed(CameraCaptureSessionWrapper r3) {
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L6;
        android.util.Log.d("CXCP", "Empty capture session configure failed");
    L6:
        if (this.$surfaceReleased.compareAndSet(false, true) == false) goto L8;
        this.$surface.release();
        this.$surfaceTexture.release();
    L8:
        this.$sessionConfigured.countDown();
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraCaptureSessionWrapper.StateCallback
    public void onConfigured(CameraCaptureSessionWrapper r3) {
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L5;
        android.util.Log.d("CXCP", "Empty capture session configured. Closing it");
    L5:
        UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0.m(r3);
        this.$sessionConfigured.countDown();
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraCaptureSessionWrapper.StateCallback
    public void onReady(CameraCaptureSessionWrapper r1) {
    }

    @Override // androidx.camera.camera2.pipe.compat.SessionStateCallback
    public void onSessionDisconnected() {
    }

    @Override // androidx.camera.camera2.pipe.compat.SessionStateCallback
    public void onSessionFinalized() {
    }
}
