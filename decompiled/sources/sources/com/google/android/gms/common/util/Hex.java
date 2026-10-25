package com.google.android.gms.common.util;

import kotlin.UByte;

/* loaded from: classes4.dex */
public abstract class Hex {
    private static final char[] zza = null;
    private static final char[] zzb = null;

    static {
        zza = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        zzb = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    }

    public static String bytesToStringLowercase(byte[] r7) {
        int r0 = r7.length;
        char[] r02 = new char[r0 + r0];
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r1 >= r7.length) goto L7;
        byte r3 = r7[r1];
        int r4 = r3 & UByte.MAX_VALUE;
        char[] r6 = zzb;
        r02[r2] = r6[r4 >>> 4];
        r02[r2 + 1] = r6[r3 & 15];
        r2 = r2 + 2;
        r1 = r1 + 1;
        goto L4
    L7:
        return new String(r02);
    }

    public static String bytesToStringUppercase(byte[] r5, boolean r6) {
        int r0 = r5.length;
        StringBuilder r2 = new StringBuilder(r0 + r0);
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L11;
        if (r6 == false) goto L9;
        if (r1 != (r0 - 1)) goto L9;
        if ((r5[r1] & UByte.MAX_VALUE) == 0) goto L11;
    L9:
        char[] r3 = zza;
        r2.append(r3[(r5[r1] & 240) >>> 4]);
        r2.append(r3[r5[r1] & 15]);
        r1 = r1 + 1;
    L11:
        return r2.toString();
    }
}
