package org.webrtc;

import android.annotation.TargetApi;
import android.media.MediaCodecInfo;
import android.os.Build;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
class MediaCodecUtils {
    static final int COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m = 2141391876;
    static final int COLOR_QCOM_FORMATYVU420PackedSemiPlanar16m4ka = 2141391874;
    static final int COLOR_QCOM_FORMATYVU420PackedSemiPlanar32m4ka = 2141391873;
    static final int COLOR_QCOM_FORMATYVU420PackedSemiPlanar64x32Tile2m8ka = 2141391875;
    static final int[] DECODER_COLOR_FORMATS = null;
    static final int[] ENCODER_COLOR_FORMATS = null;
    static final String EXYNOS_PREFIX = "OMX.Exynos.";
    static final String INTEL_PREFIX = "OMX.Intel.";
    static final String NVIDIA_PREFIX = "OMX.Nvidia.";
    static final String QCOM_PREFIX = "OMX.qcom.";
    static final String[] SOFTWARE_IMPLEMENTATION_PREFIXES = null;
    private static final String TAG = "MediaCodecUtils";
    static final int[] TEXTURE_COLOR_FORMATS = null;

    /* renamed from: org.webrtc.MediaCodecUtils$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$webrtc$VideoCodecMimeType = null;

        static {
            int[] r0 = new int[VideoCodecMimeType.values().length];
            $SwitchMap$org$webrtc$VideoCodecMimeType = r0;
            r0[VideoCodecMimeType.VP8.ordinal()] = 1;     // Catch: NoSuchFieldError -> L9
        L14:
            $SwitchMap$org$webrtc$VideoCodecMimeType[VideoCodecMimeType.VP9.ordinal()] = 2;     // Catch: NoSuchFieldError -> L10
        L18:
            $SwitchMap$org$webrtc$VideoCodecMimeType[VideoCodecMimeType.AV1.ordinal()] = 3;     // Catch: NoSuchFieldError -> L11
        L22:
            $SwitchMap$org$webrtc$VideoCodecMimeType[VideoCodecMimeType.H265.ordinal()] = 4;     // Catch: NoSuchFieldError -> L12
        L16:
            $SwitchMap$org$webrtc$VideoCodecMimeType[VideoCodecMimeType.H264.ordinal()] = 5;     // Catch: NoSuchFieldError -> L13
            return;
        }
    }

    static {
        SOFTWARE_IMPLEMENTATION_PREFIXES = new String[]{"OMX.google.", "OMX.SEC.", "c2.android"};
        DECODER_COLOR_FORMATS = new int[]{19, 21, 2141391872, COLOR_QCOM_FORMATYVU420PackedSemiPlanar32m4ka, COLOR_QCOM_FORMATYVU420PackedSemiPlanar16m4ka, COLOR_QCOM_FORMATYVU420PackedSemiPlanar64x32Tile2m8ka, COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m};
        ENCODER_COLOR_FORMATS = new int[]{19, 21, 2141391872, COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m};
        TEXTURE_COLOR_FORMATS = new int[]{2130708361};
    }

    private MediaCodecUtils() {
    }

    public static boolean codecSupportsType(MediaCodecInfo r5, VideoCodecMimeType r6) {
        String[] r52 = r5.getSupportedTypes();
        int r0 = r52.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        if (r6.mimeType().equals(r52[r2]) == true) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    public static Map<String, String> getCodecProperties(VideoCodecMimeType r2, boolean r3) {
        int r0 = AnonymousClass1.$SwitchMap$org$webrtc$VideoCodecMimeType[r2.ordinal()];
        if (r0 == 1) goto L17;
        if (r0 == 2) goto L17;
        if (r0 == 3) goto L17;
        if (r0 == 4) goto L17;
        if (r0 == 5) goto L13;
        Native$$ExternalSyntheticBUOutline5.m("Unsupported codec: ", r2);
        return null;
    L13:
        return H264Utils.getDefaultH264Params(r3);
    L17:
        return new HashMap();
    }

    public static boolean isHardwareAccelerated(MediaCodecInfo r2) {
        if (Build.VERSION.SDK_INT < 29) goto L7;
        return isHardwareAcceleratedQOrHigher(r2);
    L7:
        return !isSoftwareOnly(r2);
    }

    @TargetApi(29)
    private static boolean isHardwareAcceleratedQOrHigher(MediaCodecInfo r0) {
        return r0.isHardwareAccelerated();
    }

    public static boolean isSoftwareOnly(MediaCodecInfo r5) {
        if (Build.VERSION.SDK_INT >= 29) goto L5;
        String r52 = r5.getName();
        String[] r0 = SOFTWARE_IMPLEMENTATION_PREFIXES;
        int r1 = r0.length;
        int r3 = 0;
    L7:
        if (r3 >= r1) goto L13;
        if (r52.startsWith(r0[r3]) == true) goto L10;
        r3 = r3 + 1;
        goto L7
    L10:
        return true;
    L13:
        return false;
    L5:
        return isSoftwareOnlyQOrHigher(r5);
    }

    @TargetApi(29)
    private static boolean isSoftwareOnlyQOrHigher(MediaCodecInfo r0) {
        return r0.isSoftwareOnly();
    }

    public static Integer selectColorFormat(int[] r8, MediaCodecInfo.CodecCapabilities r9) {
        int r0 = r8.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L12;
        int r3 = r8[r2];
        int[] r4 = r9.colorFormats;
        int r5 = r4.length;
        int r6 = 0;
    L5:
        if (r6 >= r5) goto L11;
        int r7 = r4[r6];
        if (r7 == r3) goto L9;
        r6 = r6 + 1;
        goto L5
    L9:
        return Integer.valueOf(r7);
    L11:
        r2 = r2 + 1;
        goto L3
    L12:
        return null;
    }
}
