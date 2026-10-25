package androidx.media3.exoplayer.audio;

import android.media.AudioTrack;
import android.os.Build;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Util;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.lang.reflect.Method;

/* loaded from: classes4.dex */
final class AudioTrackPositionTracker {
    private AudioTimestampPoller audioTimestampPoller;
    private AudioTrack audioTrack;
    private float audioTrackPlaybackSpeed;
    private int bufferSize;
    private long bufferSizeUs;
    private Clock clock;
    boolean enableOnAudioPositionAdvancingFix;
    private long endPlaybackHeadPosition;
    private boolean expectRawPlaybackHeadReset;
    private long forceResetWorkaroundTimeMs;
    private Method getLatencyMethod;
    private boolean hasData;
    private boolean isOutputPcm;
    private long lastLatencySampleTimeUs;
    private long lastPlayheadSampleTimeUs;
    private long lastPositionUs;
    private long lastRawPlaybackHeadPositionSampleTimeMs;
    private long lastSystemTimeUs;
    private int lastUnderrunCount;
    private long latencyUs;
    private final Listener listener;
    private boolean needsPassthroughWorkarounds;
    private int nextPlayheadOffsetIndex;
    private boolean notifiedPositionIncreasing;
    private long onPositionAdvancingFromPositionUs;
    private int outputSampleRate;
    private long passthroughWorkaroundPauseOffset;
    private int playheadOffsetCount;
    private final long[] playheadOffsets;
    private long rawPlaybackHeadPosition;
    private long rawPlaybackHeadWrapCount;
    private long smoothedPlayheadOffsetUs;
    private long stopPlaybackHeadPosition;
    private long stopTimestampUs;
    private long sumRawPlaybackHeadPosition;

    public interface Listener {
        void onInvalidLatency(long r1);

        void onPositionAdvancing(long r1);

        void onPositionFramesMismatch(long r1, long r3, long r5, long r7);

        void onSystemTimeUsMismatch(long r1, long r3, long r5, long r7);

        void onUnderrun(int r1, long r2);
    }

    public AudioTrackPositionTracker(Listener r3) {
        this.listener = (Listener) Assertions.checkNotNull(r3);
        this.getLatencyMethod = AudioTrack.class.getMethod("getLatency", null);     // Catch: NoSuchMethodException -> L6
    L4:
        this.playheadOffsets = new long[10];
        this.lastSystemTimeUs = -9223372036854775807L;
        this.lastPositionUs = -9223372036854775807L;
        this.clock = Clock.DEFAULT;
    }

    private boolean forceHasPendingData() {
        if (this.needsPassthroughWorkarounds == true) goto L5;
        return false;
    L5:
        if (((AudioTrack) Assertions.checkNotNull(this.audioTrack)).getPlayState() == 2) goto L7;
        return false;
    L7:
        if (getPlaybackHeadPosition() != 0) goto L13;
        return true;
    L13:
        return false;
    }

    private long getPlaybackHeadPosition() {
        if (this.stopTimestampUs == (-9223372036854775807L)) goto L6;
        long r0 = getSimulatedPlaybackHeadPositionAfterStop();
        return Math.min(this.endPlaybackHeadPosition, r0);
    L6:
        long r02 = this.clock.elapsedRealtime();
        if ((r02 - this.lastRawPlaybackHeadPositionSampleTimeMs) < 5) goto L10;
        updateRawPlaybackHeadPosition(r02);
        this.lastRawPlaybackHeadPositionSampleTimeMs = r02;
    L10:
        return (this.rawPlaybackHeadPosition + this.sumRawPlaybackHeadPosition) + (this.rawPlaybackHeadWrapCount << 32);
    }

    private long getPlaybackHeadPositionEstimateUs(long r6) {
        if (this.playheadOffsetCount == 0) goto L5;
        long r62 = Util.getMediaDurationForPlayoutDuration(r6 + this.smoothedPlayheadOffsetUs, this.audioTrackPlaybackSpeed);
    L9:
        long r63 = Math.max(0, r62 - this.latencyUs);
        if (this.stopTimestampUs != (-9223372036854775807L)) goto L12;
        return r63;
    L12:
        return Math.min(Util.sampleCountToDurationUs(this.endPlaybackHeadPosition, this.outputSampleRate), r63);
    L5:
        if (this.stopTimestampUs == (-9223372036854775807L)) goto L7;
        r62 = Util.sampleCountToDurationUs(getSimulatedPlaybackHeadPositionAfterStop(), this.outputSampleRate);
        goto L9
    L7:
        r62 = getPlaybackHeadPositionUs();
        goto L9
    }

    private long getPlaybackHeadPositionUs() {
        return Util.sampleCountToDurationUs(getPlaybackHeadPosition(), this.outputSampleRate);
    }

