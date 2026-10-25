package androidx.camera.camera2.compat.workaround;

import android.annotation.SuppressLint;
import androidx.camera.camera2.compat.quirk.DeviceQuirks;
import androidx.camera.camera2.compat.quirk.ImageCapturePixelHDRPlusQuirk;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.core.impl.ImageCaptureConfig;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"toggleHDRPlus", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/impl/Camera2ImplConfig$Builder;", "imageCaptureConfig", "Landroidx/camera/core/impl/ImageCaptureConfig;", "camera-camera2"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class ImageCapturePixelHDRPlusKt {
    @SuppressLint({"NewApi"})
    public static final void toggleHDRPlus(Camera2ImplConfig.Builder r2, ImageCaptureConfig r3) {
        if (((ImageCapturePixelHDRPlusQuirk) DeviceQuirks.INSTANCE.get(ImageCapturePixelHDRPlusQuirk.class)) != null) goto L6;
        return;
    L6:
        if (r3.hasCaptureMode() == false) goto L18;
        int r32 = r3.getCaptureMode();
        if (r32 != 0) goto L11;
        r2.setCaptureRequestOption(ImageCapturePixelHDRPlusKt$$ExternalSyntheticApiModelOutline0.m(), Boolean.TRUE);
        return;
    L11:
        if (r32 == 1) goto L13;
        return;
    L13:
        r2.setCaptureRequestOption(ImageCapturePixelHDRPlusKt$$ExternalSyntheticApiModelOutline0.m(), Boolean.FALSE);
        return;
    }
}
