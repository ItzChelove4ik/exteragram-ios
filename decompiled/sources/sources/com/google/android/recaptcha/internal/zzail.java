package com.google.android.recaptcha.internal;

import com.google.protobuf.Utf8$Processor$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
abstract class zzail {
    public static final /* synthetic */ int $r8$clinit = 0;

    static {
        int r0 = zzaef.$r8$clinit;
    }

    public static boolean zza(byte[] r8, int r9, int r10) {
    L2:
        if (r9 >= r10) goto L7;
        if (r8[r9] < 0) goto L7;
        r9 = r9 + 1;
    L7:
        if (r9 < r10) goto L9;
        return true;
    L9:
        if (r9 >= r10) goto L10;
        int r1 = r9 + 1;
        byte r2 = r8[r9];
        if (r2 < 0) goto L14;
        r9 = r1;
        goto L9
    L14:
        if (r2 < (-32)) goto L15;
        if (r2 < (-16)) goto L25;
        if (r1 >= (r10 - 2)) goto L44;
        int r3 = r9 + 2;
        byte r12 = r8[r1];
        if (r12 > (-65)) goto L53;
        if ((((r2 << 28) + (r12 + 112)) >> 30) != 0) goto L53;
        int r13 = r9 + 3;
        if (r8[r3] > (-65)) goto L53;
        r9 = r9 + 4;
        if (r8[r13] <= (-65)) goto L9;
    L53:
        return false;
    L44:
        return false;
    L25:
        if (r1 >= (r10 - 1)) goto L26;
        int r6 = r9 + 2;
        byte r14 = r8[r1];
        if (r14 > (-65)) goto L41;
        if (r2 != (-32)) goto L35;
        if (r14 >= (-96)) goto L35;
        return false;
    L35:
        if (r2 != (-19)) goto L39;
        if (r14 < (-96)) goto L39;
        return false;
    L39:
        r9 = r9 + 3;
        if (r8[r6] <= (-65)) goto L9;
    L41:
        return false;
    L26:
        return false;
    L15:
        if (r1 >= r10) goto L16;
        if (r2 < (-62)) goto L21;
        r9 = r9 + 2;
        if (r8[r1] <= (-65)) goto L9;
    L21:
        return false;
    L16:
        return false;
    L10:
        return true;
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
        return zzaij.zza(r10, r11, r12, r13);
    L41:
        if (r52 < 55296) goto L50;
        if (r52 > 57343) goto L50;
        int r14 = r1 + 1;
        if (r14 == r10.length()) goto L48;
        if (Character.isSurrogatePair(r52, r10.charAt(r14)) == true) goto L50;
    L48:
        return zzaij.zza(r10, r11, r12, r13);
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

    public static String zzc(byte[] r9, int r10, int r11) {
        if (r11 == 0) goto L42;
        int r0 = r9.length;
        if ((((r0 - r10) - r11) | (r10 | r11)) < 0) goto L40;
        int r02 = r10 + r11;
        char[] r7 = new char[r11];
        int r1 = 0;
    L6:
        if (r10 >= r02) goto L10;
        byte r3 = r9[r10];
        if (zzaih.zza(r3) == false) goto L10;
        r10 = r10 + 1;
        r7[r1] = (char) r3;
        r1 = r1 + 1;
    L10:
        int r8 = r1;
    L11:
        if (r10 >= r02) goto L39;
        int r12 = r10 + 1;
        byte r32 = r9[r10];
        if (zzaih.zza(r32) == true) goto L14;
        if (r32 < (-32)) goto L21;
        if (r32 < (-16)) goto L29;
        if (r12 >= (r02 - 2)) goto L36;
        byte r13 = r9[r12];
        int r5 = r10 + 3;
        byte r4 = r9[r10 + 2];
        r10 = r10 + 4;
        zzaih.zzd(r32, r13, r4, r9[r5], r7, r8);
        r8 = r8 + 2;
        goto L11
    L36:
        zzaeh$$ExternalSyntheticBUOutline0.m("Protocol message had invalid UTF-8.");
        return null;
    L29:
        if (r12 >= (r02 - 1)) goto L31;
        int r42 = r8 + 1;
        int r52 = r10 + 2;
        r10 = r10 + 3;
        zzaih.zzc(r32, r9[r12], r9[r52], r7, r8);
    L23:
        r8 = r42;
        goto L11
    L31:
        zzaeh$$ExternalSyntheticBUOutline0.m("Protocol message had invalid UTF-8.");
        return null;
    L21:
        if (r12 >= r02) goto L24;
        r42 = r8 + 1;
        r10 = r10 + 2;
        zzaih.zzb(r32, r9[r12], r7, r8);
        goto L23
    L24:
        zzaeh$$ExternalSyntheticBUOutline0.m("Protocol message had invalid UTF-8.");
        return null;
    L14:
        r7[r8] = (char) r32;
        r8 = r8 + 1;
        r10 = r12;
    L15:
        if (r10 >= r02) goto L11;
        byte r14 = r9[r10];
        if (zzaih.zza(r14) == false) goto L11;
        r10 = r10 + 1;
        r7[r8] = (char) r14;
        r8 = r8 + 1;
        goto L15
    L39:
        return new String(r7, 0, r8);
    L40:
        Utf8$Processor$$ExternalSyntheticBUOutline0.m("buffer length=%d, index=%d, size=%d", new Object[]{Integer.valueOf(r0), Integer.valueOf(r10), Integer.valueOf(r11)});
        return null;
    L42:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }
}
