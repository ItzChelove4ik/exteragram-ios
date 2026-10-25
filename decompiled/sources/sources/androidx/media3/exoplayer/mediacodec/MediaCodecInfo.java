package androidx.media3.exoplayer.mediacodec;

import android.graphics.Point;
import android.media.MediaCodecInfo;
import android.os.Build;
import android.util.Pair;
import android.util.Range;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import java.util.Objects;
import org.telegram.messenger.MediaController;

/* loaded from: classes4.dex */
public final class MediaCodecInfo {
    public final boolean adaptive;
    public final MediaCodecInfo.CodecCapabilities capabilities;
    public final String codecMimeType;
    public final boolean detachedSurfaceSupported;
    public final boolean hardwareAccelerated;
    private final boolean isVideo;
    private float maxFrameRate;
    private int maxFrameRateHeight;
    private int maxFrameRateWidth;
    public final String mimeType;
    public final String name;
    public final boolean secure;
    public final boolean softwareOnly;
    public final boolean tunneling;
    public final boolean vendor;

    public MediaCodecInfo(String r1, String r2, String r3, MediaCodecInfo.CodecCapabilities r4, boolean r5, boolean r6, boolean r7, boolean r8, boolean r9, boolean r10, boolean r11) {
        this.name = (String) Assertions.checkNotNull(r1);
        this.mimeType = r2;
        this.codecMimeType = r3;
        this.capabilities = r4;
        this.hardwareAccelerated = r5;
        this.softwareOnly = r6;
        this.vendor = r7;
        this.adaptive = r8;
        this.tunneling = r9;
        this.secure = r10;
        this.detachedSurfaceSupported = r11;
        this.isVideo = MimeTypes.isVideo(r2);
        this.maxFrameRate = -3.4028235E38f;
        this.maxFrameRateWidth = -1;
        this.maxFrameRateHeight = -1;
    }

    private static int adjustMaxInputChannelCount(String r2, String r3, int r4) {
        if (r4 <= 1) goto L5;
    L40:
        return r4;
    L5:
        if (Build.VERSION.SDK_INT < 26) goto L9;
        if (r4 > 0) goto L40;
    L9:
        if ("audio/mpeg".equals(r3) == true) goto L40;
        if ("audio/3gpp".equals(r3) == true) goto L40;
        if ("audio/amr-wb".equals(r3) == true) goto L40;
        if (MediaController.AUDIO_MIME_TYPE.equals(r3) == true) goto L40;
        if ("audio/vorbis".equals(r3) == true) goto L40;
        if ("audio/opus".equals(r3) == true) goto L40;
        if ("audio/raw".equals(r3) == true) goto L40;
        if ("audio/flac".equals(r3) == true) goto L40;
        if ("audio/g711-alaw".equals(r3) == true) goto L40;
        if ("audio/g711-mlaw".equals(r3) == true) goto L40;
        if ("audio/gsm".equals(r3) == true) goto L40;
        if ("audio/ac3".equals(r3) == false) goto L35;
        int r32 = 6;
    L38:
        Log.w("MediaCodecInfo", "AssumedMaxChannelAdjustment: " + r2 + ", [" + r4 + " to " + r32 + "]");
        return r32;
    L35:
        if ("audio/eac3".equals(r3) == false) goto L37;
        r32 = 16;
        goto L38
    L37:
        r32 = 30;
        goto L38
    }

    private static Point alignVideoSize(MediaCodecInfo.VideoCapabilities r2, int r3, int r4) {
        int r0 = r2.getWidthAlignment();
        int r22 = r2.getHeightAlignment();
        return new Point(Util.ceilDivide(r3, r0) * r0, Util.ceilDivide(r4, r22) * r22);
    }

