package org.webrtc;

/* loaded from: classes6.dex */
public class Dav1dDecoder extends WrappedNativeVideoDecoder {
    public Dav1dDecoder() {
    }

    public static native long nativeCreateDecoder();

    @Override // org.webrtc.WrappedNativeVideoDecoder, org.webrtc.VideoDecoder
    public long createNative(long r1) {
        return nativeCreateDecoder();
    }
}
