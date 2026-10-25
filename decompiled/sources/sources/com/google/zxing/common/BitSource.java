package com.google.zxing.common;

import c.f$$ExternalSyntheticBUOutline1;
import kotlin.UByte;

/* loaded from: classes5.dex */
public final class BitSource {
    private int bitOffset;
    private int byteOffset;
    private final byte[] bytes;

    public BitSource(byte[] r1) {
        this.bytes = r1;
    }

    public int available() {
        return ((this.bytes.length - this.byteOffset) * 8) - this.bitOffset;
    }

    public int readBits(int r10) {
        if (r10 >= 1) goto L5;
    L21:
        f$$ExternalSyntheticBUOutline1.m(String.valueOf(r10));
        return 0;
    L5:
        if (r10 > 32) goto L21;
        if (r10 > available()) goto L21;
        int r1 = this.bitOffset;
        int r2 = 0;
        if (r1 <= 0) goto L14;
        int r12 = 8 - r1;
        int r5 = Math.min(r10, r12);
        int r13 = r12 - r5;
        byte[] r7 = this.bytes;
        int r8 = this.byteOffset;
        int r14 = (((255 >> (8 - r5)) << r13) & r7[r8]) >> r13;
        r10 = r10 - r5;
        int r6 = this.bitOffset + r5;
        this.bitOffset = r6;
        if (r6 != 8) goto L13;
        this.bitOffset = 0;
        this.byteOffset = r8 + 1;
    L13:
        r2 = r14;
    L14:
        if (r10 <= 0) goto L20;
    L15:
        if (r10 < 8) goto L17;
        int r15 = r2 << 8;
        byte[] r22 = this.bytes;
        int r52 = this.byteOffset;
        r2 = (r22[r52] & UByte.MAX_VALUE) | r15;
        this.byteOffset = r52 + 1;
        r10 = r10 - 8;
        goto L15
    L17:
        if (r10 <= 0) goto L20;
        int r0 = 8 - r10;
        int r02 = ((((255 >> r0) << r0) & this.bytes[this.byteOffset]) >> r0) | (r2 << r10);
        this.bitOffset += r10;
        return r02;
    L20:
        return r2;
    }
}
