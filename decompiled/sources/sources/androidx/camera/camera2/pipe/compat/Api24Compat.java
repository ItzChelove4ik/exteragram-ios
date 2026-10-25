package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.params.InputConfiguration;
import android.hardware.camera2.params.OutputConfiguration;
import android.os.Handler;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J9\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00042\u000e\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u0010\n\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0007¢\u0006\u0004\b\u000e\u0010\u000fJA\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00102\u000e\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u0010\n\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0007¢\u0006\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroidx/camera/camera2/pipe/compat/Api24Compat;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroid/hardware/camera2/CameraDevice;", "cameraDevice", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/params/OutputConfiguration;", "outputConfig", "Landroid/hardware/camera2/CameraCaptureSession$StateCallback;", "stateCallback", "Landroid/os/Handler;", "handler", _UrlKt.FRAGMENT_ENCODE_SET, "createCaptureSessionByOutputConfigurations", "(Landroid/hardware/camera2/CameraDevice;Ljava/util/List;Landroid/hardware/camera2/CameraCaptureSession$StateCallback;Landroid/os/Handler;)V", "Landroid/hardware/camera2/params/InputConfiguration;", "inputConfig", "outputs", "createReprocessableCaptureSessionByConfigurations", "(Landroid/hardware/camera2/CameraDevice;Landroid/hardware/camera2/params/InputConfiguration;Ljava/util/List;Landroid/hardware/camera2/CameraCaptureSession$StateCallback;Landroid/os/Handler;)V", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Api24Compat {
    public static final Api24Compat INSTANCE = null;

    static {
        INSTANCE = new Api24Compat();
    }

    private Api24Compat() {
    }

    @JvmStatic
    public static final void createCaptureSessionByOutputConfigurations(CameraDevice r0, List<OutputConfiguration> r1, CameraCaptureSession.StateCallback r2, Handler r3) {
        r0.createCaptureSessionByOutputConfigurations(r1, r2, r3);
    }

    @JvmStatic
    public static final void createReprocessableCaptureSessionByConfigurations(CameraDevice r0, InputConfiguration r1, List<OutputConfiguration> r2, CameraCaptureSession.StateCallback r3, Handler r4) {
        r0.createReprocessableCaptureSessionByConfigurations(r1, r2, r3, r4);
    }
}
