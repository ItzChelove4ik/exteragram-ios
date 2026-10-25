package com.google.zxing.qrcode.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;

/* loaded from: classes5.dex */
final class BitMatrixParser {
    private final BitMatrix bitMatrix;
    private boolean mirror;
    private FormatInformation parsedFormatInfo;
    private Version parsedVersion;

    public BitMatrixParser(BitMatrix r3) {
        int r0 = r3.getHeight();
        if (r0 < 21) goto L9;
        if ((r0 & 3) != 1) goto L9;
        this.bitMatrix = r3;
        return;
    L9:
        throw FormatException.getFormatInstance();
    }

    private int copyBit(int r2, int r3, int r4) {
        boolean r0 = this.mirror;
        BitMatrix r1 = this.bitMatrix;
        if (r0 == false) goto L5;
        boolean r12 = r1.get(r3, r2);
    L6:
        if (r12 == false) goto L10;
        return (r4 << 1) | 1;
    L10:
        return r4 << 1;
    L5:
        r12 = r1.get(r2, r3);
        goto L6
    }

    public void mirror() {
        int r0 = 0;
    L4:
        if (r0 >= this.bitMatrix.getWidth()) goto L13;
        int r1 = r0 + 1;
        int r2 = r1;
    L7:
        if (r2 >= this.bitMatrix.getHeight()) goto L12;
        if (this.bitMatrix.get(r0, r2) == this.bitMatrix.get(r2, r0)) goto L11;
        this.bitMatrix.flip(r2, r0);
        this.bitMatrix.flip(r0, r2);
    L11:
        r2 = r2 + 1;
        goto L7
    L12:
        r0 = r1;
        goto L4
    }

    public byte[] readCodewords() {
        FormatInformation r1 = readFormatInformation();
        Version r2 = readVersion();
        DataMask r12 = DataMask.values()[r1.getDataMask()];
        int r3 = this.bitMatrix.getHeight();
        r12.unmaskBitMatrix(this.bitMatrix, r3);
        BitMatrix r13 = r2.buildFunctionPattern();
        byte[] r4 = new byte[r2.getTotalCodewords()];
        int r5 = r3 - 1;
        boolean r6 = true;
        int r8 = r5;
        int r9 = 0;
        int r10 = 0;
        int r11 = 0;
    L3:
        if (r8 <= 0) goto L27;
        if (r8 != 6) goto L7;
        r8 = r8 - 1;
    L7:
        int r122 = 0;
    L8:
        if (r122 >= r3) goto L25;
        if (r6 == false) goto L11;
        int r132 = r5 - r122;
    L12:
        int r14 = 0;
    L14:
        if (r14 >= 2) goto L24;
        int r15 = r8 - r14;
        if (r13.get(r15, r132) == true) goto L23;
        r10 = r10 + 1;
        r11 = r11 << 1;
        if (this.bitMatrix.get(r15, r132) == false) goto L21;
        r11 = r11 | 1;
    L21:
        if (r10 != 8) goto L23;
        r4[r9] = (byte) r11;
        r9 = r9 + 1;
        r10 = 0;
        r11 = 0;
    L23:
        r14 = r14 + 1;
        goto L14
    L24:
        r122 = r122 + 1;
        goto L8
    L11:
        r132 = r122;
        goto L12
    L25:
        r6 = !r6;
        r8 = r8 - 2;
        goto L3
    L27:
        if (r9 != r2.getTotalCodewords()) goto L30;
        return r4;
    L30:
        throw FormatException.getFormatInstance();
    }

    public FormatInformation readFormatInformation() {
        FormatInformation r0 = this.parsedFormatInfo;
        if (r0 == null) goto L5;
        return r0;
    L5:
        int r02 = 0;
        int r1 = 0;
        int r2 = 0;
    L7:
        if (r1 >= 6) goto L9;
        r2 = copyBit(r1, 8, r2);
        r1 = r1 + 1;
        goto L7
    L9:
        int r12 = copyBit(8, 7, copyBit(8, 8, copyBit(7, 8, r2)));
        int r22 = 5;
    L10:
        if (r22 < 0) goto L12;
        r12 = copyBit(8, r22, r12);
        r22 = r22 - 1;
        goto L10
    L12:
        int r23 = this.bitMatrix.getHeight();
        int r3 = r23 - 7;
        int r5 = r23 - 1;
    L13:
        if (r5 < r3) goto L15;
        r02 = copyBit(8, r5, r02);
        r5 = r5 - 1;
        goto L13
    L15:
        int r32 = r23 - 8;
    L16:
        if (r32 >= r23) goto L18;
        r02 = copyBit(r32, 8, r02);
        r32 = r32 + 1;
        goto L16
    L18:
        FormatInformation r03 = FormatInformation.decodeFormatInformation(r12, r02);
        this.parsedFormatInfo = r03;
        if (r03 == null) goto L22;
        return r03;
    L22:
        throw FormatException.getFormatInstance();
    }

    public Version readVersion() {
        Version r0 = this.parsedVersion;
        if (r0 == null) goto L5;
        return r0;
    L5:
        int r02 = this.bitMatrix.getHeight();
        int r1 = (r02 - 17) / 4;
        if (r1 <= 6) goto L8;
        int r12 = r02 - 11;
        int r2 = 5;
        int r3 = 0;
        int r4 = 5;
        int r5 = 0;
    L10:
        if (r4 < 0) goto L15;
        int r6 = r02 - 9;
    L12:
        if (r6 < r12) goto L14;
        r5 = copyBit(r6, r4, r5);
        r6 = r6 - 1;
        goto L12
    L14:
        r4 = r4 - 1;
        goto L10
    L15:
        Version r42 = Version.decodeVersionInformation(r5);
        if (r42 == null) goto L21;
        if (r42.getDimensionForVersion() != r02) goto L21;
        this.parsedVersion = r42;
        return r42;
    L21:
        if (r2 < 0) goto L26;
        int r43 = r02 - 9;
    L23:
        if (r43 < r12) goto L25;
        r3 = copyBit(r2, r43, r3);
        r43 = r43 - 1;
        goto L23
    L25:
        r2 = r2 - 1;
        goto L21
    L26:
        Version r13 = Version.decodeVersionInformation(r3);
        if (r13 == null) goto L33;
        if (r13.getDimensionForVersion() != r02) goto L33;
        this.parsedVersion = r13;
        return r13;
    L33:
        throw FormatException.getFormatInstance();
    L8:
        return Version.getVersionForNumber(r1);
    }

    public void remask() {
        if (this.parsedFormatInfo != null) goto L5;
        return;
    L5:
        DataMask r0 = DataMask.values()[this.parsedFormatInfo.getDataMask()];
        int r1 = this.bitMatrix.getHeight();
        r0.unmaskBitMatrix(this.bitMatrix, r1);
    }

    public void setMirror(boolean r2) {
        this.parsedVersion = null;
        this.parsedFormatInfo = null;
        this.mirror = r2;
    }
}
