package androidx.media3.common.util;

import java.nio.charset.Charset;
import kotlin.UByte;

/* loaded from: classes3.dex */
public final class ParsableBitArray {
    private int bitOffset;
    private int byteLimit;
    private int byteOffset;
    public byte[] data;

    public ParsableBitArray() {
        this.data = Util.EMPTY_BYTE_ARRAY;
    }

    public ParsableBitArray(byte[] r2) {
        this(r2, r2.length);
    }

    public ParsableBitArray(byte[] r1, int r2) {
        this.data = r1;
        this.byteLimit = r2;
    }

    private void assertValidOffset() {
        int r0 = this.byteOffset;
        if (r0 < 0) goto L10;
        int r1 = this.byteLimit;
        if (r0 < r1) goto L9;
        if (r0 != r1) goto L10;
        if (this.bitOffset != 0) goto L10;
    L9:
        boolean r2 = true;
    L11:
        Assertions.checkState(r2);
        return;
    L10:
        r2 = false;
        goto L11
    }

    public int bitsLeft() {
        return ((this.byteLimit - this.byteOffset) * 8) - this.bitOffset;
    }

    public void byteAlign() {
        if (this.bitOffset != 0) goto L5;
        return;
    L5:
        this.bitOffset = 0;
        this.byteOffset++;
        assertValidOffset();
    }

    public int getBytePosition() {
        if (this.bitOffset != 0) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkState(r0);
        return this.byteOffset;
    L5:
        r0 = false;
        goto L6
    }

    public int getPosition() {
        return (this.byteOffset * 8) + this.bitOffset;
    }

    public void putInt(int r9, int r10) {
        if (r10 >= 32) goto L5;
        r9 = r9 & ((1 << r10) - 1);
    L5:
        int r0 = Math.min(8 - this.bitOffset, r10);
        int r3 = this.bitOffset;
        int r4 = (8 - r3) - r0;
        byte[] r5 = this.data;
        int r6 = this.byteOffset;
        byte r32 = (byte) (((65280 >> r3) | ((1 << r4) - 1)) & r5[r6]);
        r5[r6] = r32;
        int r02 = r10 - r0;
        r5[r6] = (byte) (r32 | ((r9 >>> r02) << r4));
        int r62 = r6 + 1;
    L6:
        byte[] r33 = this.data;
        if (r02 <= 8) goto L9;
        r33[r62] = (byte) (r9 >>> (r02 - 8));
        r02 = r02 - 8;
        r62 = r62 + 1;
        goto L6
    L9:
        int r2 = 8 - r02;
        byte r42 = (byte) (r33[r62] & ((1 << r2) - 1));
        r33[r62] = r42;
        r33[r62] = (byte) (((r9 & ((1 << r02) - 1)) << r2) | r42);
        skipBits(r10);
        assertValidOffset();
    }

    public boolean readBit() {
        if ((this.data[this.byteOffset] & (128 >> this.bitOffset)) == 0) goto L5;
        boolean r0 = true;
    L6:
        skipBit();
        return r0;
    L5:
        r0 = false;
        goto L6
    }

    public int readBits(int r8) {
        if (r8 != 0) goto L5;
        return 0;
    L5:
        this.bitOffset += r8;
        int r1 = 0;
    L6:
        int r2 = this.bitOffset;
        if (r2 <= 8) goto L9;
        int r22 = r2 - 8;
        this.bitOffset = r22;
        byte[] r3 = this.data;
        int r4 = this.byteOffset;
        this.byteOffset = r4 + 1;
        r1 = r1 | ((r3[r4] & UByte.MAX_VALUE) << r22);
        goto L6
    L9:
        byte[] r42 = this.data;
        int r5 = this.byteOffset;
        int r82 = ((-1) >>> (32 - r8)) & (r1 | ((r42[r5] & UByte.MAX_VALUE) >> (8 - r2)));
        if (r2 != 8) goto L12;
        this.bitOffset = 0;
        this.byteOffset = r5 + 1;
    L12:
        assertValidOffset();
        return r82;
    }

