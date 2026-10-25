package org.webrtc;

/* loaded from: classes6.dex */
interface BitrateAdjuster {
    int getAdjustedBitrateBps();

    double getAdjustedFramerateFps();

    void reportEncodedFrame(int r1);

    void setTargets(int r1, double r2);
}