    private static boolean areSizeAndRateSupported(MediaCodecInfo.VideoCapabilities r4, int r5, int r6, double r7) {
        Point r52 = alignVideoSize(r4, r5, r6);
        int r62 = r52.x;
        int r53 = r52.y;
        if (r7 == (-1.0d)) goto L18;
        if (r7 < 1.0d) goto L18;
        double r72 = Math.floor(r7);
        if (r4.areSizeAndRateSupported(r62, r53, r72) == true) goto L10;
        return false;
    L10:
        Range<Double> r42 = r4.getAchievableFrameRatesFor(r62, r53);
        if (r42 != null) goto L14;
        return true;
    L14:
        if (r72 > ((Double) r42.getUpper()).doubleValue()) goto L16;
        return true;
    L16:
        return false;
    L18:
        return r4.isSizeSupported(r62, r53);
    }

    private float computeMaxSupportedFrameRate(int r6, int r7) {
        float r1 = 1024.0f;
        if (isVideoSizeAndRateSupportedV21(r6, r7, 1024.0d) == false) goto L5;
        return 1024.0f;
    L5:
        float r0 = 0.0f;
    L6:
        float r2 = r1 - r0;
        if (Math.abs(r2) <= 5.0f) goto L12;
        float r22 = (r2 / 2.0f) + r0;
        if (isVideoSizeAndRateSupportedV21(r6, r7, r22) == true) goto L10;
        r1 = r22;
        goto L6
    L10:
        r0 = r22;
        goto L6
    L12:
        return r0;
    }

    private static boolean isAdaptive(MediaCodecInfo.CodecCapabilities r1) {
        return r1.isFeatureSupported("adaptive-playback");
    }

    private boolean isCodecProfileAndLevelSupported(Format r10, boolean r11) {
        Pair<Integer, Integer> r0 = MediaCodecUtil.getCodecProfileAndLevel(r10);
        String r1 = r10.sampleMimeType;
        if (r1 != null) goto L5;
    L12:
        if (r0 != null) goto L14;
        return true;
    L14:
        int r12 = ((Integer) r0.first).intValue();
        int r02 = ((Integer) r0.second).intValue();
        if ("video/dolby-vision".equals(r10.sampleMimeType) == false) goto L36;
        String r4 = this.mimeType;
        r4.getClass();
        char r8 = 65535;
        switch(r4.hashCode()) {
            case -1662735862: goto L28;
            case -1662541442: goto L24;
            case 1331836730: goto L20;
            default: goto L31;
        };
    L31:
        switch(r8) {
            case 0: goto L34;
            case 1: goto L34;
            case 2: goto L33;
            default: goto L36;
        };
    L33:
        r12 = 8;
        r02 = 0;
        goto L36
    L34:
        r02 = 0;
        r12 = 2;
        goto L36
    L20:
        if (r4.equals(MediaController.VIDEO_MIME_TYPE) == false) goto L31;
        r8 = 2;
        goto L31
    L24:
        if (r4.equals("video/hevc") == false) goto L31;
        r8 = 1;
        goto L31
    L28:
        if (r4.equals("video/av01") == false) goto L31;
        r8 = 0;
    L36:
        if (this.isVideo == false) goto L38;
    L40:
        MediaCodecInfo.CodecProfileLevel[] r2 = getProfileLevels();
        int r42 = r2.length;
        int r6 = 0;
    L41:
        if (r6 >= r42) goto L51;
        MediaCodecInfo.CodecProfileLevel r7 = r2[r6];
        if (r7.profile != r12) goto L50;
        if (r7.level >= r02) goto L48;
        if (r11 == true) goto L50;
    L48:
        if (needsProfileExcludedWorkaround(this.mimeType, r12) == true) goto L50;
        return true;
    L50:
        r6 = r6 + 1;
        goto L41
    L51:
        logNoSupport("codec.profileLevel, " + r10.codecs + ", " + this.codecMimeType);
        return false;
    L38:
        if (r12 == 42) goto L40;
        return true;
    L5:
        if (r1.equals("video/mv-hevc") == false) goto L12;
        String r13 = MimeTypes.normalizeMimeType(this.codecMimeType);
        if (r13.equals("video/mv-hevc") == false) goto L10;
        return true;
    L10:
        if (r13.equals("video/hevc") == false) goto L12;
        r0 = MediaCodecUtil.getHevcBaseLayerCodecProfileAndLevel(r10);
        goto L12
    }

