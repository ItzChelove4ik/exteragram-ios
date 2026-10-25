package androidx.camera.camera2.compat;

import android.hardware.camera2.CaptureRequest;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\u0004\u001a\u00020\u00052\u0016\u0010\u0006\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\u0004\u0012\u00020\u00010\u00072\u0006\u0010\t\u001a\u00020\nH\u0007¨\u0006\u000b"}, d2 = {"Landroidx/camera/camera2/compat/Api35Compat;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "setFlashStrengthLevel", _UrlKt.FRAGMENT_ENCODE_SET, "parameters", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/CaptureRequest$Key;", "level", _UrlKt.FRAGMENT_ENCODE_SET, "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Api35Compat {
    public static final Api35Compat INSTANCE = null;

    static {
        INSTANCE = new Api35Compat();
    }

    private Api35Compat() {
    }

    @JvmStatic
    public static final void setFlashStrengthLevel(Map<CaptureRequest.Key<?>, Object> r1, int r2) {
        r1.put(Api35Compat$$ExternalSyntheticApiModelOutline0.m(), Integer.valueOf(r2));
    }
}
