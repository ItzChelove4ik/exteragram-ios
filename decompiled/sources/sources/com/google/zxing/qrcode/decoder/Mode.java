package com.google.zxing.qrcode.decoder;

import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public enum Mode extends Enum<Mode> {
    private static final /* synthetic */ Mode[] $VALUES = null;
    public static final Mode ALPHANUMERIC = null;
    public static final Mode BYTE = null;
    public static final Mode ECI = null;
    public static final Mode FNC1_FIRST_POSITION = null;
    public static final Mode FNC1_SECOND_POSITION = null;
    public static final Mode HANZI = null;
    public static final Mode KANJI = null;
    public static final Mode NUMERIC = null;
    public static final Mode STRUCTURED_APPEND = null;
    public static final Mode TERMINATOR = null;
    private final int bits;
    private final int[] characterCountBitsForVersions;

    private static /* synthetic */ Mode[] $values() {
        return new Mode[]{TERMINATOR, NUMERIC, ALPHANUMERIC, STRUCTURED_APPEND, BYTE, ECI, KANJI, FNC1_FIRST_POSITION, FNC1_SECOND_POSITION, HANZI};
    }

    static {
        TERMINATOR = new Mode("TERMINATOR", 0, new int[]{0, 0, 0}, 0);
        NUMERIC = new Mode("NUMERIC", 1, new int[]{10, 12, 14}, 1);
        ALPHANUMERIC = new Mode("ALPHANUMERIC", 2, new int[]{9, 11, 13}, 2);
        STRUCTURED_APPEND = new Mode("STRUCTURED_APPEND", 3, new int[]{0, 0, 0}, 3);
        BYTE = new Mode("BYTE", 4, new int[]{8, 16, 16}, 4);
        ECI = new Mode("ECI", 5, new int[]{0, 0, 0}, 7);
        KANJI = new Mode("KANJI", 6, new int[]{8, 10, 12}, 8);
        FNC1_FIRST_POSITION = new Mode("FNC1_FIRST_POSITION", 7, new int[]{0, 0, 0}, 5);
        FNC1_SECOND_POSITION = new Mode("FNC1_SECOND_POSITION", 8, new int[]{0, 0, 0}, 9);
        HANZI = new Mode("HANZI", 9, new int[]{8, 10, 12}, 13);
        $VALUES = $values();
    }

    Mode(String r1, int r2, int[] r3, int r4) {
        this.characterCountBitsForVersions = r3;
        this.bits = r4;
    }

    public static Mode forBits(int r1) {
        if (r1 == 0) goto L42;
        if (r1 == 1) goto L40;
        if (r1 == 2) goto L38;
        if (r1 == 3) goto L36;
        if (r1 == 4) goto L34;
        if (r1 == 5) goto L32;
        if (r1 == 7) goto L30;
        if (r1 == 8) goto L28;
        if (r1 == 9) goto L26;
        if (r1 == 13) goto L22;
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    L22:
        return HANZI;
    L26:
        return FNC1_SECOND_POSITION;
    L28:
        return KANJI;
    L30:
        return ECI;
    L32:
        return FNC1_FIRST_POSITION;
    L34:
        return BYTE;
    L36:
        return STRUCTURED_APPEND;
    L38:
        return ALPHANUMERIC;
    L40:
        return NUMERIC;
    L42:
        return TERMINATOR;
    }

    public static Mode valueOf(String r1) {
        return (Mode) Enum.valueOf(Mode.class, r1);
    }

    public static Mode[] values() {
        return (Mode[]) $VALUES.clone();
    }

    public int getBits() {
        return this.bits;
    }

    public int getCharacterCountBits(Version r2) {
        int r22 = r2.getVersionNumber();
        if (r22 > 9) goto L6;
        char r23 = 0;
    L10:
        return this.characterCountBitsForVersions[r23];
    L6:
        if (r22 > 26) goto L8;
        r23 = 1;
        goto L10
    L8:
        r23 = 2;
        goto L10
    }
}
