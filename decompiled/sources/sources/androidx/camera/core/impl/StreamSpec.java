package androidx.camera.core.impl;

import android.util.Range;
import android.util.Size;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.impl.AutoValue_StreamSpec;

/* loaded from: classes3.dex */
public abstract class StreamSpec {
    public static final Range<Integer> FRAME_RATE_RANGE_UNSPECIFIED = null;

    public static abstract class Builder {
        public Builder() {
        }

        public abstract StreamSpec build();

        public abstract Builder setDynamicRange(DynamicRange r1);

        public abstract Builder setExpectedFrameRateRange(Range<Integer> r1);

        public abstract Builder setImplementationOptions(Config r1);

        public abstract Builder setOriginalConfiguredResolution(Size r1);

        public abstract Builder setResolution(Size r1);

        public abstract Builder setSessionType(int r1);

        public abstract Builder setZslDisabled(boolean r1);
    }

    static {
        FRAME_RATE_RANGE_UNSPECIFIED = new Range(0, 0);
    }

    public StreamSpec() {
    }

    public static Builder builder(Size r2) {
        return new AutoValue_StreamSpec.Builder().setResolution(r2).setOriginalConfiguredResolution(r2).setSessionType(0).setExpectedFrameRateRange(FRAME_RATE_RANGE_UNSPECIFIED).setDynamicRange(DynamicRange.SDR).setZslDisabled(false);
    }

    public abstract DynamicRange getDynamicRange();

    public abstract Range<Integer> getExpectedFrameRateRange();

    public abstract Config getImplementationOptions();

    public abstract Size getOriginalConfiguredResolution();

    public abstract Size getResolution();

    public abstract int getSessionType();

    public abstract boolean getZslDisabled();

    public abstract Builder toBuilder();
}
