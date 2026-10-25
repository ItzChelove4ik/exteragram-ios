package com.google.android.gms.internal.vision;

import com.google.protobuf.Utf8$Processor$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
final class zzmh extends zzme {
    public zzmh() {
    }

    @Override // com.google.android.gms.internal.vision.zzme
    public final int zza(int r7, byte[] r8, int r9, int r10) {
    L2:
        if (r9 >= r10) goto L7;
        if (r8[r9] < 0) goto L7;
        r9 = r9 + 1;
    L7:
        if (r9 < r10) goto L9;
        return 0;
    L9:
        if (r9 >= r10) goto L10;
        int r72 = r9 + 1;
        byte r0 = r8[r9];
        if (r0 < 0) goto L14;
        r9 = r72;
        goto L9
    L14:
        if (r0 < (-32)) goto L15;
        if (r0 < (-16)) goto L25;
        if (r72 >= (r10 - 2)) goto L42;
        int r1 = r9 + 2;
        byte r73 = r8[r72];
        if (r73 > (-65)) goto L51;
        if ((((r0 << 28) + (r73 + 112)) >> 30) != 0) goto L51;
        int r74 = r9 + 3;
        if (r8[r1] > (-65)) goto L51;
        r9 = r9 + 4;
        if (r8[r74] <= (-65)) goto L9;
    L51:
        return -1;
    L42:
        return zzmd.zzc(r8, r72, r10);
    L25:
        if (r72 >= (r10 - 1)) goto L27;
        int r4 = r9 + 2;
        byte r75 = r8[r72];
        if (r75 > (-65)) goto L38;
        if (r0 != (-32)) goto L34;
        if (r75 < (-96)) goto L38;
    L34:
        if (r0 != (-19)) goto L36;
        if (r75 >= (-96)) goto L38;
    L36:
        r9 = r9 + 3;
        if (r8[r4] <= (-65)) goto L9;
    L38:
        return -1;
    L27:
        return zzmd.zzc(r8, r72, r10);
    L15:
        if (r72 >= r10) goto L16;
        if (r0 < (-62)) goto L21;
        r9 = r9 + 2;
        if (r8[r72] <= (-65)) goto L9;
    L21:
        return -1;
    L16:
        return r0;
    L10:
        return 0;
    }

    @Override // com.google.android.gms.internal.vision.zzme
    public final int zza(CharSequence r7, byte[] r8, int r9, int r10) {
        int r6 = r7.length();
        int r102 = r10 + r9;
        int r0 = 0;
    L4:
        if (r0 >= r6) goto L10;
        int r2 = r0 + r9;
        if (r2 >= r102) goto L10;
        char r3 = r7.charAt(r0);
        if (r3 >= 128) goto L10;
        r8[r2] = (byte) r3;
        r0 = r0 + 1;
    L10:
        if (r0 == r6) goto L12;
        int r92 = r9 + r0;
    L14:
        if (r0 >= r6) goto L51;
        char r22 = r7.charAt(r0);
        if (r22 >= 128) goto L20;
        if (r92 >= r102) goto L20;
        r8[r92] = (byte) r22;
        r92 = r92 + 1;
    L37:
        r0 = r0 + 1;
    L20:
        if (r22 >= 2048) goto L25;
        if (r92 > (r102 - 2)) goto L25;
        int r32 = r92 + 1;
        r8[r92] = (byte) ((r22 >>> 6) | 960);
        r92 = r92 + 2;
        r8[r32] = (byte) ((r22 & '?') | 128);
    L25:
        if (r22 < 55296) goto L28;
        if (57343 < r22) goto L28;
    L31:
        if (r92 > (r102 - 4)) goto L41;
        int r33 = r0 + 1;
        if (r33 == r7.length()) goto L40;
        char r02 = r7.charAt(r33);
        if (Character.isSurrogatePair(r22, r02) == false) goto L38;
        int r03 = Character.toCodePoint(r22, r02);
        r8[r92] = (byte) ((r03 >>> 18) | 240);
        r8[r92 + 1] = (byte) (((r03 >>> 12) & 63) | 128);
        int r23 = r92 + 3;
        r8[r92 + 2] = (byte) (((r03 >>> 6) & 63) | 128);
        r92 = r92 + 4;
        r8[r23] = (byte) ((r03 & 63) | 128);
        r0 = r33;
        goto L37
    L38:
        r0 = r33;
    L40:
        throw new zzmg(r0 - 1, r6);
    L41:
        if (55296 > r22) goto L49;
        if (r22 > 57343) goto L49;
        int r82 = r0 + 1;
        if (r82 == r7.length()) goto L48;
        if (Character.isSurrogatePair(r22, r7.charAt(r82)) == true) goto L49;
    L48:
        throw new zzmg(r0, r6);
    L49:
        StringBuilder r72 = new StringBuilder(37);
        r72.append("Failed writing ");
        r72.append(r22);
        r72.append(" at index ");
        r72.append(r92);
        throw new ArrayIndexOutOfBoundsException(r72.toString());
    L28:
        if (r92 > (r102 - 3)) goto L31;
        r8[r92] = (byte) ((r22 >>> '\f') | 480);
        int r4 = r92 + 2;
        r8[r92 + 1] = (byte) (((r22 >>> 6) & 63) | 128);
        r92 = r92 + 3;
        r8[r4] = (byte) ((r22 & '?') | 128);
        goto L37
    L51:
        return r92;
    L12:
        return r9 + r6;
    }