    private boolean isCompressedAudioBitDepthSupported(Format r3) {
        if (Objects.equals(r3.sampleMimeType, "audio/flac") == true) goto L5;
        return true;
    L5:
        if (r3.pcmEncoding == 22) goto L7;
        return true;
    L7:
        if (Build.VERSION.SDK_INT < 34) goto L9;
        return true;
    L9:
        if (this.name.equals("c2.android.flac.decoder") == false) goto L17;
        return false;
    L17:
        return true;
    }

    private static boolean isDetachedSurfaceSupported(MediaCodecInfo.CodecCapabilities r2) {
        if (Build.VERSION.SDK_INT < 35) goto L11;
        if (r2 != null) goto L6;
        return false;
    L6:
        if (r2.isFeatureSupported("detached-surface") == true) goto L8;
        return false;
    L8:
        if (needsDetachedSurfaceUnsupportedWorkaround() == true) goto L15;
        return true;
    L15:
        return false;
    L11:
        return false;
    }

    private boolean isSampleMimeTypeSupported(Format r3) {
        if (this.mimeType.equals(r3.sampleMimeType) == false) goto L5;
        return true;
    L5:
        if (this.mimeType.equals(MediaCodecUtil.getAlternativeCodecMimeType(r3)) == true) goto L11;
        return false;
    L11:
        return true;
    }

    private static boolean isSecure(MediaCodecInfo.CodecCapabilities r1) {
        return r1.isFeatureSupported("secure-playback");
    }

    private static boolean isTunneling(MediaCodecInfo.CodecCapabilities r1) {
        return r1.isFeatureSupported("tunneled-playback");
    }

    private void logAssumedSupport(String r3) {
        Log.d("MediaCodecInfo", "AssumedSupport [" + r3 + "] [" + this.name + ", " + this.mimeType + "] [" + Util.DEVICE_DEBUG_INFO + "]");
    }

    private void logNoSupport(String r3) {
        Log.d("MediaCodecInfo", "NoSupport [" + r3 + "] [" + this.name + ", " + this.mimeType + "] [" + Util.DEVICE_DEBUG_INFO + "]");
    }

    private static boolean needsAdaptationFlushWorkaround(String r1) {
        return "audio/opus".equals(r1);
    }

    private static boolean needsAdaptationReconfigureWorkaround(String r2) {
        if (Build.MODEL.startsWith("SM-T230") == true) goto L5;
        return false;
    L5:
        if ("OMX.MARVELL.VIDEO.HW.CODA7542DECODER".equals(r2) == false) goto L10;
        return true;
    L10:
        return false;
    }

    private static boolean needsDetachedSurfaceUnsupportedWorkaround() {
        String r0 = Build.MANUFACTURER;
        if (r0.equals("Xiaomi") == false) goto L5;
        return true;
    L5:
        if (r0.equals("OPPO") == false) goto L7;
        return true;
    L7:
        if (r0.equals("realme") == false) goto L9;
        return true;
    L9:
        if (r0.equals("motorola") == false) goto L11;
        return true;
    L11:
        if (r0.equals("LENOVO") == true) goto L20;
        return false;
    L20:
        return true;
    }

    private static boolean needsDisableAdaptationWorkaround(String r0) {
        return false;
    }

    private static boolean needsProfileExcludedWorkaround(String r1, int r2) {
        if ("video/hevc".equals(r1) == true) goto L5;
        return false;
    L5:
        if (2 != r2) goto L15;
        String r12 = Build.DEVICE;
        if ("sailfish".equals(r12) == false) goto L9;
        return true;
    L9:
        if ("marlin".equals(r12) == false) goto L16;
        return true;
    L16:
        return false;
    L15:
        return false;
    }

