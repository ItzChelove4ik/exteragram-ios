package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CameraExtensionSession;
import android.hardware.camera2.CameraExtensionSession$StateCallback;
import androidx.camera.camera2.pipe.CameraInterop;
import androidx.camera.camera2.pipe.compat.CameraExtensionSessionWrapper;
import androidx.camera.camera2.pipe.internal.CameraErrorListener;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicRef;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0001\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0018\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\b\u001a\u00020\tH\u0002J\u0018\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\b\u001a\u00020\tH\u0002J\b\u0010\u001c\u001a\u00020\u0015H\u0002J\b\u0010\u001d\u001a\u00020\u0015H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Landroidx/camera/camera2/pipe/compat/AndroidExtensionSessionStateCallback;", "Landroid/hardware/camera2/CameraExtensionSession$StateCallback;", "device", "Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;", "stateCallback", "Landroidx/camera/camera2/pipe/compat/CameraExtensionSessionWrapper$StateCallback;", "lastStateCallback", "Landroidx/camera/camera2/pipe/compat/SessionStateCallback;", "cameraErrorListener", "Landroidx/camera/camera2/pipe/internal/CameraErrorListener;", "interopCaptureSessionListener", "Landroidx/camera/camera2/pipe/CameraInterop$CaptureSessionListener;", "callbackExecutor", "Ljava/util/concurrent/Executor;", "<init>", "(Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;Landroidx/camera/camera2/pipe/compat/CameraExtensionSessionWrapper$StateCallback;Landroidx/camera/camera2/pipe/compat/SessionStateCallback;Landroidx/camera/camera2/pipe/internal/CameraErrorListener;Landroidx/camera/camera2/pipe/CameraInterop$CaptureSessionListener;Ljava/util/concurrent/Executor;)V", "_lastStateCallback", "Lkotlinx/atomicfu/AtomicRef;", "extensionSession", "Landroidx/camera/camera2/pipe/compat/CameraExtensionSessionWrapper;", "onConfigured", _UrlKt.FRAGMENT_ENCODE_SET, "session", "Landroid/hardware/camera2/CameraExtensionSession;", "onConfigureFailed", "onClosed", "getWrapped", "wrapSession", "finalizeSession", "finalizeLastSession", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nExtensionSessionWrapper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ExtensionSessionWrapper.kt\nandroidx/camera/camera2/pipe/compat/AndroidExtensionSessionStateCallback\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,389:1\n1#2:390\n*E\n"})
/* loaded from: classes3.dex */
public final class AndroidExtensionSessionStateCallback extends CameraExtensionSession$StateCallback {
    private final AtomicRef<SessionStateCallback> _lastStateCallback;
    private final Executor callbackExecutor;
    private final CameraErrorListener cameraErrorListener;
    private final CameraDeviceWrapper device;
    private final AtomicRef<CameraExtensionSessionWrapper> extensionSession;
    private final CameraInterop.CaptureSessionListener interopCaptureSessionListener;
    private final CameraExtensionSessionWrapper.StateCallback stateCallback;

    public AndroidExtensionSessionStateCallback(CameraDeviceWrapper r1, CameraExtensionSessionWrapper.StateCallback r2, SessionStateCallback r3, CameraErrorListener r4, CameraInterop.CaptureSessionListener r5, Executor r6) {
        this.device = r1;
        this.stateCallback = r2;
        this.cameraErrorListener = r4;
        this.interopCaptureSessionListener = r5;
        this.callbackExecutor = r6;
        this._lastStateCallback = AtomicFU.atomic(r3);
        this.extensionSession = AtomicFU.atomic(null);
    }

    private final void finalizeLastSession() {
        SessionStateCallback r1 = this._lastStateCallback.getAndSet(null);
        if (r1 == null) goto L6;
        r1.onSessionFinalized();
        return;
    }

    private final void finalizeSession() {
        finalizeLastSession();
        this.stateCallback.onSessionFinalized();
    }

    private final CameraExtensionSessionWrapper getWrapped(CameraExtensionSession r2, CameraErrorListener r3) {
        CameraExtensionSessionWrapper r0 = this.extensionSession.getValue();
        if (r0 == null) goto L5;
        return r0;
    L5:
        CameraExtensionSessionWrapper r22 = wrapSession(r2, r3);
        if (this.extensionSession.compareAndSet(null, r22) == false) goto L9;
        return r22;
    L9:
        return this.extensionSession.getValue();
    }

    private final CameraExtensionSessionWrapper wrapSession(CameraExtensionSession r3, CameraErrorListener r4) {
        return new AndroidCameraExtensionSession(this.device, r3, r4, this.callbackExecutor);
    }

    public void onClosed(CameraExtensionSession r4) {
        CameraExtensionSessionWrapper r0 = getWrapped(r4, this.cameraErrorListener);
        this.stateCallback.onClosed(getWrapped(r4, this.cameraErrorListener));
        finalizeSession();
        CameraInterop.CaptureSessionListener r42 = this.interopCaptureSessionListener;
        if (r42 == null) goto L6;
        r42.mo60onClosedrphkYDA(this.device.mo412getCameraIdDz_R5H8(), r0.mo410getId159jkk4());
        return;
    }

    public void onConfigureFailed(CameraExtensionSession r2) {
        CameraExtensionSessionWrapper r22 = getWrapped(r2, this.cameraErrorListener);
        this.stateCallback.onConfigureFailed(r22);
        finalizeSession();
        CameraInterop.CaptureSessionListener r0 = this.interopCaptureSessionListener;
        if (r0 == null) goto L6;
        r0.mo61onConfigureFailedrphkYDA(this.device.mo412getCameraIdDz_R5H8(), r22.mo410getId159jkk4());
        return;
    }

    public void onConfigured(CameraExtensionSession r2) {
        CameraExtensionSessionWrapper r22 = getWrapped(r2, this.cameraErrorListener);
        this.stateCallback.onConfigured(r22);
        finalizeLastSession();
        CameraInterop.CaptureSessionListener r0 = this.interopCaptureSessionListener;
        if (r0 == null) goto L6;
        r0.mo62onConfiguredrphkYDA(this.device.mo412getCameraIdDz_R5H8(), r22.mo410getId159jkk4());
        return;
    }
}
