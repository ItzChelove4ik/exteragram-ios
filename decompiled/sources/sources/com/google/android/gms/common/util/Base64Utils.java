package com.google.android.gms.common.util;

import android.util.Base64;

/* loaded from: classes.dex */
public abstract class Base64Utils {
    public static byte[] decodeUrlSafeNoPadding(String r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        return Base64.decode(r1, 11);
    }

    public static String encodeUrlSafeNoPadding(byte[] r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        return Base64.encodeToString(r1, 11);
    }
}