    private static boolean needsRotatedVerticalResolutionWorkaround(String r1) {
        if ("OMX.MTK.VIDEO.DECODER.HEVC".equals(r1) == true) goto L5;
        return true;
    L5:
        if ("mcv5a".equals(Build.DEVICE) == false) goto L10;
        return false;
    L10:
        return true;
    }

    public static MediaCodecInfo newInstance(String r12, String r13, String r14, MediaCodecInfo.CodecCapabilities r15, boolean r16, boolean r17, boolean r18, boolean r19, boolean r20) {
        if (r19 == true) goto L10;
        if (r15 == null) goto L10;
        if (isAdaptive(r15) == false) goto L10;
        if (needsDisableAdaptationWorkaround(r12) == true) goto L10;
        boolean r8 = true;
    L11:
        if (r15 != null) goto L13;
    L15:
        boolean r9 = false;
    L16:
        if (r20 == true) goto L22;
        if (r15 != null) goto L19;
    L21:
        boolean r10 = false;
    L24:
        return new MediaCodecInfo(r12, r13, r14, r15, r16, r17, r18, r8, r9, r10, isDetachedSurfaceSupported(r15));
    L19:
        if (isSecure(r15) == false) goto L21;
    L22:
        r10 = true;
        goto L24
    L13:
        if (isTunneling(r15) == false) goto L15;
        r9 = true;
    L10:
        r8 = false;
        goto L11
    }

    public Point alignVideoSizeV21(int r2, int r3) {
        MediaCodecInfo.CodecCapabilities r1 = this.capabilities;
        if (r1 != null) goto L5;
        return null;
    L5:
        MediaCodecInfo.VideoCapabilities r12 = r1.getVideoCapabilities();
        if (r12 != null) goto L9;
        return null;
    L9:
        return alignVideoSize(r12, r2, r3);
    }

    public DecoderReuseEvaluation canReuseCodec(Format r9, Format r10) {
        boolean r1 = false;
        if (Objects.equals(r9.sampleMimeType, r10.sampleMimeType) == true) goto L5;
        int r0 = 8;
    L7:
        if (this.isVideo == true) goto L9;
        Format r4 = r9;
        Format r5 = r10;
        if (r4.channelCount == r5.channelCount) goto L56;
        r0 = r0 | 4096;
    L56:
        if (r4.sampleRate == r5.sampleRate) goto L59;
        r0 = r0 | 8192;
    L59:
        if (r4.pcmEncoding == r5.pcmEncoding) goto L61;
        r0 = r0 | 16384;
    L61:
        if (r0 != 0) goto L73;
        if (MediaController.AUDIO_MIME_TYPE.equals(this.mimeType) == false) goto L73;
        Pair<Integer, Integer> r92 = MediaCodecUtil.getCodecProfileAndLevel(r4);
        Pair<Integer, Integer> r102 = MediaCodecUtil.getCodecProfileAndLevel(r5);
        if (r92 == null) goto L73;
        if (r102 == null) goto L73;
        int r93 = ((Integer) r92.first).intValue();
        int r103 = ((Integer) r102.first).intValue();
        if (r93 != 42) goto L73;
        if (r103 != 42) goto L73;
        return new DecoderReuseEvaluation(this.name, r4, r5, 3, 0);
    L73:
        if (r4.initializationDataEquals(r5) == true) goto L76;
        r0 = r0 | 32;
    L76:
        if (needsAdaptationFlushWorkaround(this.mimeType) == false) goto L78;
        r0 = r0 | 2;
    L78:
        if (r0 == 0) goto L80;
    L82:
        return new DecoderReuseEvaluation(this.name, r4, r5, 0, r0);
    L80:
        return new DecoderReuseEvaluation(this.name, r4, r5, 1, 0);
    L9:
        if (r9.rotationDegrees == r10.rotationDegrees) goto L12;
        r0 = r0 | 1024;
    L12:
        if (r9.width == r10.width) goto L14;
    L15:
        r1 = true;
    L17:
        if (this.adaptive == true) goto L21;
        if (r1 == false) goto L21;
        r0 = r0 | 512;
    L21:
        if (ColorInfo.isEquivalentToAssumedSdrDefault(r9.colorInfo) == false) goto L25;
        if (ColorInfo.isEquivalentToAssumedSdrDefault(r10.colorInfo) == false) goto L25;
    L28:
        if (needsAdaptationReconfigureWorkaround(this.name) == true) goto L30;
    L32:
        int r2 = r9.decodedWidth;
        if (r2 == (-1)) goto L42;
        int r42 = r9.decodedHeight;
        if (r42 == (-1)) goto L42;
        if (r2 != r10.decodedWidth) goto L42;
        if (r42 != r10.decodedHeight) goto L42;
        if (r1 == false) goto L42;
        r0 = r0 | 2;
    L42:
        if (r0 != 0) goto L50;
        String r22 = this.name;
        if (r9.initializationDataEquals(r10) == false) goto L47;
        int r8 = 3;
    L49:
        return new DecoderReuseEvaluation(r22, r9, r10, r8, 0);
    L47:
        r8 = 2;
        goto L49
    L50:
        r4 = r9;
        r5 = r10;
        goto L82
    L30:
        if (r9.initializationDataEquals(r10) == true) goto L32;
        r0 = r0 | 2;
    L25:
        if (Objects.equals(r9.colorInfo, r10.colorInfo) == true) goto L28;
        r0 = r0 | 2048;
        goto L28
    L14:
        if (r9.height == r10.height) goto L17;
    L5:
        r0 = 0;
        goto L7
    }

