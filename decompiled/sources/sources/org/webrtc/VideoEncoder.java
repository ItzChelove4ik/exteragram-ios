package org.webrtc;

import org.webrtc.EncodedImage;

/* loaded from: classes6.dex */
public interface VideoEncoder {

    public static class BitrateAllocation {
        public final int[][] bitratesBbs;

        @CalledByNative("BitrateAllocation")
        public BitrateAllocation(int[][] r1) {
            this.bitratesBbs = r1;
        }

        public int getSum() {
            int[][] r8 = this.bitratesBbs;
            int r0 = r8.length;
            int r2 = 0;
            int r3 = 0;
        L3:
            if (r2 >= r0) goto L8;
            int[] r4 = r8[r2];
            int r5 = r4.length;
            int r6 = 0;
        L5:
            if (r6 >= r5) goto L7;
            r3 = r3 + r4[r6];
            r6 = r6 + 1;
            goto L5
        L7:
            r2 = r2 + 1;
            goto L3
        L8:
            return r3;
        }
    }

    public interface Callback {
        void onEncodedFrame(EncodedImage r1, CodecSpecificInfo r2);
    }

    public static class Capabilities {
        public final boolean lossNotification;

        @CalledByNative("Capabilities")
        public Capabilities(boolean r1) {
            this.lossNotification = r1;
        }
    }

    public static class CodecSpecificInfo {
        public CodecSpecificInfo() {
        }
    }

    public static class CodecSpecificInfoAV1 extends CodecSpecificInfo {
        public CodecSpecificInfoAV1() {
        }
    }

    public static class CodecSpecificInfoH264 extends CodecSpecificInfo {
        public CodecSpecificInfoH264() {
        }
    }

    public static class CodecSpecificInfoVP8 extends CodecSpecificInfo {
        public CodecSpecificInfoVP8() {
        }
    }

    public static class CodecSpecificInfoVP9 extends CodecSpecificInfo {
        public CodecSpecificInfoVP9() {
        }
    }

    public static class EncodeInfo {
        public final EncodedImage.FrameType[] frameTypes;

        @CalledByNative("EncodeInfo")
        public EncodeInfo(EncodedImage.FrameType[] r1) {
            this.frameTypes = r1;
        }
    }

    public static class EncoderInfo {
        public final boolean applyAlignmentToAllSimulcastLayers;
        public final int requestedResolutionAlignment;

        public EncoderInfo(int r1, boolean r2) {
            this.requestedResolutionAlignment = r1;
            this.applyAlignmentToAllSimulcastLayers = r2;
        }

        @CalledByNative("EncoderInfo")
        public boolean getApplyAlignmentToAllSimulcastLayers() {
            return this.applyAlignmentToAllSimulcastLayers;
        }

        @CalledByNative("EncoderInfo")
        public int getRequestedResolutionAlignment() {
            return this.requestedResolutionAlignment;
        }
    }

    public static class RateControlParameters {
        public final BitrateAllocation bitrate;
        public final double framerateFps;

        @CalledByNative("RateControlParameters")
        public RateControlParameters(BitrateAllocation r1, double r2) {
            this.bitrate = r1;
            this.framerateFps = r2;
        }
    }

    public static class ResolutionBitrateLimits {
        public final int frameSizePixels;
        public final int maxBitrateBps;
        public final int minBitrateBps;
        public final int minStartBitrateBps;

        public ResolutionBitrateLimits(int r1, int r2, int r3, int r4) {
            this.frameSizePixels = r1;
            this.minStartBitrateBps = r2;
            this.minBitrateBps = r3;
            this.maxBitrateBps = r4;
        }

        @CalledByNative("ResolutionBitrateLimits")
        public int getFrameSizePixels() {
            return this.frameSizePixels;
        }

        @CalledByNative("ResolutionBitrateLimits")
        public int getMaxBitrateBps() {
            return this.maxBitrateBps;
        }

        @CalledByNative("ResolutionBitrateLimits")
        public int getMinBitrateBps() {
            return this.minBitrateBps;
        }

        @CalledByNative("ResolutionBitrateLimits")
        public int getMinStartBitrateBps() {
            return this.minStartBitrateBps;
        }
    }

    public static class ScalingSettings {
        public static final ScalingSettings OFF = null;
        public final Integer high;
        public final Integer low;
        public final boolean on;

        static {
            OFF = new ScalingSettings();
        }

        private ScalingSettings() {
            this.on = false;
            this.low = null;
            this.high = null;
        }

        public ScalingSettings(int r2, int r3) {
            this.on = true;
            this.low = Integer.valueOf(r2);
            this.high = Integer.valueOf(r3);
        }

        @Deprecated
        public ScalingSettings(boolean r1) {
            this.on = r1;
            this.low = null;
            this.high = null;
        }

        @Deprecated
        public ScalingSettings(boolean r1, int r2, int r3) {
            this.on = r1;
            this.low = Integer.valueOf(r2);
            this.high = Integer.valueOf(r3);
        }

        public String toString() {
            if (this.on == true) goto L5;
            return "OFF";
        L5:
            return "[ " + this.low + ", " + this.high + " ]";
        }
    }

    public static class Settings {
        public final boolean automaticResizeOn;
        public final Capabilities capabilities;
        public final int height;
        public final int maxFramerate;
        public final int numberOfCores;
        public final int numberOfSimulcastStreams;
        public final int startBitrate;
        public final int width;

        @Deprecated
        public Settings(int r10, int r11, int r12, int r13, int r14, int r15, boolean r16) {
            this(r10, r11, r12, r13, r14, r15, r16, new Capabilities(false));
        }

        @CalledByNative("Settings")
        public Settings(int r1, int r2, int r3, int r4, int r5, int r6, boolean r7, Capabilities r8) {
            this.numberOfCores = r1;
            this.width = r2;
            this.height = r3;
            this.startBitrate = r4;
            this.maxFramerate = r5;
            this.numberOfSimulcastStreams = r6;
            this.automaticResizeOn = r7;
            this.capabilities = r8;
        }
    }

    @CalledByNative
    default long createNativeVideoEncoder() {
        return 0;
    }

    @CalledByNative
    VideoCodecStatus encode(VideoFrame r1, EncodeInfo r2);

    @CalledByNative
    default EncoderInfo getEncoderInfo() {
        return new EncoderInfo(1, false);
    }

    @CalledByNative
    String getImplementationName();

    @CalledByNative
    default ResolutionBitrateLimits[] getResolutionBitrateLimits() {
        return new ResolutionBitrateLimits[0];
    }

    @CalledByNative
    ScalingSettings getScalingSettings();

    @CalledByNative
    VideoCodecStatus initEncode(Settings r1, Callback r2);

    @CalledByNative
    default boolean isHardwareEncoder() {
        return true;
    }

    @CalledByNative
    VideoCodecStatus release();

    VideoCodecStatus setRateAllocation(BitrateAllocation r1, int r2);

    @CalledByNative
    default VideoCodecStatus setRates(RateControlParameters r3) {
        int r0 = (int) Math.ceil(r3.framerateFps);
        return setRateAllocation(r3.bitrate, r0);
    }
}
