package androidx.media3.exoplayer.video;

import android.content.Context;
import android.view.Surface;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Util;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class VideoFrameReleaseControl {
    private final long allowedJoiningTimeMs;
    private Clock clock;
    private boolean disableAdvancingTimestampChecks;
    private int firstFrameState;
    private boolean frameReadyWithoutSurface;
    private final VideoFrameReleaseHelper frameReleaseHelper;
    private final FrameTimingEvaluator frameTimingEvaluator;
    private boolean hasOutputSurface;
    private long initialPositionUs;
    private long joiningDeadlineMs;
    private boolean joiningRenderNextFrameImmediately;
    private long lastPresentationTimeUs;
    private long lastReleaseRealtimeUs;
    private float playbackSpeed;
    private boolean started;

    public static class FrameReleaseInfo {
        private long earlyUs;
        private long releaseTimeNs;

        public FrameReleaseInfo() {
            this.earlyUs = -9223372036854775807L;
            this.releaseTimeNs = -9223372036854775807L;
        }

        public static /* synthetic */ void access$000(FrameReleaseInfo r0) {
            r0.reset();
        }

        public static /* synthetic */ long access$100(FrameReleaseInfo r2) {
            return r2.earlyUs;
        }

        public static /* synthetic */ long access$102(FrameReleaseInfo r0, long r1) {
            r0.earlyUs = r1;
            return r1;
        }

        public static /* synthetic */ long access$200(FrameReleaseInfo r2) {
            return r2.releaseTimeNs;
        }

        public static /* synthetic */ long access$202(FrameReleaseInfo r0, long r1) {
            r0.releaseTimeNs = r1;
            return r1;
        }

        private void reset() {
            this.earlyUs = -9223372036854775807L;
            this.releaseTimeNs = -9223372036854775807L;
        }

        public long getEarlyUs() {
            return this.earlyUs;
        }

        public long getReleaseTimeNs() {
            return this.releaseTimeNs;
        }
    }

    public interface FrameTimingEvaluator {
        boolean shouldDropFrame(long r1, long r3, boolean r5);

        boolean shouldForceReleaseFrame(long r1, long r3);

        boolean shouldIgnoreFrame(long r1, long r3, long r5, boolean r7, boolean r8);
    }

    public VideoFrameReleaseControl(Context r1, FrameTimingEvaluator r2, long r3) {
        this.frameTimingEvaluator = r2;
        this.allowedJoiningTimeMs = r3;
        this.frameReleaseHelper = new VideoFrameReleaseHelper(r1);
        this.firstFrameState = 0;
        this.initialPositionUs = -9223372036854775807L;
        this.lastPresentationTimeUs = -9223372036854775807L;
        this.joiningDeadlineMs = -9223372036854775807L;
        this.playbackSpeed = 1.0f;
        this.clock = Clock.DEFAULT;
    }

    private long calculateEarlyTimeUs(long r1, long r3, long r5) {
        long r12 = (long) ((r5 - r1) / this.playbackSpeed);
        if (this.started == true) goto L5;
        return r12;
    L5:
        return r12 - (Util.msToUs(this.clock.elapsedRealtime()) - r3);
    }

    private void lowerFirstFrameState(int r2) {
        this.firstFrameState = Math.min(this.firstFrameState, r2);
    }

    private boolean shouldForceRelease(long r8, long r10, long r12) {
        if (this.joiningDeadlineMs != (-9223372036854775807L)) goto L5;
    L7:
        int r0 = this.firstFrameState;
        if (r0 == 0) goto L35;
        if (r0 != 1) goto L12;
        return true;
    L12:
        if (r0 == 2) goto L30;
        if (r0 != 3) goto L27;
        long r122 = Util.msToUs(this.clock.elapsedRealtime()) - this.lastReleaseRealtimeUs;
        if (this.started == true) goto L18;
    L26:
        return false;
    L18:
        if (this.disableAdvancingTimestampChecks == true) goto L24;
        long r5 = this.initialPositionUs;
        if (r5 == (-9223372036854775807L)) goto L26;
        if (r5 == r8) goto L26;
    L24:
        if (this.frameTimingEvaluator.shouldForceReleaseFrame(r10, r122) == false) goto L26;
        return true;
    L27:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return false;
    L30:
        if (r8 < r12) goto L32;
        return true;
    L32:
        return false;
    L35:
        return this.started;
    L5:
        if (this.joiningRenderNextFrameImmediately == true) goto L7;
        return false;
    }

    public void allowReleaseFirstFrameBeforeStarted() {
        if (this.firstFrameState != 0) goto L6;
        this.firstFrameState = 1;
        return;
    }

    public int getFrameReleaseAction(long r19, long r21, long r23, long r25, boolean r27, boolean r28, FrameReleaseInfo r29) {
        FrameReleaseInfo.access$000(r29);
        if (this.started == false) goto L8;
        if (this.initialPositionUs != (-9223372036854775807L)) goto L8;
        this.initialPositionUs = r21;
    L8:
        if (this.lastPresentationTimeUs == r19) goto L10;
        this.frameReleaseHelper.onNextFrame(r19);
        this.lastPresentationTimeUs = r19;
    L10:
        FrameReleaseInfo.access$102(r29, calculateEarlyTimeUs(r21, r23, r19));
        if (r27 == false) goto L15;
        if (r28 == true) goto L15;
        return 3;
    L15:
        if (this.hasOutputSurface == true) goto L26;
        this.frameReadyWithoutSurface = true;
        if (this.frameTimingEvaluator.shouldIgnoreFrame(FrameReleaseInfo.access$100(r29), r21, r23, r28, true) == false) goto L20;
        return 4;
    L20:
        if (this.started == true) goto L22;
    L24:
        return 5;
    L22:
        if (FrameReleaseInfo.access$100(r29) >= NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS) goto L24;
        return 3;
    L26:
        if (shouldForceRelease(r21, FrameReleaseInfo.access$100(r29), r25) == false) goto L29;
        return 0;
    L29:
        if (this.started == true) goto L31;
    L52:
        return 5;
    L31:
        if (r21 == this.initialPositionUs) goto L52;
        long r1 = this.clock.nanoTime();
        FrameReleaseInfo.access$202(r29, this.frameReleaseHelper.adjustReleaseTime((FrameReleaseInfo.access$100(r29) * 1000) + r1));
        FrameReleaseInfo.access$102(r29, (FrameReleaseInfo.access$200(r29) - r1) / 1000);
        if (this.joiningDeadlineMs != (-9223372036854775807L)) goto L36;
    L38:
        boolean r8 = false;
    L40:
        if (this.frameTimingEvaluator.shouldIgnoreFrame(FrameReleaseInfo.access$100(r29), r21, r23, r28, r8) == false) goto L43;
        return 4;
    L43:
        if (this.frameTimingEvaluator.shouldDropFrame(FrameReleaseInfo.access$100(r29), r23, r28) == false) goto L49;
        if (r8 == false) goto L46;
        return 3;
    L46:
        return 2;
    L49:
        if (FrameReleaseInfo.access$100(r29) <= 50000) goto L51;
        return 5;
    L51:
        return 1;
    L36:
        if (this.joiningRenderNextFrameImmediately == true) goto L38;
        r8 = true;
        goto L40
    }

    public boolean isReady(boolean r9) {
        if (r9 == false) goto L13;
        if (this.firstFrameState != 3) goto L7;
    L10:
        this.joiningDeadlineMs = -9223372036854775807L;
        return true;
    L7:
        if (this.hasOutputSurface == true) goto L13;
        if (this.frameReadyWithoutSurface == true) goto L10;
    L13:
        if (this.joiningDeadlineMs != (-9223372036854775807L)) goto L16;
        return false;
    L16:
        if (this.clock.elapsedRealtime() >= this.joiningDeadlineMs) goto L18;
        return true;
    L18:
        this.joiningDeadlineMs = -9223372036854775807L;
        return false;
    }

    public void join(boolean r5) {
        this.joiningRenderNextFrameImmediately = r5;
        if (this.allowedJoiningTimeMs <= 0) goto L5;
        long r0 = this.clock.elapsedRealtime() + this.allowedJoiningTimeMs;
    L6:
        this.joiningDeadlineMs = r0;
        return;
    L5:
        r0 = -9223372036854775807L;
        goto L6
    }

    public boolean onFrameReleasedIsFirstFrame() {
        if (this.firstFrameState == 3) goto L5;
        boolean r0 = true;
    L6:
        this.firstFrameState = 3;
        this.lastReleaseRealtimeUs = Util.msToUs(this.clock.elapsedRealtime());
        return r0;
    L5:
        r0 = false;
        goto L6
    }

    public void onStarted() {
        this.started = true;
        this.lastReleaseRealtimeUs = Util.msToUs(this.clock.elapsedRealtime());
        this.frameReleaseHelper.onStarted();
    }

    public void onStopped() {
        this.started = false;
        this.joiningDeadlineMs = -9223372036854775807L;
        this.frameReleaseHelper.onStopped();
    }

    public void onStreamChanged(int r2) {
        if (r2 == 0) goto L13;
        if (r2 != 1) goto L6;
        this.firstFrameState = 0;
        return;
    L6:
        if (r2 != 2) goto L9;
        lowerFirstFrameState(2);
        return;
    L9:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return;
    L13:
        this.firstFrameState = 1;
    }

    public void reset() {
        this.frameReleaseHelper.onPositionReset();
        this.lastPresentationTimeUs = -9223372036854775807L;
        this.initialPositionUs = -9223372036854775807L;
        lowerFirstFrameState(1);
        this.joiningDeadlineMs = -9223372036854775807L;
    }

    public void setChangeFrameRateStrategy(int r1) {
        this.frameReleaseHelper.setChangeFrameRateStrategy(r1);
    }

    public void setClock(Clock r1) {
        this.clock = r1;
    }

    public void setFrameRate(float r1) {
        this.frameReleaseHelper.onFormatChanged(r1);
    }

    public void setOutputSurface(Surface r4) {
        if (r4 == null) goto L5;
        boolean r2 = true;
    L6:
        this.hasOutputSurface = r2;
        this.frameReadyWithoutSurface = false;
        this.frameReleaseHelper.onSurfaceChanged(r4);
        lowerFirstFrameState(1);
        return;
    L5:
        r2 = false;
        goto L6
    }

    public void setPlaybackSpeed(float r2) {
        if (r2 <= 0.0f) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkArgument(r0);
        if (r2 != this.playbackSpeed) goto L9;
        return;
    L9:
        this.playbackSpeed = r2;
        this.frameReleaseHelper.onPlaybackSpeed(r2);
        return;
    L5:
        r0 = false;
        goto L6
    }
}
