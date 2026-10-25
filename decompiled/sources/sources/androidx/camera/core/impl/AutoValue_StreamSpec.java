package androidx.camera.core.impl;

import android.util.Range;
import android.util.Size;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.impl.StreamSpec;
import c.f$$ExternalSyntheticBUOutline2;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
final class AutoValue_StreamSpec extends StreamSpec {
    private final DynamicRange dynamicRange;
    private final Range<Integer> expectedFrameRateRange;
    private final Config implementationOptions;
    private final Size originalConfiguredResolution;
    private final Size resolution;
    private final int sessionType;
    private final boolean zslDisabled;

    /* renamed from: androidx.camera.core.impl.AutoValue_StreamSpec$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder extends StreamSpec.Builder {
        private DynamicRange dynamicRange;
        private Range<Integer> expectedFrameRateRange;
        private Config implementationOptions;
        private Size originalConfiguredResolution;
        private Size resolution;
        private Integer sessionType;
        private Boolean zslDisabled;

        public Builder() {
        }

        private Builder(StreamSpec r2) {
            this.resolution = r2.getResolution();
            this.originalConfiguredResolution = r2.getOriginalConfiguredResolution();
            this.dynamicRange = r2.getDynamicRange();
            this.sessionType = Integer.valueOf(r2.getSessionType());
            this.expectedFrameRateRange = r2.getExpectedFrameRateRange();
            this.implementationOptions = r2.getImplementationOptions();
            this.zslDisabled = Boolean.valueOf(r2.getZslDisabled());
        }

        public /* synthetic */ Builder(StreamSpec r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // androidx.camera.core.impl.StreamSpec.Builder
        public StreamSpec build() {
            if (this.resolution != null) goto L5;
            String r0 = " resolution";
        L7:
            if (this.originalConfiguredResolution != null) goto L10;
            r0 = r0.concat(" originalConfiguredResolution");
        L10:
            if (this.dynamicRange != null) goto L13;
            r0 = r0.concat(" dynamicRange");
        L13:
            if (this.sessionType != null) goto L16;
            r0 = r0.concat(" sessionType");
        L16:
            if (this.expectedFrameRateRange != null) goto L19;
            r0 = r0.concat(" expectedFrameRateRange");
        L19:
            if (this.zslDisabled != null) goto L22;
            r0 = r0.concat(" zslDisabled");
        L22:
            if (r0.isEmpty() == true) goto L24;
            Segment$$ExternalSyntheticBUOutline1.m("Missing required properties:".concat(r0));
            return null;
        L24:
            return new AutoValue_StreamSpec(this.resolution, this.originalConfiguredResolution, this.dynamicRange, this.sessionType.intValue(), this.expectedFrameRateRange, this.implementationOptions, this.zslDisabled.booleanValue(), null);
        L5:
            r0 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L7
        }

        @Override // androidx.camera.core.impl.StreamSpec.Builder
        public StreamSpec.Builder setDynamicRange(DynamicRange r1) {
            if (r1 == null) goto L5;
            this.dynamicRange = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null dynamicRange");
            return null;
        }

        @Override // androidx.camera.core.impl.StreamSpec.Builder
        public StreamSpec.Builder setExpectedFrameRateRange(Range<Integer> r1) {
            if (r1 == null) goto L5;
            this.expectedFrameRateRange = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null expectedFrameRateRange");
            return null;
        }

        @Override // androidx.camera.core.impl.StreamSpec.Builder
        public StreamSpec.Builder setImplementationOptions(Config r1) {
            this.implementationOptions = r1;
            return this;
        }

        @Override // androidx.camera.core.impl.StreamSpec.Builder
        public StreamSpec.Builder setOriginalConfiguredResolution(Size r1) {
            if (r1 == null) goto L5;
            this.originalConfiguredResolution = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null originalConfiguredResolution");
            return null;
        }

        @Override // androidx.camera.core.impl.StreamSpec.Builder
        public StreamSpec.Builder setResolution(Size r1) {
            if (r1 == null) goto L5;
            this.resolution = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null resolution");
            return null;
        }

        @Override // androidx.camera.core.impl.StreamSpec.Builder
        public StreamSpec.Builder setSessionType(int r1) {
            this.sessionType = Integer.valueOf(r1);
            return this;
        }

        @Override // androidx.camera.core.impl.StreamSpec.Builder
        public StreamSpec.Builder setZslDisabled(boolean r1) {
            this.zslDisabled = Boolean.valueOf(r1);
            return this;
        }
    }

