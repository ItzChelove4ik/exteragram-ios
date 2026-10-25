package androidx.camera.core.internal.compat.workaround;

import androidx.camera.core.ImageProxy;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.internal.compat.quirk.DeviceQuirks;
import androidx.camera.core.internal.compat.quirk.ImageCaptureRotationOptionQuirk;
import androidx.camera.core.internal.utils.ImageUtil;

/* loaded from: classes3.dex */
public class ExifRotationAvailability {
    public ExifRotationAvailability() {
    }

    public boolean isRotationOptionSupported() {
        ImageCaptureRotationOptionQuirk r1 = (ImageCaptureRotationOptionQuirk) DeviceQuirks.get(ImageCaptureRotationOptionQuirk.class);
        if (r1 != null) goto L5;
        return true;
    L5:
        if (r1.isSupported(CaptureConfig.OPTION_ROTATION) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean shouldUseExifOrientation(ImageProxy r1) {
        if (isRotationOptionSupported() == true) goto L5;
        return false;
    L5:
        if (ImageUtil.isJpegFormats(r1.getFormat()) == false) goto L10;
        return true;
    L10:
        return false;
    }
}
