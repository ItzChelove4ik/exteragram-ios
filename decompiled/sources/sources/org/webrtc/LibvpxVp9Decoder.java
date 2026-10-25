package org.webrtc;

/* loaded from: classes6.dex */
public class LibvpxVp9Decoder extends WrappedNativeVideoDecoder {
    public LibvpxVp9Decoder() {
    }

    public static native long nativeCreateDecoder();

    public static native boolean nativeIsSupported();

    @Override // org.webrtc.WrappedNativeVideoDecoder, org.webrtc.VideoDecoder
    public long createNative(long r1) {
        return nativeCreateDecoder();
    }
}