    public float getMaxSupportedFrameRate(int r3, int r4) {
        if (this.isVideo == true) goto L5;
        return -3.4028235E38f;
    L5:
        float r0 = this.maxFrameRate;
        if (r0 != (-3.4028235E38f)) goto L8;
    L12:
        float r02 = computeMaxSupportedFrameRate(r3, r4);
        this.maxFrameRate = r02;
        this.maxFrameRateWidth = r3;
        this.maxFrameRateHeight = r4;
        return r02;
    L8:
        if (this.maxFrameRateWidth != r3) goto L12;
        if (this.maxFrameRateHeight != r4) goto L12;
        return r0;
    }

    public MediaCodecInfo.CodecProfileLevel[] getProfileLevels() {
        MediaCodecInfo.CodecCapabilities r0 = this.capabilities;
        if (r0 == null) goto L9;
        MediaCodecInfo.CodecProfileLevel[] r02 = r0.profileLevels;
        if (r02 == null) goto L9;
        return r02;
    L9:
        return new MediaCodecInfo.CodecProfileLevel[0];
    }

    public boolean isAudioChannelCountSupportedV21(int r5) {
        MediaCodecInfo.CodecCapabilities r0 = this.capabilities;
        if (r0 != null) goto L6;
        logNoSupport("channelCount.caps");
        return false;
    L6:
        MediaCodecInfo.AudioCapabilities r02 = r0.getAudioCapabilities();
        if (r02 != null) goto L11;
        logNoSupport("channelCount.aCaps");
        return false;
    L11:
        if (adjustMaxInputChannelCount(this.name, this.mimeType, r02.getMaxInputChannelCount()) >= r5) goto L14;
        logNoSupport("channelCount.support, " + r5);
        return false;
    L14:
        return true;
    }

    public boolean isAudioSampleRateSupportedV21(int r4) {
        MediaCodecInfo.CodecCapabilities r0 = this.capabilities;
        if (r0 != null) goto L6;
        logNoSupport("sampleRate.caps");
        return false;
    L6:
        MediaCodecInfo.AudioCapabilities r02 = r0.getAudioCapabilities();
        if (r02 != null) goto L11;
        logNoSupport("sampleRate.aCaps");
        return false;
    L11:
        if (r02.isSampleRateSupported(r4) == true) goto L14;
        logNoSupport("sampleRate.support, " + r4);
        return false;
    L14:
        return true;
    }

