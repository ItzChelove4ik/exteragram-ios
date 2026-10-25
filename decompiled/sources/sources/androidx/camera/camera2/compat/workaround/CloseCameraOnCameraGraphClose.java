package androidx.camera.camera2.compat.workaround;

import androidx.camera.camera2.compat.quirk.CloseCameraDeviceOnCameraGraphCloseQuirk;
import androidx.camera.camera2.compat.quirk.DeviceQuirks;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Landroidx/camera/camera2/compat/workaround/CloseCameraOnCameraGraphClose;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "closeCameraDeviceOnCameraGraphCloseQuirk", "Landroidx/camera/camera2/compat/quirk/CloseCameraDeviceOnCameraGraphCloseQuirk;", "shouldCloseCameraDevice", _UrlKt.FRAGMENT_ENCODE_SET, "isExtensions", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CloseCameraOnCameraGraphClose {
    private final CloseCameraDeviceOnCameraGraphCloseQuirk closeCameraDeviceOnCameraGraphCloseQuirk;

    public CloseCameraOnCameraGraphClose() {
        this.closeCameraDeviceOnCameraGraphCloseQuirk = (CloseCameraDeviceOnCameraGraphCloseQuirk) DeviceQuirks.INSTANCE.get(CloseCameraDeviceOnCameraGraphCloseQuirk.class);
    }

    public final boolean shouldCloseCameraDevice(boolean r1) {
        CloseCameraDeviceOnCameraGraphCloseQuirk r0 = this.closeCameraDeviceOnCameraGraphCloseQuirk;
        if (r0 != null) goto L5;
        return false;
    L5:
        return r0.shouldCloseCameraDevice(r1);
    }
}
