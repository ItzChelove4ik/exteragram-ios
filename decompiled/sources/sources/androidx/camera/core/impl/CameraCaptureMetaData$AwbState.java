package androidx.camera.core.impl;

/* loaded from: classes3.dex */
public enum CameraCaptureMetaData$AwbState extends Enum<CameraCaptureMetaData$AwbState> {
    private static final /* synthetic */ CameraCaptureMetaData$AwbState[] $VALUES = null;
    public static final CameraCaptureMetaData$AwbState CONVERGED = null;
    public static final CameraCaptureMetaData$AwbState INACTIVE = null;
    public static final CameraCaptureMetaData$AwbState LOCKED = null;
    public static final CameraCaptureMetaData$AwbState METERING = null;
    public static final CameraCaptureMetaData$AwbState UNKNOWN = null;

    private static /* synthetic */ CameraCaptureMetaData$AwbState[] $values() {
        return new CameraCaptureMetaData$AwbState[]{UNKNOWN, INACTIVE, METERING, CONVERGED, LOCKED};
    }

    static {
        UNKNOWN = new CameraCaptureMetaData$AwbState("UNKNOWN", 0);
        INACTIVE = new CameraCaptureMetaData$AwbState("INACTIVE", 1);
        METERING = new CameraCaptureMetaData$AwbState("METERING", 2);
        CONVERGED = new CameraCaptureMetaData$AwbState("CONVERGED", 3);
        LOCKED = new CameraCaptureMetaData$AwbState("LOCKED", 4);
        $VALUES = $values();
    }

    CameraCaptureMetaData$AwbState(String r1, int r2) {
    }

    public static CameraCaptureMetaData$AwbState valueOf(String r1) {
        return (CameraCaptureMetaData$AwbState) Enum.valueOf(CameraCaptureMetaData$AwbState.class, r1);
    }

    public static CameraCaptureMetaData$AwbState[] values() {
        return (CameraCaptureMetaData$AwbState[]) $VALUES.clone();
    }
}
