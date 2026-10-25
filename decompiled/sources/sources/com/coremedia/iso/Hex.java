package com.coremedia.iso;

import org.mvel2.asm.signature.SignatureVisitor;

/* loaded from: classes4.dex */
public abstract class Hex {
    private static final char[] DIGITS = null;

    static {
        DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    }

    public static String encodeHex(byte[] r1) {
        return encodeHex(r1, 0);
    }

    public static String encodeHex(byte[] r8, int r9) {
        int r0 = r8.length;
        int r1 = r0 << 1;
        int r2 = 0;
        if (r9 <= 0) goto L5;
        int r3 = r0 / r9;
    L6:
        char[] r12 = new char[r1 + r3];
        int r32 = 0;
    L7:
        if (r2 >= r0) goto L9;
        if (r9 <= 0) goto L15;
        if ((r2 % r9) != 0) goto L15;
        if (r32 <= 0) goto L15;
        r12[r32] = SignatureVisitor.SUPER;
        r32 = r32 + 1;
    L15:
        int r4 = r32 + 1;
        char[] r5 = DIGITS;
        byte r6 = r8[r2];
        r12[r32] = r5[(r6 & 240) >>> 4];
        r32 = r32 + 2;
        r12[r4] = r5[r6 & 15];
        r2 = r2 + 1;
        goto L7
    L9:
        return new String(r12);
    L5:
        r3 = 0;
        goto L6
    }
}
