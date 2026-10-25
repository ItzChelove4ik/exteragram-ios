package org.webrtc;

import org.webrtc.VideoEncoder;

/* loaded from: classes6.dex */
class VideoEncoderWrapper {
    /* renamed from: $r8$lambda$jYrJJY9KI0LueT-hhuMHDCSjs4w, reason: not valid java name */
    public static /* synthetic */ void m22400$r8$lambda$jYrJJY9KI0LueThhuMHDCSjs4w(long r0, EncodedImage r2, VideoEncoder.CodecSpecificInfo r3) {
        nativeOnEncodedFrame(r0, r2);
    }

    public VideoEncoderWrapper() {
    }

    @CalledByNative
    public static VideoEncoder.Callback createEncoderCallback(long r1) {
        return new VideoEncoderWrapper$$ExternalSyntheticLambda0(r1);
    }

    @CalledByNative
    public static Integer getScalingSettingsHigh(VideoEncoder.ScalingSettings r0) {
        return r0.high;
    }

    @CalledByNative
    public static Integer getScalingSettingsLow(VideoEncoder.ScalingSettings r0) {
        return r0.low;
    }

    @CalledByNative
    public static boolean getScalingSettingsOn(VideoEncoder.ScalingSettings r0) {
        return r0.on;
    }

    private static native void nativeOnEncodedFrame(long r0, EncodedImage r2);
}