    private AutoValue_StreamSpec(Size r1, Size r2, DynamicRange r3, int r4, Range<Integer> r5, Config r6, boolean r7) {
        this.resolution = r1;
        this.originalConfiguredResolution = r2;
        this.dynamicRange = r3;
        this.sessionType = r4;
        this.expectedFrameRateRange = r5;
        this.implementationOptions = r6;
        this.zslDisabled = r7;
    }

    public /* synthetic */ AutoValue_StreamSpec(Size r1, Size r2, DynamicRange r3, int r4, Range r5, Config r6, boolean r7, AnonymousClass1 r8) {
        this(r1, r2, r3, r4, r5, r6, r7);
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof StreamSpec) == false) goto L27;
        StreamSpec r52 = (StreamSpec) r5;
        if (this.resolution.equals(r52.getResolution()) == false) goto L27;
        if (this.originalConfiguredResolution.equals(r52.getOriginalConfiguredResolution()) == false) goto L27;
        if (this.dynamicRange.equals(r52.getDynamicRange()) == false) goto L27;
        if (this.sessionType != r52.getSessionType()) goto L27;
        if (this.expectedFrameRateRange.equals(r52.getExpectedFrameRateRange()) == false) goto L27;
        Config r1 = this.implementationOptions;
        if (r1 != null) goto L23;
        if (r52.getImplementationOptions() != null) goto L27;
    L25:
        if (this.zslDisabled != r52.getZslDisabled()) goto L27;
        return true;
    L23:
        if (r1.equals(r52.getImplementationOptions()) == true) goto L25;
    L27:
        return false;
    }

    @Override // androidx.camera.core.impl.StreamSpec
    public DynamicRange getDynamicRange() {
        return this.dynamicRange;
    }

    @Override // androidx.camera.core.impl.StreamSpec
    public Range<Integer> getExpectedFrameRateRange() {
        return this.expectedFrameRateRange;
    }

    @Override // androidx.camera.core.impl.StreamSpec
    public Config getImplementationOptions() {
        return this.implementationOptions;
    }

    @Override // androidx.camera.core.impl.StreamSpec
    public Size getOriginalConfiguredResolution() {
        return this.originalConfiguredResolution;
    }

    @Override // androidx.camera.core.impl.StreamSpec
    public Size getResolution() {
        return this.resolution;
    }

    @Override // androidx.camera.core.impl.StreamSpec
    public int getSessionType() {
        return this.sessionType;
    }

    @Override // androidx.camera.core.impl.StreamSpec
    public boolean getZslDisabled() {
        return this.zslDisabled;
    }

    public int hashCode() {
        int r0 = (((((((((this.resolution.hashCode() ^ 1000003) * 1000003) ^ this.originalConfiguredResolution.hashCode()) * 1000003) ^ this.dynamicRange.hashCode()) * 1000003) ^ this.sessionType) * 1000003) ^ this.expectedFrameRateRange.hashCode()) * 1000003;
        Config r2 = this.implementationOptions;
        if (r2 != null) goto L5;
        int r22 = 0;
    L6:
        int r02 = (r0 ^ r22) * 1000003;
        if (this.zslDisabled == false) goto L9;
        int r3 = 1231;
    L11:
        return r3 ^ r02;
    L9:
        r3 = 1237;
        goto L11
    L5:
        r22 = r2.hashCode();
        goto L6
    }

    @Override // androidx.camera.core.impl.StreamSpec
    public StreamSpec.Builder toBuilder() {
        return new Builder(this, null);
    }

    public String toString() {
        return "StreamSpec{resolution=" + this.resolution + ", originalConfiguredResolution=" + this.originalConfiguredResolution + ", dynamicRange=" + this.dynamicRange + ", sessionType=" + this.sessionType + ", expectedFrameRateRange=" + this.expectedFrameRateRange + ", implementationOptions=" + this.implementationOptions + ", zslDisabled=" + this.zslDisabled + "}";
    }
}
