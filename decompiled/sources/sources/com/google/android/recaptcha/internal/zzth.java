package com.google.android.recaptcha.internal;

import c.f$$ExternalSyntheticBUOutline1;
import java.math.BigInteger;
import java.util.Arrays;

/* loaded from: classes5.dex */
public abstract class zzth {
    public static byte[] zza(BigInteger r2) {
        if (r2.signum() != (-1)) goto L5;
        f$$ExternalSyntheticBUOutline1.m("n must not be negative");
        return null;
    L5:
        return r2.toByteArray();
    }

    public static byte[] zzb(BigInteger r5, int r6) {
        if (r5.signum() == (-1)) goto L21;
        byte[] r52 = r5.toByteArray();
        int r0 = r52.length;
        if (r0 != r6) goto L7;
        return r52;
    L7:
        int r1 = r6 + 1;
        if (r0 <= r1) goto L10;
        zzsj$$ExternalSyntheticBUOutline1.m("integer too large");
        return null;
    L10:
        if (r0 == r1) goto L12;
        byte[] r12 = new byte[r6];
        System.arraycopy(r52, 0, r12, r6 - r0, r0);
        return r12;
    L12:
        if (r52[0] == 0) goto L14;
        zzsj$$ExternalSyntheticBUOutline1.m("integer too large");
        return null;
    L14:
        return Arrays.copyOfRange(r52, 1, r0);
    L21:
        f$$ExternalSyntheticBUOutline1.m("integer must be nonnegative");
        return null;
    }
}
