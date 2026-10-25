package org.webrtc;

import org.webrtc.VideoFrame;
import org.webrtc.VideoProcessor;

/* loaded from: classes6.dex */
class NativeCapturerObserver implements CapturerObserver {
    private final NativeAndroidVideoTrackSource nativeAndroidVideoTrackSource;

    @CalledByNative
    public NativeCapturerObserver(long r2) {
        this.nativeAndroidVideoTrackSource = new NativeAndroidVideoTrackSource(r2);
    }

    @Override // org.webrtc.CapturerObserver
    public void onCapturerStarted(boolean r1) {
        this.nativeAndroidVideoTrackSource.setState(r1);
    }

    @Override // org.webrtc.CapturerObserver
    public void onCapturerStopped() {
        this.nativeAndroidVideoTrackSource.setState(false);
    }

    @Override // org.webrtc.CapturerObserver
    public void onFrameCaptured(VideoFrame r10) {
        VideoProcessor.FrameAdaptationParameters r0 = this.nativeAndroidVideoTrackSource.adaptFrame(r10);
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.drop == true) goto L10;
        VideoFrame.Buffer r1 = r10.getBuffer().cropAndScale(r0.cropX, r0.cropY, r0.cropWidth, r0.cropHeight, r0.scaleWidth, r0.scaleHeight);
        this.nativeAndroidVideoTrackSource.onFrameCaptured(new VideoFrame(r1, r10.getRotation(), r0.timestampNs));
        r1.release();
        return;
    }
}
