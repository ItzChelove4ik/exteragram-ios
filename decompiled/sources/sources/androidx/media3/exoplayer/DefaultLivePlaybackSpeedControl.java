package androidx.media3.exoplayer;

import android.os.SystemClock;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Util;
import com.google.common.primitives.Longs;

/* loaded from: classes4.dex */
public final class DefaultLivePlaybackSpeedControl implements LivePlaybackSpeedControl {
    private float adjustedPlaybackSpeed;
    private long currentTargetLiveOffsetUs;
    private final float fallbackMaxPlaybackSpeed;
    private final float fallbackMinPlaybackSpeed;
    private long idealTargetLiveOffsetUs;
    private long lastPlaybackSpeedUpdateMs;
    private final long maxLiveOffsetErrorUsForUnitSpeed;
    private float maxPlaybackSpeed;
    private long maxTargetLiveOffsetUs;
    private long mediaConfigurationTargetLiveOffsetUs;
    private float minPlaybackSpeed;
    private final float minPossibleLiveOffsetSmoothingFactor;
    private long minTargetLiveOffsetUs;
    private final long minUpdateIntervalMs;
    private final float proportionalControlFactor;
    private long smoothedMinPossibleLiveOffsetDeviationUs;
    private long smoothedMinPossibleLiveOffsetUs;
    private long targetLiveOffsetOverrideUs;
    private final long targetLiveOffsetRebufferDeltaUs;

    /* renamed from: androidx.media3.exoplayer.DefaultLivePlaybackSpeedControl$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder {
        private float fallbackMaxPlaybackSpeed;
        private float fallbackMinPlaybackSpeed;
        private long maxLiveOffsetErrorUsForUnitSpeed;
        private float minPossibleLiveOffsetSmoothingFactor;
        private long minUpdateIntervalMs;
        private float proportionalControlFactorUs;
        private long targetLiveOffsetIncrementOnRebufferUs;

        public Builder() {
            this.fallbackMinPlaybackSpeed = 0.97f;
            this.fallbackMaxPlaybackSpeed = 1.03f;
            this.minUpdateIntervalMs = 1000;
            this.proportionalControlFactorUs = 1.0E-7f;
            this.maxLiveOffsetErrorUsForUnitSpeed = Util.msToUs(20);
            this.targetLiveOffsetIncrementOnRebufferUs = Util.msToUs(500);
            this.minPossibleLiveOffsetSmoothingFactor = 0.999f;
        }

        public DefaultLivePlaybackSpeedControl build() {
            return new DefaultLivePlaybackSpeedControl(this.fallbackMinPlaybackSpeed, this.fallbackMaxPlaybackSpeed, this.minUpdateIntervalMs, this.proportionalControlFactorUs, this.maxLiveOffsetErrorUsForUnitSpeed, this.targetLiveOffsetIncrementOnRebufferUs, this.minPossibleLiveOffsetSmoothingFactor, null);
        }
    }

    private DefaultLivePlaybackSpeedControl(float r1, float r2, long r3, float r5, long r6, long r8, float r10) {
        this.fallbackMinPlaybackSpeed = r1;
        this.fallbackMaxPlaybackSpeed = r2;
        this.minUpdateIntervalMs = r3;
        this.proportionalControlFactor = r5;
        this.maxLiveOffsetErrorUsForUnitSpeed = r6;
        this.targetLiveOffsetRebufferDeltaUs = r8;
        this.minPossibleLiveOffsetSmoothingFactor = r10;
        this.mediaConfigurationTargetLiveOffsetUs = -9223372036854775807L;
        this.targetLiveOffsetOverrideUs = -9223372036854775807L;
        this.minTargetLiveOffsetUs = -9223372036854775807L;
        this.maxTargetLiveOffsetUs = -9223372036854775807L;
        this.minPlaybackSpeed = r1;
        this.maxPlaybackSpeed = r2;
        this.adjustedPlaybackSpeed = 1.0f;
        this.lastPlaybackSpeedUpdateMs = -9223372036854775807L;
        this.idealTargetLiveOffsetUs = -9223372036854775807L;
        this.currentTargetLiveOffsetUs = -9223372036854775807L;
        this.smoothedMinPossibleLiveOffsetUs = -9223372036854775807L;
        this.smoothedMinPossibleLiveOffsetDeviationUs = -9223372036854775807L;
    }

    public /* synthetic */ DefaultLivePlaybackSpeedControl(float r1, float r2, long r3, float r5, long r6, long r8, float r10, AnonymousClass1 r11) {
        this(r1, r2, r3, r5, r6, r8, r10);
    }

