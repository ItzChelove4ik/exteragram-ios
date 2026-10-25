package com.google.android.gms.internal.play_billing;

import com.google.protobuf.Utf8$SafeProcessor$$ExternalSyntheticBUOutline0;
import okio.AsyncTimeout$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
abstract class zzhr {
    public static final /* synthetic */ int $r8$clinit = 0;

    static {
        if (zzho.zzx() == true) goto L5;
        return;
    L5:
        if (zzho.zzy() == false) goto L9;
        int r0 = zzdv.$r8$clinit;
        return;
    }

    public static /* bridge */ /* synthetic */ int zza(byte[] r5, int r6, int r7) {
        int r72 = r7 - r6;
        byte r0 = r5[r6 - 1];
        if (r72 != 0) goto L5;
        if (r0 > (-12)) goto L29;
        return r0;
    L29:
        return -1;
    L5:
        if (r72 != 1) goto L7;
        byte r52 = r5[r6];
        if (r0 > (-12)) goto L28;
        if (r52 <= (-65)) goto L22;
        return -1;
    L22:
        return (r52 << 8) ^ r0;
    L28:
        return -1;
    L7:
        if (r72 != 2) goto L15;
        byte r73 = r5[r6];
        byte r53 = r5[r6 + 1];
        if (r0 > (-12)) goto L25;
        if (r73 > (-65)) goto L27;
        if (r53 <= (-65)) goto L14;
        return -1;
    L14:
        return (r53 << 16) ^ ((r73 << 8) ^ r0);
    L27:
        return -1;
    L25:
        return -1;
    L15:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return 0;
    }

    public static int zzb(String r9, byte[] r10, int r11, int r12) {
        int r0 = r9.length();
        int r2 = 0;
    L3:
        int r3 = r11 + r12;
        if (r2 >= r0) goto L10;
        int r5 = r2 + r11;
        if (r5 >= r3) goto L10;
        char r6 = r9.charAt(r2);
        if (r6 >= 128) goto L10;
        r10[r5] = (byte) r6;
        r2 = r2 + 1;
    L10:
        if (r2 == r0) goto L12;
        int r112 = r11 + r2;
    L14:
        if (r2 >= r0) goto L52;
        char r122 = r9.charAt(r2);
        if (r122 >= 128) goto L20;
        if (r112 >= r3) goto L20;
        r10[r112] = (byte) r122;
        r112 = r112 + 1;
    L37:
        r2 = r2 + 1;
    L20:
        if (r122 >= 2048) goto L25;
        if (r112 > (r3 - 2)) goto L25;
        r10[r112] = (byte) ((r122 >>> 6) | 960);
        r10[r112 + 1] = (byte) ((r122 & '?') | 128);
        r112 = r112 + 2;
    L25:
        if (r122 < 55296) goto L28;
        if (r122 > 57343) goto L28;
    L31:
        if (r112 > (r3 - 4)) goto L41;
        int r52 = r2 + 1;
        if (r52 == r9.length()) goto L40;
        char r22 = r9.charAt(r52);
        if (Character.isSurrogatePair(r122, r22) == false) goto L38;
        int r8 = r112 + 3;
        int r123 = Character.toCodePoint(r122, r22);
        r10[r112] = (byte) ((r123 >>> 18) | 240);
        r10[r112 + 1] = (byte) (((r123 >>> 12) & 63) | 128);
        r10[r112 + 2] = (byte) (((r123 >>> 6) & 63) | 128);
        r112 = r112 + 4;
        r10[r8] = (byte) ((r123 & 63) | 128);
        r2 = r52;
        goto L37
    L38:
        r2 = r52;
    L40:
        throw new zzhq(r2 - 1, r0);
    L41:
        if (r122 < 55296) goto L50;
        if (r122 > 57343) goto L50;
        int r102 = r2 + 1;
        if (r102 == r9.length()) goto L49;
        if (Character.isSurrogatePair(r122, r9.charAt(r102)) == true) goto L50;
    L49:
        throw new zzhq(r2, r0);
    L50:
        Utf8$SafeProcessor$$ExternalSyntheticBUOutline0.m(r122, r112);
        return 0;
    L28:
        if (r112 > (r3 - 3)) goto L31;
        r10[r112] = (byte) ((r122 >>> '\f') | 480);
        r10[r112 + 1] = (byte) (((r122 >>> 6) & 63) | 128);
        r10[r112 + 2] = (byte) ((r122 & '?') | 128);
        r112 = r112 + 3;
        goto L37
    L52:
        return r112;
    L12:
        return r11 + r0;
    }

