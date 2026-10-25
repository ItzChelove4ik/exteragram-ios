package org.webrtc;

/* loaded from: classes6.dex */
public class LibvpxVp8Encoder extends WrappedNativeVideoEncoder {
    public LibvpxVp8Encoder() {
    }

    public static native long nativeCreateEncoder();

    @Override // org.webrtc.WrappedNativeVideoEncoder, org.webrtc.VideoEncoder
    public long createNativeVideoEncoder() {
        return nativeCreateEncoder();
    }

    @Override // org.webrtc.WrappedNativeVideoEncoder, org.webrtc.VideoEncoder
    public boolean isHardwareEncoder() {
        return false;
    }
}
