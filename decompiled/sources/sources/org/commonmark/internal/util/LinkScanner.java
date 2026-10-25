package org.commonmark.internal.util;

import kotlin.text.Typography;

/* loaded from: classes5.dex */
public abstract class LinkScanner {
    public static int scanLinkDestination(CharSequence r4, int r5) {
        if (r5 < r4.length()) goto L6;
        return -1;
    L6:
        if (r4.charAt(r5) != '<') goto L24;
    L7:
        r5 = r5 + 1;
        if (r5 >= r4.length()) goto L22;
        char r0 = r4.charAt(r5);
        if (r0 == '\n') goto L22;
        if (r0 == '<') goto L22;
        if (r0 == '>') goto L21;
        if (r0 != '\\') goto L7;
        int r02 = r5 + 1;
        if (Parsing.isEscapable(r4, r02) == false) goto L7;
        r5 = r02;
        goto L7
    L21:
        return r5 + 1;
    L22:
        return -1;
    L24:
        return scanLinkDestinationWithBalancedParens(r4, r5);
    }

    private static int scanLinkDestinationWithBalancedParens(CharSequence r6, int r7) {
        int r0 = 0;
        int r1 = r7;
    L4:
        if (r1 >= r6.length()) goto L34;
        char r2 = r6.charAt(r1);
        if (r2 == 0) goto L30;
        if (r2 == ' ') goto L30;
        if (r2 != '\\') goto L12;
        int r22 = r1 + 1;
        if (Parsing.isEscapable(r6, r22) == false) goto L29;
        r1 = r22;
    L29:
        r1 = r1 + 1;
        goto L4
    L12:
        if (r2 != '(') goto L14;
        r0 = r0 + 1;
        if (r0 <= 32) goto L29;
        return -1;
    L14:
        if (r2 != ')') goto L16;
        if (r0 == 0) goto L31;
        r0 = r0 - 1;
    L31:
        return r1;
    L16:
        if (Character.isISOControl(r2) == false) goto L29;
        if (r1 != r7) goto L31;
        return -1;
    L30:
        if (r1 != r7) goto L31;
        return -1;
    L34:
        return r6.length();
    }

    public static int scanLinkLabelContent(CharSequence r2, int r3) {
    L3:
        if (r3 >= r2.length()) goto L15;
        switch(r2.charAt(r3)) {
            case 91: goto L12;
            case 92: goto L8;
            case 93: goto L7;
            default: goto L11;
        };
    L8:
        int r0 = r3 + 1;
        if (Parsing.isEscapable(r2, r0) == false) goto L11;
        r3 = r0;
        goto L11
    L7:
        return r3;
    L12:
        return -1;
    L11:
        r3 = r3 + 1;
        goto L3
    L15:
        return r2.length();
    }

    public static int scanLinkTitle(CharSequence r3, int r4) {
        if (r4 < r3.length()) goto L5;
        return -1;
    L5:
        char r0 = r3.charAt(r4);
        char r2 = Typography.quote;
        if (r0 == '\"') goto L13;
        r2 = '\'';
        if (r0 == '\'') goto L13;
        if (r0 == '(') goto L12;
        return -1;
    L12:
        r2 = ')';
    L13:
        int r42 = scanLinkTitleContent(r3, r4 + 1, r2);
        if (r42 != (-1)) goto L17;
        return -1;
    L17:
        if (r42 < r3.length()) goto L19;
    L23:
        return -1;
    L19:
        if (r3.charAt(r42) != r2) goto L23;
        return r42 + 1;
    }

    public static int scanLinkTitleContent(CharSequence r3, int r4, char r5) {
    L3:
        if (r4 >= r3.length()) goto L19;
        char r0 = r3.charAt(r4);
        if (r0 != '\\') goto L9;
        int r1 = r4 + 1;
        if (Parsing.isEscapable(r3, r1) == false) goto L9;
        r4 = r1;
    L17:
        r4 = r4 + 1;
    L9:
        if (r0 == r5) goto L10;
        if (r5 != ')') goto L17;
        if (r0 != '(') goto L17;
        return -1;
    L10:
        return r4;
    L19:
        return r3.length();
    }
}
