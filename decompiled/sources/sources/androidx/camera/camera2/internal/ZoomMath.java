package androidx.camera.camera2.internal;

import androidx.core.math.MathUtils;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\b\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\b\u0010\tJ%\u0010\r\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u0010\u0010\u0011¨\u0006\u0013"}, d2 = {"Landroidx/camera/camera2/internal/ZoomMath;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "num1", "num2", _UrlKt.FRAGMENT_ENCODE_SET, "areFloatsEqual", "(FF)Z", "zoomRatio", "minZoomRatio", "maxZoomRatio", "getLinearZoomFromZoomRatio", "(FFF)F", "num", "nearZero$camera_camera2", "(F)Z", "nearZero", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ZoomMath {
    public static final ZoomMath INSTANCE = null;

    static {
        INSTANCE = new ZoomMath();
    }

    private ZoomMath() {
    }

    private final boolean areFloatsEqual(float r1, float r2) {
        return nearZero$camera_camera2(r1 - r2);
    }

    public final float getLinearZoomFromZoomRatio(float r4, float r5, float r6) {
        if (areFloatsEqual(r5, r6) == false) goto L6;
        return 0.0f;
    L6:
        if (nearZero$camera_camera2(r4) == false) goto L9;
        return 0.0f;
    L9:
        if (areFloatsEqual(r4, r6) == false) goto L12;
        return 1.0f;
    L12:
        if (areFloatsEqual(r4, r5) == false) goto L14;
        return 0.0f;
    L14:
        float r3 = 1.0f / r4;
        float r52 = 1.0f / r5;
        return MathUtils.clamp((r52 - r3) / (r52 - (1.0f / r6)), 0.0f, 1.0f);
    }

    public final boolean nearZero$camera_camera2(float r5) {
        if (Math.abs(r5) >= (Math.ulp(Math.abs(r5)) * 2.0d)) goto L6;
        return true;
    L6:
        return false;
    }
}
