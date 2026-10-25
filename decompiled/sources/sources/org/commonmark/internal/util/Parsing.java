package org.commonmark.internal.util;

/* loaded from: classes5.dex */
public abstract class Parsing {
    public static int CODE_BLOCK_INDENT = 4;

    static {
    }

    public static int columnsToNextTabStop(int r0) {
        return 4 - (r0 % 4);
    }

    public static int find(char r2, CharSequence r3, int r4) {
        int r0 = r3.length();
    L3:
        if (r4 >= r0) goto L8;
        if (r3.charAt(r4) == r2) goto L6;
        r4 = r4 + 1;
        goto L3
    L6:
        return r4;
    L8:
        return -1;
    }

    public static int findLineBreak(CharSequence r3, int r4) {
        int r0 = r3.length();
    L3:
        if (r4 >= r0) goto L10;
        char r1 = r3.charAt(r4);
        if (r1 == '\n') goto L9;
        if (r1 == '\r') goto L9;
        r4 = r4 + 1;
    L9:
        return r4;
    L10:
        return -1;
    }

    private static int findNonSpace(CharSequence r3, int r4) {
        int r0 = r3.length();
    L3:
        if (r4 >= r0) goto L9;
        char r1 = r3.charAt(r4);
        if (r1 == ' ') goto L8;
        switch(r1) {
            case 9: goto L8;
            case 10: goto L8;
            case 11: goto L8;
            case 12: goto L8;
            case 13: goto L8;
            default: goto L7;
        };
    L7:
        return r4;
    L8:
        r4 = r4 + 1;
        goto L3
    L9:
        return -1;
    }

    public static boolean hasNonSpace(CharSequence r3) {
        int r0 = r3.length();
        if (skip(' ', r3, 0, r0) == r0) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isBlank(CharSequence r2) {
        if (findNonSpace(r2, 0) != (-1)) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isEscapable(CharSequence r1, int r2) {
        if (r2 >= r1.length()) goto L12;
        char r12 = r1.charAt(r2);
        switch(r12) {
            case 33: goto L10;
            case 34: goto L10;
            case 35: goto L10;
            case 36: goto L10;
            case 37: goto L10;
            case 38: goto L10;
            case 39: goto L10;
            case 40: goto L10;
            case 41: goto L10;
            case 42: goto L10;
            case 43: goto L10;
            case 44: goto L10;
            case 45: goto L10;
            case 46: goto L10;
            case 47: goto L10;
            default: goto L6;
        };
    L6:
        switch(r12) {
            case 58: goto L14;
            case 59: goto L14;
            case 60: goto L14;
            case 61: goto L14;
            case 62: goto L14;
            case 63: goto L14;
            case 64: goto L14;
            default: goto L7;
        };
    L7:
        switch(r12) {
            case 91: goto L15;
            case 92: goto L15;
            case 93: goto L15;
            case 94: goto L15;
            case 95: goto L15;
            case 96: goto L15;
            default: goto L8;
        };
    L8:
        switch(r12) {
            case 123: goto L16;
            case 124: goto L16;
            case 125: goto L16;
            case 126: goto L16;
            default: goto L17;
        };
    L16:
        return true;
    L17:
        return false;
    L15:
        return true;
    L14:
        return true;
    L10:
        return true;
    L12:
        return false;
    }

    public static boolean isLetter(CharSequence r0, int r1) {
        return Character.isLetter(Character.codePointAt(r0, r1));
    }

    public static boolean isSpaceOrTab(CharSequence r1, int r2) {
        if (r2 >= r1.length()) goto L11;
        char r12 = r1.charAt(r2);
        if (r12 != '\t') goto L7;
        return true;
    L7:
        if (r12 != ' ') goto L14;
        return true;
    L14:
        return false;
    L11:
        return false;
    }

    public static CharSequence prepareLine(CharSequence r5) {
        int r0 = r5.length();
        StringBuilder r1 = null;
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L12;
        char r4 = r5.charAt(r3);
        if (r4 == 0) goto L8;
        if (r1 == null) goto L11;
        r1.append(r4);
    L11:
        r3 = r3 + 1;
        goto L3
    L8:
        if (r1 != null) goto L10;
        r1 = new StringBuilder(r0);
        r1.append(r5, 0, r3);
    L10:
        r1.append(65533);
        goto L11
    L12:
        if (r1 != null) goto L14;
        return r5;
    L14:
        return r1.toString();
    }

    public static int skip(char r1, CharSequence r2, int r3, int r4) {
    L2:
        if (r3 >= r4) goto L7;
        if (r2.charAt(r3) != r1) goto L5;
        r3 = r3 + 1;
        goto L2
    L5:
        return r3;
    L7:
        return r4;
    }

    public static int skipBackwards(char r1, CharSequence r2, int r3, int r4) {
    L2:
        if (r3 < r4) goto L8;
        if (r2.charAt(r3) != r1) goto L5;
        r3 = r3 - 1;
        goto L2
    L5:
        return r3;
    L8:
        return r4 - 1;
    }

    public static int skipSpaceTab(CharSequence r2, int r3, int r4) {
    L2:
        if (r3 >= r4) goto L9;
        char r0 = r2.charAt(r3);
        if (r0 == '\t') goto L8;
        if (r0 == ' ') goto L8;
        return r3;
    L8:
        r3 = r3 + 1;
        goto L2
    L9:
        return r4;
    }

    public static int skipSpaceTabBackwards(CharSequence r2, int r3, int r4) {
    L2:
        if (r3 < r4) goto L10;
        char r0 = r2.charAt(r3);
        if (r0 == '\t') goto L8;
        if (r0 == ' ') goto L8;
        return r3;
    L8:
        r3 = r3 - 1;
        goto L2
    L10:
        return r4 - 1;
    }
}
