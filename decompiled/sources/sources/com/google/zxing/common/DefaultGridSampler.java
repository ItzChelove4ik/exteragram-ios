package com.google.zxing.common;

import com.google.zxing.NotFoundException;

/* loaded from: classes5.dex */
public final class DefaultGridSampler extends GridSampler {
    public DefaultGridSampler() {
    }

    @Override // com.google.zxing.common.GridSampler
    public BitMatrix sampleGrid(BitMatrix r8, int r9, int r10, PerspectiveTransform r11) {
        if (r9 <= 0) goto L20;
        if (r10 <= 0) goto L20;
        BitMatrix r7 = new BitMatrix(r9, r10);
        int r92 = r9 * 2;
        float[] r0 = new float[r92];
        int r2 = 0;
    L5:
        if (r2 >= r10) goto L18;
        float r3 = r2 + 0.5f;
        int r5 = 0;
    L7:
        if (r5 >= r92) goto L9;
        r0[r5] = (r5 / 2) + 0.5f;
        r0[r5 + 1] = r3;
        r5 = r5 + 2;
        goto L7
    L9:
        r11.transformPoints(r0);
        GridSampler.checkAndNudgePoints(r8, r0);
        int r32 = 0;
    L10:
        if (r32 >= r92) goto L17;
        if (r8.get((int) r0[r32], (int) r0[r32 + 1]) == false) goto L14;
        r7.set(r32 / 2, r2);     // Catch: ArrayIndexOutOfBoundsException -> L15
    L14:
        r32 = r32 + 2;
    L16:
        throw NotFoundException.getNotFoundInstance();
    L17:
        r2 = r2 + 1;
        goto L5
    L18:
        return r7;
    L20:
        throw NotFoundException.getNotFoundInstance();
    }
}