    private void adjustTargetLiveOffsetUs(long r13) {
        long r10 = this.smoothedMinPossibleLiveOffsetUs + (this.smoothedMinPossibleLiveOffsetDeviationUs * 3);
        if (this.currentTargetLiveOffsetUs <= r10) goto L6;
        float r132 = Util.msToUs(this.minUpdateIntervalMs);
        this.currentTargetLiveOffsetUs = Longs.max(new long[]{r10, this.idealTargetLiveOffsetUs, this.currentTargetLiveOffsetUs - (((long) ((this.adjustedPlaybackSpeed - 1.0f) * r132)) + ((long) ((this.maxPlaybackSpeed - 1.0f) * r132)))});
        return;
    L6:
        long r133 = Util.constrainValue(r13 - ((long) (Math.max(0.0f, this.adjustedPlaybackSpeed - 1.0f) / this.proportionalControlFactor)), this.currentTargetLiveOffsetUs, r10);
        this.currentTargetLiveOffsetUs = r133;
        long r0 = this.maxTargetLiveOffsetUs;
        if (r0 != (-9223372036854775807L)) goto L9;
        return;
    L9:
        if (r133 <= r0) goto L13;
        this.currentTargetLiveOffsetUs = r0;
        return;
    }

    private void maybeResetTargetLiveOffsetUs() {
        long r0 = this.mediaConfigurationTargetLiveOffsetUs;
        if (r0 == (-9223372036854775807L)) goto L18;
        long r4 = this.targetLiveOffsetOverrideUs;
        if (r4 != (-9223372036854775807L)) goto L20;
        long r42 = this.minTargetLiveOffsetUs;
        if (r42 != (-9223372036854775807L)) goto L10;
    L12:
        r4 = this.maxTargetLiveOffsetUs;
        if (r4 != (-9223372036854775807L)) goto L15;
    L17:
        r4 = r0;
        goto L20
    L15:
        if (r0 <= r4) goto L17;
    L10:
        if (r0 >= r42) goto L12;
        r0 = r42;
    L20:
        if (this.idealTargetLiveOffsetUs != r4) goto L22;
        return;
    L22:
        this.idealTargetLiveOffsetUs = r4;
        this.currentTargetLiveOffsetUs = r4;
        this.smoothedMinPossibleLiveOffsetUs = -9223372036854775807L;
        this.smoothedMinPossibleLiveOffsetDeviationUs = -9223372036854775807L;
        this.lastPlaybackSpeedUpdateMs = -9223372036854775807L;
        return;
    L18:
        r4 = -9223372036854775807L;
        goto L20
    }

    private static long smooth(long r0, long r2, float r4) {
        return (long) ((r0 * r4) + ((1.0f - r4) * r2));
    }

    private void updateSmoothedMinPossibleLiveOffsetUs(long r3, long r5) {
        long r32 = r3 - r5;
        long r52 = this.smoothedMinPossibleLiveOffsetUs;
        if (r52 != (-9223372036854775807L)) goto L6;
        this.smoothedMinPossibleLiveOffsetUs = r32;
        this.smoothedMinPossibleLiveOffsetDeviationUs = 0;
        return;
    L6:
        long r53 = Math.max(r32, smooth(r52, r32, this.minPossibleLiveOffsetSmoothingFactor));
        this.smoothedMinPossibleLiveOffsetUs = r53;
        long r33 = Math.abs(r32 - r53);
        this.smoothedMinPossibleLiveOffsetDeviationUs = smooth(this.smoothedMinPossibleLiveOffsetDeviationUs, r33, this.minPossibleLiveOffsetSmoothingFactor);
    }

