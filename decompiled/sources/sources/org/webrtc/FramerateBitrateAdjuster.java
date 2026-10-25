package org.webrtc;

/* loaded from: classes6.dex */
class FramerateBitrateAdjuster extends BaseBitrateAdjuster {
    private static final int DEFAULT_FRAMERATE_FPS = 30;

    public FramerateBitrateAdjuster() {
    }

    @Override // org.webrtc.BaseBitrateAdjuster, org.webrtc.BitrateAdjuster
    public void setTargets(int r3, double r4) {
        this.targetFramerateFps = 30.0d;
        this.targetBitrateBps = (int) ((r3 * 30) / r4);
    }
}
