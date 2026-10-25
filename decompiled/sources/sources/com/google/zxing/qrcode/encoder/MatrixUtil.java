package com.google.zxing.qrcode.encoder;

import c.f$$ExternalSyntheticBUOutline1;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitArray;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Version;

/* loaded from: classes5.dex */
abstract class MatrixUtil {
    private static final int[][] POSITION_ADJUSTMENT_PATTERN = null;
    private static final int[][] POSITION_ADJUSTMENT_PATTERN_COORDINATE_TABLE = null;
    private static final int[][] POSITION_DETECTION_PATTERN = null;
    private static final int[][] TYPE_INFO_COORDINATES = null;

    static {
        POSITION_DETECTION_PATTERN = new int[][]{new int[]{1, 1, 1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1, 1, 1}};
        POSITION_ADJUSTMENT_PATTERN = new int[][]{new int[]{1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 0, 1, 0, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1}};
        POSITION_ADJUSTMENT_PATTERN_COORDINATE_TABLE = new int[][]{new int[]{-1, -1, -1, -1, -1, -1, -1}, new int[]{6, 18, -1, -1, -1, -1, -1}, new int[]{6, 22, -1, -1, -1, -1, -1}, new int[]{6, 26, -1, -1, -1, -1, -1}, new int[]{6, 30, -1, -1, -1, -1, -1}, new int[]{6, 34, -1, -1, -1, -1, -1}, new int[]{6, 22, 38, -1, -1, -1, -1}, new int[]{6, 24, 42, -1, -1, -1, -1}, new int[]{6, 26, 46, -1, -1, -1, -1}, new int[]{6, 28, 50, -1, -1, -1, -1}, new int[]{6, 30, 54, -1, -1, -1, -1}, new int[]{6, 32, 58, -1, -1, -1, -1}, new int[]{6, 34, 62, -1, -1, -1, -1}, new int[]{6, 26, 46, 66, -1, -1, -1}, new int[]{6, 26, 48, 70, -1, -1, -1}, new int[]{6, 26, 50, 74, -1, -1, -1}, new int[]{6, 30, 54, 78, -1, -1, -1}, new int[]{6, 30, 56, 82, -1, -1, -1}, new int[]{6, 30, 58, 86, -1, -1, -1}, new int[]{6, 34, 62, 90, -1, -1, -1}, new int[]{6, 28, 50, 72, 94, -1, -1}, new int[]{6, 26, 50, 74, 98, -1, -1}, new int[]{6, 30, 54, 78, 102, -1, -1}, new int[]{6, 28, 54, 80, 106, -1, -1}, new int[]{6, 32, 58, 84, 110, -1, -1}, new int[]{6, 30, 58, 86, 114, -1, -1}, new int[]{6, 34, 62, 90, 118, -1, -1}, new int[]{6, 26, 50, 74, 98, 122, -1}, new int[]{6, 30, 54, 78, 102, 126, -1}, new int[]{6, 26, 52, 78, 104, 130, -1}, new int[]{6, 30, 56, 82, 108, 134, -1}, new int[]{6, 34, 60, 86, 112, 138, -1}, new int[]{6, 30, 58, 86, 114, 142, -1}, new int[]{6, 34, 62, 90, 118, 146, -1}, new int[]{6, 30, 54, 78, 102, 126, 150}, new int[]{6, 24, 50, 76, 102, 128, 154}, new int[]{6, 28, 54, 80, 106, 132, 158}, new int[]{6, 32, 58, 84, 110, 136, 162}, new int[]{6, 26, 54, 82, 110, 138, 166}, new int[]{6, 30, 58, 86, 114, 142, 170}};
        TYPE_INFO_COORDINATES = new int[][]{new int[]{8, 0}, new int[]{8, 1}, new int[]{8, 2}, new int[]{8, 3}, new int[]{8, 4}, new int[]{8, 5}, new int[]{8, 7}, new int[]{8, 8}, new int[]{7, 8}, new int[]{5, 8}, new int[]{4, 8}, new int[]{3, 8}, new int[]{2, 8}, new int[]{1, 8}, new int[]{0, 8}};
    }

    public static void buildMatrix(BitArray r0, ErrorCorrectionLevel r1, Version r2, int r3, ByteMatrix r4) {
        clearMatrix(r4);
        embedBasicPatterns(r2, r4);
        embedTypeInfo(r1, r3, r4);
        maybeEmbedVersionInfo(r2, r4);
        embedDataBits(r0, r3, r4);
    }

    public static int calculateBCHCode(int r2, int r3) {
        if (r3 == 0) goto L8;
        int r0 = findMSBSet(r3);
        int r22 = r2 << (r0 - 1);
    L5:
        if (findMSBSet(r22) < r0) goto L7;
        r22 = r22 ^ (r3 << (findMSBSet(r22) - r0));
        goto L5
    L7:
        return r22;
    L8:
        f$$ExternalSyntheticBUOutline1.m("0 polynomial");
        return 0;
    }

