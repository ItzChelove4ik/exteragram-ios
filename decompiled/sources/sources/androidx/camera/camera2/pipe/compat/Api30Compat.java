package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J#\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\b\u0010\tJ\u001f\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0007¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Landroidx/camera/camera2/pipe/compat/Api30Compat;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroid/hardware/camera2/CameraManager;", "cameraManager", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "getConcurrentCameraIds", "(Landroid/hardware/camera2/CameraManager;)Ljava/util/Set;", "Landroid/hardware/camera2/CameraDevice;", "cameraDevice", _UrlKt.FRAGMENT_ENCODE_SET, "mode", _UrlKt.FRAGMENT_ENCODE_SET, "setCameraAudioRestriction", "(Landroid/hardware/camera2/CameraDevice;I)V", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Api30Compat {
    public static final Api30Compat INSTANCE = null;

    static {
        INSTANCE = new Api30Compat();
    }

    private Api30Compat() {
    }

    @JvmStatic
    public static final Set<Set<String>> getConcurrentCameraIds(CameraManager r0) {
        return r0.getConcurrentCameraIds();
    }

    @JvmStatic
    public static final void setCameraAudioRestriction(CameraDevice r0, int r1) {
        r0.setCameraAudioRestriction(r1);
    }
}
