package androidx.camera.camera2.pipe.compat;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\b\u0080\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\t\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\f\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Landroidx/camera/camera2/pipe/compat/AwaitOpenCameraResult;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;", "cameraDeviceWrapper", "Landroidx/camera/camera2/pipe/compat/AndroidCameraState;", "androidCameraState", "<init>", "(Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;Landroidx/camera/camera2/pipe/compat/AndroidCameraState;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;", "getCameraDeviceWrapper", "()Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;", "Landroidx/camera/camera2/pipe/compat/AndroidCameraState;", "getAndroidCameraState", "()Landroidx/camera/camera2/pipe/compat/AndroidCameraState;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AwaitOpenCameraResult {
    private final AndroidCameraState androidCameraState;
    private final CameraDeviceWrapper cameraDeviceWrapper;

    public AwaitOpenCameraResult(CameraDeviceWrapper r1, AndroidCameraState r2) {
        this.cameraDeviceWrapper = r1;
        this.androidCameraState = r2;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof AwaitOpenCameraResult) == true) goto L8;
        return false;
    L8:
        AwaitOpenCameraResult r52 = (AwaitOpenCameraResult) r5;
        if (Intrinsics.areEqual(this.cameraDeviceWrapper, r52.cameraDeviceWrapper) == true) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(this.androidCameraState, r52.androidCameraState) == true) goto L14;
        return false;
    L14:
        return true;
    }

    public final AndroidCameraState getAndroidCameraState() {
        return this.androidCameraState;
    }

    public final CameraDeviceWrapper getCameraDeviceWrapper() {
        return this.cameraDeviceWrapper;
    }

    public int hashCode() {
        CameraDeviceWrapper r0 = this.cameraDeviceWrapper;
        int r1 = 0;
        if (r0 != null) goto L5;
        int r02 = 0;
    L6:
        int r03 = r02 * 31;
        AndroidCameraState r2 = this.androidCameraState;
        if (r2 == null) goto L11;
        r1 = r2.hashCode();
    L11:
        return r03 + r1;
    L5:
        r02 = r0.hashCode();
        goto L6
    }

    public String toString() {
        return "AwaitOpenCameraResult(cameraDeviceWrapper=" + this.cameraDeviceWrapper + ", androidCameraState=" + this.androidCameraState + ')';
    }
}
