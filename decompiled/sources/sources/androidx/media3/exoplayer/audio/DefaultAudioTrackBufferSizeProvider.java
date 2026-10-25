package androidx.media3.exoplayer.audio;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.audio.DefaultAudioSink;
import androidx.media3.extractor.ExtractorUtil;
import com.google.common.math.IntMath;
import com.google.common.primitives.Ints;
import java.math.RoundingMode;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public class DefaultAudioTrackBufferSizeProvider implements DefaultAudioSink.AudioTrackBufferSizeProvider {
    public final int ac3BufferMultiplicationFactor;
    public final int dtshdBufferMultiplicationFactor;
    protected final int maxPcmBufferDurationUs;
    protected final int minPcmBufferDurationUs;
    protected final int offloadBufferDurationUs;
    protected final int passthroughBufferDurationUs;
    protected final int pcmBufferMultiplicationFactor;

    public static class Builder {
        private int ac3BufferMultiplicationFactor;
        private int dtshdBufferMultiplicationFactor;
        private int maxPcmBufferDurationUs;
        private int minPcmBufferDurationUs;
        private int offloadBufferDurationUs;
        private int passthroughBufferDurationUs;
        private int pcmBufferMultiplicationFactor;

        public Builder() {
            this.minPcmBufferDurationUs = 250000;
            this.maxPcmBufferDurationUs = 750000;
            this.pcmBufferMultiplicationFactor = 4;
            this.passthroughBufferDurationUs = 250000;
            this.offloadBufferDurationUs = 50000000;
            this.ac3BufferMultiplicationFactor = 2;
            this.dtshdBufferMultiplicationFactor = 4;
        }

        public static /* synthetic */ int access$000(Builder r0) {
            return r0.minPcmBufferDurationUs;
        }

        public static /* synthetic */ int access$100(Builder r0) {
            return r0.maxPcmBufferDurationUs;
        }

        public static /* synthetic */ int access$200(Builder r0) {
            return r0.pcmBufferMultiplicationFactor;
        }

        public static /* synthetic */ int access$300(Builder r0) {
            return r0.passthroughBufferDurationUs;
        }

        public static /* synthetic */ int access$400(Builder r0) {
            return r0.offloadBufferDurationUs;
        }

        public static /* synthetic */ int access$500(Builder r0) {
            return r0.ac3BufferMultiplicationFactor;
        }

        public static /* synthetic */ int access$600(Builder r0) {
            return r0.dtshdBufferMultiplicationFactor;
        }

        public DefaultAudioTrackBufferSizeProvider build() {
            return new DefaultAudioTrackBufferSizeProvider(this);
        }
    }

    public DefaultAudioTrackBufferSizeProvider(Builder r2) {
        this.minPcmBufferDurationUs = Builder.access$000(r2);
        this.maxPcmBufferDurationUs = Builder.access$100(r2);
        this.pcmBufferMultiplicationFactor = Builder.access$200(r2);
        this.passthroughBufferDurationUs = Builder.access$300(r2);
        this.offloadBufferDurationUs = Builder.access$400(r2);
        this.ac3BufferMultiplicationFactor = Builder.access$500(r2);
        this.dtshdBufferMultiplicationFactor = Builder.access$600(r2);
    }

    public static int durationUsToBytes(int r2, int r3, int r4) {
        return Ints.checkedCast(((r2 * r3) * r4) / 1000000);
    }

    private static int getNonPcmMaximumEncodedRateBytesPerSecond(int r1) {
        int r12 = ExtractorUtil.getMaximumEncodedRateBytesPerSecond(r1);
        if (r12 == (-2147483647)) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkState(r0);
        return r12;
    L5:
        r0 = false;
        goto L6
    }

    public int get1xBufferSizeInBytes(int r1, int r2, int r3, int r4, int r5, int r6) {
        if (r3 == 0) goto L14;
        if (r3 == 1) goto L12;
        if (r3 == 2) goto L8;
        Segment$$ExternalSyntheticBUOutline0.m();
        return 0;
    L8:
        return getPassthroughBufferSizeInBytes(r2, r6);
    L12:
        return getOffloadBufferSizeInBytes(r2);
    L14:
        return getPcmBufferSizeInBytes(r1, r5, r4);
    }

    @Override // androidx.media3.exoplayer.audio.DefaultAudioSink.AudioTrackBufferSizeProvider
    public int getBufferSizeInBytes(int r1, int r2, int r3, int r4, int r5, int r6, double r7) {
        return (((Math.max(r1, (int) (get1xBufferSizeInBytes(r1, r2, r3, r4, r5, r6) * r7)) + r4) - 1) / r4) * r4;
    }

    public int getOffloadBufferSizeInBytes(int r3) {
        return Ints.checkedCast((this.offloadBufferDurationUs * getNonPcmMaximumEncodedRateBytesPerSecond(r3)) / 1000000);
    }

    public int getPassthroughBufferSizeInBytes(int r4, int r5) {
        int r0 = this.passthroughBufferDurationUs;
        if (r4 != 5) goto L6;
        int r3 = this.ac3BufferMultiplicationFactor;
    L5:
        r0 = r0 * r3;
    L9:
        if (r5 == (-1)) goto L11;
        int r32 = IntMath.divide(r5, 8, RoundingMode.CEILING);
    L13:
        return Ints.checkedCast((r0 * r32) / 1000000);
    L11:
        r32 = getNonPcmMaximumEncodedRateBytesPerSecond(r4);
        goto L13
    L6:
        if (r4 != 8) goto L9;
        r3 = this.dtshdBufferMultiplicationFactor;
        goto L5
    }

    public int getPcmBufferSizeInBytes(int r2, int r3, int r4) {
        return Util.constrainValue(r2 * this.pcmBufferMultiplicationFactor, durationUsToBytes(this.minPcmBufferDurationUs, r3, r4), durationUsToBytes(this.maxPcmBufferDurationUs, r3, r4));
    }
}
