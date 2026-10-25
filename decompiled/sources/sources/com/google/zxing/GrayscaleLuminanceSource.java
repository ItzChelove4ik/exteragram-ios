package com.google.zxing;

import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class GrayscaleLuminanceSource extends LuminanceSource {
    private final int dataHeight;
    private final int dataWidth;
    private final int left;
    private final byte[] luminances;
    private final int top;

    public GrayscaleLuminanceSource(int r1, int r2, byte[] r3) {
        super(r1, r2);
        this.luminances = r3;
        this.dataWidth = r1;
        this.dataHeight = r2;
        this.left = 0;
        this.top = 0;
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
        System.arraycopy(this.luminances, r5, r4, 0, r3);
        return r4;
    L12:
        if (r6 >= r1) goto L14;
        byte[] r32 = this.luminances;
        System.arraycopy(r32, r5, r4, r6 * r0, r0);
        r5 = r5 + this.dataWidth;
        r6 = r6 + 1;
        goto L12
    L14:
        return r4;
    L5:
        if (r1 != this.dataHeight) goto L8;
        return this.luminances;
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
        System.arraycopy(this.luminances, r32, r4, 0, r0);
        return r4;
    L8:
        if (r4.length >= r0) goto L10;
        goto L9
    }
}
