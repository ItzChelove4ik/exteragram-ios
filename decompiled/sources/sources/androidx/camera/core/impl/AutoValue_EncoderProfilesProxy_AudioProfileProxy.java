package androidx.camera.core.impl;

import androidx.camera.core.impl.EncoderProfilesProxy;
import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes3.dex */
final class AutoValue_EncoderProfilesProxy_AudioProfileProxy extends EncoderProfilesProxy.AudioProfileProxy {
    private final int bitrate;
    private final int channels;
    private final int codec;
    private final String mediaType;
    private final int profile;
    private final int sampleRate;

    public AutoValue_EncoderProfilesProxy_AudioProfileProxy(int r1, String r2, int r3, int r4, int r5, int r6) {
        this.codec = r1;
        if (r2 == null) goto L6;
        this.mediaType = r2;
        this.bitrate = r3;
        this.sampleRate = r4;
        this.channels = r5;
        this.profile = r6;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("Null mediaType");
        throw null;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof EncoderProfilesProxy.AudioProfileProxy) == false) goto L20;
        EncoderProfilesProxy.AudioProfileProxy r52 = (EncoderProfilesProxy.AudioProfileProxy) r5;
        if (this.codec != r52.getCodec()) goto L20;
        if (this.mediaType.equals(r52.getMediaType()) == false) goto L20;
        if (this.bitrate != r52.getBitrate()) goto L20;
        if (this.sampleRate != r52.getSampleRate()) goto L20;
        if (this.channels != r52.getChannels()) goto L20;
        if (this.profile != r52.getProfile()) goto L20;
        return true;
    L20:
        return false;
    }

    @Override // androidx.camera.core.impl.EncoderProfilesProxy.AudioProfileProxy
    public int getBitrate() {
        return this.bitrate;
    }

    @Override // androidx.camera.core.impl.EncoderProfilesProxy.AudioProfileProxy
    public int getChannels() {
        return this.channels;
    }

    @Override // androidx.camera.core.impl.EncoderProfilesProxy.AudioProfileProxy
    public int getCodec() {
        return this.codec;
    }

    @Override // androidx.camera.core.impl.EncoderProfilesProxy.AudioProfileProxy
    public String getMediaType() {
        return this.mediaType;
    }

    @Override // androidx.camera.core.impl.EncoderProfilesProxy.AudioProfileProxy
    public int getProfile() {
        return this.profile;
    }

    @Override // androidx.camera.core.impl.EncoderProfilesProxy.AudioProfileProxy
    public int getSampleRate() {
        return this.sampleRate;
    }

    public int hashCode() {
        int r0 = (((((((((this.codec ^ 1000003) * 1000003) ^ this.mediaType.hashCode()) * 1000003) ^ this.bitrate) * 1000003) ^ this.sampleRate) * 1000003) ^ this.channels) * 1000003;
        return this.profile ^ r0;
    }

    public String toString() {
        return "AudioProfileProxy{codec=" + this.codec + ", mediaType=" + this.mediaType + ", bitrate=" + this.bitrate + ", sampleRate=" + this.sampleRate + ", channels=" + this.channels + ", profile=" + this.profile + "}";
    }
}
