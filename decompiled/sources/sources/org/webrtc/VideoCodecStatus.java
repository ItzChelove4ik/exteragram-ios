package org.webrtc;

import com.google.android.gms.cast.MediaError;

/* loaded from: classes6.dex */
public enum VideoCodecStatus extends Enum<VideoCodecStatus> {
    private static final /* synthetic */ VideoCodecStatus[] $VALUES = null;
    public static final VideoCodecStatus ERROR = null;
    public static final VideoCodecStatus ERR_PARAMETER = null;
    public static final VideoCodecStatus ERR_REQUEST_SLI = null;
    public static final VideoCodecStatus ERR_SIZE = null;
    public static final VideoCodecStatus FALLBACK_SOFTWARE = null;
    public static final VideoCodecStatus LEVEL_EXCEEDED = null;
    public static final VideoCodecStatus MEMORY = null;
    public static final VideoCodecStatus NO_OUTPUT = null;
    public static final VideoCodecStatus OK = null;
    public static final VideoCodecStatus REQUEST_SLI = null;
    public static final VideoCodecStatus TARGET_BITRATE_OVERSHOOT = null;
    public static final VideoCodecStatus TIMEOUT = null;
    public static final VideoCodecStatus UNINITIALIZED = null;
    private final int number;

    private static /* synthetic */ VideoCodecStatus[] $values() {
        return new VideoCodecStatus[]{TARGET_BITRATE_OVERSHOOT, REQUEST_SLI, NO_OUTPUT, OK, ERROR, LEVEL_EXCEEDED, MEMORY, ERR_PARAMETER, ERR_SIZE, TIMEOUT, UNINITIALIZED, ERR_REQUEST_SLI, FALLBACK_SOFTWARE};
    }

    static {
        TARGET_BITRATE_OVERSHOOT = new VideoCodecStatus("TARGET_BITRATE_OVERSHOOT", 0, 5);
        REQUEST_SLI = new VideoCodecStatus("REQUEST_SLI", 1, 2);
        NO_OUTPUT = new VideoCodecStatus("NO_OUTPUT", 2, 1);
        OK = new VideoCodecStatus("OK", 3, 0);
        ERROR = new VideoCodecStatus(MediaError.ERROR_TYPE_ERROR, 4, -1);
        LEVEL_EXCEEDED = new VideoCodecStatus("LEVEL_EXCEEDED", 5, -2);
        MEMORY = new VideoCodecStatus("MEMORY", 6, -3);
        ERR_PARAMETER = new VideoCodecStatus("ERR_PARAMETER", 7, -4);
        ERR_SIZE = new VideoCodecStatus("ERR_SIZE", 8, -5);
        TIMEOUT = new VideoCodecStatus("TIMEOUT", 9, -6);
        UNINITIALIZED = new VideoCodecStatus("UNINITIALIZED", 10, -7);
        ERR_REQUEST_SLI = new VideoCodecStatus("ERR_REQUEST_SLI", 11, -12);
        FALLBACK_SOFTWARE = new VideoCodecStatus("FALLBACK_SOFTWARE", 12, -13);
        $VALUES = $values();
    }

    VideoCodecStatus(String r1, int r2, int r3) {
        this.number = r3;
    }

    public static VideoCodecStatus valueOf(String r1) {
        return (VideoCodecStatus) Enum.valueOf(VideoCodecStatus.class, r1);
    }

    public static VideoCodecStatus[] values() {
        return (VideoCodecStatus[]) $VALUES.clone();
    }

    @CalledByNative
    public int getNumber() {
        return this.number;
    }
}