    @Override // androidx.media3.exoplayer.LivePlaybackSpeedControl
    public float getAdjustedPlaybackSpeed(long r5, long r7) {
        if (this.mediaConfigurationTargetLiveOffsetUs != (-9223372036854775807L)) goto L5;
        return 1.0f;
    L5:
        updateSmoothedMinPossibleLiveOffsetUs(r5, r7);
        if (this.lastPlaybackSpeedUpdateMs != (-9223372036854775807L)) goto L8;
    L11:
        this.lastPlaybackSpeedUpdateMs = SystemClock.elapsedRealtime();
        adjustTargetLiveOffsetUs(r5);
        long r52 = r5 - this.currentTargetLiveOffsetUs;
        if (Math.abs(r52) >= this.maxLiveOffsetErrorUsForUnitSpeed) goto L14;
        this.adjustedPlaybackSpeed = 1.0f;
    L16:
        return this.adjustedPlaybackSpeed;
    L14:
        this.adjustedPlaybackSpeed = Util.constrainValue((this.proportionalControlFactor * r52) + 1.0f, this.minPlaybackSpeed, this.maxPlaybackSpeed);
        goto L16
    L8:
        if ((SystemClock.elapsedRealtime() - this.lastPlaybackSpeedUpdateMs) >= this.minUpdateIntervalMs) goto L11;
        return this.adjustedPlaybackSpeed;
    }

    @Override // androidx.media3.exoplayer.LivePlaybackSpeedControl
    public long getTargetLiveOffsetUs() {
        return this.currentTargetLiveOffsetUs;
    }

    @Override // androidx.media3.exoplayer.LivePlaybackSpeedControl
    public void notifyRebuffer() {
        long r0 = this.currentTargetLiveOffsetUs;
        if (r0 != (-9223372036854775807L)) goto L5;
        return;
    L5:
        long r02 = r0 + this.targetLiveOffsetRebufferDeltaUs;
        this.currentTargetLiveOffsetUs = r02;
        long r4 = this.maxTargetLiveOffsetUs;
        if (r4 != (-9223372036854775807L)) goto L8;
    L10:
        this.lastPlaybackSpeedUpdateMs = -9223372036854775807L;
        return;
    L8:
        if (r02 <= r4) goto L10;
        this.currentTargetLiveOffsetUs = r4;
        goto L10
    }

    @Override // androidx.media3.exoplayer.LivePlaybackSpeedControl
    public void setLiveConfiguration(MediaItem.LiveConfiguration r4) {
        this.mediaConfigurationTargetLiveOffsetUs = Util.msToUs(r4.targetOffsetMs);
        this.minTargetLiveOffsetUs = Util.msToUs(r4.minOffsetMs);
        this.maxTargetLiveOffsetUs = Util.msToUs(r4.maxOffsetMs);
        float r0 = r4.minPlaybackSpeed;
        if (r0 != (-3.4028235E38f)) goto L6;
        r0 = this.fallbackMinPlaybackSpeed;
    L6:
        this.minPlaybackSpeed = r0;
        float r42 = r4.maxPlaybackSpeed;
        if (r42 != (-3.4028235E38f)) goto L10;
        r42 = this.fallbackMaxPlaybackSpeed;
    L10:
        this.maxPlaybackSpeed = r42;
        if (r0 == 1.0f) goto L13;
    L15:
        maybeResetTargetLiveOffsetUs();
        return;
    L13:
        if (r42 != 1.0f) goto L15;
        this.mediaConfigurationTargetLiveOffsetUs = -9223372036854775807L;
        goto L15
    }

    @Override // androidx.media3.exoplayer.LivePlaybackSpeedControl
    public void setTargetLiveOffsetOverrideUs(long r1) {
        this.targetLiveOffsetOverrideUs = r1;
        maybeResetTargetLiveOffsetUs();
    }
}
