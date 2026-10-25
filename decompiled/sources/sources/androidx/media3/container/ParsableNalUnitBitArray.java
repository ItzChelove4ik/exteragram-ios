package androidx.media3.container;

import androidx.media3.common.util.Assertions;
import kotlin.UByte;

/* loaded from: classes3.dex */
public final class ParsableNalUnitBitArray {
    private int bitOffset;
    private int byteLimit;
    private int byteOffset;
    private byte[] data;

    public ParsableNalUnitBitArray(byte[] r1, int r2, int r3) {
        reset(r1, r2, r3);
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

    private int readExpGolombCodeNum() {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (readBit() == true) goto L6;
        r1 = r1 + 1;
        goto L4
    L6:
        int r3 = (1 << r1) - 1;
        if (r1 <= 0) goto L10;
        r0 = readBits(r1);
    L10:
        return r3 + r0;
    }

    private boolean shouldSkipByte(int r3) {
        if (2 <= r3) goto L5;
        return false;
    L5:
        if (r3 >= this.byteLimit) goto L15;
        byte[] r2 = this.data;
        if (r2[r3] == 3) goto L9;
        return false;
    L9:
        if (r2[r3 - 2] == 0) goto L11;
        return false;
    L11:
        if (r2[r3 - 1] != 0) goto L18;
        return true;
    L18:
        return false;
    L15:
        return false;
    }

    public void byteAlign() {
        int r0 = this.bitOffset;
        if (r0 <= 0) goto L6;
        skipBits(8 - r0);
        return;
    }

    public boolean canReadBits(int r5) {
        int r0 = this.byteOffset;
        int r1 = r5 / 8;
        int r2 = r0 + r1;
        int r3 = (this.bitOffset + r5) - (r1 * 8);
        if (r3 <= 7) goto L6;
        r2 = r2 + 1;
        r3 = r3 - 8;
    L6:
        r0 = r0 + 1;
        if (r0 > r2) goto L13;
        if (r2 >= this.byteLimit) goto L13;
        if (shouldSkipByte(r0) == false) goto L6;
        r2 = r2 + 1;
        r0 = r0 + 2;
    L13:
        int r4 = this.byteLimit;
        if (r2 < r4) goto L20;
        if (r2 != r4) goto L18;
        if (r3 == 0) goto L20;
        return false;
    L18:
        return false;
    L20:
        return true;
    }

    public boolean canReadExpGolombCodedNum() {
        int r0 = this.byteOffset;
        int r1 = this.bitOffset;
        int r3 = 0;
    L4:
        if (this.byteOffset >= this.byteLimit) goto L9;
        if (readBit() == true) goto L9;
        r3 = r3 + 1;
    L9:
        if (this.byteOffset != this.byteLimit) goto L11;
        boolean r4 = true;
    L12:
        this.byteOffset = r0;
        this.bitOffset = r1;
        if (r4 == false) goto L15;
    L17:
        return false;
    L15:
        if (canReadBits((r3 * 2) + 1) == false) goto L17;
        return true;
    L11:
        r4 = false;
        goto L12
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

    public int readBits(int r10) {
        this.bitOffset += r10;
        int r1 = 0;
    L3:
        int r2 = this.bitOffset;
        int r3 = 2;
        if (r2 <= 8) goto L10;
        int r22 = r2 - 8;
        this.bitOffset = r22;
        byte[] r5 = this.data;
        int r6 = this.byteOffset;
        r1 = r1 | ((r5[r6] & UByte.MAX_VALUE) << r22);
        if (shouldSkipByte(r6 + 1) == true) goto L9;
        r3 = 1;
    L9:
        this.byteOffset = r6 + r3;
        goto L3
    L10:
        byte[] r62 = this.data;
        int r7 = this.byteOffset;
        int r102 = ((-1) >>> (32 - r10)) & (r1 | ((r62[r7] & UByte.MAX_VALUE) >> (8 - r2)));
        if (r2 != 8) goto L17;
        this.bitOffset = 0;
        if (shouldSkipByte(r7 + 1) == true) goto L16;
        r3 = 1;
    L16:
        this.byteOffset = r7 + r3;
    L17:
        assertValidOffset();
        return r102;
    }

    public int readSignedExpGolombCodedInt() {
        int r2 = readExpGolombCodeNum();
        if ((r2 % 2) != 0) goto L5;
        int r0 = -1;
    L7:
        return r0 * ((r2 + 1) / 2);
    L5:
        r0 = 1;
        goto L7
    }

    public int readUnsignedExpGolombCodedInt() {
        return readExpGolombCodeNum();
    }

    public void reset(byte[] r1, int r2, int r3) {
        this.data = r1;
        this.byteOffset = r2;
        this.byteLimit = r3;
        this.bitOffset = 0;
        assertValidOffset();
    }

    public void skipBit() {
        int r1 = 1;
        int r0 = this.bitOffset + 1;
        this.bitOffset = r0;
        if (r0 != 8) goto L8;
        this.bitOffset = 0;
        int r02 = this.byteOffset;
        if (shouldSkipByte(r02 + 1) == false) goto L7;
        r1 = 2;
    L7:
        this.byteOffset = r02 + r1;
    L8:
        assertValidOffset();
    }

    public void skipBits(int r5) {
        int r0 = this.byteOffset;
        int r1 = r5 / 8;
        int r2 = r0 + r1;
        this.byteOffset = r2;
        int r3 = this.bitOffset + (r5 - (r1 * 8));
        this.bitOffset = r3;
        if (r3 <= 7) goto L5;
        this.byteOffset = r2 + 1;
        this.bitOffset = r3 - 8;
    L5:
        r0 = r0 + 1;
        if (r0 > this.byteOffset) goto L10;
        if (shouldSkipByte(r0) == false) goto L5;
        this.byteOffset++;
        r0 = r0 + 2;
        goto L5
    L10:
        assertValidOffset();
    }
}