    @Override // com.google.android.gms.internal.vision.zzme
    public final String zzb(byte[] r7, int r8, int r9) {
        if (((r8 | r9) | ((r7.length - r8) - r9)) < 0) goto L39;
        int r6 = r8 + r9;
        char[] r4 = new char[r9];
        int r0 = 0;
    L5:
        if (r8 >= r6) goto L9;
        byte r1 = r7[r8];
        if (zzmf.zza(r1) == false) goto L9;
        r8 = r8 + 1;
        zzmf.zza(r1, r4, r0);
        r0 = r0 + 1;
    L9:
        int r5 = r0;
    L10:
        if (r8 >= r6) goto L38;
        int r02 = r8 + 1;
        byte r03 = r7[r8];
        if (zzmf.zza(r03) == true) goto L13;
        if (zzmf.zzb(r03) == true) goto L21;
        if (zzmf.zzc(r03) == true) goto L28;
        if (r02 >= (r6 - 2)) goto L36;
        byte r12 = r7[r02];
        int r3 = r8 + 3;
        byte r2 = r7[r8 + 2];
        r8 = r8 + 4;
        zzmf.zza(r03, r12, r2, r7[r3], r4, r5);
        r5 = r5 + 2;
        goto L10
    L36:
        throw zzjk.zzh();
    L28:
        if (r02 >= (r6 - 1)) goto L31;
        int r22 = r8 + 2;
        r8 = r8 + 3;
        zzmf.zza(r03, r7[r02], r7[r22], r4, r5);
        r5 = r5 + 1;
        goto L10
    L31:
        throw zzjk.zzh();
    L21:
        if (r02 >= r6) goto L24;
        r8 = r8 + 2;
        zzmf.zza(r03, r7[r02], r4, r5);
        r5 = r5 + 1;
        goto L10
    L24:
        throw zzjk.zzh();
    L13:
        int r82 = r5 + 1;
        zzmf.zza(r03, r4, r5);
        int r04 = r02;
    L14:
        if (r04 >= r6) goto L18;
        byte r13 = r7[r04];
        if (zzmf.zza(r13) == false) goto L18;
        r04 = r04 + 1;
        zzmf.zza(r13, r4, r82);
        r82 = r82 + 1;
    L18:
        r5 = r82;
        r8 = r04;
        goto L10
    L38:
        return new String(r4, 0, r5);
    L39:
        Utf8$Processor$$ExternalSyntheticBUOutline0.m("buffer length=%d, index=%d, size=%d", new Object[]{Integer.valueOf(r7.length), Integer.valueOf(r8), Integer.valueOf(r9)});
        return null;
    }
}
