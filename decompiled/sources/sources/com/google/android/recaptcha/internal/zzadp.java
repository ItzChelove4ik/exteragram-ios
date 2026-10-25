package com.google.android.recaptcha.internal;

import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public abstract class zzadp {
    public static byte[] zza(String r6) {
        byte[] r1 = new byte[19];
        int r2 = 0;
    L3:
        if (r2 >= 19) goto L10;
        int r3 = r2 + r2;
        int r4 = Character.digit(r6.charAt(r3), 16);
        int r32 = Character.digit(r6.charAt(r3 + 1), 16);
        if (r4 == (-1)) goto L8;
        if (r32 == (-1)) goto L8;
        r1[r2] = (byte) ((r4 * 16) + r32);
        r2 = r2 + 1;
    L8:
        f$$ExternalSyntheticBUOutline1.m("input is not hexadecimal");
        return null;
    L10:
        return r1;
    }
}
