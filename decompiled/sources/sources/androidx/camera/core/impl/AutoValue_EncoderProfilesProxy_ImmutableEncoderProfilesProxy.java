package androidx.camera.core.impl;

import androidx.camera.core.impl.EncoderProfilesProxy;
import c.f$$ExternalSyntheticBUOutline2;
import java.util.List;

/* loaded from: classes3.dex */
final class AutoValue_EncoderProfilesProxy_ImmutableEncoderProfilesProxy extends EncoderProfilesProxy.ImmutableEncoderProfilesProxy {
    private final List<EncoderProfilesProxy.AudioProfileProxy> audioProfiles;
    private final int defaultDurationSeconds;
    private final int recommendedFileFormat;
    private final List<EncoderProfilesProxy.VideoProfileProxy> videoProfiles;

    public AutoValue_EncoderProfilesProxy_ImmutableEncoderProfilesProxy(int r1, int r2, List<EncoderProfilesProxy.AudioProfileProxy> r3, List<EncoderProfilesProxy.VideoProfileProxy> r4) {
        this.defaultDurationSeconds = r1;
        this.recommendedFileFormat = r2;
        if (r3 == null) goto L10;
        this.audioProfiles = r3;
        if (r4 == null) goto L8;
        this.videoProfiles = r4;
        return;
    L8:
        f$$ExternalSyntheticBUOutline2.m("Null videoProfiles");
        throw null;
    L10:
        f$$ExternalSyntheticBUOutline2.m("Null audioProfiles");
        throw null;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof EncoderProfilesProxy.ImmutableEncoderProfilesProxy) == false) goto L16;
        EncoderProfilesProxy.ImmutableEncoderProfilesProxy r52 = (EncoderProfilesProxy.ImmutableEncoderProfilesProxy) r5;
        if (this.defaultDurationSeconds != r52.getDefaultDurationSeconds()) goto L16;
        if (this.recommendedFileFormat != r52.getRecommendedFileFormat()) goto L16;
        if (this.audioProfiles.equals(r52.getAudioProfiles()) == false) goto L16;
        if (this.videoProfiles.equals(r52.getVideoProfiles()) == false) goto L16;
        return true;
    L16:
        return false;
    }

    @Override // androidx.camera.core.impl.EncoderProfilesProxy
    public List<EncoderProfilesProxy.AudioProfileProxy> getAudioProfiles() {
        return this.audioProfiles;
    }

    @Override // androidx.camera.core.impl.EncoderProfilesProxy
    public int getDefaultDurationSeconds() {
        return this.defaultDurationSeconds;
    }

    @Override // androidx.camera.core.impl.EncoderProfilesProxy
    public int getRecommendedFileFormat() {
        return this.recommendedFileFormat;
    }

    @Override // androidx.camera.core.impl.EncoderProfilesProxy
    public List<EncoderProfilesProxy.VideoProfileProxy> getVideoProfiles() {
        return this.videoProfiles;
    }

    public int hashCode() {
        int r0 = (((((this.defaultDurationSeconds ^ 1000003) * 1000003) ^ this.recommendedFileFormat) * 1000003) ^ this.audioProfiles.hashCode()) * 1000003;
        return this.videoProfiles.hashCode() ^ r0;
    }

    public String toString() {
        return "ImmutableEncoderProfilesProxy{defaultDurationSeconds=" + this.defaultDurationSeconds + ", recommendedFileFormat=" + this.recommendedFileFormat + ", audioProfiles=" + this.audioProfiles + ", videoProfiles=" + this.videoProfiles + "}";
    }
}
