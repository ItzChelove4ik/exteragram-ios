package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CameraManager;
import androidx.camera.camera2.pipe.CameraId;
import kotlin.Metadata;
import kotlinx.coroutines.channels.ChannelsKt;
import kotlinx.coroutines.channels.ProducerScope;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"androidx/camera/camera2/pipe/compat/Camera2CameraAvailabilityMonitor$availableCameraFlow$1$availabilityCallback$1", "Landroid/hardware/camera2/CameraManager$AvailabilityCallback;", "onCameraAvailable", _UrlKt.FRAGMENT_ENCODE_SET, "cameraIdString", _UrlKt.FRAGMENT_ENCODE_SET, "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Camera2CameraAvailabilityMonitor$availableCameraFlow$1$availabilityCallback$1 extends CameraManager.AvailabilityCallback {
    final /* synthetic */ ProducerScope<CameraId> $$this$callbackFlow;

    /* JADX WARN: Multi-variable type inference failed */
    public Camera2CameraAvailabilityMonitor$availableCameraFlow$1$availabilityCallback$1(ProducerScope<? super CameraId> r1) {
        this.$$this$callbackFlow = r1;
    }

    @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
    public void onCameraAvailable(String r1) {
        ChannelsKt.trySendBlocking(this.$$this$callbackFlow, CameraId.m227boximpl(CameraId.m228constructorimpl(r1)));
    }
}
