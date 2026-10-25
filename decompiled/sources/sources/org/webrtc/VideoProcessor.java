package org.webrtc;

/* loaded from: classes6.dex */
public interface VideoProcessor extends CapturerObserver {

    public static class FrameAdaptationParameters {
        public final int cropHeight;
        public final int cropWidth;
        public final int cropX;
        public final int cropY;
        public final boolean drop;
        public final int scaleHeight;
        public final int scaleWidth;
        public final long timestampNs;

        public FrameAdaptationParameters(int r1, int r2, int r3, int r4, int r5, int r6, long r7, boolean r9) {
            this.cropX = r1;
            this.cropY = r2;
            this.cropWidth = r3;
            this.cropHeight = r4;
            this.scaleWidth = r5;
            this.scaleHeight = r6;
            this.timestampNs = r7;
            this.drop = r9;
        }
    }

    static VideoFrame applyFrameAdaptationParameters(VideoFrame r7, FrameAdaptationParameters r8) {
        if (r8.drop == false) goto L7;
        return null;
    L7:
        return new VideoFrame(r7.getBuffer().cropAndScale(r8.cropX, r8.cropY, r8.cropWidth, r8.cropHeight, r8.scaleWidth, r8.scaleHeight), r7.getRotation(), r8.timestampNs);
    }

    default void onFrameCaptured(VideoFrame r1, FrameAdaptationParameters r2) {
        VideoFrame r12 = applyFrameAdaptationParameters(r1, r2);
        if (r12 == null) goto L6;
        onFrameCaptured(r12);
        r12.release();
        return;
    }

    void setSink(VideoSink r1);
}
