package org.webrtc;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
class H264Utils {
    public static VideoCodecInfo DEFAULT_H264_BASELINE_PROFILE_CODEC = null;
    public static VideoCodecInfo DEFAULT_H264_HIGH_PROFILE_CODEC = null;
    public static final String H264_CONSTRAINED_BASELINE_3_1 = "42e01f";
    public static final String H264_CONSTRAINED_HIGH_3_1 = "640c1f";
    public static final String H264_FMTP_LEVEL_ASYMMETRY_ALLOWED = "level-asymmetry-allowed";
    public static final String H264_FMTP_PACKETIZATION_MODE = "packetization-mode";
    public static final String H264_FMTP_PROFILE_LEVEL_ID = "profile-level-id";
    public static final String H264_LEVEL_3_1 = "1f";
    public static final String H264_PROFILE_CONSTRAINED_BASELINE = "42e0";
    public static final String H264_PROFILE_CONSTRAINED_HIGH = "640c";

    static {
        DEFAULT_H264_BASELINE_PROFILE_CODEC = new VideoCodecInfo("H264", getDefaultH264Params(false));
        DEFAULT_H264_HIGH_PROFILE_CODEC = new VideoCodecInfo("H264", getDefaultH264Params(true));
    }

    public H264Utils() {
    }

    public static Map<String, String> getDefaultH264Params(boolean r3) {
        HashMap r0 = new HashMap();
        r0.put("level-asymmetry-allowed", "1");
        r0.put("packetization-mode", "1");
        if (r3 == false) goto L5;
        String r32 = "640c1f";
    L6:
        r0.put("profile-level-id", r32);
        return r0;
    L5:
        r32 = "42e01f";
        goto L6
    }

    public static boolean isSameH264Profile(Map<String, String> r0, Map<String, String> r1) {
        return nativeIsSameH264Profile(r0, r1);
    }

    private static native boolean nativeIsSameH264Profile(Map<String, String> r0, Map<String, String> r1);
}
