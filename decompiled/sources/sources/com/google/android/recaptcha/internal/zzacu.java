package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public abstract class zzacu {
    public static byte[] zza(byte[]... r7) {
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r1 >= 2) goto L10;
        int r3 = r7[r1].length;
        if (r2 > (Integer.MAX_VALUE - r3)) goto L8;
        r2 = r2 + r3;
        r1 = r1 + 1;
        goto L4
    L8:
        zzsj$$ExternalSyntheticBUOutline1.m("exceeded size limit");
        return null;
    L10:
        byte[] r12 = new byte[r2];
        int r22 = 0;
        int r4 = 0;
    L11:
        if (r22 >= 2) goto L13;
        byte[] r5 = r7[r22];
        int r6 = r5.length;
        System.arraycopy(r5, 0, r12, r4, r6);
        r4 = r4 + r6;
        r22 = r22 + 1;
        goto L11
    L13:
        return r12;
    }
}
