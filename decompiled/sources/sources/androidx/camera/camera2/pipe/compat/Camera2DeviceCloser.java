package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CameraDevice;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001JD\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\rH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0003"}, d2 = {"Landroidx/camera/camera2/pipe/compat/Camera2DeviceCloser;", _UrlKt.FRAGMENT_ENCODE_SET, "closeCamera", _UrlKt.FRAGMENT_ENCODE_SET, "cameraDeviceWrapper", "Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;", "cameraDevice", "Landroid/hardware/camera2/CameraDevice;", "androidCameraState", "Landroidx/camera/camera2/pipe/compat/AndroidCameraState;", "audioRestrictionController", "Landroidx/camera/camera2/pipe/compat/AudioRestrictionController;", "shouldReopenCamera", _UrlKt.FRAGMENT_ENCODE_SET, "shouldCreateEmptyCaptureSession", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface Camera2DeviceCloser {
    static /* synthetic */ void closeCamera$default(Camera2DeviceCloser r1, CameraDeviceWrapper r2, CameraDevice r3, AndroidCameraState r4, AudioRestrictionController r5, boolean r6, boolean r7, int r8, Object r9) {
        if (r9 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: closeCamera");
        return;
    L4:
        if ((r8 & 1) == 0) goto L7;
        r2 = null;
    L7:
        if ((r8 & 2) == 0) goto L10;
        r3 = null;
    L10:
        if ((r8 & 16) == 0) goto L13;
        r6 = false;
    L13:
        if ((r8 & 32) == 0) goto L15;
        r7 = false;
    L15:
        r1.closeCamera(r2, r3, r4, r5, r6, r7);
    }

    void closeCamera(CameraDeviceWrapper r1, CameraDevice r2, AndroidCameraState r3, AudioRestrictionController r4, boolean r5, boolean r6);
}
