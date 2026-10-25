package com.google.zxing.common;

import com.google.zxing.ResultPoint;

/* loaded from: classes5.dex */
public class DetectorResult {
    private final BitMatrix bits;
    private final ResultPoint[] points;

    public DetectorResult(BitMatrix r1, ResultPoint[] r2) {
        this.bits = r1;
        this.points = r2;
    }

    public final BitMatrix getBits() {
        return this.bits;
    }

    public final ResultPoint[] getPoints() {
        return this.points;
    }
}
