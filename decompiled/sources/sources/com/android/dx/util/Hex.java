package com.android.dx.util;

import c.f$$ExternalSyntheticBUOutline1;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.signature.SignatureVisitor;

/* loaded from: classes4.dex */
public final class Hex {
    private Hex() {
    }

    public static String dump(byte[] r6, int r7, int r8, int r9, int r10, int r11) {
        int r112 = r7 + r8;
        if (((r7 | r8) | r112) >= 0) goto L5;
    L37:
        Hex$$ExternalSyntheticBUOutline0.m("arr.length ", r6.length, "; ", r7, "..!", r112);
        return null;
    L5:
        if (r112 > r6.length) goto L37;
        if (r9 < 0) goto L35;
        if (r8 != 0) goto L10;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L10:
        StringBuilder r113 = new StringBuilder((r8 * 4) + 6);
        int r3 = 0;
    L12:
        if (r8 <= 0) goto L31;
        if (r3 != 0) goto L25;
        if (r11 != 2) goto L17;
        String r5 = u1(r9);
    L23:
        r113.append(r5);
        r113.append(": ");
    L27:
        r113.append(u1(r6[r7]));
        r9 = r9 + 1;
        r7 = r7 + 1;
        r3 = r3 + 1;
        if (r3 != r10) goto L30;
        r113.append('\n');
        r3 = 0;
    L30:
        r8 = r8 - 1;
        goto L12
    L17:
        if (r11 == 4) goto L21;
        if (r11 == 6) goto L20;
        r5 = u4(r9);
        goto L23
    L20:
        r5 = u3(r9);
        goto L23
    L21:
        r5 = u2(r9);
        goto L23
    L25:
        if ((r3 & 1) != 0) goto L27;
        r113.append(' ');
        goto L27
    L31:
        if (r3 == 0) goto L34;
        r113.append('\n');
    L34:
        return r113.toString();
    L35:
        f$$ExternalSyntheticBUOutline1.m("outOffset < 0");
        return null;
    }

    public static String s1(int r5) {
        char[] r0 = new char[3];
        int r1 = 0;
        if (r5 >= 0) goto L5;
        r0[0] = SignatureVisitor.SUPER;
        r5 = -r5;
    L7:
        if (r1 >= 2) goto L10;
        r0[2 - r1] = Character.forDigit(r5 & 15, 16);
        r5 = r5 >> 4;
        r1 = r1 + 1;
        goto L7
    L10:
        return new String(r0);
    L5:
        r0[0] = SignatureVisitor.EXTENDS;
        goto L7
    }

    public static String s2(int r6) {
        char[] r0 = new char[5];
        int r1 = 0;
        if (r6 >= 0) goto L5;
        r0[0] = SignatureVisitor.SUPER;
        r6 = -r6;
    L7:
        if (r1 >= 4) goto L10;
        r0[4 - r1] = Character.forDigit(r6 & 15, 16);
        r6 = r6 >> 4;
        r1 = r1 + 1;
        goto L7
    L10:
        return new String(r0);
    L5:
        r0[0] = SignatureVisitor.EXTENDS;
        goto L7
    }

    public static String s4(int r5) {
        char[] r0 = new char[9];
        int r1 = 0;
        if (r5 >= 0) goto L5;
        r0[0] = SignatureVisitor.SUPER;
        r5 = -r5;
    L7:
        if (r1 >= 8) goto L10;
        r0[8 - r1] = Character.forDigit(r5 & 15, 16);
        r5 = r5 >> 4;
        r1 = r1 + 1;
        goto L7
    L10:
        return new String(r0);
    L5:
        r0[0] = SignatureVisitor.EXTENDS;
        goto L7
    }

    public static String s8(long r5) {
        char[] r0 = new char[17];
        int r2 = 0;
        if (r5 >= 0) goto L5;
        r0[0] = SignatureVisitor.SUPER;
        r5 = -r5;
    L7:
        if (r2 >= 16) goto L10;
        r0[16 - r2] = Character.forDigit(((int) r5) & 15, 16);
        r5 = r5 >> 4;
        r2 = r2 + 1;
        goto L7
    L10:
        return new String(r0);
    L5:
        r0[0] = SignatureVisitor.EXTENDS;
        goto L7
    }

    public static String u1(int r6) {
        char[] r1 = new char[2];
        int r2 = 0;
    L3:
        if (r2 >= 2) goto L6;
        r1[1 - r2] = Character.forDigit(r6 & 15, 16);
        r6 = r6 >> 4;
        r2 = r2 + 1;
        goto L3
    L6:
        return new String(r1);
    }

    public static String u2(int r6) {
        char[] r1 = new char[4];
        int r2 = 0;
    L3:
        if (r2 >= 4) goto L6;
        r1[3 - r2] = Character.forDigit(r6 & 15, 16);
        r6 = r6 >> 4;
        r2 = r2 + 1;
        goto L3
    L6:
        return new String(r1);
    }

    public static String u2or4(int r1) {
        if (r1 != ((char) r1)) goto L7;
        return u2(r1);
    L7:
        return u4(r1);
    }

    public static String u3(int r6) {
        char[] r1 = new char[6];
        int r2 = 0;
    L3:
        if (r2 >= 6) goto L6;
        r1[5 - r2] = Character.forDigit(r6 & 15, 16);
        r6 = r6 >> 4;
        r2 = r2 + 1;
        goto L3
    L6:
        return new String(r1);
    }

    public static String u4(int r6) {
        char[] r1 = new char[8];
        int r2 = 0;
    L3:
        if (r2 >= 8) goto L6;
        r1[7 - r2] = Character.forDigit(r6 & 15, 16);
        r6 = r6 >> 4;
        r2 = r2 + 1;
        goto L3
    L6:
        return new String(r1);
    }

    public static String u8(long r5) {
        char[] r1 = new char[16];
        int r2 = 0;
    L3:
        if (r2 >= 16) goto L6;
        r1[15 - r2] = Character.forDigit(((int) r5) & 15, 16);
        r5 = r5 >> 4;
        r2 = r2 + 1;
        goto L3
    L6:
        return new String(r1);
    }

    public static String uNibble(int r2) {
        return new String(new char[]{Character.forDigit(r2 & 15, 16)});
    }
}
