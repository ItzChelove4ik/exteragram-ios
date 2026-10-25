package com.google.zxing.qrcode.detector;

import com.google.zxing.ResultPoint;

/* loaded from: classes5.dex */
public final class AlignmentPattern extends ResultPoint {
    private final float estimatedModuleSize;

    public AlignmentPattern(float r1, float r2, float r3) {
        super(r1, r2);
        this.estimatedModuleSize = r3;
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

    public AlignmentPattern combineEstimate(float r3, float r4, float r5) {
        return new AlignmentPattern((getX() + r4) / 2.0f, (getY() + r3) / 2.0f, (this.estimatedModuleSize + r5) / 2.0f);
    }
}
