package com.google.zxing.qrcode.detector;

import com.google.zxing.ResultPoint;

/* loaded from: classes5.dex */
public final class FinderPattern extends ResultPoint {
    private final int count;
    private final float estimatedModuleSize;

    public FinderPattern(float r2, float r3, float r4) {
        this(r2, r3, r4, 1);
    }

    private FinderPattern(float r1, float r2, float r3, int r4) {
        super(r1, r2);
        this.estimatedModuleSize = r3;
        this.count = r4;
    }

    public boolean aboutEquals(float r2, float r3, float r4) {
        if (Math.abs(r3 - getY()) <= r2) goto L5;
    L14:
        return false;
    L5:
        if (Math.abs(r4 - getX()) > r2) goto L14;
        float r22 = Math.abs(r2 - this.estimatedModuleSize);
        if (r22 > 1.0f) goto L9;
        return true;
    L9:
        if (r22 <= this.estimatedModuleSize) goto L15;
        return false;
    L15:
        return true;
    }

    public FinderPattern combineEstimate(float r5, float r6, float r7) {
        int r0 = this.count;
        int r1 = r0 + 1;
        float r02 = (r0 * getX()) + r6;
        float r62 = r1;
        return new FinderPattern(r02 / r62, ((this.count * getY()) + r5) / r62, ((this.count * this.estimatedModuleSize) + r7) / r62, r1);
    }

    public int getCount() {
        return this.count;
    }

    public float getEstimatedModuleSize() {
        return this.estimatedModuleSize;
    }
}
