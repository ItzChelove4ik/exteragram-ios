package com.android.dx.util;

import org.mvel2.MVEL$$ExternalSyntheticBUOutline0;
import org.mvel2.ast.Instance$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class HexParser {
    private HexParser() {
    }

    public static byte[] parse(String r17) {
        int r1 = r17.length();
        int r2 = r1 / 2;
        byte[] r3 = new byte[r2];
        int r4 = 0;
        int r5 = 0;
        int r6 = 0;
    L3:
        if (r5 >= r1) goto L54;
        int r7 = r17.indexOf(10, r5);
        if (r7 >= 0) goto L7;
        r7 = r1;
    L7:
        int r8 = r17.indexOf(35, r5);
        if (r8 < 0) goto L11;
        if (r8 >= r7) goto L11;
        String r52 = r17.substring(r5, r8);
    L12:
        int r72 = r7 + 1;
        int r82 = r52.indexOf(58);
        char r10 = 16;
        if (r82 == (-1)) goto L23;
        int r13 = r52.indexOf(34);
        if (r13 == (-1)) goto L18;
        if (r13 < r82) goto L23;
    L18:
        String r132 = r52.substring(r4, r82).trim();
        r52 = r52.substring(r82 + 1);
        if (Integer.parseInt(r132, 16) == r6) goto L23;
        MVEL$$ExternalSyntheticBUOutline0.m("bogus offset marker: ", r132);
        return null;
    L23:
        int r83 = r52.length();
        int r133 = r4;
        int r15 = r133;
        int r14 = -1;
    L25:
        if (r133 >= r83) goto L47;
        char r42 = r52.charAt(r133);
        if (r15 == 0) goto L32;
        if (r42 != '\"') goto L30;
        char r9 = r10;
        r15 = 0;
    L44:
        r133 = r133 + 1;
        r10 = r9;
        goto L25
    L30:
        r3[r6] = (byte) r42;
        r6 = r6 + 1;
        r9 = r10;
        goto L44
    L32:
        if (r42 > ' ') goto L34;
    L33:
        r9 = 16;
        goto L44
    L34:
        if (r42 != '\"') goto L39;
        if (r14 != (-1)) goto L37;
        r15 = 1;
        goto L33
    L37:
        Instance$$ExternalSyntheticBUOutline0.m("spare digit around offset ", Hex.u4(r6));
        return null;
    L39:
        r9 = 16;
        int r102 = Character.digit(r42, 16);
        if (r102 == (-1)) goto L46;
        if (r14 != (-1)) goto L43;
        r14 = r102;
        goto L44
    L43:
        r3[r6] = (byte) ((r14 << 4) | r102);
        r6 = r6 + 1;
        r14 = -1;
        goto L44
    L46:
        throw new RuntimeException("bogus digit character: \"" + r42 + "\"");
    L47:
        if (r14 != (-1)) goto L52;
        if (r15 != 0) goto L50;
        r5 = r72;
        r4 = 0;
        goto L3
    L50:
        Instance$$ExternalSyntheticBUOutline0.m("unterminated quote around offset ", Hex.u4(r6));
        return null;
    L52:
        Instance$$ExternalSyntheticBUOutline0.m("spare digit around offset ", Hex.u4(r6));
        return null;
    L11:
        r52 = r17.substring(r5, r7);
        goto L12
    L54:
        if (r6 >= r2) goto L57;
        byte[] r0 = new byte[r6];
        System.arraycopy(r3, 0, r0, 0, r6);
        return r0;
    L57:
        return r3;
    }
}
