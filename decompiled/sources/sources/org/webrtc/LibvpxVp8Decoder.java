package org.webrtc;

/* loaded from: classes6.dex */
public class LibvpxVp8Decoder extends WrappedNativeVideoDecoder {
    public LibvpxVp8Decoder() {
    }

    public static native long nativeCreateDecoder(long r0);

    @Override // org.webrtc.WrappedNativeVideoDecoder, org.webrtc.VideoDecoder
    public long createNative(long r1) {
        return nativeCreateDecoder(r1);
    }
}
