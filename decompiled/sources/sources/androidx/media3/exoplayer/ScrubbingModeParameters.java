package androidx.media3.exoplayer;

import com.google.common.collect.ImmutableSet;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class ScrubbingModeParameters {
    public static final ScrubbingModeParameters DEFAULT = null;
    public final boolean allowSkippingMediaCodecFlush;
    public final ImmutableSet<Integer> disabledTrackTypes;
    public final Double fractionalSeekToleranceAfter;
    public final Double fractionalSeekToleranceBefore;

    @Deprecated
    public final boolean isMediaCodecFlushEnabled;
    public final boolean shouldEnableDynamicScheduling;
    public final boolean shouldIncreaseCodecOperatingRate;
    public final boolean useDecodeOnlyFlag;

    /* renamed from: androidx.media3.exoplayer.ScrubbingModeParameters$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder {
        private boolean allowSkippingMediaCodecFlush;
        private ImmutableSet<Integer> disabledTrackTypes;
        private Double fractionalSeekToleranceAfter;
        private Double fractionalSeekToleranceBefore;
        private boolean shouldEnableDynamicScheduling;
        private boolean shouldIncreaseCodecOperatingRate;
        private boolean useDecodeOnlyFlag;

        public Builder() {
            this.disabledTrackTypes = ImmutableSet.of(1, 5);
            this.shouldIncreaseCodecOperatingRate = true;
            this.allowSkippingMediaCodecFlush = true;
            this.shouldEnableDynamicScheduling = true;
            this.useDecodeOnlyFlag = true;
        }

        public static /* synthetic */ ImmutableSet access$100(Builder r0) {
            return r0.disabledTrackTypes;
        }

        public static /* synthetic */ Double access$200(Builder r0) {
            return r0.fractionalSeekToleranceBefore;
        }

        public static /* synthetic */ Double access$300(Builder r0) {
            return r0.fractionalSeekToleranceAfter;
        }

        public static /* synthetic */ boolean access$400(Builder r0) {
            return r0.shouldIncreaseCodecOperatingRate;
        }

        public static /* synthetic */ boolean access$500(Builder r0) {
            return r0.allowSkippingMediaCodecFlush;
        }

        public static /* synthetic */ boolean access$600(Builder r0) {
            return r0.shouldEnableDynamicScheduling;
        }

        public static /* synthetic */ boolean access$700(Builder r0) {
            return r0.useDecodeOnlyFlag;
        }

        public ScrubbingModeParameters build() {
            return new ScrubbingModeParameters(this, null);
        }
    }

    static {
        DEFAULT = new Builder().build();
    }

    private ScrubbingModeParameters(Builder r2) {
        this.disabledTrackTypes = Builder.access$100(r2);
        this.fractionalSeekToleranceBefore = Builder.access$200(r2);
        this.fractionalSeekToleranceAfter = Builder.access$300(r2);
        this.shouldIncreaseCodecOperatingRate = Builder.access$400(r2);
        this.isMediaCodecFlushEnabled = !Builder.access$500(r2);
        this.allowSkippingMediaCodecFlush = Builder.access$500(r2);
        this.shouldEnableDynamicScheduling = Builder.access$600(r2);
        this.useDecodeOnlyFlag = Builder.access$700(r2);
    }

    public /* synthetic */ ScrubbingModeParameters(Builder r1, AnonymousClass1 r2) {
        this(r1);
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof ScrubbingModeParameters) == true) goto L5;
        return false;
    L5:
        ScrubbingModeParameters r42 = (ScrubbingModeParameters) r4;
        if (this.disabledTrackTypes.equals(r42.disabledTrackTypes) == true) goto L8;
    L21:
        return false;
    L8:
        if (this.allowSkippingMediaCodecFlush != r42.allowSkippingMediaCodecFlush) goto L21;
        if (Objects.equals(this.fractionalSeekToleranceBefore, r42.fractionalSeekToleranceBefore) == false) goto L21;
        if (Objects.equals(this.fractionalSeekToleranceAfter, r42.fractionalSeekToleranceAfter) == false) goto L21;
        if (this.shouldIncreaseCodecOperatingRate != r42.shouldIncreaseCodecOperatingRate) goto L21;
        if (this.shouldEnableDynamicScheduling != r42.shouldEnableDynamicScheduling) goto L21;
        if (this.useDecodeOnlyFlag != r42.useDecodeOnlyFlag) goto L21;
        return true;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.disabledTrackTypes, this.fractionalSeekToleranceBefore, this.fractionalSeekToleranceAfter, Boolean.valueOf(this.shouldIncreaseCodecOperatingRate), Boolean.valueOf(this.allowSkippingMediaCodecFlush), Boolean.valueOf(this.shouldEnableDynamicScheduling), Boolean.valueOf(this.useDecodeOnlyFlag)});
    }
}