    private long getSimulatedPlaybackHeadPositionAfterStop() {
        if (((AudioTrack) Assertions.checkNotNull(this.audioTrack)).getPlayState() == 2) goto L5;
        long r0 = Util.durationUsToSampleCount(Util.getMediaDurationForPlayoutDuration(Util.msToUs(this.clock.elapsedRealtime()) - this.stopTimestampUs, this.audioTrackPlaybackSpeed), this.outputSampleRate);
        return this.stopPlaybackHeadPosition + r0;
    L5:
        return this.stopPlaybackHeadPosition;
    }

    private boolean hasPendingAudioTrackUnderruns() {
        int r0 = ((AudioTrack) Assertions.checkNotNull(this.audioTrack)).getUnderrunCount();
        if (r0 <= this.lastUnderrunCount) goto L5;
        boolean r1 = true;
    L6:
        this.lastUnderrunCount = r0;
        return r1;
    L5:
        r1 = false;
        goto L6
    }

    private void maybeSampleSyncParams() {
        long r5 = this.clock.nanoTime() / 1000;
        if ((r5 - this.lastPlayheadSampleTimeUs) < NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS) goto L15;
        long r0 = getPlaybackHeadPositionUs();
        if (r0 == 0) goto L20;
        this.playheadOffsets[this.nextPlayheadOffsetIndex] = Util.getPlayoutDurationForMediaDuration(r0, this.audioTrackPlaybackSpeed) - r5;
        this.nextPlayheadOffsetIndex = (this.nextPlayheadOffsetIndex + 1) % 10;
        int r02 = this.playheadOffsetCount;
        if (r02 >= 10) goto L10;
        this.playheadOffsetCount = r02 + 1;
    L10:
        this.lastPlayheadSampleTimeUs = r5;
        this.smoothedPlayheadOffsetUs = 0;
        int r03 = 0;
    L11:
        int r1 = this.playheadOffsetCount;
        if (r03 >= r1) goto L15;
        this.smoothedPlayheadOffsetUs += this.playheadOffsets[r03] / r1;
        r03 = r03 + 1;
        goto L11
    L20:
        return;
    L15:
        if (this.needsPassthroughWorkarounds == false) goto L17;
        return;
    L17:
        maybeUpdateLatency(r5);
        ((AudioTimestampPoller) Assertions.checkNotNull(this.audioTimestampPoller)).maybePollTimestamp(r5, this.audioTrackPlaybackSpeed, getPlaybackHeadPositionEstimateUs(r5));
    }

    private void maybeTriggerOnPositionAdvancingCallback(long r6) {
        if (this.enableOnAudioPositionAdvancingFix == false) goto L11;
        long r0 = this.onPositionAdvancingFromPositionUs;
        if (r0 != (-9223372036854775807L)) goto L7;
        return;
    L7:
        if (r6 < r0) goto L13;
        long r62 = Util.getPlayoutDurationForMediaDuration(r6 - r0, this.audioTrackPlaybackSpeed);
        long r02 = this.clock.currentTimeMillis() - Util.usToMs(r62);
        this.onPositionAdvancingFromPositionUs = -9223372036854775807L;
        this.listener.onPositionAdvancing(r02);
        return;
    L13:
        return;
    }

    private void maybeUpdateLatency(long r9) {
        if (this.isOutputPcm == true) goto L5;
        return;
    L5:
        if (this.getLatencyMethod != null) goto L7;
        return;
    L7:
        if ((r9 - this.lastLatencySampleTimeUs) >= 500000) goto L16;
        return;
    L16:
        long r2 = (((Integer) Util.castNonNull((Integer) r0.invoke(Assertions.checkNotNull(this.audioTrack), null))).intValue() * 1000) - this.bufferSizeUs;     // Catch: Exception -> L13
        this.latencyUs = r2;     // Catch: Exception -> L13
        long r22 = Math.max(r2, 0);     // Catch: Exception -> L13
        this.latencyUs = r22;     // Catch: Exception -> L13
        if (r22 <= 5000000) goto L14;
        this.listener.onInvalidLatency(r22);     // Catch: Exception -> L13
        this.latencyUs = 0;     // Catch: Exception -> L13
    L14:
        this.lastLatencySampleTimeUs = r9;
        return;
    L13:
        this.getLatencyMethod = null;
        goto L14
    }

    private static boolean needsPassthroughWorkarounds(int r0) {
        return false;
    }

    private void resetSyncParams() {
        this.smoothedPlayheadOffsetUs = 0;
        this.playheadOffsetCount = 0;
        this.nextPlayheadOffsetIndex = 0;
        this.lastPlayheadSampleTimeUs = 0;
        this.lastPositionUs = -9223372036854775807L;
        this.lastSystemTimeUs = -9223372036854775807L;
        this.notifiedPositionIncreasing = false;
    }

