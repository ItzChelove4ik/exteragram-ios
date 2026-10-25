package org.webrtc.audio;

import android.media.AudioTrack;
import android.os.Build;
import org.webrtc.Logging;

/* loaded from: classes6.dex */
class LowLatencyAudioBufferManager {
    private static final String TAG = "LowLatencyAudioBufferManager";
    private int bufferIncreaseCounter;
    private boolean keepLoweringBufferSize;
    private int prevUnderrunCount;
    private int ticksUntilNextDecrease;

    public LowLatencyAudioBufferManager() {
        this.prevUnderrunCount = 0;
        this.ticksUntilNextDecrease = 10;
        this.keepLoweringBufferSize = true;
        this.bufferIncreaseCounter = 0;
    }

    public void maybeAdjustBufferSize(AudioTrack r9) {
        if (Build.VERSION.SDK_INT < 26) goto L20;
        int r0 = r9.getUnderrunCount();
        if (r0 <= this.prevUnderrunCount) goto L12;
        if (this.bufferIncreaseCounter >= 5) goto L9;
        int r1 = r9.getBufferSizeInFrames();
        int r5 = (r9.getPlaybackRate() / 100) + r1;
        Logging.d(TAG, "Underrun detected! Increasing AudioTrack buffer size from " + r1 + " to " + r5);
        r9.setBufferSizeInFrames(r5);
        this.bufferIncreaseCounter = this.bufferIncreaseCounter + 1;
    L9:
        this.keepLoweringBufferSize = false;
        this.prevUnderrunCount = r0;
        this.ticksUntilNextDecrease = 10;
        return;
    L12:
        if (this.keepLoweringBufferSize == false) goto L21;
        int r02 = this.ticksUntilNextDecrease - 1;
        this.ticksUntilNextDecrease = r02;
        if (r02 > 0) goto L22;
        int r03 = r9.getPlaybackRate() / 100;
        int r12 = r9.getBufferSizeInFrames();
        int r04 = Math.max(r03, r12 - r03);
        if (r04 == r12) goto L18;
        Logging.d(TAG, "Lowering AudioTrack buffer size from " + r12 + " to " + r04);
        r9.setBufferSizeInFrames(r04);
    L18:
        this.ticksUntilNextDecrease = 10;
        return;
    L22:
        return;
    L21:
        return;
    }
}
