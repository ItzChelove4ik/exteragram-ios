package com.google.zxing.qrcode.decoder;

/* loaded from: classes5.dex */
final class FormatInformation {
    private static final int[][] FORMAT_INFO_DECODE_LOOKUP = null;
    private final byte dataMask;
    private final ErrorCorrectionLevel errorCorrectionLevel;

    static {
        FORMAT_INFO_DECODE_LOOKUP = new int[][]{new int[]{21522, 0}, new int[]{20773, 1}, new int[]{24188, 2}, new int[]{23371, 3}, new int[]{17913, 4}, new int[]{16590, 5}, new int[]{20375, 6}, new int[]{19104, 7}, new int[]{30660, 8}, new int[]{29427, 9}, new int[]{32170, 10}, new int[]{30877, 11}, new int[]{26159, 12}, new int[]{25368, 13}, new int[]{27713, 14}, new int[]{26998, 15}, new int[]{5769, 16}, new int[]{5054, 17}, new int[]{7399, 18}, new int[]{6608, 19}, new int[]{1890, 20}, new int[]{597, 21}, new int[]{3340, 22}, new int[]{2107, 23}, new int[]{13663, 24}, new int[]{12392, 25}, new int[]{16177, 26}, new int[]{14854, 27}, new int[]{9396, 28}, new int[]{8579, 29}, new int[]{11994, 30}, new int[]{11245, 31}};
    }

    private FormatInformation(int r2) {
        this.errorCorrectionLevel = ErrorCorrectionLevel.forBits((r2 >> 3) & 3);
        this.dataMask = (byte) (r2 & 7);
    }

    public static FormatInformation decodeFormatInformation(int r1, int r2) {
        FormatInformation r0 = doDecodeFormatInformation(r1, r2);
        if (r0 == null) goto L6;
        return r0;
    L6:
        return doDecodeFormatInformation(r1 ^ 21522, r2 ^ 21522);
    }

    private static FormatInformation doDecodeFormatInformation(int r10, int r11) {
        int[][] r0 = FORMAT_INFO_DECODE_LOOKUP;
        int r1 = r0.length;
        int r2 = Integer.MAX_VALUE;
        int r4 = 0;
        int r5 = 0;
    L3:
        if (r4 >= r1) goto L19;
        int[] r6 = r0[r4];
        int r7 = r6[0];
        if (r7 == r10) goto L17;
        if (r7 == r11) goto L17;
        int r9 = numBitsDiffering(r10, r7);
        if (r9 >= r2) goto L11;
        r5 = r6[1];
        r2 = r9;
    L11:
        if (r10 == r11) goto L15;
        int r72 = numBitsDiffering(r11, r7);
        if (r72 >= r2) goto L15;
        r5 = r6[1];
        r2 = r72;
    L15:
        r4 = r4 + 1;
    L17:
        return new FormatInformation(r6[1]);
    L19:
        if (r2 <= 3) goto L21;
        return null;
    L21:
        return new FormatInformation(r5);
    }

    public static int numBitsDiffering(int r0, int r1) {
        return Integer.bitCount(r0 ^ r1);
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof FormatInformation) == true) goto L5;
        return false;
    L5:
        FormatInformation r42 = (FormatInformation) r4;
        if (this.errorCorrectionLevel == r42.errorCorrectionLevel) goto L8;
    L11:
        return false;
    L8:
        if (this.dataMask != r42.dataMask) goto L11;
        return true;
    }

    public byte getDataMask() {
        return this.dataMask;
    }

    public ErrorCorrectionLevel getErrorCorrectionLevel() {
        return this.errorCorrectionLevel;
    }

    public int hashCode() {
        int r0 = this.errorCorrectionLevel.ordinal() << 3;
        return this.dataMask | r0;
    }
}