    private void updateRawPlaybackHeadPosition(long r11) {
        int r1 = ((AudioTrack) Assertions.checkNotNull(this.audioTrack)).getPlayState();
        if (r1 == 1) goto L35;
        long r2 = r0.getPlaybackHeadPosition() & 4294967295L;
        if (this.needsPassthroughWorkarounds == false) goto L14;
        if (r1 == 2) goto L10;
    L12:
        r2 = r2 + this.passthroughWorkaroundPauseOffset;
        goto L14
    L10:
        if (r2 != 0) goto L12;
        this.passthroughWorkaroundPauseOffset = this.rawPlaybackHeadPosition;
    L14:
        if (Build.VERSION.SDK_INT <= 29) goto L16;
    L26:
        long r112 = this.rawPlaybackHeadPosition;
        if (r112 > r2) goto L29;
    L32:
        this.rawPlaybackHeadPosition = r2;
        return;
    L29:
        if (this.expectRawPlaybackHeadReset == false) goto L31;
        this.sumRawPlaybackHeadPosition += r112;
        this.expectRawPlaybackHeadReset = false;
        goto L32
    L31:
        this.rawPlaybackHeadWrapCount++;
        goto L32
    L16:
        if (r2 == 0) goto L18;
    L25:
        this.forceResetWorkaroundTimeMs = -9223372036854775807L;
        goto L26
    L18:
        if (this.rawPlaybackHeadPosition <= 0) goto L25;
        if (r1 != 3) goto L25;
        if (this.forceResetWorkaroundTimeMs != (-9223372036854775807L)) goto L34;
        this.forceResetWorkaroundTimeMs = r11;
        return;
    L34:
        return;
    }

    public void expectRawPlaybackHeadReset() {
        this.expectRawPlaybackHeadReset = true;
        AudioTimestampPoller r1 = this.audioTimestampPoller;
        if (r1 == null) goto L6;
        r1.expectTimestampFramePositionReset();
        return;
    }

    public long getCurrentPositionUs() {
        AudioTrack r1 = (AudioTrack) Assertions.checkNotNull(this.audioTrack);
        if (r1.getPlayState() != 3) goto L5;
        maybeSampleSyncParams();
    L5:
        long r4 = this.clock.nanoTime() / 1000;
        AudioTimestampPoller r2 = (AudioTimestampPoller) Assertions.checkNotNull(this.audioTimestampPoller);
        boolean r6 = r2.hasAdvancingTimestamp();
        if (r6 == false) goto L9;
        long r7 = r2.getTimestampPositionUs(r4, this.audioTrackPlaybackSpeed);
    L8:
        long r9 = r7;
        int r12 = r1.getPlayState();
        if (r12 != 3) goto L34;
        if (r6 == false) goto L14;
    L15:
        maybeTriggerOnPositionAdvancingCallback(r9);
    L16:
        long r13 = this.lastSystemTimeUs;
        if (r13 == (-9223372036854775807L)) goto L24;
        long r11 = r9 - this.lastPositionUs;
        long r14 = Util.getMediaDurationForPlayoutDuration(r4 - r13, this.audioTrackPlaybackSpeed);
        long r132 = this.lastPositionUs + r14;
        long r17 = Math.abs(r132 - r9);
        if (r11 == 0) goto L24;
        if (r17 >= 1000000) goto L24;
        long r15 = (r14 * 10) / 100;
        r9 = Util.constrainValue(r9, r132 - r15, r132 + r15);
    L24:
        if (this.enableOnAudioPositionAdvancingFix == false) goto L26;
    L32:
        this.lastSystemTimeUs = r4;
        this.lastPositionUs = r9;
        return r9;
    L26:
        if (this.notifiedPositionIncreasing == true) goto L32;
        long r16 = this.lastPositionUs;
        if (r16 == (-9223372036854775807L)) goto L32;
        if (r9 <= r16) goto L32;
        this.notifiedPositionIncreasing = true;
        long r18 = Util.getPlayoutDurationForMediaDuration(Util.usToMs(r9 - r16), this.audioTrackPlaybackSpeed);
        long r62 = this.clock.currentTimeMillis() - Util.usToMs(r18);
        this.listener.onPositionAdvancing(r62);
        goto L32
    L14:
        if (r2.isWaitingForAdvancingTimestamp() == true) goto L16;
    L34:
        if (r12 != 1) goto L36;
        maybeTriggerOnPositionAdvancingCallback(r9);
    L36:
        return r9;
    L9:
        r7 = getPlaybackHeadPositionEstimateUs(r4);
        goto L8
    }

