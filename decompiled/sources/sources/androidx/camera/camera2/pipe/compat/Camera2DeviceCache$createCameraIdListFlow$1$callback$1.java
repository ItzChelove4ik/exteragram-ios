package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CameraManager;
import androidx.camera.camera2.pipe.CameraId;
import java.util.List;
import kotlin.Metadata;
import kotlinx.coroutines.channels.ProducerScope;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0007"}, d2 = {"androidx/camera/camera2/pipe/compat/Camera2DeviceCache$createCameraIdListFlow$1$callback$1", "Landroid/hardware/camera2/CameraManager$AvailabilityCallback;", "onCameraAvailable", _UrlKt.FRAGMENT_ENCODE_SET, "cameraId", _UrlKt.FRAGMENT_ENCODE_SET, "onCameraUnavailable", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Camera2DeviceCache$createCameraIdListFlow$1$callback$1 extends CameraManager.AvailabilityCallback {
    final /* synthetic */ ProducerScope<List<CameraId>> $$this$callbackFlow;
    final /* synthetic */ Camera2DeviceCache this$0;

    /* JADX WARN: Multi-variable type inference failed */
    public Camera2DeviceCache$createCameraIdListFlow$1$callback$1(Camera2DeviceCache r1, ProducerScope<? super List<CameraId>> r2) {
        this.this$0 = r1;
        this.$$this$callbackFlow = r2;
    }

    @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
    public void onCameraAvailable(String r3) {
        Camera2DeviceCache.access$onCameraAvailabilityChanged(this.this$0, this.$$this$callbackFlow, r3, true);
    }

    @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
    public void onCameraUnavailable(String r3) {
        Camera2DeviceCache.access$onCameraAvailabilityChanged(this.this$0, this.$$this$callbackFlow, r3, false);
    }
}
