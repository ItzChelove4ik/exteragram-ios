package androidx.media3.exoplayer.video;

import android.util.Range;
import androidx.media3.common.util.Assertions;

/* loaded from: classes4.dex */
class VideoFrameReleaseEarlyTimeForecaster {
    private double derivativeOfEarlyTime;
    private Range<Double> derivativeOfEarlyTimeRange;
    private long lastFrameEarlyUs;
    private long lastFramePresentationTimeUs;

    public VideoFrameReleaseEarlyTimeForecaster(float r7) {
        if (r7 <= 0.0f) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkArgument(r0);
        Range<Double> r02 = new Range(Double.valueOf(0.0d), Double.valueOf(1.0d / r7));
        this.derivativeOfEarlyTimeRange = r02;
        this.derivativeOfEarlyTime = ((Double) r02.getUpper()).doubleValue();
        this.lastFramePresentationTimeUs = -9223372036854775807L;
        this.lastFrameEarlyUs = -9223372036854775807L;
        return;
    L5:
        r0 = false;
        goto L6
    }

    private double calculateDerivativeFromLastFrame(long r7, long r9) {
        long r0 = this.lastFramePresentationTimeUs;
        if (r0 == (-9223372036854775807L)) goto L11;
        if (this.lastFrameEarlyUs == (-9223372036854775807L)) goto L11;
        if (r7 == r0) goto L11;
        return (r9 - r4) / (r7 - r0);
    L11:
        return ((Double) this.derivativeOfEarlyTimeRange.getUpper()).doubleValue();
    }

    private void updateDerivativeWithExponentialMovingAverage(double r5) {
        this.derivativeOfEarlyTime = (this.derivativeOfEarlyTime * 0.800000011920929d) + (r5 * 0.20000000298023224d);
    }

    public void onVideoFrameProcessed(long r6, long r8) {
        boolean r3 = false;
        if (r6 == (-9223372036854775807L)) goto L5;
        boolean r2 = true;
    L6:
        Assertions.checkArgument(r2);
        if (r8 == (-9223372036854775807L)) goto L9;
        r3 = true;
    L9:
        Assertions.checkArgument(r3);
        updateDerivativeWithExponentialMovingAverage(((Double) this.derivativeOfEarlyTimeRange.clamp(Double.valueOf(calculateDerivativeFromLastFrame(r6, r8)))).doubleValue());
        this.lastFramePresentationTimeUs = r6;
        this.lastFrameEarlyUs = r8;
        return;
    L5:
        r2 = false;
        goto L6
    }

    public long predictEarlyUs(long r6) {
        if (this.lastFramePresentationTimeUs != (-9223372036854775807L)) goto L6;
        return -9223372036854775807L;
    L6:
        return (long) (this.lastFrameEarlyUs + ((r6 - r0) * this.derivativeOfEarlyTime));
    }

    public void reset() {
        this.derivativeOfEarlyTime = ((Double) this.derivativeOfEarlyTimeRange.getUpper()).doubleValue();
        this.lastFramePresentationTimeUs = -9223372036854775807L;
        this.lastFrameEarlyUs = -9223372036854775807L;
    }

    public void setPlaybackSpeed(float r7) {
        if (r7 <= 0.0f) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkArgument(r0);
        this.derivativeOfEarlyTimeRange = new Range(Double.valueOf(0.0d), Double.valueOf(1.0d / r7));
        reset();
        return;
    L5:
        r0 = false;
        goto L6
    }
}
