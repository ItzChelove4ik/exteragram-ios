package androidx.media3.extractor;

import androidx.media3.common.util.Assertions;
import kotlin.UByte;

/* loaded from: classes4.dex */
public final class VorbisBitArray {
    private int bitOffset;
    private final int byteLimit;
    private int byteOffset;
    private final byte[] data;

    public VorbisBitArray(byte[] r1) {
        this.data = r1;
        this.byteLimit = r1.length;
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

    public int getPosition() {
        return (this.byteOffset * 8) + this.bitOffset;
    }

    public boolean readBit() {
        if ((((this.data[this.byteOffset] & UByte.MAX_VALUE) >> this.bitOffset) & 1) != 1) goto L5;
        boolean r0 = true;
    L6:
        skipBits(1);
        return r0;
    L5:
        r0 = false;
        goto L6
    }

    public int readBits(int r7) {
        int r0 = this.byteOffset;
        int r1 = Math.min(r7, 8 - this.bitOffset);
        int r3 = r0 + 1;
        int r02 = ((this.data[r0] & UByte.MAX_VALUE) >> this.bitOffset) & (255 >> (8 - r1));
    L3:
        if (r1 >= r7) goto L5;
        int r5 = r3 + 1;
        r02 = r02 | ((this.data[r3] & UByte.MAX_VALUE) << r1);
        r1 = r1 + 8;
        r3 = r5;
        goto L3
    L5:
        int r03 = r02 & ((-1) >>> (32 - r7));
        skipBits(r7);
        return r03;
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
}
