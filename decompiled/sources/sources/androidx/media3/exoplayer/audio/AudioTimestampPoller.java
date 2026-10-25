package androidx.media3.exoplayer.audio;

import android.media.AudioTimestamp;
import android.media.AudioTrack;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.audio.AudioTrackPositionTracker;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
final class AudioTimestampPoller {
    private final AudioTimestampWrapper audioTimestamp;
    private final AudioTrackPositionTracker.Listener errorListener;
    private long initialTimestampPositionFrames;
    private long initialTimestampSystemTimeUs;
    private long initializeSystemTimeUs;
    private long lastTimestampSampleTimeUs;
    private long sampleIntervalUs;
    private final int sampleRate;
    private int state;

    public static final class AudioTimestampWrapper {
        private long accumulatedRawTimestampFramePosition;
        private final AudioTimestamp audioTimestamp;
        private final AudioTrack audioTrack;
        private boolean expectTimestampFramePositionReset;
        private long lastTimestampPositionFrames;
        private long lastTimestampRawPositionFrames;
        private long rawTimestampFramePositionWrapCount;

        public AudioTimestampWrapper(AudioTrack r1) {
            this.audioTrack = r1;
            this.audioTimestamp = new AudioTimestamp();
        }

        public void expectTimestampFramePositionReset() {
            this.expectTimestampFramePositionReset = true;
        }

        public long getTimestampPositionFrames() {
            return this.lastTimestampPositionFrames;
        }

        public long getTimestampSystemTimeUs() {
            return this.audioTimestamp.nanoTime / 1000;
        }

        public boolean maybeUpdateTimestamp() {
            boolean r0 = this.audioTrack.getTimestamp(this.audioTimestamp);
            if (r0 == false) goto L11;
            long r1 = this.audioTimestamp.framePosition;
            long r3 = this.lastTimestampRawPositionFrames;
            if (r3 > r1) goto L7;
        L10:
            this.lastTimestampRawPositionFrames = r1;
            this.lastTimestampPositionFrames = (r1 + this.accumulatedRawTimestampFramePosition) + (this.rawTimestampFramePositionWrapCount << 32);
            goto L11
        L7:
            if (this.expectTimestampFramePositionReset == false) goto L9;
            this.accumulatedRawTimestampFramePosition += r3;
            this.expectTimestampFramePositionReset = false;
            goto L10
        L9:
            this.rawTimestampFramePositionWrapCount++;
        L11:
            return r0;
        }
    }

    public AudioTimestampPoller(AudioTrack r2, AudioTrackPositionTracker.Listener r3) {
        this.audioTimestamp = new AudioTimestampWrapper(r2);
        this.sampleRate = r2.getSampleRate();
        this.errorListener = r3;
        reset();
    }

    private void checkTimestampIsPlausibleAndUpdateErrorState(long r12, float r14, long r15) {
        long r4 = this.audioTimestamp.getTimestampSystemTimeUs();
        long r0 = computeTimestampPositionUs(r12, r14);
        if (Math.abs(r4 - r12) <= 5000000) goto L7;
        this.errorListener.onSystemTimeUsMismatch(this.audioTimestamp.getTimestampPositionFrames(), r4, r12, r15);
        updateState(4);
        return;
    L7:
        if (Math.abs(r0 - r15) <= 5000000) goto L11;
        this.errorListener.onPositionFramesMismatch(this.audioTimestamp.getTimestampPositionFrames(), r4, r12, r15);
        updateState(4);
        return;
    L11:
        if (this.state != 4) goto L14;
        reset();
        return;
    }

    private long computeTimestampPositionUs(long r10, float r12) {
        return computeTimestampPositionUs(this.audioTimestamp.getTimestampPositionFrames(), this.audioTimestamp.getTimestampSystemTimeUs(), r10, r12);
    }

    private long computeTimestampPositionUs(long r1, long r3, long r5, float r7) {
        return Util.sampleCountToDurationUs(r1, this.sampleRate) + Util.getMediaDurationForPlayoutDuration(r5 - r3, r7);
    }

