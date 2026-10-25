package com.google.android.gms.internal.clearcut;

import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
final class zzfh extends zzfg {
    public zzfh() {
    }

    @Override // com.google.android.gms.internal.clearcut.zzfg
    public final int zzb(int r7, byte[] r8, int r9, int r10) {
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
        return zzff.zzg(r8, r72, r10);
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
        return zzff.zzg(r8, r72, r10);
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

    @Override // com.google.android.gms.internal.clearcut.zzfg
    public final int zzb(CharSequence r7, byte[] r8, int r9, int r10) {
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
        throw new zzfi(r0 - 1, r6);
    L41:
        if (55296 > r22) goto L49;
        if (r22 > 57343) goto L49;
        int r82 = r0 + 1;
        if (r82 == r7.length()) goto L48;
        if (Character.isSurrogatePair(r22, r7.charAt(r82)) == true) goto L49;
    L48:
        throw new zzfi(r0, r6);
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

    @Override // com.google.android.gms.internal.clearcut.zzfg
    public final void zzb(CharSequence r1, ByteBuffer r2) {
        zzfg.zzc(r1, r2);
    }
}
