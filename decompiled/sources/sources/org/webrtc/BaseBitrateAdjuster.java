package org.webrtc;

/* loaded from: classes6.dex */
class BaseBitrateAdjuster implements BitrateAdjuster {
    protected int targetBitrateBps;
    protected double targetFramerateFps;

    public BaseBitrateAdjuster() {
    }

    @Override // org.webrtc.BitrateAdjuster
    public int getAdjustedBitrateBps() {
        return this.targetBitrateBps;
    }

    @Override // org.webrtc.BitrateAdjuster
    public double getAdjustedFramerateFps() {
        return this.targetFramerateFps;
    }

    @Override // org.webrtc.BitrateAdjuster
    public void reportEncodedFrame(int r1) {
    }

    @Override // org.webrtc.BitrateAdjuster
    public void setTargets(int r1, double r2) {
        this.targetBitrateBps = r1;
        this.targetFramerateFps = r2;
    }
}
