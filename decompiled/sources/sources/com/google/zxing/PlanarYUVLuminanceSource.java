package com.google.zxing;

import c.f$$ExternalSyntheticBUOutline1;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class PlanarYUVLuminanceSource extends LuminanceSource {
    private final int dataHeight;
    private final int dataWidth;
    private final int left;
    private final int top;
    private final byte[] yuvData;

    public PlanarYUVLuminanceSource(byte[] r2, int r3, int r4, int r5, int r6, int r7, int r8, boolean r9) {
        super(r7, r8);
        if ((r5 + r7) <= r3) goto L5;
    L10:
        f$$ExternalSyntheticBUOutline1.m("Crop rectangle does not fit within image data.");
        throw null;
    L5:
        if ((r6 + r8) > r4) goto L10;
        this.yuvData = r2;
        this.dataWidth = r3;
        this.dataHeight = r4;
        this.left = r5;
        this.top = r6;
        if (r9 == false) goto L12;
        reverseHorizontal(r7, r8);
        return;
    }

    private void reverseHorizontal(int r9, int r10) {
        byte[] r0 = this.yuvData;
        int r1 = (this.top * this.dataWidth) + this.left;
        int r2 = 0;
    L3:
        if (r2 >= r10) goto L8;
        int r3 = (r9 / 2) + r1;
        int r4 = (r1 + r9) - 1;
        int r5 = r1;
    L5:
        if (r5 >= r3) goto L7;
        byte r6 = r0[r5];
        r0[r5] = r0[r4];
        r0[r4] = r6;
        r5 = r5 + 1;
        r4 = r4 - 1;
        goto L5
    L7:
        r2 = r2 + 1;
        r1 = r1 + this.dataWidth;
        goto L3
    }

    @Override // com.google.zxing.LuminanceSource
    public byte[] getMatrix() {
        int r0 = getWidth();
        int r1 = getHeight();
        int r2 = this.dataWidth;
        if (r0 == r2) goto L5;
    L8:
        int r3 = r0 * r1;
        byte[] r4 = new byte[r3];
        int r5 = (this.top * r2) + this.left;
        int r6 = 0;
        if (r0 != r2) goto L12;
        System.arraycopy(this.yuvData, r5, r4, 0, r3);
        return r4;
    L12:
        if (r6 >= r1) goto L14;
        byte[] r32 = this.yuvData;
        System.arraycopy(r32, r5, r4, r6 * r0, r0);
        r5 = r5 + this.dataWidth;
        r6 = r6 + 1;
        goto L12
    L14:
        return r4;
    L5:
        if (r1 != this.dataHeight) goto L8;
        return this.yuvData;
    }

    @Override // com.google.zxing.LuminanceSource
    public byte[] getRow(int r3, byte[] r4) {
        if (r3 >= 0) goto L4;
    L12:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Requested row is outside the image: ", r3);
        return null;
    L4:
        if (r3 >= getHeight()) goto L12;
        int r0 = getWidth();
        if (r4 != null) goto L8;
    L9:
        r4 = new byte[r0];
    L10:
        int r32 = ((r3 + this.top) * this.dataWidth) + this.left;
        System.arraycopy(this.yuvData, r32, r4, 0, r0);
        return r4;
    L8:
        if (r4.length >= r0) goto L10;
        goto L9
    }
}
