package androidx.camera.camera2.compat.workaround;

import android.hardware.camera2.CaptureRequest;
import android.util.Rational;
import android.util.Size;
import androidx.camera.camera2.compat.quirk.DeviceQuirks;
import androidx.camera.camera2.compat.quirk.PreviewPixelHDRnetQuirk;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.core.impl.SessionConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\u0012\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0001H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"ASPECT_RATIO_16_9", "Landroid/util/Rational;", "setupHDRnet", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/impl/SessionConfig$Builder;", "resolution", "Landroid/util/Size;", "isAspectRatioMatch", _UrlKt.FRAGMENT_ENCODE_SET, "aspectRatio", "camera-camera2"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class PreviewPixelHDRnetKt {
    private static final Rational ASPECT_RATIO_16_9 = null;

    static {
        ASPECT_RATIO_16_9 = new Rational(16, 9);
    }

    private static final boolean isAspectRatioMatch(Size r2, Rational r3) {
        return Intrinsics.areEqual(r3, new Rational(r2.getWidth(), r2.getHeight()));
    }

    public static final void setupHDRnet(SessionConfig.Builder r2, Size r3) {
        if (((PreviewPixelHDRnetQuirk) DeviceQuirks.INSTANCE.get(PreviewPixelHDRnetQuirk.class)) != null) goto L6;
        return;
    L6:
        if (isAspectRatioMatch(r3, ASPECT_RATIO_16_9) == false) goto L8;
        return;
    L8:
        Camera2ImplConfig.Builder r32 = new Camera2ImplConfig.Builder();
        r32.setCaptureRequestOption(CaptureRequest.TONEMAP_MODE, 2);
        r2.addImplementationOptions(r32.build());
    }
}
