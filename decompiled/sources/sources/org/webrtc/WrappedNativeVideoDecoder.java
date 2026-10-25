package org.webrtc;

import org.webrtc.VideoDecoder;

/* loaded from: classes6.dex */
public abstract class WrappedNativeVideoDecoder implements VideoDecoder {
    public WrappedNativeVideoDecoder() {
    }

    @Override // org.webrtc.VideoDecoder
    public abstract long createNative(long r1);

    @Override // org.webrtc.VideoDecoder
    public final VideoCodecStatus decode(EncodedImage r1, VideoDecoder.DecodeInfo r2) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override // org.webrtc.VideoDecoder
    public final String getImplementationName() {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override // org.webrtc.VideoDecoder
    public final VideoCodecStatus initDecode(VideoDecoder.Settings r1, VideoDecoder.Callback r2) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override // org.webrtc.VideoDecoder
    public final VideoCodecStatus release() {
        throw new UnsupportedOperationException("Not implemented.");
    }
}
