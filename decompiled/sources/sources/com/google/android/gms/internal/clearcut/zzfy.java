package com.google.android.gms.internal.clearcut;

import java.nio.charset.Charset;
import java.util.Arrays;

/* loaded from: classes4.dex */
public abstract class zzfy {
    private static final Charset ISO_8859_1 = null;
    private static final Charset UTF_8 = null;
    public static final Object zzrr = null;

    static {
        UTF_8 = Charset.forName("UTF-8");
        ISO_8859_1 = Charset.forName("ISO-8859-1");
        zzrr = new Object();
    }

    public static boolean equals(int[] r1, int[] r2) {
        if (r1 != null) goto L4;
    L8:
        if (r2 != null) goto L10;
        return true;
    L10:
        if (r2.length == 0) goto L16;
        return false;
    L16:
        return true;
    L4:
        if (r1.length == 0) goto L8;
        return Arrays.equals(r1, r2);
    }

    public static boolean equals(Object[] r8, Object[] r9) {
        if (r8 != null) goto L5;
        int r1 = 0;
    L6:
        if (r9 != null) goto L8;
        int r2 = 0;
    L9:
        int r3 = 0;
        int r4 = 0;
    L10:
        if (r3 >= r1) goto L14;
        if (r8[r3] != null) goto L14;
        r3 = r3 + 1;
    L14:
        if (r4 >= r2) goto L19;
        if (r9[r4] != null) goto L19;
        r4 = r4 + 1;
    L19:
        if (r3 < r1) goto L21;
        boolean r6 = true;
    L22:
        if (r4 < r2) goto L24;
        boolean r7 = true;
    L25:
        if (r6 == false) goto L28;
        if (r7 == false) goto L28;
        return true;
    L28:
        if (r6 != r7) goto L29;
        if (r8[r3].equals(r9[r4]) == false) goto L32;
        r3 = r3 + 1;
        r4 = r4 + 1;
        goto L10
    L32:
        return false;
    L29:
        return false;
    L24:
        r7 = false;
        goto L25
    L21:
        r6 = false;
        goto L22
    L8:
        r2 = r9.length;
        goto L9
    L5:
        r1 = r8.length;
        goto L6
    }

    public static int hashCode(int[] r1) {
        if (r1 != null) goto L4;
        return 0;
    L4:
        if (r1.length != 0) goto L7;
        return 0;
    L7:
        return Arrays.hashCode(r1);
    }

    public static int hashCode(Object[] r4) {
        int r0 = 0;
        if (r4 != null) goto L5;
        int r1 = 0;
    L6:
        int r2 = 0;
    L7:
        if (r0 >= r1) goto L12;
        Object r3 = r4[r0];
        if (r3 == null) goto L11;
        r2 = (r2 * 31) + r3.hashCode();
    L11:
        r0 = r0 + 1;
        goto L7
    L12:
        return r2;
    L5:
        r1 = r4.length;
        goto L6
    }

    public static void zza(zzfu r0, zzfu r1) {
        r0.getClass();
    }
}