    public boolean isFormatFunctionallySupported(Format r3) {
        if (isSampleMimeTypeSupported(r3) == true) goto L5;
    L10:
        return false;
    L5:
        if (isCodecProfileAndLevelSupported(r3, false) == false) goto L10;
        if (isCompressedAudioBitDepthSupported(r3) == false) goto L10;
        return true;
    }

    public boolean isFormatSupported(Format r6) {
        if (isSampleMimeTypeSupported(r6) == true) goto L6;
        return false;
    L6:
        if (isCodecProfileAndLevelSupported(r6, true) == true) goto L9;
        return false;
    L9:
        if (isCompressedAudioBitDepthSupported(r6) == true) goto L12;
        return false;
    L12:
        if (this.isVideo == false) goto L21;
        int r1 = r6.width;
        if (r1 <= 0) goto L20;
        int r2 = r6.height;
        if (r2 <= 0) goto L20;
        return isVideoSizeAndRateSupportedV21(r1, r2, r6.frameRate);
    L20:
        return true;
    L21:
        int r22 = r6.sampleRate;
        if (r22 != (-1)) goto L24;
    L25:
        int r62 = r6.channelCount;
        if (r62 != (-1)) goto L28;
    L31:
        return true;
    L28:
        if (isAudioChannelCountSupportedV21(r62) == true) goto L31;
    L30:
        return false;
    L24:
        if (isAudioSampleRateSupportedV21(r22) == false) goto L30;
        goto L25
    }

    public boolean isHdr10PlusOutOfBandMetadataSupported() {
        if (Build.VERSION.SDK_INT >= 29) goto L5;
    L13:
        return false;
    L5:
        if ("video/x-vnd.on2.vp9".equals(this.mimeType) == false) goto L13;
        MediaCodecInfo.CodecProfileLevel[] r5 = getProfileLevels();
        int r0 = r5.length;
        int r1 = 0;
    L7:
        if (r1 >= r0) goto L13;
        if (r5[r1].profile == 16384) goto L10;
        r1 = r1 + 1;
        goto L7
    L10:
        return true;
    }

    public boolean isSeamlessAdaptationSupported(Format r2) {
        if (this.isVideo == true) goto L5;
        Pair<Integer, Integer> r1 = MediaCodecUtil.getCodecProfileAndLevel(r2);
        if (r1 != null) goto L9;
        return false;
    L9:
        if (((Integer) r1.first).intValue() != 42) goto L14;
        return true;
    L14:
        return false;
    L5:
        return this.adaptive;
    }

    public boolean isVideoSizeAndRateSupportedV21(int r8, int r9, double r10) {
        MediaCodecInfo.CodecCapabilities r0 = this.capabilities;
        if (r0 != null) goto L6;
        logNoSupport("sizeAndRate.caps");
        return false;
    L6:
        MediaCodecInfo.VideoCapabilities r02 = r0.getVideoCapabilities();
        if (r02 != null) goto L11;
        logNoSupport("sizeAndRate.vCaps");
        return false;
    L11:
        if (Build.VERSION.SDK_INT < 29) goto L19;
        int r2 = MediaCodecPerformancePointCoverageProvider.areResolutionAndFrameRateCovered(r02, r8, r9, r10);
        if (r2 != 2) goto L15;
        return true;
    L15:
        if (r2 != 1) goto L19;
        logNoSupport("sizeAndRate.cover, " + r8 + "x" + r9 + "@" + r10);
        return false;
    L19:
        if (areSizeAndRateSupported(r02, r8, r9, r10) == true) goto L29;
        if (r8 < r9) goto L22;
    L27:
        logNoSupport("sizeAndRate.support, " + r8 + "x" + r9 + "@" + r10);
        return false;
    L22:
        if (needsRotatedVerticalResolutionWorkaround(this.name) == false) goto L27;
        if (areSizeAndRateSupported(r02, r9, r8, r10) == false) goto L27;
        logAssumedSupport("sizeAndRate.rotated, " + r8 + "x" + r9 + "@" + r10);
    L29:
        return true;
    }

    public String toString() {
        return this.name;
    }
}
