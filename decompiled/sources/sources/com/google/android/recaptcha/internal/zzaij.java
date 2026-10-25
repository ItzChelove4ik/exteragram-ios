package com.google.android.recaptcha.internal;

import com.google.android.gms.cast.internal.zzar$$ExternalSyntheticBUOutline0;
import java.nio.charset.StandardCharsets;

/* loaded from: classes5.dex */
abstract class zzaij {
    public static final int zza(String r2, byte[] r3, int r4, int r5) {
        byte[] r22 = r2.getBytes(StandardCharsets.UTF_8);
        int r0 = r22.length;
        if ((r0 - r4) > r5) goto L7;
        System.arraycopy(r22, 0, r3, r4, r0);
        return r4 + r0;
    L7:
        throw new ArrayIndexOutOfBoundsException("Not enough space in output buffer to encode UTF-8 string");
    }

    public static int zzb(String r9) {
        int r0 = r9.length();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L7;
        if (r9.charAt(r2) >= 128) goto L7;
        r2 = r2 + 1;
    L7:
        int r3 = r0;
    L8:
        if (r2 >= r0) goto L30;
        char r4 = r9.charAt(r2);
        if (r4 >= 2048) goto L34;
        r3 = r3 + ((127 - r4) >>> 31);
        r2 = r2 + 1;
        goto L8
    L34:
        int r42 = zzail.$r8$clinit;     // Catch: zzaik -> L28
        int r43 = r9.length();     // Catch: zzaik -> L28
        int r6 = 0;
    L13:
        if (r2 >= r43) goto L27;
        char r7 = r9.charAt(r2);     // Catch: zzaik -> L28
        if (r7 >= 2048) goto L17;
        r6 = r6 + ((127 - r7) >>> 31);     // Catch: zzaik -> L28
    L26:
        r2 = r2 + 1;
        goto L13
    L17:
        r6 = r6 + 2;     // Catch: zzaik -> L28
        if (r7 < 55296) goto L26;
        if (r7 > 57343) goto L26;
        if (Character.codePointAt(r9, r2) < 65536) goto L25;
        r2 = r2 + 1;     // Catch: zzaik -> L28
        goto L26
    L25:
        throw new zzaik(r2, r43);     // Catch: zzaik -> L28
    L27:
        r3 = r3 + r6;
    L29:
        return r9.getBytes(StandardCharsets.UTF_8).length;
    L30:
        if (r3 < r0) goto L32;
        return r3;
    L32:
        long r22 = r3 + 4294967296L;
        zzar$$ExternalSyntheticBUOutline0.m(String.valueOf(r22).length() + 34, "UTF-8 length does not fit in int: ", r22);
        return 0;
    }
}
