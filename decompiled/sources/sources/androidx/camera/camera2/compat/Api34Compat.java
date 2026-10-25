package androidx.camera.camera2.compat;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureRequest;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.sun.jna.Callback;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0007J \u0010\u000f\u001a\u00020\u00052\u0016\u0010\u0010\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0012\u0012\u0004\u0012\u00020\u00010\u0011H\u0007¨\u0006\u0013"}, d2 = {"Landroidx/camera/camera2/compat/Api34Compat;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "onReadoutStarted", _UrlKt.FRAGMENT_ENCODE_SET, Callback.METHOD_NAME, "Landroid/hardware/camera2/CameraCaptureSession$CaptureCallback;", "session", "Landroid/hardware/camera2/CameraCaptureSession;", PluginsConstants.REQUEST, "Landroid/hardware/camera2/CaptureRequest;", "timestamp", _UrlKt.FRAGMENT_ENCODE_SET, "frameNumber", "setSettingsOverrideZoom", "parameters", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/CaptureRequest$Key;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Api34Compat {
    public static final Api34Compat INSTANCE = null;

    static {
        INSTANCE = new Api34Compat();
    }

    private Api34Compat() {
    }

    @JvmStatic
    public static final void onReadoutStarted(CameraCaptureSession.CaptureCallback r0, CameraCaptureSession r1, CaptureRequest r2, long r3, long r5) {
        r0.onReadoutStarted(r1, r2, r3, r5);
    }

    @JvmStatic
    public static final void setSettingsOverrideZoom(Map<CaptureRequest.Key<?>, Object> r2) {
        r2.put(AndroidRZoomCompat$$ExternalSyntheticApiModelOutline1.m(), 1);
    }
}
