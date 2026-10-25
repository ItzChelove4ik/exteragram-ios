package org.webrtc;

/* loaded from: classes6.dex */
public class VideoDecoderFallback extends WrappedNativeVideoDecoder {
    private final VideoDecoder fallback;
    private final VideoDecoder primary;

    public VideoDecoderFallback(VideoDecoder r1, VideoDecoder r2) {
        this.fallback = r1;
        this.primary = r2;
    }

    private static native long nativeCreate(long r0, VideoDecoder r2, VideoDecoder r3);

    @Override // org.webrtc.WrappedNativeVideoDecoder, org.webrtc.VideoDecoder
    public long createNative(long r2) {
        return nativeCreate(r2, this.fallback, this.primary);
    }
}
