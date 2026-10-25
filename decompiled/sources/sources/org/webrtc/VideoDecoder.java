package org.webrtc;

/* loaded from: classes6.dex */
public interface VideoDecoder {

    public interface Callback {
        void onDecodedFrame(VideoFrame r1, Integer r2, Integer r3);
    }

    public static class DecodeInfo {
        public final boolean isMissingFrames;
        public final long renderTimeMs;

        public DecodeInfo(boolean r1, long r2) {
            this.isMissingFrames = r1;
            this.renderTimeMs = r2;
        }
    }

    public static class Settings {
        public final int height;
        public final int numberOfCores;
        public final int width;

        @CalledByNative("Settings")
        public Settings(int r1, int r2, int r3) {
            this.numberOfCores = r1;
            this.width = r2;
            this.height = r3;
        }
    }

    @CalledByNative
    default long createNative(long r1) {
        return 0;
    }

    @CalledByNative
    VideoCodecStatus decode(EncodedImage r1, DecodeInfo r2);

    @CalledByNative
    String getImplementationName();

    @CalledByNative
    VideoCodecStatus initDecode(Settings r1, Callback r2);

    @CalledByNative
    VideoCodecStatus release();
}