    public void handleEndOfStream(long r3) {
        this.stopPlaybackHeadPosition = getPlaybackHeadPosition();
        this.stopTimestampUs = Util.msToUs(this.clock.elapsedRealtime());
        this.endPlaybackHeadPosition = r3;
    }

    public boolean hasPendingData(long r4) {
        if (r4 <= Util.durationUsToSampleCount(getCurrentPositionUs(), this.outputSampleRate)) goto L5;
        return true;
    L5:
        if (forceHasPendingData() == true) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean isPlaying() {
        if (((AudioTrack) Assertions.checkNotNull(this.audioTrack)).getPlayState() != 3) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isStalled(long r5) {
        if (this.forceResetWorkaroundTimeMs != (-9223372036854775807L)) goto L5;
        return false;
    L5:
        if (r5 > 0) goto L7;
        return false;
    L7:
        if ((this.clock.elapsedRealtime() - this.forceResetWorkaroundTimeMs) < 200) goto L13;
        return true;
    L13:
        return false;
    }

    public boolean mayHandleBuffer(long r5) {
        int r52 = ((AudioTrack) Assertions.checkNotNull(this.audioTrack)).getPlayState();
        if (this.needsPassthroughWorkarounds == false) goto L13;
        if (r52 != 2) goto L8;
        this.hasData = false;
        return false;
    L8:
        if (r52 != 1) goto L13;
        if (getPlaybackHeadPosition() != 0) goto L13;
        return false;
    L13:
        if (hasPendingAudioTrackUnderruns() == false) goto L15;
        this.listener.onUnderrun(this.bufferSize, Util.usToMs(this.bufferSizeUs));
    L15:
        return true;
    }

    public void pause() {
        resetSyncParams();
        if (this.stopTimestampUs != (-9223372036854775807L)) goto L5;
        ((AudioTimestampPoller) Assertions.checkNotNull(this.audioTimestampPoller)).reset();
    L5:
        this.stopPlaybackHeadPosition = getPlaybackHeadPosition();
    }

    public void reset() {
        resetSyncParams();
        this.audioTrack = null;
        this.audioTimestampPoller = null;
    }

    public void setAudioTrack(AudioTrack r3, boolean r4, int r5, int r6, int r7, boolean r8) {
        this.audioTrack = r3;
        this.bufferSize = r7;
        this.audioTimestampPoller = new AudioTimestampPoller(r3, this.listener);
        this.outputSampleRate = r3.getSampleRate();
        if (r4 == true) goto L5;
    L7:
        boolean r42 = false;
    L8:
        this.needsPassthroughWorkarounds = r42;
        boolean r43 = Util.isEncodingLinearPcm(r5);
        this.isOutputPcm = r43;
        if (r43 == false) goto L11;
        long r44 = Util.sampleCountToDurationUs(r7 / r6, this.outputSampleRate);
    L12:
        this.bufferSizeUs = r44;
        this.rawPlaybackHeadPosition = 0;
        this.rawPlaybackHeadWrapCount = 0;
        this.expectRawPlaybackHeadReset = false;
        this.sumRawPlaybackHeadPosition = 0;
        this.passthroughWorkaroundPauseOffset = 0;
        this.hasData = false;
        this.stopTimestampUs = -9223372036854775807L;
        this.forceResetWorkaroundTimeMs = -9223372036854775807L;
        this.lastLatencySampleTimeUs = 0;
        this.latencyUs = 0;
        this.audioTrackPlaybackSpeed = 1.0f;
        this.lastUnderrunCount = 0;
        this.onPositionAdvancingFromPositionUs = -9223372036854775807L;
        this.enableOnAudioPositionAdvancingFix = r8;
        return;
    L11:
        r44 = -9223372036854775807L;
        goto L12
    L5:
        if (needsPassthroughWorkarounds(r5) == false) goto L7;
        r42 = true;
        goto L8
    }

    public void setAudioTrackPlaybackSpeed(float r1) {
        this.audioTrackPlaybackSpeed = r1;
        AudioTimestampPoller r12 = this.audioTimestampPoller;
        if (r12 == null) goto L5;
        r12.reset();
    L5:
        resetSyncParams();
    }

    public void setClock(Clock r1) {
        this.clock = r1;
    }

    public void start() {
        if (this.stopTimestampUs == (-9223372036854775807L)) goto L5;
        this.stopTimestampUs = Util.msToUs(this.clock.elapsedRealtime());
    L5:
        this.onPositionAdvancingFromPositionUs = getPlaybackHeadPositionUs();
        ((AudioTimestampPoller) Assertions.checkNotNull(this.audioTimestampPoller)).reset();
    }
}
