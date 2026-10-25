package org.webrtc;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.Build;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.webrtc.EglBase;
import org.webrtc.EglBase14;

/* loaded from: classes6.dex */
public class HardwareVideoEncoderFactory implements VideoEncoderFactory {
    private static final List<String> H264_HW_EXCEPTION_MODELS = null;
    private static final int PERIODIC_KEY_FRAME_INTERVAL_S = 3600;
    private static final int QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_L_MS = 15000;
    private static final int QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_M_MS = 20000;
    private static final int QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_N_MS = 15000;
    private static final String TAG = "HardwareVideoEncoderFactory";
    private final Predicate<MediaCodecInfo> codecAllowedPredicate;
    private final boolean enableH264HighProfile;
    private final boolean enableIntelVp8Encoder;
    private final EglBase14.Context sharedContext;

    /* renamed from: org.webrtc.HardwareVideoEncoderFactory$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$webrtc$VideoCodecMimeType = null;

        static {
            int[] r0 = new int[VideoCodecMimeType.values().length];
            $SwitchMap$org$webrtc$VideoCodecMimeType = r0;
            r0[VideoCodecMimeType.VP8.ordinal()] = 1;     // Catch: NoSuchFieldError -> L9
        L14:
            $SwitchMap$org$webrtc$VideoCodecMimeType[VideoCodecMimeType.VP9.ordinal()] = 2;     // Catch: NoSuchFieldError -> L10
        L18:
            $SwitchMap$org$webrtc$VideoCodecMimeType[VideoCodecMimeType.H264.ordinal()] = 3;     // Catch: NoSuchFieldError -> L11
        L22:
            $SwitchMap$org$webrtc$VideoCodecMimeType[VideoCodecMimeType.H265.ordinal()] = 4;     // Catch: NoSuchFieldError -> L12
        L16:
            $SwitchMap$org$webrtc$VideoCodecMimeType[VideoCodecMimeType.AV1.ordinal()] = 5;     // Catch: NoSuchFieldError -> L13
            return;
        }
    }

    static {
        H264_HW_EXCEPTION_MODELS = Arrays.asList(new String[]{"SAMSUNG-SGH-I337", "Nexus 7", "Nexus 4"});
    }

    public HardwareVideoEncoderFactory(EglBase.Context r2, boolean r3, boolean r4) {
        this(r2, r3, r4, null);
    }

    public HardwareVideoEncoderFactory(EglBase.Context r2, boolean r3, boolean r4, Predicate<MediaCodecInfo> r5) {
        if ((r2 instanceof EglBase14.Context) == false) goto L5;
        this.sharedContext = (EglBase14.Context) r2;
    L6:
        this.enableIntelVp8Encoder = r3;
        this.enableH264HighProfile = r4;
        this.codecAllowedPredicate = r5;
        return;
    L5:
        Logging.w(TAG, "No shared EglBase.Context.  Encoders will not use texture mode.");
        this.sharedContext = null;
        goto L6
    }

    @Deprecated
    public HardwareVideoEncoderFactory(boolean r2, boolean r3) {
        this(null, r2, r3);
    }

    private BitrateAdjuster createBitrateAdjuster(VideoCodecMimeType r1, String r2) {
        if (r2.startsWith("OMX.Exynos.") == false) goto L11;
        if (r1 != VideoCodecMimeType.VP8) goto L9;
        return new DynamicBitrateAdjuster();
    L9:
        return new FramerateBitrateAdjuster();
    L11:
        return new BaseBitrateAdjuster();
    }

    private MediaCodecInfo findCodecForType(VideoCodecMimeType r6) {
        int r0 = 0;
    L3:
        MediaCodecInfo r2 = null;
        if (r0 >= MediaCodecList.getCodecCount()) goto L17;
        r2 = MediaCodecList.getCodecInfoAt(r0);     // Catch: IllegalArgumentException -> L7
    L9:
        if (r2 == null) goto L16;
        if (r2.isEncoder() == false) goto L16;
        if (isSupportedCodec(r2, r6) == false) goto L16;
        return r2;
    L16:
        r0 = r0 + 1;
    L7:
        e = move-exception;
        Logging.e(TAG, "Cannot retrieve encoder codec info", e);
        goto L9
    L17:
        return null;
    }

    private int getForcedKeyFrameIntervalMs(VideoCodecMimeType r1, String r2) {
        if (r1 == VideoCodecMimeType.VP8) goto L5;
        return 0;
    L5:
        if (r2.startsWith("OMX.qcom.") == false) goto L10;
        return 15000;
    L10:
        return 0;
    }

    private boolean isH264HighProfileSupported(MediaCodecInfo r1) {
        if (this.enableH264HighProfile == true) goto L5;
        return false;
    L5:
        if (r1.getName().startsWith("OMX.Exynos.") == false) goto L10;
        return true;
    L10:
        return false;
    }

    private boolean isHardwareSupportedInCurrentSdk(MediaCodecInfo r3, VideoCodecMimeType r4) {
        if (Build.VERSION.SDK_INT >= 29) goto L5;
        int r42 = AnonymousClass1.$SwitchMap$org$webrtc$VideoCodecMimeType[r4.ordinal()];
        if (r42 == 1) goto L19;
        if (r42 == 2) goto L17;
        if (r42 == 3) goto L15;
        return false;
    L15:
        return isHardwareSupportedInCurrentSdkH264(r3);
    L17:
        return isHardwareSupportedInCurrentSdkVp9(r3);
    L19:
        return isHardwareSupportedInCurrentSdkVp8(r3);
    L5:
        return r3.isHardwareAccelerated();
    }

    private boolean isHardwareSupportedInCurrentSdkH264(MediaCodecInfo r2) {
        if (H264_HW_EXCEPTION_MODELS.contains(Build.MODEL) == false) goto L5;
        return false;
    L5:
        String r1 = r2.getName();
        if (r1.startsWith("OMX.qcom.") == false) goto L8;
        return true;
    L8:
        if (r1.startsWith("OMX.Exynos.") == true) goto L13;
        return false;
    L13:
        return true;
    }

    private boolean isHardwareSupportedInCurrentSdkVp8(MediaCodecInfo r2) {
        String r22 = r2.getName();
        if (r22.startsWith("OMX.qcom.") == false) goto L5;
        return true;
    L5:
        if (r22.startsWith("OMX.Exynos.") == false) goto L8;
        return true;
    L8:
        if (r22.startsWith("OMX.Intel.") == true) goto L10;
        return false;
    L10:
        if (this.enableIntelVp8Encoder == true) goto L18;
        return false;
    L18:
        return true;
    }

    private boolean isHardwareSupportedInCurrentSdkVp9(MediaCodecInfo r1) {
        String r0 = r1.getName();
        if (r0.startsWith("OMX.qcom.") == false) goto L5;
        return true;
    L5:
        if (r0.startsWith("OMX.Exynos.") == true) goto L11;
        return false;
    L11:
        return true;
    }

    private boolean isMediaCodecAllowed(MediaCodecInfo r1) {
        Predicate<MediaCodecInfo> r0 = this.codecAllowedPredicate;
        if (r0 != null) goto L7;
        return true;
    L7:
        return r0.test(r1);
    }

    private boolean isSupportedCodec(MediaCodecInfo r4, VideoCodecMimeType r5) {
        if (MediaCodecUtils.codecSupportsType(r4, r5) == true) goto L6;
        return false;
    L6:
        if (MediaCodecUtils.selectColorFormat(MediaCodecUtils.ENCODER_COLOR_FORMATS, r4.getCapabilitiesForType(r5.mimeType())) != null) goto L9;
        return false;
    L9:
        if (isHardwareSupportedInCurrentSdk(r4, r5) == true) goto L11;
    L14:
        return false;
    L11:
        if (isMediaCodecAllowed(r4) == false) goto L14;
        return true;
    }

    @Override // org.webrtc.VideoEncoderFactory
    public VideoEncoder createEncoder(VideoCodecInfo r13) {
        VideoCodecMimeType r4 = VideoCodecMimeType.valueOf(r13.getName());
        MediaCodecInfo r0 = findCodecForType(r4);
        if (r0 != null) goto L5;
        return null;
    L5:
        String r3 = r0.getName();
        String r2 = r4.mimeType();
        Integer r5 = MediaCodecUtils.selectColorFormat(MediaCodecUtils.TEXTURE_COLOR_FORMATS, r0.getCapabilitiesForType(r2));
        Integer r6 = MediaCodecUtils.selectColorFormat(MediaCodecUtils.ENCODER_COLOR_FORMATS, r0.getCapabilitiesForType(r2));
        if (r4 != VideoCodecMimeType.H264) goto L16;
        boolean r22 = H264Utils.isSameH264Profile(r13.params, MediaCodecUtils.getCodecProperties(r4, true));
        boolean r7 = H264Utils.isSameH264Profile(r13.params, MediaCodecUtils.getCodecProperties(r4, false));
        if (r22 == true) goto L11;
        if (r7 == true) goto L11;
        return null;
    L11:
        if (r22 == false) goto L16;
        if (isH264HighProfileSupported(r0) == true) goto L16;
        return null;
    L16:
        return new HardwareVideoEncoder(new MediaCodecWrapperFactoryImpl(), r3, r4, r5, r6, r13.params, PERIODIC_KEY_FRAME_INTERVAL_S, getForcedKeyFrameIntervalMs(r4, r3), createBitrateAdjuster(r4, r3), this.sharedContext);
    }

    @Override // org.webrtc.VideoEncoderFactory
    public VideoCodecInfo[] getSupportedCodecs() {
        ArrayList r0 = new ArrayList();
        VideoCodecMimeType[] r1 = {VideoCodecMimeType.VP8, VideoCodecMimeType.VP9, VideoCodecMimeType.H264, VideoCodecMimeType.AV1, VideoCodecMimeType.H265};
        int r3 = 0;
    L4:
        if (r3 >= 5) goto L15;
        VideoCodecMimeType r4 = r1[r3];
        MediaCodecInfo r5 = findCodecForType(r4);
        if (r5 == null) goto L13;
        String r6 = r4.name();
        if (r4 == VideoCodecMimeType.H264) goto L10;
    L12:
        r0.add(new VideoCodecInfo(r6, MediaCodecUtils.getCodecProperties(r4, false)));
        goto L13
    L10:
        if (isH264HighProfileSupported(r5) == false) goto L12;
        r0.add(new VideoCodecInfo(r6, MediaCodecUtils.getCodecProperties(r4, true)));
    L13:
        r3 = r3 + 1;
        goto L4
    L15:
        return (VideoCodecInfo[]) r0.toArray(new VideoCodecInfo[r0.size()]);
    }
}
