package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CaptureRequest;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.List;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0006\u0010\u0005\u001a\u00020\u0004H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/compat/CameraConstrainedHighSpeedCaptureSessionWrapper;", "Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper;", "createHighSpeedRequestList", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/CaptureRequest;", PluginsConstants.REQUEST, "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface CameraConstrainedHighSpeedCaptureSessionWrapper extends CameraCaptureSessionWrapper {
    List<CaptureRequest> createHighSpeedRequestList(CaptureRequest r1);
}
