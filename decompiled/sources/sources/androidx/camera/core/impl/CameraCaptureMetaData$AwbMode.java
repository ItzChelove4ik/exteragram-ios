package androidx.camera.core.impl;

/* loaded from: classes3.dex */
public enum CameraCaptureMetaData$AwbMode extends Enum<CameraCaptureMetaData$AwbMode> {
    private static final /* synthetic */ CameraCaptureMetaData$AwbMode[] $VALUES = null;
    public static final CameraCaptureMetaData$AwbMode AUTO = null;
    public static final CameraCaptureMetaData$AwbMode CLOUDY_DAYLIGHT = null;
    public static final CameraCaptureMetaData$AwbMode DAYLIGHT = null;
    public static final CameraCaptureMetaData$AwbMode FLUORESCENT = null;
    public static final CameraCaptureMetaData$AwbMode INCANDESCENT = null;
    public static final CameraCaptureMetaData$AwbMode OFF = null;
    public static final CameraCaptureMetaData$AwbMode SHADE = null;
    public static final CameraCaptureMetaData$AwbMode TWILIGHT = null;
    public static final CameraCaptureMetaData$AwbMode UNKNOWN = null;
    public static final CameraCaptureMetaData$AwbMode WARM_FLUORESCENT = null;

    private static /* synthetic */ CameraCaptureMetaData$AwbMode[] $values() {
        return new CameraCaptureMetaData$AwbMode[]{UNKNOWN, OFF, AUTO, INCANDESCENT, FLUORESCENT, WARM_FLUORESCENT, DAYLIGHT, CLOUDY_DAYLIGHT, TWILIGHT, SHADE};
    }

    static {
        UNKNOWN = new CameraCaptureMetaData$AwbMode("UNKNOWN", 0);
        OFF = new CameraCaptureMetaData$AwbMode("OFF", 1);
        AUTO = new CameraCaptureMetaData$AwbMode("AUTO", 2);
        INCANDESCENT = new CameraCaptureMetaData$AwbMode("INCANDESCENT", 3);
        FLUORESCENT = new CameraCaptureMetaData$AwbMode("FLUORESCENT", 4);
        WARM_FLUORESCENT = new CameraCaptureMetaData$AwbMode("WARM_FLUORESCENT", 5);
        DAYLIGHT = new CameraCaptureMetaData$AwbMode("DAYLIGHT", 6);
        CLOUDY_DAYLIGHT = new CameraCaptureMetaData$AwbMode("CLOUDY_DAYLIGHT", 7);
        TWILIGHT = new CameraCaptureMetaData$AwbMode("TWILIGHT", 8);
        SHADE = new CameraCaptureMetaData$AwbMode("SHADE", 9);
        $VALUES = $values();
    }

    CameraCaptureMetaData$AwbMode(String r1, int r2) {
    }

    public static CameraCaptureMetaData$AwbMode valueOf(String r1) {
        return (CameraCaptureMetaData$AwbMode) Enum.valueOf(CameraCaptureMetaData$AwbMode.class, r1);
    }

    public static CameraCaptureMetaData$AwbMode[] values() {
        return (CameraCaptureMetaData$AwbMode[]) $VALUES.clone();
    }
}
