package org.webrtc;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes6.dex */
public class VideoCodecInfo {
    public static final String H264_CONSTRAINED_BASELINE_3_1 = "42e01f";
    public static final String H264_CONSTRAINED_HIGH_3_1 = "640c1f";
    public static final String H264_FMTP_LEVEL_ASYMMETRY_ALLOWED = "level-asymmetry-allowed";
    public static final String H264_FMTP_PACKETIZATION_MODE = "packetization-mode";
    public static final String H264_FMTP_PROFILE_LEVEL_ID = "profile-level-id";
    public static final String H264_LEVEL_3_1 = "1f";
    public static final String H264_PROFILE_CONSTRAINED_BASELINE = "42e0";
    public static final String H264_PROFILE_CONSTRAINED_HIGH = "640c";
    public final String name;
    public final Map<String, String> params;

    @Deprecated
    public final int payload;

    @Deprecated
    public VideoCodecInfo(int r1, String r2, Map<String, String> r3) {
        this.payload = r1;
        this.name = r2;
        this.params = r3;
    }

    @CalledByNative
    public VideoCodecInfo(String r2, Map<String, String> r3) {
        this.payload = 0;
        this.name = r2;
        this.params = r3;
    }

    public boolean equals(Object r5) {
        if (r5 != null) goto L6;
        return false;
    L6:
        if (r5 != this) goto L9;
        return true;
    L9:
        if ((r5 instanceof VideoCodecInfo) == true) goto L11;
        return false;
    L11:
        VideoCodecInfo r52 = (VideoCodecInfo) r5;
        if (this.name.equalsIgnoreCase(r52.name) == true) goto L14;
    L16:
        return false;
    L14:
        if (this.params.equals(r52.params) == false) goto L16;
        return true;
    }

    @CalledByNative
    public String getName() {
        return this.name;
    }

    @CalledByNative
    public Map getParams() {
        return this.params;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.name.toUpperCase(Locale.ROOT), this.params});
    }

    public String toString() {
        return "VideoCodec{" + this.name + " " + this.params + "}";
    }
}