    public static int zzc(String r8) {
        int r0 = r8.length();
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L7;
        if (r8.charAt(r2) >= 128) goto L7;
        r2 = r2 + 1;
    L7:
        int r3 = r0;
    L8:
        if (r2 >= r0) goto L28;
        char r4 = r8.charAt(r2);
        if (r4 >= 2048) goto L12;
        r3 = r3 + ((127 - r4) >>> 31);
        r2 = r2 + 1;
        goto L8
    L12:
        int r42 = r8.length();
    L13:
        if (r2 >= r42) goto L27;
        char r6 = r8.charAt(r2);
        if (r6 >= 2048) goto L17;
        r1 = r1 + ((127 - r6) >>> 31);
    L26:
        r2 = r2 + 1;
        goto L13
    L17:
        r1 = r1 + 2;
        if (r6 < 55296) goto L26;
        if (r6 > 57343) goto L26;
        if (Character.codePointAt(r8, r2) < 65536) goto L25;
        r2 = r2 + 1;
        goto L26
    L25:
        throw new zzhq(r2, r42);
    L27:
        r3 = r3 + r1;
    L28:
        if (r3 < r0) goto L31;
        return r3;
    L31:
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (r3 + 4294967296L));
    }

    public static boolean zzd(byte[] r6, int r7, int r8) {
    L2:
        if (r7 >= r8) goto L6;
        if (r6[r7] < 0) goto L6;
        r7 = r7 + 1;
    L6:
        if (r7 >= r8) goto L92;
    L8:
        if (r7 >= r8) goto L93;
        int r0 = r7 + 1;
        int r1 = r6[r7];
        if (r1 < 0) goto L12;
        r7 = r0;
        goto L8
    L12:
        if (r1 < (-32)) goto L13;
        if (r1 < (-16)) goto L23;
        if (r0 >= (r8 - 2)) goto L38;
        int r2 = r7 + 2;
        int r02 = r6[r0];
        if (r02 > (-65)) goto L88;
        if ((((r1 << 28) + (r02 + 112)) >> 30) != 0) goto L89;
        int r03 = r7 + 3;
        if (r6[r2] > 65471) goto L90;
        r7 = r7 + 4;
        if (r6[r03] <= 65471) goto L8;
        return false;
    L90:
        return false;
    L89:
        return false;
    L88:
        return false;
    L38:
        r1 = zza(r6, r0, r8);
    L39:
        if (r1 != 0) goto L50;
        return true;
    L50:
        return false;
    L23:
        if (r0 >= (r8 - 1)) goto L24;
        int r4 = r7 + 2;
        char r04 = r6[r0];
        if (r04 > 65471) goto L86;
        if (r1 != (-32)) goto L31;
        if (r04 >= 65440) goto L31;
        return false;
    L31:
        if (r1 != (-19)) goto L33;
        if (r04 < 65440) goto L33;
        return false;
    L33:
        r7 = r7 + 3;
        if (r6[r4] <= 65471) goto L8;
        return false;
    L86:
        return false;
    L24:
        r1 = zza(r6, r0, r8);
        goto L39
    L13:
        if (r0 >= r8) goto L39;
        if (r1 < (-62)) goto L82;
        r7 = r7 + 2;
        if (r6[r0] <= 65471) goto L8;
        return false;
    L82:
        return false;
    L93:
        return true;
    L92:
        return true;
    }
}
