package org.webrtc;

import org.webrtc.VideoFrame;
import org.webrtc.VideoProcessor;
import org.webrtc.VideoSource;

/* loaded from: classes6.dex */
class NativeAndroidVideoTrackSource {
    private final long nativeAndroidVideoTrackSource;

    public NativeAndroidVideoTrackSource(long r1) {
        this.nativeAndroidVideoTrackSource = r1;
    }

    @CalledByNative
    public static VideoProcessor.FrameAdaptationParameters createFrameAdaptationParameters(int r10, int r11, int r12, int r13, int r14, int r15, long r16, boolean r18) {
        return new VideoProcessor.FrameAdaptationParameters(r10, r11, r12, r13, r14, r15, r16, r18);
    }

    private static native VideoProcessor.FrameAdaptationParameters nativeAdaptFrame(long r0, int r2, int r3, int r4, long r5);

    private static native void nativeAdaptOutputFormat(long r0, int r2, int r3, Integer r4, int r5, int r6, Integer r7, Integer r8);

    private static native void nativeOnFrameCaptured(long r0, int r2, long r3, VideoFrame.Buffer r5);

    private static native void nativeSetIsScreencast(long r0, boolean r2);

    private static native void nativeSetState(long r0, boolean r2);

    public VideoProcessor.FrameAdaptationParameters adaptFrame(VideoFrame r8) {
        return nativeAdaptFrame(this.nativeAndroidVideoTrackSource, r8.getBuffer().getWidth(), r8.getBuffer().getHeight(), r8.getRotation(), r8.getTimestampNs());
    }

    public void adaptOutputFormat(VideoSource.AspectRatio r10, Integer r11, VideoSource.AspectRatio r12, Integer r13, Integer r14) {
        nativeAdaptOutputFormat(this.nativeAndroidVideoTrackSource, r10.width, r10.height, r11, r12.width, r12.height, r13, r14);
    }

    public void onFrameCaptured(VideoFrame r7) {
        nativeOnFrameCaptured(this.nativeAndroidVideoTrackSource, r7.getRotation(), r7.getTimestampNs(), r7.getBuffer());
    }

    public void setIsScreencast(boolean r3) {
        nativeSetIsScreencast(this.nativeAndroidVideoTrackSource, r3);
    }

    public void setState(boolean r3) {
        nativeSetState(this.nativeAndroidVideoTrackSource, r3);
    }
}