    private boolean isTimestampAdvancingFromInitialTimestamp(long r11, float r13) {
        long r0 = this.audioTimestamp.getTimestampPositionFrames();
        long r3 = this.initialTimestampPositionFrames;
        if (r0 > r3) goto L5;
        return false;
    L5:
        long r10 = computeTimestampPositionUs(r3, this.initialTimestampSystemTimeUs, r11, r13);
        if (Math.abs(computeTimestampPositionUs(r11, r13) - r10) >= 1000) goto L9;
        return true;
    L9:
        return false;
    }

    private void updateState(int r7) {
        this.state = r7;
        if (r7 != 0) goto L5;
        this.lastTimestampSampleTimeUs = 0;
        this.initialTimestampPositionFrames = -1;
        this.initialTimestampSystemTimeUs = -9223372036854775807L;
        this.initializeSystemTimeUs = System.nanoTime() / 1000;
        this.sampleIntervalUs = NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS;
        return;
    L5:
        if (r7 != 1) goto L7;
        this.sampleIntervalUs = NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS;
        return;
    L7:
        if (r7 != 2) goto L9;
    L16:
        this.sampleIntervalUs = 10000000;
        return;
    L9:
        if (r7 == 3) goto L16;
        if (r7 != 4) goto L14;
        this.sampleIntervalUs = 500000;
        return;
    L14:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
    }

    public void expectTimestampFramePositionReset() {
        this.audioTimestamp.expectTimestampFramePositionReset();
    }

    public long getTimestampPositionUs(long r1, float r3) {
        return computeTimestampPositionUs(r1, r3);
    }

    public boolean hasAdvancingTimestamp() {
        if (this.state != 2) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isWaitingForAdvancingTimestamp() {
        int r1 = this.state;
        if (r1 == 0) goto L8;
        if (r1 == 1) goto L8;
        return false;
    L8:
        return true;
    }

    public void maybePollTimestamp(long r5, float r7, long r8) {
        if ((r5 - this.lastTimestampSampleTimeUs) < this.sampleIntervalUs) goto L51;
        this.lastTimestampSampleTimeUs = r5;
        boolean r0 = this.audioTimestamp.maybeUpdateTimestamp();
        if (r0 == false) goto L8;
        checkTimestampIsPlausibleAndUpdateErrorState(r5, r7, r8);
    L8:
        int r82 = this.state;
        if (r82 != 0) goto L11;
        if (r0 == false) goto L44;
        if (this.audioTimestamp.getTimestampSystemTimeUs() < this.initializeSystemTimeUs) goto L49;
        this.initialTimestampPositionFrames = this.audioTimestamp.getTimestampPositionFrames();
        this.initialTimestampSystemTimeUs = this.audioTimestamp.getTimestampSystemTimeUs();
        updateState(1);
        return;
    L49:
        return;
    L44:
        if ((r5 - this.initializeSystemTimeUs) <= 500000) goto L50;
        updateState(3);
        return;
    L50:
        return;
    L11:
        if (r82 == 1) goto L25;
        if (r82 == 2) goto L22;
        if (r82 != 3) goto L15;
        if (r0 == false) goto L47;
        reset();
        return;
    L47:
        return;
    L15:
        if (r82 == 4) goto L52;
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return;
    L52:
        return;
    L22:
        if (r0 == true) goto L48;
        reset();
        return;
    L48:
        return;
    L25:
        if (r0 == true) goto L27;
        reset();
        return;
    L27:
        if (isTimestampAdvancingFromInitialTimestamp(r5, r7) == false) goto L31;
        updateState(2);
        return;
    L31:
        if ((r5 - this.initializeSystemTimeUs) <= 2000000) goto L34;
        updateState(3);
        return;
    L34:
        this.initialTimestampPositionFrames = this.audioTimestamp.getTimestampPositionFrames();
        this.initialTimestampSystemTimeUs = this.audioTimestamp.getTimestampSystemTimeUs();
        return;
    }

    public void reset() {
        updateState(0);
    }
}
