package com.google.android.gms.internal.fido;

/* loaded from: classes4.dex */
public abstract class zzgj {
    public static byte[] zza(byte[]... r7) {
        int r1 = 0;
        int r2 = 0;
    L3:
        int r3 = r7.length;
        if (r1 >= r3) goto L6;
        r2 = r2 + r7[r1].length;
        r1 = r1 + 1;
        goto L3
    L6:
        byte[] r12 = new byte[r2];
        int r22 = 0;
        int r4 = 0;
    L7:
        if (r22 >= r3) goto L9;
        byte[] r5 = r7[r22];
        int r6 = r5.length;
        System.arraycopy(r5, 0, r12, r4, r6);
        r4 = r4 + r6;
        r22 = r22 + 1;
        goto L7
    L9:
        return r12;
    }
}