    public static void clearMatrix(ByteMatrix r1) {
        r1.clear((byte) -1);
    }

    public static void embedBasicPatterns(Version r0, ByteMatrix r1) {
        embedPositionDetectionPatternsAndSeparators(r1);
        embedDarkDotAtLeftBottomCorner(r1);
        maybeEmbedPositionAdjustmentPatterns(r0, r1);
        embedTimingPatterns(r1);
    }

    private static void embedDarkDotAtLeftBottomCorner(ByteMatrix r3) {
        if (r3.get(8, r3.getHeight() - 8) == 0) goto L7;
        r3.set(8, r3.getHeight() - 8, 1);
        return;
    L7:
        throw new WriterException();
    }

    public static void embedDataBits(BitArray r10, int r11, ByteMatrix r12) {
        int r0 = r12.getWidth() - 1;
        int r1 = r12.getHeight() - 1;
        int r4 = 0;
        int r5 = -1;
    L3:
        if (r0 <= 0) goto L29;
        if (r0 != 6) goto L7;
        r0 = r0 - 1;
    L7:
        if (r1 < 0) goto L27;
        if (r1 >= r12.getHeight()) goto L27;
        int r6 = 0;
    L12:
        if (r6 >= 2) goto L26;
        int r7 = r0 - r6;
        if (isEmpty(r12.get(r7, r1)) == false) goto L25;
        if (r4 >= r10.getSize()) goto L19;
        boolean r8 = r10.get(r4);
        r4 = r4 + 1;
    L20:
        if (r11 != (-1)) goto L22;
    L24:
        r12.set(r7, r1, r8);
        goto L25
    L22:
        if (MaskUtil.getDataMaskBit(r11, r7, r1) == false) goto L24;
        r8 = !r8;
        goto L24
    L19:
        r8 = false;
    L25:
        r6 = r6 + 1;
        goto L12
    L26:
        r1 = r1 + r5;
    L27:
        r5 = -r5;
        r1 = r1 + r5;
        r0 = r0 - 2;
        goto L3
    L29:
        if (r4 != r10.getSize()) goto L32;
        return;
    L32:
        throw new WriterException("Not all bits consumed: " + r4 + '/' + r10.getSize());
    }

    private static void embedHorizontalSeparationPattern(int r4, int r5, ByteMatrix r6) {
        int r1 = 0;
    L4:
        if (r1 >= 8) goto L10;
        int r2 = r4 + r1;
        if (isEmpty(r6.get(r2, r5)) == false) goto L9;
        r6.set(r2, r5, 0);
        r1 = r1 + 1;
        goto L4
    L9:
        throw new WriterException();
    }

    private static void embedPositionAdjustmentPattern(int r8, int r9, ByteMatrix r10) {
        int r1 = 0;
    L4:
        if (r1 >= 5) goto L9;
        int[] r3 = POSITION_ADJUSTMENT_PATTERN[r1];
        int r4 = 0;
    L6:
        if (r4 >= 5) goto L8;
        r10.set(r8 + r4, r9 + r1, r3[r4]);
        r4 = r4 + 1;
        goto L6
    L8:
        r1 = r1 + 1;
        goto L4
    }

    private static void embedPositionDetectionPattern(int r8, int r9, ByteMatrix r10) {
        int r1 = 0;
    L4:
        if (r1 >= 7) goto L9;
        int[] r3 = POSITION_DETECTION_PATTERN[r1];
        int r4 = 0;
    L6:
        if (r4 >= 7) goto L8;
        r10.set(r8 + r4, r9 + r1, r3[r4]);
        r4 = r4 + 1;
        goto L6
    L8:
        r1 = r1 + 1;
        goto L4
    }

    private static void embedPositionDetectionPatternsAndSeparators(ByteMatrix r3) {
        int r0 = POSITION_DETECTION_PATTERN[0].length;
        embedPositionDetectionPattern(0, 0, r3);
        embedPositionDetectionPattern(r3.getWidth() - r0, 0, r3);
        embedPositionDetectionPattern(0, r3.getWidth() - r0, r3);
        embedHorizontalSeparationPattern(0, 7, r3);
        embedHorizontalSeparationPattern(r3.getWidth() - 8, 7, r3);
        embedHorizontalSeparationPattern(0, r3.getWidth() - 8, r3);
        embedVerticalSeparationPattern(7, 0, r3);
        embedVerticalSeparationPattern(r3.getHeight() - 8, 0, r3);
        embedVerticalSeparationPattern(7, r3.getHeight() - 7, r3);
    }

