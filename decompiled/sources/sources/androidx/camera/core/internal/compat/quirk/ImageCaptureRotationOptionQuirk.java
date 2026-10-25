package androidx.camera.core.internal.compat.quirk;

import android.os.Build;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.Quirk;

/* loaded from: classes3.dex */
public final class ImageCaptureRotationOptionQuirk implements Quirk {
    public ImageCaptureRotationOptionQuirk() {
    }

    private static boolean isHonor9X() {
        if ("HONOR".equalsIgnoreCase(Build.BRAND) == true) goto L5;
        return false;
    L5:
        if ("STK-LX1".equalsIgnoreCase(Build.MODEL) == false) goto L10;
        return true;
    L10:
        return false;
    }

    private static boolean isHuaweiMate20Lite() {
        if ("HUAWEI".equalsIgnoreCase(Build.BRAND) == true) goto L5;
        return false;
    L5:
        if ("SNE-LX1".equalsIgnoreCase(Build.MODEL) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean load() {
        if (isHuaweiMate20Lite() == false) goto L5;
        return true;
    L5:
        if (isHonor9X() == true) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean isSupported(Config.Option<?> r1) {
        if (r1 == CaptureConfig.OPTION_ROTATION) goto L6;
        return true;
    L6:
        return false;
    }
}
