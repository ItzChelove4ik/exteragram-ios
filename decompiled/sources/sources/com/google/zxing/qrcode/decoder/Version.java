package com.google.zxing.qrcode.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class Version {
    private static final Version[] VERSIONS = null;
    private static final int[] VERSION_DECODE_INFO = null;
    private final int[] alignmentPatternCenters;
    private final ECBlocks[] ecBlocks;
    private final int totalCodewords;
    private final int versionNumber;

    public static final class ECB {
        private final int count;
        private final int dataCodewords;

        public ECB(int r1, int r2) {
            this.count = r1;
            this.dataCodewords = r2;
        }

        public int getCount() {
            return this.count;
        }

        public int getDataCodewords() {
            return this.dataCodewords;
        }
    }

    public static final class ECBlocks {
        private final ECB[] ecBlocks;
        private final int ecCodewordsPerBlock;

        public ECBlocks(int r1, ECB... r2) {
            this.ecCodewordsPerBlock = r1;
            this.ecBlocks = r2;
        }

        public ECB[] getECBlocks() {
            return this.ecBlocks;
        }

        public int getECCodewordsPerBlock() {
            return this.ecCodewordsPerBlock;
        }

        public int getNumBlocks() {
            ECB[] r4 = this.ecBlocks;
            int r0 = r4.length;
            int r1 = 0;
            int r2 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r2 = r2 + r4[r1].getCount();
            r1 = r1 + 1;
            goto L3
        L5:
            return r2;
        }

        public int getTotalECCodewords() {
            return this.ecCodewordsPerBlock * getNumBlocks();
        }
    }

    static {
        VERSION_DECODE_INFO = new int[]{31892, 34236, 39577, 42195, 48118, 51042, 55367, 58893, 63784, 68472, 70749, 76311, 79154, 84390, 87683, 92361, 96236, 102084, 102881, 110507, 110734, 117786, 119615, 126325, 127568, 133589, 136944, 141498, 145311, 150283, 152622, 158308, 161089, 167017};
        VERSIONS = buildVersions();
    }

    private Version(int r5, int[] r6, ECBlocks... r7) {
        this.versionNumber = r5;
        this.alignmentPatternCenters = r6;
        this.ecBlocks = r7;
        int r52 = 0;
        int r62 = r7[0].getECCodewordsPerBlock();
        ECB[] r72 = r7[0].getECBlocks();
        int r0 = r72.length;
        int r1 = 0;
    L3:
        if (r52 >= r0) goto L5;
        ECB r2 = r72[r52];
        r1 = r1 + (r2.getCount() * (r2.getDataCodewords() + r62));
        r52 = r52 + 1;
        goto L3
    L5:
        this.totalCodewords = r1;
    }

    private static Version[] buildVersions() {
        return new Version[]{new Version(1, new int[0], new ECBlocks[]{new ECBlocks(7, new ECB[]{new ECB(1, 19)}), new ECBlocks(10, new ECB[]{new ECB(1, 16)}), new ECBlocks(13, new ECB[]{new ECB(1, 13)}), new ECBlocks(17, new ECB[]{new ECB(1, 9)})}), new Version(2, new int[]{6, 18}, new ECBlocks[]{new ECBlocks(10, new ECB[]{new ECB(1, 34)}), new ECBlocks(16, new ECB[]{new ECB(1, 28)}), new ECBlocks(22, new ECB[]{new ECB(1, 22)}), new ECBlocks(28, new ECB[]{new ECB(1, 16)})}), new Version(3, new int[]{6, 22}, new ECBlocks[]{new ECBlocks(15, new ECB[]{new ECB(1, 55)}), new ECBlocks(26, new ECB[]{new ECB(1, 44)}), new ECBlocks(18, new ECB[]{new ECB(2, 17)}), new ECBlocks(22, new ECB[]{new ECB(2, 13)})}), new Version(4, new int[]{6, 26}, new ECBlocks[]{new ECBlocks(20, new ECB[]{new ECB(1, 80)}), new ECBlocks(18, new ECB[]{new ECB(2, 32)}), new ECBlocks(26, new ECB[]{new ECB(2, 24)}), new ECBlocks(16, new ECB[]{new ECB(4, 9)})}), new Version(5, new int[]{6, 30}, new ECBlocks[]{new ECBlocks(26, new ECB[]{new ECB(1, 108)}), new ECBlocks(24, new ECB[]{new ECB(2, 43)}), new ECBlocks(18, new ECB[]{new ECB(2, 15), new ECB(2, 16)}), new ECBlocks(22, new ECB[]{new ECB(2, 11), new ECB(2, 12)})}), new Version(6, new int[]{6, 34}, new ECBlocks[]{new ECBlocks(18, new ECB[]{new ECB(2, 68)}), new ECBlocks(16, new ECB[]{new ECB(4, 27)}), new ECBlocks(24, new ECB[]{new ECB(4, 19)}), new ECBlocks(28, new ECB[]{new ECB(4, 15)})}), new Version(7, new int[]{6, 22, 38}, new ECBlocks[]{new ECBlocks(20, new ECB[]{new ECB(2, 78)}), new ECBlocks(18, new ECB[]{new ECB(4, 31)}), new ECBlocks(18, new ECB[]{new ECB(2, 14), new ECB(4, 15)}), new ECBlocks(26, new ECB[]{new ECB(4, 13), new ECB(1, 14)})}), new Version(8, new int[]{6, 24, 42}, new ECBlocks[]{new ECBlocks(24, new ECB[]{new ECB(2, 97)}), new ECBlocks(22, new ECB[]{new ECB(2, 38), new ECB(2, 39)}), new ECBlocks(22, new ECB[]{new ECB(4, 18), new ECB(2, 19)}), new ECBlocks(26, new ECB[]{new ECB(4, 14), new ECB(2, 15)})}), new Version(9, new int[]{6, 26, 46}, new ECBlocks[]{new ECBlocks(30, new ECB[]{new ECB(2, 116)}), new ECBlocks(22, new ECB[]{new ECB(3, 36), new ECB(2, 37)}), new ECBlocks(20, new ECB[]{new ECB(4, 16), new ECB(4, 17)}), new ECBlocks(24, new ECB[]{new ECB(4, 12), new ECB(4, 13)})}), new Version(10, new int[]{6, 28, 50}, new ECBlocks[]{new ECBlocks(18, new ECB[]{new ECB(2, 68), new ECB(2, 69)}), new ECBlocks(26, new ECB[]{new ECB(4, 43), new ECB(1, 44)}), new ECBlocks(24, new ECB[]{new ECB(6, 19), new ECB(2, 20)}), new ECBlocks(28, new ECB[]{new ECB(6, 15), new ECB(2, 16)})}), new Version(11, new int[]{6, 30, 54}, new ECBlocks[]{new ECBlocks(20, new ECB[]{new ECB(4, 81)}), new ECBlocks(30, new ECB[]{new ECB(1, 50), new ECB(4, 51)}), new ECBlocks(28, new ECB[]{new ECB(4, 22), new ECB(4, 23)}), new ECBlocks(24, new ECB[]{new ECB(3, 12), new ECB(8, 13)})}), new Version(12, new int[]{6, 32, 58}, new ECBlocks[]{new ECBlocks(24, new ECB[]{new ECB(2, 92), new ECB(2, 93)}), new ECBlocks(22, new ECB[]{new ECB(6, 36), new ECB(2, 37)}), new ECBlocks(26, new ECB[]{new ECB(4, 20), new ECB(6, 21)}), new ECBlocks(28, new ECB[]{new ECB(7, 14), new ECB(4, 15)})}), new Version(13, new int[]{6, 34, 62}, new ECBlocks[]{new ECBlocks(26, new ECB[]{new ECB(4, 107)}), new ECBlocks(22, new ECB[]{new ECB(8, 37), new ECB(1, 38)}), new ECBlocks(24, new ECB[]{new ECB(8, 20), new ECB(4, 21)}), new ECBlocks(22, new ECB[]{new ECB(12, 11), new ECB(4, 12)})}), new Version(14, new int[]{6, 26, 46, 66}, new ECBlocks[]{new ECBlocks(30, new ECB[]{new ECB(3, 115), new ECB(1, 116)}), new ECBlocks(24, new ECB[]{new ECB(4, 40), new ECB(5, 41)}), new ECBlocks(20, new ECB[]{new ECB(11, 16), new ECB(5, 17)}), new ECBlocks(24, new ECB[]{new ECB(11, 12), new ECB(5, 13)})}), new Version(15, new int[]{6, 26, 48, 70}, new ECBlocks[]{new ECBlocks(22, new ECB[]{new ECB(5, 87), new ECB(1, 88)}), new ECBlocks(24, new ECB[]{new ECB(5, 41), new ECB(5, 42)}), new ECBlocks(30, new ECB[]{new ECB(5, 24), new ECB(7, 25)}), new ECBlocks(24, new ECB[]{new ECB(11, 12), new ECB(7, 13)})}), new Version(16, new int[]{6, 26, 50, 74}, new ECBlocks[]{new ECBlocks(24, new ECB[]{new ECB(5, 98), new ECB(1, 99)}), new ECBlocks(28, new ECB[]{new ECB(7, 45), new ECB(3, 46)}), new ECBlocks(24, new ECB[]{new ECB(15, 19), new ECB(2, 20)}), new ECBlocks(30, new ECB[]{new ECB(3, 15), new ECB(13, 16)})}), new Version(17, new int[]{6, 30, 54, 78}, new ECBlocks[]{new ECBlocks(28, new ECB[]{new ECB(1, 107), new ECB(5, 108)}), new ECBlocks(28, new ECB[]{new ECB(10, 46), new ECB(1, 47)}), new ECBlocks(28, new ECB[]{new ECB(1, 22), new ECB(15, 23)}), new ECBlocks(28, new ECB[]{new ECB(2, 14), new ECB(17, 15)})}), new Version(18, new int[]{6, 30, 56, 82}, new ECBlocks[]{new ECBlocks(30, new ECB[]{new ECB(5, 120), new ECB(1, 121)}), new ECBlocks(26, new ECB[]{new ECB(9, 43), new ECB(4, 44)}), new ECBlocks(28, new ECB[]{new ECB(17, 22), new ECB(1, 23)}), new ECBlocks(28, new ECB[]{new ECB(2, 14), new ECB(19, 15)})}), new Version(19, new int[]{6, 30, 58, 86}, new ECBlocks[]{new ECBlocks(28, new ECB[]{new ECB(3, 113), new ECB(4, 114)}), new ECBlocks(26, new ECB[]{new ECB(3, 44), new ECB(11, 45)}), new ECBlocks(26, new ECB[]{new ECB(17, 21), new ECB(4, 22)}), new ECBlocks(26, new ECB[]{new ECB(9, 13), new ECB(16, 14)})}), new Version(20, new int[]{6, 34, 62, 90}, new ECBlocks[]{new ECBlocks(28, new ECB[]{new ECB(3, 107), new ECB(5, 108)}), new ECBlocks(26, new ECB[]{new ECB(3, 41), new ECB(13, 42)}), new ECBlocks(30, new ECB[]{new ECB(15, 24), new ECB(5, 25)}), new ECBlocks(28, new ECB[]{new ECB(15, 15), new ECB(10, 16)})}), new Version(21, new int[]{6, 28, 50, 72, 94}, new ECBlocks[]{new ECBlocks(28, new ECB[]{new ECB(4, 116), new ECB(4, 117)}), new ECBlocks(26, new ECB[]{new ECB(17, 42)}), new ECBlocks(28, new ECB[]{new ECB(17, 22), new ECB(6, 23)}), new ECBlocks(30, new ECB[]{new ECB(19, 16), new ECB(6, 17)})}), new Version(22, new int[]{6, 26, 50, 74, 98}, new ECBlocks[]{new ECBlocks(28, new ECB[]{new ECB(2, 111), new ECB(7, 112)}), new ECBlocks(28, new ECB[]{new ECB(17, 46)}), new ECBlocks(30, new ECB[]{new ECB(7, 24), new ECB(16, 25)}), new ECBlocks(24, new ECB[]{new ECB(34, 13)})}), new Version(23, new int[]{6, 30, 54, 78, 102}, new ECBlocks[]{new ECBlocks(30, new ECB[]{new ECB(4, 121), new ECB(5, 122)}), new ECBlocks(28, new ECB[]{new ECB(4, 47), new ECB(14, 48)}), new ECBlocks(30, new ECB[]{new ECB(11, 24), new ECB(14, 25)}), new ECBlocks(30, new ECB[]{new ECB(16, 15), new ECB(14, 16)})}), new Version(24, new int[]{6, 28, 54, 80, 106}, new ECBlocks[]{new ECBlocks(30, new ECB[]{new ECB(6, 117), new ECB(4, 118)}), new ECBlocks(28, new ECB[]{new ECB(6, 45), new ECB(14, 46)}), new ECBlocks(30, new ECB[]{new ECB(11, 24), new ECB(16, 25)}), new ECBlocks(30, new ECB[]{new ECB(30, 16), new ECB(2, 17)})}), new Version(25, new int[]{6, 32, 58, 84, 110}, new ECBlocks[]{new ECBlocks(26, new ECB[]{new ECB(8, 106), new ECB(4, 107)}), new ECBlocks(28, new ECB[]{new ECB(8, 47), new ECB(13, 48)}), new ECBlocks(30, new ECB[]{new ECB(7, 24), new ECB(22, 25)}), new ECBlocks(30, new ECB[]{new ECB(22, 15), new ECB(13, 16)})}), new Version(26, new int[]{6, 30, 58, 86, 114}, new ECBlocks[]{new ECBlocks(28, new ECB[]{new ECB(10, 114), new ECB(2, 115)}), new ECBlocks(28, new ECB[]{new ECB(19, 46), new ECB(4, 47)}), new ECBlocks(28, new ECB[]{new ECB(28, 22), new ECB(6, 23)}), new ECBlocks(30, new ECB[]{new ECB(33, 16), new ECB(4, 17)})}), new Version(27, new int[]{6, 34, 62, 90, 118}, new ECBlocks[]{new ECBlocks(30, new ECB[]{new ECB(8, 122), new ECB(4, 123)}), new ECBlocks(28, new ECB[]{new ECB(22, 45), new ECB(3, 46)}), new ECBlocks(30, new ECB[]{new ECB(8, 23), new ECB(26, 24)}), new ECBlocks(30, new ECB[]{new ECB(12, 15), new ECB(28, 16)})}), new Version(28, new int[]{6, 26, 50, 74, 98, 122}, new ECBlocks[]{new ECBlocks(30, new ECB[]{new ECB(3, 117), new ECB(10, 118)}), new ECBlocks(28, new ECB[]{new ECB(3, 45), new ECB(23, 46)}), new ECBlocks(30, new ECB[]{new ECB(4, 24), new ECB(31, 25)}), new ECBlocks(30, new ECB[]{new ECB(11, 15), new ECB(31, 16)})}), new Version(29, new int[]{6, 30, 54, 78, 102, 126}, new ECBlocks[]{new ECBlocks(30, new ECB[]{new ECB(7, 116), new ECB(7, 117)}), new ECBlocks(28, new ECB[]{new ECB(21, 45), new ECB(7, 46)}), new ECBlocks(30, new ECB[]{new ECB(1, 23), new ECB(37, 24)}), new ECBlocks(30, new ECB[]{new ECB(19, 15), new ECB(26, 16)})}), new Version(30, new int[]{6, 26, 52, 78, 104, 130}, new ECBlocks[]{new ECBlocks(30, new ECB[]{new ECB(5, 115), new ECB(10, 116)}), new ECBlocks(28, new ECB[]{new ECB(19, 47), new ECB(10, 48)}), new ECBlocks(30, new ECB[]{new ECB(15, 24), new ECB(25, 25)}), new ECBlocks(30, new ECB[]{new ECB(23, 15), new ECB(25, 16)})}), new Version(31, new int[]{6, 30, 56, 82, 108, 134}, new ECBlocks[]{new ECBlocks(30, new ECB[]{new ECB(13, 115), new ECB(3, 116)}), new ECBlocks(28, new ECB[]{new ECB(2, 46), new ECB(29, 47)}), new ECBlocks(30, new ECB[]{new ECB(42, 24), new ECB(1, 25)}), new ECBlocks(30, new ECB[]{new ECB(23, 15), new ECB(28, 16)})}), new Version(32, new int[]{6, 34, 60, 86, 112, 138}, new ECBlocks[]{new ECBlocks(30, new ECB[]{new ECB(17, 115)}), new ECBlocks(28, new ECB[]{new ECB(10, 46), new ECB(23, 47)}), new ECBlocks(30, new ECB[]{new ECB(10, 24), new ECB(35, 25)}), new ECBlocks(30, new ECB[]{new ECB(19, 15), new ECB(35, 16)})}), new Version(33, new int[]{6, 30, 58, 86, 114, 142}, new ECBlocks[]{new ECBlocks(30, new ECB[]{new ECB(17, 115), new ECB(1, 116)}), new ECBlocks(28, new ECB[]{new ECB(14, 46), new ECB(21, 47)}), new ECBlocks(30, new ECB[]{new ECB(29, 24), new ECB(19, 25)}), new ECBlocks(30, new ECB[]{new ECB(11, 15), new ECB(46, 16)})}), new Version(34, new int[]{6, 34, 62, 90, 118, 146}, new ECBlocks[]{new ECBlocks(30, new ECB[]{new ECB(13, 115), new ECB(6, 116)}), new ECBlocks(28, new ECB[]{new ECB(14, 46), new ECB(23, 47)}), new ECBlocks(30, new ECB[]{new ECB(44, 24), new ECB(7, 25)}), new ECBlocks(30, new ECB[]{new ECB(59, 16), new ECB(1, 17)})}), new Version(35, new int[]{6, 30, 54, 78, 102, 126, 150}, new ECBlocks[]{new ECBlocks(30, new ECB[]{new ECB(12, 121), new ECB(7, 122)}), new ECBlocks(28, new ECB[]{new ECB(12, 47), new ECB(26, 48)}), new ECBlocks(30, new ECB[]{new ECB(39, 24), new ECB(14, 25)}), new ECBlocks(30, new ECB[]{new ECB(22, 15), new ECB(41, 16)})}), new Version(36, new int[]{6, 24, 50, 76, 102, 128, 154}, new ECBlocks[]{new ECBlocks(30, new ECB[]{new ECB(6, 121), new ECB(14, 122)}), new ECBlocks(28, new ECB[]{new ECB(6, 47), new ECB(34, 48)}), new ECBlocks(30, new ECB[]{new ECB(46, 24), new ECB(10, 25)}), new ECBlocks(30, new ECB[]{new ECB(2, 15), new ECB(64, 16)})}), new Version(37, new int[]{6, 28, 54, 80, 106, 132, 158}, new ECBlocks[]{new ECBlocks(30, new ECB[]{new ECB(17, 122), new ECB(4, 123)}), new ECBlocks(28, new ECB[]{new ECB(29, 46), new ECB(14, 47)}), new ECBlocks(30, new ECB[]{new ECB(49, 24), new ECB(10, 25)}), new ECBlocks(30, new ECB[]{new ECB(24, 15), new ECB(46, 16)})}), new Version(38, new int[]{6, 32, 58, 84, 110, 136, 162}, new ECBlocks[]{new ECBlocks(30, new ECB[]{new ECB(4, 122), new ECB(18, 123)}), new ECBlocks(28, new ECB[]{new ECB(13, 46), new ECB(32, 47)}), new ECBlocks(30, new ECB[]{new ECB(48, 24), new ECB(14, 25)}), new ECBlocks(30, new ECB[]{new ECB(42, 15), new ECB(32, 16)})}), new Version(39, new int[]{6, 26, 54, 82, 110, 138, 166}, new ECBlocks[]{new ECBlocks(30, new ECB[]{new ECB(20, 117), new ECB(4, 118)}), new ECBlocks(28, new ECB[]{new ECB(40, 47), new ECB(7, 48)}), new ECBlocks(30, new ECB[]{new ECB(43, 24), new ECB(22, 25)}), new ECBlocks(30, new ECB[]{new ECB(10, 15), new ECB(67, 16)})}), new Version(40, new int[]{6, 30, 58, 86, 114, 142, 170}, new ECBlocks[]{new ECBlocks(30, new ECB[]{new ECB(19, 118), new ECB(6, 119)}), new ECBlocks(28, new ECB[]{new ECB(18, 47), new ECB(31, 48)}), new ECBlocks(30, new ECB[]{new ECB(34, 24), new ECB(34, 25)}), new ECBlocks(30, new ECB[]{new ECB(20, 15), new ECB(61, 16)})})};
    }

    public static Version decodeVersionInformation(int r5) {
        int r0 = Integer.MAX_VALUE;
        int r1 = 0;
        int r2 = 0;
    L3:
        int[] r3 = VERSION_DECODE_INFO;
        if (r1 >= r3.length) goto L14;
        int r32 = r3[r1];
        if (r32 == r5) goto L8;
        int r33 = FormatInformation.numBitsDiffering(r5, r32);
        if (r33 >= r0) goto L12;
        r2 = r1 + 7;
        r0 = r33;
    L12:
        r1 = r1 + 1;
        goto L3
    L8:
        return getVersionForNumber(r1 + 7);
    L14:
        if (r0 <= 3) goto L16;
        return null;
    L16:
        return getVersionForNumber(r2);
    }

    public static Version getProvisionalVersionForDimension(int r2) {
        if ((r2 % 4) != 1) goto L10;
        return getVersionForNumber((r2 - 17) / 4);
    L8:
        throw FormatException.getFormatInstance();
    L10:
        throw FormatException.getFormatInstance();
    }

    public static Version getVersionForNumber(int r2) {
        if (r2 >= 1) goto L5;
    L8:
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    L5:
        if (r2 > 40) goto L8;
        return VERSIONS[r2 - 1];
    }

    public BitMatrix buildFunctionPattern() {
        int r0 = getDimensionForVersion();
        BitMatrix r1 = new BitMatrix(r0);
        r1.setRegion(0, 0, 9, 9);
        int r4 = r0 - 8;
        r1.setRegion(r4, 0, 8, 9);
        r1.setRegion(0, r4, 9, 8);
        int r42 = this.alignmentPatternCenters.length;
        int r5 = 0;
    L3:
        if (r5 >= r42) goto L16;
        int r6 = this.alignmentPatternCenters[r5] - 2;
        int r7 = 0;
    L5:
        if (r7 >= r42) goto L15;
        if (r5 != 0) goto L11;
        if (r7 == 0) goto L14;
        if (r7 != (r42 - 1)) goto L11;
    L14:
        r7 = r7 + 1;
    L11:
        if (r5 != (r42 - 1)) goto L13;
        if (r7 == 0) goto L14;
    L13:
        r1.setRegion(this.alignmentPatternCenters[r7] - 2, r6, 5, 5);
        goto L14
    L15:
        r5 = r5 + 1;
        goto L3
    L16:
        int r43 = r0 - 17;
        r1.setRegion(6, 9, 1, r43);
        r1.setRegion(9, 6, r43, 1);
        if (this.versionNumber <= 6) goto L19;
        int r02 = r0 - 11;
        r1.setRegion(r02, 0, 3, 6);
        r1.setRegion(0, r02, 6, 3);
    L19:
        return r1;
    }

    public int[] getAlignmentPatternCenters() {
        return this.alignmentPatternCenters;
    }

    public int getDimensionForVersion() {
        return (this.versionNumber * 4) + 17;
    }

    public ECBlocks getECBlocksForLevel(ErrorCorrectionLevel r1) {
        return this.ecBlocks[r1.ordinal()];
    }

    public int getTotalCodewords() {
        return this.totalCodewords;
    }

    public int getVersionNumber() {
        return this.versionNumber;
    }

    public String toString() {
        return String.valueOf(this.versionNumber);
    }
}
