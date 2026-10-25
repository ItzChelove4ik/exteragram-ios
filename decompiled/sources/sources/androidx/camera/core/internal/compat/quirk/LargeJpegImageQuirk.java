package androidx.camera.core.internal.compat.quirk;

import android.os.Build;
import androidx.camera.core.impl.Quirk;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/* loaded from: classes3.dex */
public final class LargeJpegImageQuirk implements Quirk {
    private static final Set<String> SAMSUNG_DEVICE_MODELS = null;
    private static final Set<String> VIVO_DEVICE_MODELS = null;

    static {
        SAMSUNG_DEVICE_MODELS = new HashSet(Arrays.asList(new String[]{"SM-A520F", "SM-A520L", "SM-A520K", "SM-A520S", "SM-A520X", "SM-A520W", "SM-A525F", "SM-A525M", "SM-A705F", "SM-A705FN", "SM-A705GM", "SM-A705MN", "SM-A7050", "SM-A705W", "SM-A705YN", "SM-A705U", "SM-A715F", "SM-A715F/DS", "SM-A715F/DSM", "SM-A715F/DSN", "SM-A715W", "SM-A715X", "SM-A725F", "SM-A725M", "SM-M515F", "SM-M515F/DSN", "SM-G930T", "SM-G930V", "SM-S901B", "SM-S901B/DS", "SM-S906B"}));
        VIVO_DEVICE_MODELS = new HashSet(Arrays.asList(new String[]{"V2244A", "V2045", "V2046"}));
    }

    public LargeJpegImageQuirk() {
    }

    private static boolean isSamsungDevice() {
        return "Samsung".equalsIgnoreCase(Build.BRAND);
    }

    private static boolean isSamsungProblematicDevice() {
        if ("Samsung".equalsIgnoreCase(Build.BRAND) == true) goto L5;
        return false;
    L5:
        if (SAMSUNG_DEVICE_MODELS.contains(Build.MODEL.toUpperCase(Locale.US)) == false) goto L10;
        return true;
    L10:
        return false;
    }

    private static boolean isVivoProblematicDevice() {
        if ("Vivo".equalsIgnoreCase(Build.BRAND) == true) goto L5;
        return false;
    L5:
        if (VIVO_DEVICE_MODELS.contains(Build.MODEL.toUpperCase(Locale.US)) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean load() {
        if (isSamsungDevice() == false) goto L5;
        return true;
    L5:
        if (isVivoProblematicDevice() == true) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean shouldCheckInvalidJpegData(byte[] r2) {
        if (isSamsungProblematicDevice() == false) goto L5;
    L12:
        return true;
    L5:
        if (isVivoProblematicDevice() == true) goto L12;
        if (r2.length <= 10000000) goto L10;
        return true;
    L10:
        return false;
    }
}
