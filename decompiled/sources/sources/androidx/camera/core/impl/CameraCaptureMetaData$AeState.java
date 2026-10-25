package androidx.camera.core.impl;

/* loaded from: classes3.dex */
public enum CameraCaptureMetaData$AeState extends Enum<CameraCaptureMetaData$AeState> {
    private static final /* synthetic */ CameraCaptureMetaData$AeState[] $VALUES = null;
    public static final CameraCaptureMetaData$AeState CONVERGED = null;
    public static final CameraCaptureMetaData$AeState FLASH_REQUIRED = null;
    public static final CameraCaptureMetaData$AeState INACTIVE = null;
    public static final CameraCaptureMetaData$AeState LOCKED = null;
    public static final CameraCaptureMetaData$AeState SEARCHING = null;
    public static final CameraCaptureMetaData$AeState UNKNOWN = null;

    private static /* synthetic */ CameraCaptureMetaData$AeState[] $values() {
        return new CameraCaptureMetaData$AeState[]{UNKNOWN, INACTIVE, SEARCHING, FLASH_REQUIRED, CONVERGED, LOCKED};
    }

    static {
        UNKNOWN = new CameraCaptureMetaData$AeState("UNKNOWN", 0);
        INACTIVE = new CameraCaptureMetaData$AeState("INACTIVE", 1);
        SEARCHING = new CameraCaptureMetaData$AeState("SEARCHING", 2);
        FLASH_REQUIRED = new CameraCaptureMetaData$AeState("FLASH_REQUIRED", 3);
        CONVERGED = new CameraCaptureMetaData$AeState("CONVERGED", 4);
        LOCKED = new CameraCaptureMetaData$AeState("LOCKED", 5);
        $VALUES = $values();
    }

    CameraCaptureMetaData$AeState(String r1, int r2) {
    }

    public static CameraCaptureMetaData$AeState valueOf(String r1) {
        return (CameraCaptureMetaData$AeState) Enum.valueOf(CameraCaptureMetaData$AeState.class, r1);
    }

    public static CameraCaptureMetaData$AeState[] values() {
        return (CameraCaptureMetaData$AeState[]) $VALUES.clone();
    }
}
