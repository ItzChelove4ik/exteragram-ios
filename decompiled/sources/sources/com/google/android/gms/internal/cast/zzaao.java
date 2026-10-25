package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.internal.zzar$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
abstract class zzaao {
    static {
        if (System.getenv("PROTOBUF_DISABLE_UNSAFE_UTF8_PROCESSOR_FOR_TESTING") == null) goto L6;
        return;
    L6:
        if (zzaak.zza() == true) goto L8;
        return;
    L8:
        if (zzaak.zzb() == false) goto L15;
        int r0 = zzxb.$r8$clinit;
        return;
    }

    public static int zza(String r9) {
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
        int r42 = r9.length();     // Catch: zzaan -> L28
        int r6 = 0;
    L13:
        if (r2 >= r42) goto L27;
        char r7 = r9.charAt(r2);     // Catch: zzaan -> L28
        if (r7 >= 2048) goto L17;
        r6 = r6 + ((127 - r7) >>> 31);     // Catch: zzaan -> L28
    L26:
        r2 = r2 + 1;
        goto L13
    L17:
        r6 = r6 + 2;     // Catch: zzaan -> L28
        if (r7 < 55296) goto L26;
        if (r7 > 57343) goto L26;
        if (Character.codePointAt(r9, r2) < 65536) goto L25;
        r2 = r2 + 1;     // Catch: zzaan -> L28
        goto L26
    L25:
        throw new zzaan(r2, r42);     // Catch: zzaan -> L28
    L27:
        r3 = r3 + r6;
    L29:
        return r9.getBytes(zzym.zza).length;
    L30:
        if (r3 < r0) goto L32;
        return r3;
    L32:
        long r22 = r3 + 4294967296L;
        zzar$$ExternalSyntheticBUOutline0.m(String.valueOf(r22).length() + 34, "UTF-8 length does not fit in int: ", r22);
        return 0;
    }

    public static int zzb(String r10, byte[] r11, int r12, int r13) {
        int r0 = r10.length();
        int r1 = 0;
    L3:
        int r2 = r12 + r13;
        if (r1 >= r0) goto L10;
        int r4 = r1 + r12;
        if (r4 >= r2) goto L10;
        char r5 = r10.charAt(r1);
        if (r5 >= 128) goto L10;
        r11[r4] = (byte) r5;
        r1 = r1 + 1;
    L10:
        if (r1 == r0) goto L12;
        int r42 = r12 + r1;
    L14:
        if (r1 >= r0) goto L51;
        char r52 = r10.charAt(r1);
        if (r52 >= 128) goto L20;
        if (r42 >= r2) goto L20;
        r11[r42] = (byte) r52;
        r42 = r42 + 1;
    L38:
        r1 = r1 + 1;
    L20:
        if (r52 >= 2048) goto L25;
        if (r42 > (r2 - 2)) goto L25;
        r11[r42] = (byte) ((r52 >>> 6) | 960);
        r11[r42 + 1] = (byte) ((r52 & '?') | 128);
        r42 = r42 + 2;
    L25:
        if (r52 < 55296) goto L28;
        if (r52 > 57343) goto L28;
    L31:
        if (r42 > (r2 - 4)) goto L41;
        r1 = r1 + 1;
        if (r1 == r10.length()) goto L40;
        char r6 = r10.charAt(r1);
        if (Character.isSurrogatePair(r52, r6) == false) goto L40;
        int r9 = r42 + 3;
        int r53 = Character.toCodePoint(r52, r6);
        r11[r42] = (byte) ((r53 >>> 18) | 240);
        r11[r42 + 1] = (byte) (((r53 >>> 12) & 63) | 128);
        r11[r42 + 2] = (byte) (((r53 >>> 6) & 63) | 128);
        r42 = r42 + 4;
        r11[r9] = (byte) ((r53 & 63) | 128);
    L40:
        return zzaal.zza(r10, r11, r12, r13);
    L41:
        if (r52 < 55296) goto L50;
        if (r52 > 57343) goto L50;
        int r14 = r1 + 1;
        if (r14 == r10.length()) goto L48;
        if (Character.isSurrogatePair(r52, r10.charAt(r14)) == true) goto L50;
    L48:
        return zzaal.zza(r10, r11, r12, r13);
    L50:
        throw new ArrayIndexOutOfBoundsException("Not enough space in output buffer to encode UTF-8 string");
    L28:
        if (r42 > (r2 - 3)) goto L31;
        r11[r42] = (byte) ((r52 >>> '\f') | 480);
        r11[r42 + 1] = (byte) (((r52 >>> 6) & 63) | 128);
        r11[r42 + 2] = (byte) ((r52 & '?') | 128);
        r42 = r42 + 3;
        goto L38
    L51:
        return r42;
    L12:
        return r12 + r0;
    }
}