    private static void embedTimingPatterns(ByteMatrix r6) {
        int r1 = 8;
    L4:
        if (r1 >= (r6.getWidth() - 8)) goto L12;
        int r2 = r1 + 1;
        int r3 = r2 % 2;
        if (isEmpty(r6.get(r1, 6)) == false) goto L9;
        r6.set(r1, 6, r3);
    L9:
        if (isEmpty(r6.get(6, r1)) == false) goto L11;
        r6.set(6, r1, r3);
    L11:
        r1 = r2;
        goto L4
    }

    public static void embedTypeInfo(ErrorCorrectionLevel r5, int r6, ByteMatrix r7) {
        BitArray r0 = new BitArray();
        makeTypeInfoBits(r5, r6, r0);
        int r62 = 0;
    L4:
        if (r62 >= r0.getSize()) goto L10;
        boolean r1 = r0.get((r0.getSize() - 1) - r62);
        int[] r3 = TYPE_INFO_COORDINATES[r62];
        r7.set(r3[0], r3[1], r1);
        int r32 = 8;
        if (r62 >= 8) goto L8;
        int r2 = 8;
        r32 = (r7.getWidth() - r62) - 1;
    L9:
        r7.set(r32, r2, r1);
        r62 = r62 + 1;
        goto L4
    L8:
        r2 = (r7.getHeight() - 7) + (r62 - 8);
        goto L9
    }

    private static void embedVerticalSeparationPattern(int r4, int r5, ByteMatrix r6) {
        int r1 = 0;
    L4:
        if (r1 >= 7) goto L10;
        int r2 = r5 + r1;
        if (isEmpty(r6.get(r4, r2)) == false) goto L9;
        r6.set(r4, r2, 0);
        r1 = r1 + 1;
        goto L4
    L9:
        throw new WriterException();
    }

    public static int findMSBSet(int r0) {
        return 32 - Integer.numberOfLeadingZeros(r0);
    }

    private static boolean isEmpty(int r1) {
        if (r1 != (-1)) goto L6;
        return true;
    L6:
        return false;
    }

    public static void makeTypeInfoBits(ErrorCorrectionLevel r1, int r2, BitArray r3) {
        if (QRCode.isValidMaskPattern(r2) == false) goto L10;
        int r12 = (r1.getBits() << 3) | r2;
        r3.appendBits(r12, 5);
        r3.appendBits(calculateBCHCode(r12, 1335), 10);
        BitArray r13 = new BitArray();
        r13.appendBits(21522, 15);
        r3.xor(r13);
        if (r3.getSize() != 15) goto L8;
        return;
    L8:
        throw new WriterException("should not happen but we got: " + r3.getSize());
    L10:
        throw new WriterException("Invalid mask pattern");
    }

    public static void makeVersionInfoBits(Version r2, BitArray r3) {
        r3.appendBits(r2.getVersionNumber(), 6);
        r3.appendBits(calculateBCHCode(r2.getVersionNumber(), 7973), 12);
        if (r3.getSize() != 18) goto L6;
        return;
    L6:
        throw new WriterException("should not happen but we got: " + r3.getSize());
    }

    private static void maybeEmbedPositionAdjustmentPatterns(Version r8, ByteMatrix r9) {
        if (r8.getVersionNumber() < 2) goto L18;
        int[] r82 = POSITION_ADJUSTMENT_PATTERN_COORDINATE_TABLE[r8.getVersionNumber() - 1];
        int r0 = r82.length;
        int r2 = 0;
    L6:
        if (r2 >= r0) goto L25;
        int r3 = r82[r2];
        if (r3 < 0) goto L17;
        int r4 = r82.length;
        int r5 = 0;
    L10:
        if (r5 >= r4) goto L17;
        int r6 = r82[r5];
        if (r6 < 0) goto L16;
        if (isEmpty(r9.get(r6, r3)) == false) goto L16;
        embedPositionAdjustmentPattern(r6 - 2, r3 - 2, r9);
    L16:
        r5 = r5 + 1;
    L17:
        r2 = r2 + 1;
        goto L6
    L25:
        return;
    }

    public static void maybeEmbedVersionInfo(Version r6, ByteMatrix r7) {
        if (r6.getVersionNumber() < 7) goto L13;
        BitArray r0 = new BitArray();
        makeVersionInfoBits(r6, r0);
        int r1 = 17;
        int r2 = 0;
    L7:
        if (r2 >= 6) goto L16;
        int r3 = 0;
    L10:
        if (r3 >= 3) goto L12;
        boolean r4 = r0.get(r1);
        r1 = r1 - 1;
        r7.set(r2, (r7.getHeight() - 11) + r3, r4);
        r7.set((r7.getHeight() - 11) + r3, r2, r4);
        r3 = r3 + 1;
        goto L10
    L12:
        r2 = r2 + 1;
        goto L7
    L16:
        return;
    }
}
