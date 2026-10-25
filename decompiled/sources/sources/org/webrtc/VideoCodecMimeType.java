package org.webrtc;

import org.telegram.messenger.MediaController;

/* loaded from: classes6.dex */
enum VideoCodecMimeType extends Enum<VideoCodecMimeType> {
    private static final /* synthetic */ VideoCodecMimeType[] $VALUES = null;
    public static final VideoCodecMimeType AV1 = null;
    public static final VideoCodecMimeType H264 = null;
    public static final VideoCodecMimeType H265 = null;
    public static final VideoCodecMimeType VP8 = null;
    public static final VideoCodecMimeType VP9 = null;
    private final String mimeType;

    private static /* synthetic */ VideoCodecMimeType[] $values() {
        return new VideoCodecMimeType[]{VP8, VP9, H264, AV1, H265};
    }

    static {
        VP8 = new VideoCodecMimeType("VP8", 0, "video/x-vnd.on2.vp8");
        VP9 = new VideoCodecMimeType("VP9", 1, "video/x-vnd.on2.vp9");
        H264 = new VideoCodecMimeType("H264", 2, MediaController.VIDEO_MIME_TYPE);
        AV1 = new VideoCodecMimeType("AV1", 3, "video/av01");
        H265 = new VideoCodecMimeType("H265", 4, "video/hevc");
        $VALUES = $values();
    }

    VideoCodecMimeType(String r1, int r2, String r3) {
        this.mimeType = r3;
    }

    public static VideoCodecMimeType valueOf(String r1) {
        return (VideoCodecMimeType) Enum.valueOf(VideoCodecMimeType.class, r1);
    }

    public static VideoCodecMimeType[] values() {
        return (VideoCodecMimeType[]) $VALUES.clone();
    }

    public String mimeType() {
        return this.mimeType;
    }
}
