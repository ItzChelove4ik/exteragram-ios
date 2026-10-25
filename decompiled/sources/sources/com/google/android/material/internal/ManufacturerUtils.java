package com.google.android.material.internal;

import android.os.Build;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public class ManufacturerUtils {
    private static final String LGE = "lge";
    private static final String MEIZU = "meizu";
    private static final String SAMSUNG = "samsung";

    private ManufacturerUtils() {
    }

    private static String getManufacturer() {
        String r0 = Build.MANUFACTURER;
        if (r0 != null) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        return r0.toLowerCase(Locale.ENGLISH);
    }

    public static boolean isDateInputKeyboardMissingSeparatorCharacters() {
        if (isLGEDevice() == false) goto L5;
        return true;
    L5:
        if (isSamsungDevice() == true) goto L11;
        return false;
    L11:
        return true;
    }

    public static boolean isLGEDevice() {
        return getManufacturer().equals(LGE);
    }

    public static boolean isMeizuDevice() {
        return getManufacturer().equals(MEIZU);
    }

    public static boolean isSamsungDevice() {
        return getManufacturer().equals(SAMSUNG);
    }
}
