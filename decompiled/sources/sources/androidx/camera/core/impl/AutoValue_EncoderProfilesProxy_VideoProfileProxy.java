package androidx.camera.core.impl;

import androidx.camera.core.impl.EncoderProfilesProxy;
import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes3.dex */
final class AutoValue_EncoderProfilesProxy_VideoProfileProxy extends EncoderProfilesProxy.VideoProfileProxy {
    private final int bitDepth;
    private final int bitrate;
    private final int chromaSubsampling;
    private final int codec;
    private final int frameRate;
    private final int hdrFormat;
    private final int height;
    private final String mediaType;
    private final int profile;
    private final int width;

    public AutoValue_EncoderProfilesProxy_VideoProfileProxy(int r1, String r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9, int r10) {
        this.codec = r1;
        if (r2 == null) goto L6;
        this.mediaType = r2;
        this.bitrate = r3;
        this.frameRate = r4;
        this.width = r5;
        this.height = r6;
        this.profile = r7;
        this.bitDepth = r8;
        this.chromaSubsampling = r9;
        this.hdrFormat = r10;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("Null mediaType");
        throw null;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof EncoderProfilesProxy.VideoProfileProxy) == false) goto L28;
        EncoderProfilesProxy.VideoProfileProxy r52 = (EncoderProfilesProxy.VideoProfileProxy) r5;
        if (this.codec != r52.getCodec()) goto L28;
        if (this.mediaType.equals(r52.getMediaType()) == false) goto L28;
        if (this.bitrate != r52.getBitrate()) goto L28;
        if (this.frameRate != r52.getFrameRate()) goto L28;
        if (this.width != r52.getWidth()) goto L28;
        if (this.height != r52.getHeight()) goto L28;
        if (this.profile != r52.getProfile()) goto L28;
        if (this.bitDepth != r52.getBitDepth()) goto L28;
        if (this.chromaSubsampling != r52.getChromaSubsampling()) goto L28;
        if (this.hdrFormat != r52.getHdrFormat()) goto L28;
        return true;
    L28:
        return false;
    }

    @Override // androidx.camera.core.impl.EncoderProfilesProxy.VideoProfileProxy
    public int getBitDepth() {
        return this.bitDepth;
    }

    @Override // androidx.camera.core.impl.EncoderProfilesProxy.VideoProfileProxy
    public int getBitrate() {
        return this.bitrate;
    }

    @Override // androidx.camera.core.impl.EncoderProfilesProxy.VideoProfileProxy
    public int getChromaSubsampling() {
        return this.chromaSubsampling;
    }

    @Override // androidx.camera.core.impl.EncoderProfilesProxy.VideoProfileProxy
    public int getCodec() {
        return this.codec;
    }

    @Override // androidx.camera.core.impl.EncoderProfilesProxy.VideoProfileProxy
    public int getFrameRate() {
        return this.frameRate;
    }

    @Override // androidx.camera.core.impl.EncoderProfilesProxy.VideoProfileProxy
    public int getHdrFormat() {
        return this.hdrFormat;
    }

    @Override // androidx.camera.core.impl.EncoderProfilesProxy.VideoProfileProxy
    public int getHeight() {
        return this.height;
    }

    @Override // androidx.camera.core.impl.EncoderProfilesProxy.VideoProfileProxy
    public String getMediaType() {
        return this.mediaType;
    }

    @Override // androidx.camera.core.impl.EncoderProfilesProxy.VideoProfileProxy
    public int getProfile() {
        return this.profile;
    }

    @Override // androidx.camera.core.impl.EncoderProfilesProxy.VideoProfileProxy
    public int getWidth() {
        return this.width;
    }

    public int hashCode() {
        int r0 = (((((((((((((((((this.codec ^ 1000003) * 1000003) ^ this.mediaType.hashCode()) * 1000003) ^ this.bitrate) * 1000003) ^ this.frameRate) * 1000003) ^ this.width) * 1000003) ^ this.height) * 1000003) ^ this.profile) * 1000003) ^ this.bitDepth) * 1000003) ^ this.chromaSubsampling) * 1000003;
        return this.hdrFormat ^ r0;
    }

    public String toString() {
        return "VideoProfileProxy{codec=" + this.codec + ", mediaType=" + this.mediaType + ", bitrate=" + this.bitrate + ", frameRate=" + this.frameRate + ", width=" + this.width + ", height=" + this.height + ", profile=" + this.profile + ", bitDepth=" + this.bitDepth + ", chromaSubsampling=" + this.chromaSubsampling + ", hdrFormat=" + this.hdrFormat + "}";
    }
}
