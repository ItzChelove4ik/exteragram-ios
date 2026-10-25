package androidx.camera.core.impl;

/* loaded from: classes3.dex */
public enum CameraCaptureMetaData$AfState extends Enum<CameraCaptureMetaData$AfState> {
    private static final /* synthetic */ CameraCaptureMetaData$AfState[] $VALUES = null;
    public static final CameraCaptureMetaData$AfState INACTIVE = null;
    public static final CameraCaptureMetaData$AfState LOCKED_FOCUSED = null;
    public static final CameraCaptureMetaData$AfState LOCKED_NOT_FOCUSED = null;
    public static final CameraCaptureMetaData$AfState PASSIVE_FOCUSED = null;
    public static final CameraCaptureMetaData$AfState PASSIVE_NOT_FOCUSED = null;
    public static final CameraCaptureMetaData$AfState SCANNING = null;
    public static final CameraCaptureMetaData$AfState UNKNOWN = null;

    private static /* synthetic */ CameraCaptureMetaData$AfState[] $values() {
        return new CameraCaptureMetaData$AfState[]{UNKNOWN, INACTIVE, SCANNING, PASSIVE_FOCUSED, PASSIVE_NOT_FOCUSED, LOCKED_FOCUSED, LOCKED_NOT_FOCUSED};
    }

    static {
        UNKNOWN = new CameraCaptureMetaData$AfState("UNKNOWN", 0);
        INACTIVE = new CameraCaptureMetaData$AfState("INACTIVE", 1);
        SCANNING = new CameraCaptureMetaData$AfState("SCANNING", 2);
        PASSIVE_FOCUSED = new CameraCaptureMetaData$AfState("PASSIVE_FOCUSED", 3);
        PASSIVE_NOT_FOCUSED = new CameraCaptureMetaData$AfState("PASSIVE_NOT_FOCUSED", 4);
        LOCKED_FOCUSED = new CameraCaptureMetaData$AfState("LOCKED_FOCUSED", 5);
        LOCKED_NOT_FOCUSED = new CameraCaptureMetaData$AfState("LOCKED_NOT_FOCUSED", 6);
        $VALUES = $values();
    }

    CameraCaptureMetaData$AfState(String r1, int r2) {
    }

    public static CameraCaptureMetaData$AfState valueOf(String r1) {
        return (CameraCaptureMetaData$AfState) Enum.valueOf(CameraCaptureMetaData$AfState.class, r1);
    }

    public static CameraCaptureMetaData$AfState[] values() {
        return (CameraCaptureMetaData$AfState[]) $VALUES.clone();
    }
}