    public void readBits(byte[] r8, int r9, int r10) {
        int r0 = (r10 >> 3) + r9;
    L4:
        if (r9 >= r0) goto L6;
        byte[] r3 = this.data;
        int r4 = this.byteOffset;
        int r5 = r4 + 1;
        this.byteOffset = r5;
        byte r42 = r3[r4];
        int r6 = this.bitOffset;
        byte r43 = (byte) (r42 << r6);
        r8[r9] = r43;
        r8[r9] = (byte) (((255 & r3[r5]) >> (8 - r6)) | r43);
        r9 = r9 + 1;
        goto L4
    L6:
        int r92 = r10 & 7;
        if (r92 != 0) goto L9;
        return;
    L9:
        byte r102 = (byte) (r8[r0] & (255 >> r92));
        r8[r0] = r102;
        int r32 = this.bitOffset;
        if ((r32 + r92) <= 8) goto L12;
        byte[] r44 = this.data;
        int r52 = this.byteOffset;
        this.byteOffset = r52 + 1;
        r8[r0] = (byte) (r102 | ((r44[r52] & UByte.MAX_VALUE) << r32));
        this.bitOffset = r32 - 8;
    L12:
        int r103 = this.bitOffset + r92;
        this.bitOffset = r103;
        byte[] r33 = this.data;
        int r45 = this.byteOffset;
        int r1 = (255 & r33[r45]) >> (8 - r103);
        byte r93 = (byte) (r1 << (8 - r92));
        r8[r0] = (byte) (r93 | r8[r0]);
        if (r103 != 8) goto L15;
        this.bitOffset = 0;
        this.byteOffset = r45 + 1;
    L15:
        assertValidOffset();
    }

    public long readBitsToLong(int r2) {
        if (r2 > 32) goto L7;
        return Util.toUnsignedLong(readBits(r2));
    L7:
        return Util.toLong(readBits(r2 - 32), readBits(32));
    }

    public void readBytes(byte[] r3, int r4, int r5) {
        if (this.bitOffset != 0) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkState(r0);
        System.arraycopy(this.data, this.byteOffset, r3, r4, r5);
        this.byteOffset += r5;
        assertValidOffset();
        return;
    L5:
        r0 = false;
        goto L6
    }

    public String readBytesAsString(int r3, Charset r4) {
        byte[] r0 = new byte[r3];
        readBytes(r0, 0, r3);
        return new String(r0, r4);
    }

    public void reset(ParsableByteArray r3) {
        reset(r3.getData(), r3.limit());
        setPosition(r3.getPosition() * 8);
    }

    public void reset(byte[] r2) {
        reset(r2, r2.length);
    }

    public void reset(byte[] r1, int r2) {
        this.data = r1;
        this.byteOffset = 0;
        this.bitOffset = 0;
        this.byteLimit = r2;
    }

    public void setPosition(int r2) {
        int r0 = r2 / 8;
        this.byteOffset = r0;
        this.bitOffset = r2 - (r0 * 8);
        assertValidOffset();
    }

    public void skipBit() {
        int r0 = this.bitOffset + 1;
        this.bitOffset = r0;
        if (r0 != 8) goto L5;
        this.bitOffset = 0;
        this.byteOffset++;
    L5:
        assertValidOffset();
    }

    public void skipBits(int r4) {
        int r0 = r4 / 8;
        int r1 = this.byteOffset + r0;
        this.byteOffset = r1;
        int r2 = this.bitOffset + (r4 - (r0 * 8));
        this.bitOffset = r2;
        if (r2 <= 7) goto L5;
        this.byteOffset = r1 + 1;
        this.bitOffset = r2 - 8;
    L5:
        assertValidOffset();
    }

    public void skipBytes(int r2) {
        if (this.bitOffset != 0) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkState(r0);
        this.byteOffset += r2;
        assertValidOffset();
        return;
    L5:
        r0 = false;
        goto L6
    }
}
