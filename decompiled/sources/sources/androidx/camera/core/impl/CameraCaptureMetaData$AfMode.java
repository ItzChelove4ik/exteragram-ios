package androidx.camera.core.impl;

/* loaded from: classes3.dex */
public enum CameraCaptureMetaData$AfMode extends Enum<CameraCaptureMetaData$AfMode> {
    private static final /* synthetic */ CameraCaptureMetaData$AfMode[] $VALUES = null;
    public static final CameraCaptureMetaData$AfMode OFF = null;
    public static final CameraCaptureMetaData$AfMode ON_CONTINUOUS_AUTO = null;
    public static final CameraCaptureMetaData$AfMode ON_MANUAL_AUTO = null;
    public static final CameraCaptureMetaData$AfMode UNKNOWN = null;

    private static /* synthetic */ CameraCaptureMetaData$AfMode[] $values() {
        return new CameraCaptureMetaData$AfMode[]{UNKNOWN, OFF, ON_MANUAL_AUTO, ON_CONTINUOUS_AUTO};
    }

    static {
        UNKNOWN = new CameraCaptureMetaData$AfMode("UNKNOWN", 0);
        OFF = new CameraCaptureMetaData$AfMode("OFF", 1);
        ON_MANUAL_AUTO = new CameraCaptureMetaData$AfMode("ON_MANUAL_AUTO", 2);
        ON_CONTINUOUS_AUTO = new CameraCaptureMetaData$AfMode("ON_CONTINUOUS_AUTO", 3);
        $VALUES = $values();
    }

    CameraCaptureMetaData$AfMode(String r1, int r2) {
    }

    public static CameraCaptureMetaData$AfMode valueOf(String r1) {
        return (CameraCaptureMetaData$AfMode) Enum.valueOf(CameraCaptureMetaData$AfMode.class, r1);
    }

    public static CameraCaptureMetaData$AfMode[] values() {
        return (CameraCaptureMetaData$AfMode[]) $VALUES.clone();
    }
}
