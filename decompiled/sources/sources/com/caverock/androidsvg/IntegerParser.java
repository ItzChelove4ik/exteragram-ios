package com.caverock.androidsvg;

/* loaded from: classes4.dex */
class IntegerParser {
    private int pos;
    private long value;

    public IntegerParser(long r1, int r3) {
        this.value = r1;
        this.pos = r3;
    }

    public static IntegerParser parseHex(String r10, int r11, int r12) {
        if (r11 < r12) goto L5;
        return null;
    L5:
        long r1 = 0;
        int r3 = r11;
    L6:
        if (r3 >= r12) goto L27;
        char r4 = r10.charAt(r3);
        if (r4 < '0') goto L13;
        if (r4 > '9') goto L13;
        r1 = (r1 * 16) + (r4 - '0');
    L24:
        if (r1 > 4294967295L) goto L25;
        r3 = r3 + 1;
        goto L6
    L25:
        return null;
    L13:
        if (r4 < 'A') goto L19;
        if (r4 > 'F') goto L19;
        long r13 = r1 * 16;
        int r42 = r4 - 'A';
    L17:
        r1 = (r13 + r42) + 10;
    L19:
        if (r4 < 'a') goto L27;
        if (r4 > 'f') goto L27;
        r13 = r1 * 16;
        r42 = r4 - 'a';
    L27:
        if (r3 != r11) goto L30;
        return null;
    L30:
        return new IntegerParser(r1, r3);
    }

    public static IntegerParser parseInt(String r7, int r8, int r9, boolean r10) {
        if (r8 < r9) goto L5;
        return null;
    L5:
        boolean r1 = false;
        if (r10 == false) goto L14;
        char r102 = r7.charAt(r8);
        if (r102 != '+') goto L10;
    L13:
        r8 = r8 + 1;
        goto L14
    L10:
        if (r102 != '-') goto L14;
        r1 = true;
    L14:
        long r2 = 0;
        int r103 = r8;
    L15:
        if (r103 >= r9) goto L29;
        char r4 = r7.charAt(r103);
        if (r4 < '0') goto L29;
        if (r4 > '9') goto L29;
        if (r1 == false) goto L25;
        r2 = (r2 * 10) - (r4 - '0');
        if (r2 >= (-2147483648L)) goto L28;
        return null;
    L28:
        r103 = r103 + 1;
        goto L15
    L25:
        r2 = (r2 * 10) + (r4 - '0');
        if (r2 <= 2147483647L) goto L28;
        return null;
    L29:
        if (r103 != r8) goto L32;
        return null;
    L32:
        return new IntegerParser(r2, r103);
    }

    public int getEndPos() {
        return this.pos;
    }

    public int value() {
        return (int) this.value;
    }
}
