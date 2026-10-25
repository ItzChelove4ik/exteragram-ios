package com.google.zxing;

import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public final class RGBLuminanceSource extends GrayscaleLuminanceSource {
    public RGBLuminanceSource(int r1, int r2, int[] r3) {
        super(r1, r2, toGrayscale(r1, r2, r3));
    }

    private static byte[] toGrayscale(int r4, int r5, int[] r6) {
        int r42 = r4 * r5;
        if (r6 != null) goto L5;
    L10:
        f$$ExternalSyntheticBUOutline1.m("Pixel array length is less than width * height");
        return null;
    L5:
        if (r6.length < r42) goto L10;
        byte[] r52 = new byte[r42];
        int r0 = 0;
    L7:
        if (r0 >= r42) goto L9;
        int r1 = r6[r0];
        int r2 = (r1 >> 16) & 255;
        int r3 = (r1 >> 7) & 510;
        int r22 = r2 + r3;
        r52[r0] = (byte) ((r22 + (r1 & 255)) / 4);
        r0 = r0 + 1;
        goto L7
    L9:
        return r52;
    }
}
