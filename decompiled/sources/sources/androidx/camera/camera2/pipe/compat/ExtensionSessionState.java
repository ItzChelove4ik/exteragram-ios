package androidx.camera.camera2.pipe.compat;

import androidx.camera.camera2.pipe.compat.CameraExtensionSessionWrapper;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u0007H\u0016J\b\u0010\u000b\u001a\u00020\u0007H\u0016J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Landroidx/camera/camera2/pipe/compat/ExtensionSessionState;", "Landroidx/camera/camera2/pipe/compat/CameraExtensionSessionWrapper$StateCallback;", "captureSessionState", "Landroidx/camera/camera2/pipe/compat/CaptureSessionState;", "<init>", "(Landroidx/camera/camera2/pipe/compat/CaptureSessionState;)V", "onConfigured", _UrlKt.FRAGMENT_ENCODE_SET, "session", "Landroidx/camera/camera2/pipe/compat/CameraExtensionSessionWrapper;", "onSessionDisconnected", "onSessionFinalized", "onConfigureFailed", "onClosed", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ExtensionSessionState implements CameraExtensionSessionWrapper.StateCallback {
    private final CaptureSessionState captureSessionState;

    public ExtensionSessionState(CaptureSessionState r1) {
        this.captureSessionState = r1;
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraExtensionSessionWrapper.StateCallback
    public void onClosed(CameraExtensionSessionWrapper r1) {
        this.captureSessionState.onClosed(r1);
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraExtensionSessionWrapper.StateCallback
    public void onConfigureFailed(CameraExtensionSessionWrapper r1) {
        this.captureSessionState.onConfigureFailed(r1);
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraExtensionSessionWrapper.StateCallback
    public void onConfigured(CameraExtensionSessionWrapper r1) {
        this.captureSessionState.onConfigured(r1);
    }

    @Override // androidx.camera.camera2.pipe.compat.SessionStateCallback
    public void onSessionDisconnected() {
        this.captureSessionState.onSessionDisconnected();
    }

    @Override // androidx.camera.camera2.pipe.compat.SessionStateCallback
    public void onSessionFinalized() {
        this.captureSessionState.onSessionFinalized();
    }
}
