package androidx.camera.camera2.pipe.compat;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0080\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0007\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\fHÖ\u0003¢\u0006\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Landroidx/camera/camera2/pipe/compat/CameraStateOpen;", "Landroidx/camera/camera2/pipe/compat/CameraState;", "Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;", "cameraDevice", "<init>", "(Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;", "getCameraDevice", "()Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CameraStateOpen extends CameraState {
    private final CameraDeviceWrapper cameraDevice;

    public CameraStateOpen(CameraDeviceWrapper r2) {
        super(null);
        this.cameraDevice = r2;
    }

    public boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if ((r4 instanceof CameraStateOpen) == true) goto L9;
        return false;
    L9:
        if (Intrinsics.areEqual(this.cameraDevice, ((CameraStateOpen) r4).cameraDevice) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public final CameraDeviceWrapper getCameraDevice() {
        return this.cameraDevice;
    }

    public int hashCode() {
        return this.cameraDevice.hashCode();
    }

    public String toString() {
        return "CameraStateOpen(cameraDevice=" + this.cameraDevice + ')';
    }
}
