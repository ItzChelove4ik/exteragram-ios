package com.google.zxing.qrcode.decoder;

import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public enum ErrorCorrectionLevel extends Enum<ErrorCorrectionLevel> {
    private static final /* synthetic */ ErrorCorrectionLevel[] $VALUES = null;
    private static final ErrorCorrectionLevel[] FOR_BITS = null;
    public static final ErrorCorrectionLevel H = null;
    public static final ErrorCorrectionLevel L = null;
    public static final ErrorCorrectionLevel M = null;
    public static final ErrorCorrectionLevel Q = null;
    private final int bits;

    private static /* synthetic */ ErrorCorrectionLevel[] $values() {
        return new ErrorCorrectionLevel[]{L, M, Q, H};
    }

    static {
        ErrorCorrectionLevel r0 = new ErrorCorrectionLevel("L", 0, 1);
        L = r0;
        ErrorCorrectionLevel r1 = new ErrorCorrectionLevel("M", 1, 0);
        M = r1;
        ErrorCorrectionLevel r2 = new ErrorCorrectionLevel("Q", 2, 3);
        Q = r2;
        ErrorCorrectionLevel r3 = new ErrorCorrectionLevel("H", 3, 2);
        H = r3;
        $VALUES = $values();
        FOR_BITS = new ErrorCorrectionLevel[]{r1, r0, r3, r2};
    }

    ErrorCorrectionLevel(String r1, int r2, int r3) {
        this.bits = r3;
    }

    public static ErrorCorrectionLevel forBits(int r2) {
        if (r2 < 0) goto L7;
        ErrorCorrectionLevel[] r0 = FOR_BITS;
        if (r2 >= r0.length) goto L7;
        return r0[r2];
    L7:
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    public static ErrorCorrectionLevel valueOf(String r1) {
        return (ErrorCorrectionLevel) Enum.valueOf(ErrorCorrectionLevel.class, r1);
    }

    public static ErrorCorrectionLevel[] values() {
        return (ErrorCorrectionLevel[]) $VALUES.clone();
    }

    public int getBits() {
        return this.bits;
    }
}
