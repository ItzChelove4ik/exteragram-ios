package com.android.dex;

import okhttp3.internal.url._UrlKt;
import org.chromium.support_lib_boundary.WebViewProviderFactoryBoundaryInterface;

/* loaded from: classes.dex */
public abstract class DexFormat {
    public static String apiToMagic(int r2) {
        String r0 = "039";
        if (r2 >= 28) goto L15;
        if (r2 >= 28) goto L15;
        if (r2 < 26) goto L11;
        r0 = "038";
        goto L15
    L11:
        if (r2 < 24) goto L13;
        r0 = "037";
        goto L15
    L13:
        r0 = "035";
    L15:
        return "dex\n" + r0 + WebViewProviderFactoryBoundaryInterface.MULTI_COOKIE_VALUE_SEPARATOR;
    }

    public static boolean isSupportedDexMagic(byte[] r0) {
        if (magicToApi(r0) <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static int magicToApi(byte[] r4) {
        if (r4.length == 8) goto L6;
        return -1;
    L6:
        if (r4[0] == 100) goto L8;
    L34:
        return -1;
    L8:
        if (r4[1] != 101) goto L34;
        if (r4[2] != 120) goto L34;
        if (r4[3] != 10) goto L34;
        if (r4[7] != 0) goto L34;
        String r42 = _UrlKt.FRAGMENT_ENCODE_SET + ((char) r4[4]) + ((char) r4[5]) + ((char) r4[6]);
        if (r42.equals("035") == false) goto L21;
        return 13;
    L21:
        if (r42.equals("037") == false) goto L25;
        return 24;
    L25:
        if (r42.equals("038") == false) goto L29;
        return 26;
    L29:
        if (r42.equals("039") == false) goto L32;
        return 28;
    L32:
        if (r42.equals("039") == false) goto L34;
        return 28;
    }
}
