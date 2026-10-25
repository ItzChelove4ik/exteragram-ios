package org.webrtc;

import org.webrtc.VideoDecoder;

/* loaded from: classes6.dex */
class VideoDecoderWrapper {
    /* renamed from: $r8$lambda$7bMzuIVw8g6pRhYmvpg0ph-zE20, reason: not valid java name */
    public static /* synthetic */ void m22399$r8$lambda$7bMzuIVw8g6pRhYmvpg0phzE20(long r0, VideoFrame r2, Integer r3, Integer r4) {
        nativeOnDecodedFrame(r0, r2, r3, r4);
    }

    public VideoDecoderWrapper() {
    }

    @CalledByNative
    public static VideoDecoder.Callback createDecoderCallback(long r1) {
        return new VideoDecoderWrapper$$ExternalSyntheticLambda0(r1);
    }

    private static native void nativeOnDecodedFrame(long r0, VideoFrame r2, Integer r3, Integer r4);
}
