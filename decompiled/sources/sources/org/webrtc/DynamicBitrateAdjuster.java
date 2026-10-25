package org.webrtc;

/* loaded from: classes6.dex */
class DynamicBitrateAdjuster extends BaseBitrateAdjuster {
    private static final double BITRATE_ADJUSTMENT_MAX_SCALE = 4.0d;
    private static final double BITRATE_ADJUSTMENT_SEC = 3.0d;
    private static final int BITRATE_ADJUSTMENT_STEPS = 20;
    private static final double BITS_PER_BYTE = 8.0d;
    private int bitrateAdjustmentScaleExp;
    private double deviationBytes;
    private double timeSinceLastAdjustmentMs;

    public DynamicBitrateAdjuster() {
    }

    private double getBitrateAdjustmentScale() {
        return Math.pow(BITRATE_ADJUSTMENT_MAX_SCALE, this.bitrateAdjustmentScaleExp / 20.0d);
    }

    @Override // org.webrtc.BaseBitrateAdjuster, org.webrtc.BitrateAdjuster
    public int getAdjustedBitrateBps() {
        return (int) (this.targetBitrateBps * getBitrateAdjustmentScale());
    }

    @Override // org.webrtc.BaseBitrateAdjuster, org.webrtc.BitrateAdjuster
    public void reportEncodedFrame(int r14) {
        double r0 = this.targetFramerateFps;
        if (r0 == 0.0d) goto L16;
        int r4 = this.targetBitrateBps;
        double r9 = this.deviationBytes + (r14 - ((r4 / BITS_PER_BYTE) / r0));
        this.deviationBytes = r9;
        this.timeSinceLastAdjustmentMs += 1000.0d / r0;
        double r02 = r4 / BITS_PER_BYTE;
        double r42 = 3.0d * r02;
        double r6 = Math.min(r9, r42);
        this.deviationBytes = r6;
        double r43 = Math.max(r6, -r42);
        this.deviationBytes = r43;
        if (this.timeSinceLastAdjustmentMs > 3000.0d) goto L9;
        return;
    L9:
        if (r43 <= r02) goto L11;
        int r44 = this.bitrateAdjustmentScaleExp - ((int) ((r43 / r02) + 0.5d));
        this.bitrateAdjustmentScaleExp = r44;
        this.bitrateAdjustmentScaleExp = Math.max(r44, -20);
        this.deviationBytes = r02;
    L14:
        this.timeSinceLastAdjustmentMs = 0.0d;
        return;
    L11:
        double r8 = -r02;
        if (r43 >= r8) goto L14;
        int r03 = this.bitrateAdjustmentScaleExp + ((int) (((-r43) / r02) + 0.5d));
        this.bitrateAdjustmentScaleExp = r03;
        this.bitrateAdjustmentScaleExp = Math.min(r03, 20);
        this.deviationBytes = r8;
        goto L14
    }

    @Override // org.webrtc.BaseBitrateAdjuster, org.webrtc.BitrateAdjuster
    public void setTargets(int r6, double r7) {
        int r0 = this.targetBitrateBps;
        if (r0 <= 0) goto L6;
        if (r6 >= r0) goto L6;
        this.deviationBytes = (this.deviationBytes * r6) / r0;
    L6:
        super.setTargets(r6, r7);
    }
}
