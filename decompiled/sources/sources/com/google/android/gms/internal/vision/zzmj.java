package com.google.android.gms.internal.vision;

import com.google.protobuf.Utf8$Processor$$ExternalSyntheticBUOutline0;
import okio.AsyncTimeout$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
final class zzmj extends zzme {
    public zzmj() {
    }

    private static int zza(byte[] r2, int r3, long r4, int r6) {
        if (r6 == 0) goto L14;
        if (r6 == 1) goto L12;
        if (r6 == 2) goto L8;
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return 0;
    L8:
        return zzmd.zza(r3, zzma.zza(r2, r4), zzma.zza(r2, r4 + 1));
    L12:
        return zzmd.zza(r3, zzma.zza(r2, r4));
    L14:
        return zzmd.zza(r3);
    }

    @Override // com.google.android.gms.internal.vision.zzme
    public final int zza(int r18, byte[] r19, int r20, int r21) {
        if (((r20 | r21) | (r19.length - r21)) < 0) goto L66;
        long r5 = r20;
        int r1 = (int) (r21 - r5);
        long r7 = 1;
        if (r1 >= 16) goto L7;
        int r2 = 0;
    L14:
        int r12 = r1 - r2;
        long r52 = r5 + r2;
    L15:
        byte r22 = 0;
    L16:
        if (r12 <= 0) goto L21;
        long r23 = r52 + r7;
        byte r53 = zzma.zza(r19, r52);
        if (r53 < 0) goto L20;
        r12 = r12 - 1;
        r22 = r53;
        r52 = r23;
        goto L16
    L20:
        r22 = r53;
        r52 = r23;
    L21:
        if (r12 == 0) goto L22;
        int r3 = r12 - 1;
        if (r22 >= (-32)) goto L35;
        if (r3 == 0) goto L26;
        r12 = r12 - 2;
        if (r22 < (-62)) goto L33;
        long r24 = r52 + r7;
        if (zzma.zza(r19, r52) > (-65)) goto L33;
        r52 = r24;
        long r17 = r7;
    L51:
        r7 = r17;
    L33:
        return -1;
    L26:
        return r22;
    L35:
        if (r22 < (-16)) goto L37;
        r17 = r7;
        if (r3 < 3) goto L56;
        r12 = r12 - 4;
        long r72 = r52 + r17;
        byte r32 = zzma.zza(r19, r52);
        if (r32 > (-65)) goto L65;
        if ((((r22 << 28) + (r32 + 112)) >> 30) != 0) goto L65;
        long r13 = 2 + r52;
        if (zzma.zza(r19, r72) > (-65)) goto L65;
        r52 = r52 + 3;
        if (zzma.zza(r19, r13) <= (-65)) goto L51;
    L65:
        return -1;
    L56:
        return zza(r19, r22, r52, r3);
    L37:
        if (r3 < 2) goto L39;
        r12 = r12 - 3;
        r17 = r7;
        long r73 = r52 + r17;
        byte r33 = zzma.zza(r19, r52);
        if (r33 > (-65)) goto L52;
        if (r22 != (-32)) goto L46;
        if (r33 < (-96)) goto L52;
    L46:
        if (r22 != (-19)) goto L48;
        if (r33 >= (-96)) goto L52;
    L48:
        r52 = r52 + 2;
        if (zzma.zza(r19, r73) <= (-65)) goto L51;
    L52:
        return -1;
    L39:
        return zza(r19, r22, r52, r3);
    L22:
        return 0;
    L7:
        r2 = 0;
        long r9 = r5;
    L8:
        if (r2 >= r1) goto L13;
        long r11 = r9 + 1;
        if (zzma.zza(r19, r9) < 0) goto L14;
        r2 = r2 + 1;
        r9 = r11;
        goto L8
    L13:
        r2 = r1;
        goto L14
    L66:
        Utf8$Processor$$ExternalSyntheticBUOutline0.m("Array length=%d, index=%d, limit=%d", new Object[]{Integer.valueOf(r19.length), Integer.valueOf(r20), Integer.valueOf(r21)});
        return 0;
    }

