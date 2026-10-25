package androidx.camera.camera2.compat;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureRequest;
import android.view.Surface;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.sun.jna.Callback;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007¨\u0006\u0010"}, d2 = {"Landroidx/camera/camera2/compat/Api24Compat;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "onCaptureBufferLost", _UrlKt.FRAGMENT_ENCODE_SET, Callback.METHOD_NAME, "Landroid/hardware/camera2/CameraCaptureSession$CaptureCallback;", "session", "Landroid/hardware/camera2/CameraCaptureSession;", PluginsConstants.REQUEST, "Landroid/hardware/camera2/CaptureRequest;", "surface", "Landroid/view/Surface;", "frameNumber", _UrlKt.FRAGMENT_ENCODE_SET, "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Api24Compat {
    public static final Api24Compat INSTANCE = null;

    static {
        INSTANCE = new Api24Compat();
    }

    private Api24Compat() {
    }

    @JvmStatic
    public static final void onCaptureBufferLost(CameraCaptureSession.CaptureCallback r0, CameraCaptureSession r1, CaptureRequest r2, Surface r3, long r4) {
        r0.onCaptureBufferLost(r1, r2, r3, r4);
    }
}
