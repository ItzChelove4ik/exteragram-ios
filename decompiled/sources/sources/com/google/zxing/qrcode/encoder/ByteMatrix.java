package com.google.zxing.qrcode.encoder;

import java.lang.reflect.Array;
import java.util.Arrays;

/* loaded from: classes5.dex */
public final class ByteMatrix {
    private final byte[][] bytes;
    private final int height;
    private final int width;

    public ByteMatrix(int r3, int r4) {
        this.bytes = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{r4, r3});
        this.width = r3;
        this.height = r4;
    }

    public void clear(byte r4) {
        byte[][] r3 = this.bytes;
        int r0 = r3.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        Arrays.fill(r3[r1], r4);
        r1 = r1 + 1;
        goto L3
    }

    public byte get(int r1, int r2) {
        return this.bytes[r2][r1];
    }

    public byte[][] getArray() {
        return this.bytes;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public void set(int r1, int r2, int r3) {
        this.bytes[r2][r1] = (byte) r3;
    }

    public void set(int r1, int r2, boolean r3) {
        this.bytes[r2][r1] = r3 ? 1 : 0;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder(((this.width * 2) * this.height) + 2);
        int r2 = 0;
    L4:
        if (r2 >= this.height) goto L18;
        byte[] r3 = this.bytes[r2];
        int r4 = 0;
    L7:
        if (r4 >= this.width) goto L16;
        byte r5 = r3[r4];
        if (r5 != 0) goto L11;
        r0.append(" 0");
    L15:
        r4 = r4 + 1;
        goto L7
    L11:
        if (r5 == 1) goto L13;
        r0.append("  ");
        goto L15
    L13:
        r0.append(" 1");
        goto L15
    L16:
        r0.append('\n');
        r2 = r2 + 1;
        goto L4
    L18:
        return r0.toString();
    }
}