    @Override // com.google.android.gms.internal.vision.zzme
    public final int zza(CharSequence r23, byte[] r24, int r25, int r26) {
        long r4 = r25;
        long r6 = r26 + r4;
        int r8 = r23.length();
        if (r8 <= r26) goto L5;
    L56:
        zzmj$$ExternalSyntheticBUOutline1.m(r23.charAt(r8 - 1), r25 + r26);
        return 0;
    L5:
        if ((r24.length - r26) < r25) goto L56;
        int r2 = 0;
    L7:
        long r10 = 1;
        if (r2 >= r8) goto L12;
        char r12 = r23.charAt(r2);
        if (r12 >= 128) goto L12;
        zzma.zza(r24, r4, (byte) r12);
        r2 = r2 + 1;
        r4 = 1 + r4;
    L12:
        if (r2 == r8) goto L14;
    L15:
        if (r2 >= r8) goto L55;
        char r122 = r23.charAt(r2);
        if (r122 >= 128) goto L22;
        if (r4 >= r6) goto L22;
        zzma.zza(r24, r4, (byte) r122);
        long r252 = r10;
        r4 = r4 + r10;
    L39:
        r2 = r2 + 1;
        r10 = r252;
    L22:
        if (r122 < 2048) goto L24;
    L26:
        r252 = r10;
        if (r122 < 55296) goto L30;
        if (57343 < r122) goto L30;
    L33:
        if (r4 > (r6 - 4)) goto L43;
        int r9 = r2 + 1;
        if (r9 == r8) goto L42;
        char r22 = r23.charAt(r9);
        if (Character.isSurrogatePair(r122, r22) == false) goto L40;
        int r27 = Character.toCodePoint(r122, r22);
        zzma.zza(r24, r4, (byte) ((r27 >>> 18) | 240));
        zzma.zza(r24, r4 + r252, (byte) (((r27 >>> 12) & 63) | 128));
        long r102 = r4 + 3;
        zzma.zza(r24, 2 + r4, (byte) (((r27 >>> 6) & 63) | 128));
        r4 = r4 + 4;
        zzma.zza(r24, r102, (byte) ((r27 & 63) | 128));
        r2 = r9;
        goto L39
    L40:
        r2 = r9;
    L42:
        throw new zzmg(r2 - 1, r8);
    L43:
        if (55296 > r122) goto L52;
        if (r122 > 57343) goto L52;
        int r1 = r2 + 1;
        if (r1 == r8) goto L51;
        if (Character.isSurrogatePair(r122, r23.charAt(r1)) == true) goto L52;
    L51:
        throw new zzmg(r2, r8);
    L52:
        zzmj$$ExternalSyntheticBUOutline0.m(r122, r4);
        return 0;
    L30:
        if (r4 > (r6 - 3)) goto L33;
        zzma.zza(r24, r4, (byte) ((r122 >>> '\f') | 480));
        long r14 = 2 + r4;
        zzma.zza(r24, r4 + r252, (byte) (((r122 >>> 6) & 63) | 128));
        r4 = r4 + 3;
        zzma.zza(r24, r14, (byte) ((r122 & '?') | 128));
        goto L39
    L24:
        if (r4 > (r6 - 2)) goto L26;
        r252 = r10;
        long r92 = r4 + r252;
        zzma.zza(r24, r4, (byte) ((r122 >>> 6) | 960));
        r4 = r4 + 2;
        zzma.zza(r24, r92, (byte) ((r122 & '?') | 128));
        goto L39
    L55:
        return (int) r4;
    L14:
        return (int) r4;
    }

    @Override // com.google.android.gms.internal.vision.zzme
    public final String zzb(byte[] r10, int r11, int r12) {
        if (((r11 | r12) | ((r10.length - r11) - r12)) < 0) goto L39;
        int r9 = r11 + r12;
        char[] r4 = new char[r12];
        int r0 = 0;
    L5:
        if (r11 >= r9) goto L9;
        byte r1 = zzma.zza(r10, r11);
        if (zzmf.zza(r1) == false) goto L9;
        r11 = r11 + 1;
        zzmf.zza(r1, r4, r0);
        r0 = r0 + 1;
    L9:
        int r5 = r0;
    L10:
        if (r11 >= r9) goto L38;
        int r02 = r11 + 1;
        byte r13 = zzma.zza(r10, r11);
        if (zzmf.zza(r13) == true) goto L13;
        if (zzmf.zzb(r13) == true) goto L21;
        if (zzmf.zzc(r13) == true) goto L28;
        if (r02 >= (r9 - 2)) goto L36;
        byte r03 = zzma.zza(r10, r02);
        int r3 = r11 + 3;
        byte r2 = zzma.zza(r10, r11 + 2);
        r11 = r11 + 4;
        zzmf.zza(r13, r03, r2, zzma.zza(r10, r3), r4, r5);
        r5 = r5 + 2;
        goto L10
    L36:
        throw zzjk.zzh();
    L28:
        if (r02 >= (r9 - 1)) goto L31;
        int r22 = r11 + 2;
        r11 = r11 + 3;
        zzmf.zza(r13, zzma.zza(r10, r02), zzma.zza(r10, r22), r4, r5);
        r5 = r5 + 1;
        goto L10
    L31:
        throw zzjk.zzh();
    L21:
        if (r02 >= r9) goto L24;
        r11 = r11 + 2;
        zzmf.zza(r13, zzma.zza(r10, r02), r4, r5);
        r5 = r5 + 1;
        goto L10
    L24:
        throw zzjk.zzh();
    L13:
        int r112 = r5 + 1;
        zzmf.zza(r13, r4, r5);
    L14:
        if (r02 >= r9) goto L18;
        byte r14 = zzma.zza(r10, r02);
        if (zzmf.zza(r14) == false) goto L18;
        r02 = r02 + 1;
        zzmf.zza(r14, r4, r112);
        r112 = r112 + 1;
    L18:
        r5 = r112;
        r11 = r02;
        goto L10
    L38:
        return new String(r4, 0, r5);
    L39:
        Utf8$Processor$$ExternalSyntheticBUOutline0.m("buffer length=%d, index=%d, size=%d", new Object[]{Integer.valueOf(r10.length), Integer.valueOf(r11), Integer.valueOf(r12)});
        return null;
    }
}
