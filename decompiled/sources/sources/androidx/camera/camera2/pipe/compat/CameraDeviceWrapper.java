package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.InputConfiguration;
import android.view.Surface;
import androidx.camera.camera2.pipe.UnsafeWrapper;
import androidx.camera.camera2.pipe.compat.AudioRestrictionController;
import androidx.camera.camera2.pipe.compat.CameraCaptureSessionWrapper;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.List;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b`\u0018\u00002\u00020\u00012\u00020\u0002J\u0019\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0003H&¢\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\u000b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\n\u001a\u00020\tH&¢\u0006\u0004\b\u000b\u0010\fJ%\u0010\u0013\u001a\u00020\u00122\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0011\u001a\u00020\u0010H&¢\u0006\u0004\b\u0013\u0010\u0014J-\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00152\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0011\u001a\u00020\u0010H&¢\u0006\u0004\b\u0017\u0010\u0018J%\u0010\u0019\u001a\u00020\u00122\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0011\u001a\u00020\u0010H&¢\u0006\u0004\b\u0019\u0010\u0014J%\u0010\u001c\u001a\u00020\u00122\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\r2\u0006\u0010\u0011\u001a\u00020\u0010H'¢\u0006\u0004\b\u001c\u0010\u0014J-\u0010\u001f\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u001d2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u001a0\r2\u0006\u0010\u0011\u001a\u00020\u0010H'¢\u0006\u0004\b\u001f\u0010 J\u0017\u0010\u0013\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020!H'¢\u0006\u0004\b\u0013\u0010#J\u0017\u0010%\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020$H'¢\u0006\u0004\b%\u0010&J\u000f\u0010(\u001a\u00020'H&¢\u0006\u0004\b(\u0010)J\u000f\u0010*\u001a\u00020'H&¢\u0006\u0004\b*\u0010)R\u0014\u0010.\u001a\u00020+8&X¦\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006/À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;", "Landroidx/camera/camera2/pipe/UnsafeWrapper;", "Landroidx/camera/camera2/pipe/compat/AudioRestrictionController$Listener;", "Landroidx/camera/camera2/pipe/RequestTemplate;", "template", "Landroid/hardware/camera2/CaptureRequest$Builder;", "createCaptureRequest-2PPcXtw", "(I)Landroid/hardware/camera2/CaptureRequest$Builder;", "createCaptureRequest", "Landroid/hardware/camera2/TotalCaptureResult;", "inputResult", "createReprocessCaptureRequest", "(Landroid/hardware/camera2/TotalCaptureResult;)Landroid/hardware/camera2/CaptureRequest$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/view/Surface;", "outputs", "Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper$StateCallback;", "stateCallback", _UrlKt.FRAGMENT_ENCODE_SET, "createCaptureSession", "(Ljava/util/List;Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper$StateCallback;)Z", "Landroid/hardware/camera2/params/InputConfiguration;", PluginsConstants.Settings.TYPE_INPUT, "createReprocessableCaptureSession", "(Landroid/hardware/camera2/params/InputConfiguration;Ljava/util/List;Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper$StateCallback;)Z", "createConstrainedHighSpeedCaptureSession", "Landroidx/camera/camera2/pipe/compat/OutputConfigurationWrapper;", "outputConfigurations", "createCaptureSessionByOutputConfigurations", "Landroidx/camera/camera2/pipe/compat/InputConfigData;", "inputConfig", "createReprocessableCaptureSessionByConfigurations", "(Landroidx/camera/camera2/pipe/compat/InputConfigData;Ljava/util/List;Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper$StateCallback;)Z", "Landroidx/camera/camera2/pipe/compat/SessionConfigData;", "config", "(Landroidx/camera/camera2/pipe/compat/SessionConfigData;)Z", "Landroidx/camera/camera2/pipe/compat/ExtensionSessionConfigData;", "createExtensionSession", "(Landroidx/camera/camera2/pipe/compat/ExtensionSessionConfigData;)Z", _UrlKt.FRAGMENT_ENCODE_SET, "onDeviceClosing", "()V", "onDeviceClosed", "Landroidx/camera/camera2/pipe/CameraId;", "getCameraId-Dz_R5H8", "()Ljava/lang/String;", "cameraId", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface CameraDeviceWrapper extends UnsafeWrapper, AudioRestrictionController.Listener {
    /* renamed from: createCaptureRequest-2PPcXtw */
    CaptureRequest.Builder mo411createCaptureRequest2PPcXtw(int r1);

    boolean createCaptureSession(SessionConfigData r1);

    boolean createCaptureSession(List<? extends Surface> r1, CameraCaptureSessionWrapper.StateCallback r2);

    boolean createCaptureSessionByOutputConfigurations(List<? extends OutputConfigurationWrapper> r1, CameraCaptureSessionWrapper.StateCallback r2);

    boolean createConstrainedHighSpeedCaptureSession(List<? extends Surface> r1, CameraCaptureSessionWrapper.StateCallback r2);

    boolean createExtensionSession(ExtensionSessionConfigData r1);

    CaptureRequest.Builder createReprocessCaptureRequest(TotalCaptureResult r1);

    boolean createReprocessableCaptureSession(InputConfiguration r1, List<? extends Surface> r2, CameraCaptureSessionWrapper.StateCallback r3);

    boolean createReprocessableCaptureSessionByConfigurations(InputConfigData r1, List<? extends OutputConfigurationWrapper> r2, CameraCaptureSessionWrapper.StateCallback r3);

    /* renamed from: getCameraId-Dz_R5H8 */
    String mo412getCameraIdDz_R5H8();

    void onDeviceClosed();

    void onDeviceClosing();
}
