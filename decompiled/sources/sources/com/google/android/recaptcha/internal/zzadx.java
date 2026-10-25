package com.google.android.recaptcha.internal;

import com.google.android.gms.cast.MediaStatus;
import java.math.BigInteger;
import java.security.GeneralSecurityException;

/* loaded from: classes5.dex */
public abstract class zzadx {
    static {
        String.format("^projects/%s/locations/%s/keyRings/%s/cryptoKeys/%s$", new Object[]{"([0-9a-zA-Z\\-\\.\\_~])+", "([0-9a-zA-Z\\-\\.\\_~])+", "([0-9a-zA-Z\\-\\.\\_~])+", "([0-9a-zA-Z\\-\\.\\_~])+"});
        String.format("^projects/%s/locations/%s/keyRings/%s/cryptoKeys/%s/cryptoKeyVersions/%s$", new Object[]{"([0-9a-zA-Z\\-\\.\\_~])+", "([0-9a-zA-Z\\-\\.\\_~])+", "([0-9a-zA-Z\\-\\.\\_~])+", "([0-9a-zA-Z\\-\\.\\_~])+", "([0-9a-zA-Z\\-\\.\\_~])+"});
    }

    public static void zza(zzado r2) {
        int r0 = r2.ordinal();
        if (r0 != 2) goto L5;
        return;
    L5:
        if (r0 != 3) goto L7;
        return;
    L7:
        if (r0 != 4) goto L10;
        return;
    L10:
        throw new GeneralSecurityException("Unsupported hash: ".concat(String.valueOf(r2.name())));
    }

    public static void zzb(int r2) {
        if (r2 < 2048) goto L14;
        if (zztf.zza() == false) goto L12;
        if (r2 != 2048) goto L8;
        return;
    L8:
        if (r2 != 3072) goto L11;
        return;
    L11:
        throw new GeneralSecurityException(String.format("Modulus size is %d; only modulus size of 2048- or 3072-bit is supported in FIPS mode.", new Object[]{Integer.valueOf(r2)}));
    L12:
        return;
    L14:
        throw new GeneralSecurityException(String.format("Modulus size is %d; only modulus size >= 2048-bit is supported", new Object[]{Integer.valueOf(r2)}));
    }

    public static void zzc(BigInteger r2) {
        if (r2.testBit(0) == true) goto L5;
        zzsj$$ExternalSyntheticBUOutline1.m("Public exponent must be odd.");
        return;
    L5:
        if (r2.compareTo(BigInteger.valueOf(MediaStatus.COMMAND_FOLLOW)) <= 0) goto L7;
        return;
    L7:
        zzsj$$ExternalSyntheticBUOutline1.m("Public exponent must be greater than 65536.");
    }
}
