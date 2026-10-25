package com.google.android.recaptcha.internal;

import java.security.GeneralSecurityException;

/* loaded from: classes5.dex */
public abstract class zzadw {
    public static String zza(zzado r1) {
        zzadx.zza(r1);
        return r1.toString().concat("withECDSA");
    }

    public static String zzb(zzado r2) {
        int r0 = r2.ordinal();
        if (r0 != 0) goto L5;
        return "SHA-1";
    L5:
        if (r0 != 1) goto L7;
        return "SHA-224";
    L7:
        if (r0 != 2) goto L9;
        return "SHA-256";
    L9:
        if (r0 != 3) goto L11;
        return "SHA-384";
    L11:
        if (r0 != 4) goto L15;
        return "SHA-512";
    L15:
        throw new GeneralSecurityException("Unsupported hash ".concat(r2.toString()));
    }
}
