package androidx.camera.core.impl;

/* loaded from: classes3.dex */
public enum CameraCaptureMetaData$FlashState extends Enum<CameraCaptureMetaData$FlashState> {
    private static final /* synthetic */ CameraCaptureMetaData$FlashState[] $VALUES = null;
    public static final CameraCaptureMetaData$FlashState FIRED = null;
    public static final CameraCaptureMetaData$FlashState NONE = null;
    public static final CameraCaptureMetaData$FlashState READY = null;
    public static final CameraCaptureMetaData$FlashState UNKNOWN = null;

    private static /* synthetic */ CameraCaptureMetaData$FlashState[] $values() {
        return new CameraCaptureMetaData$FlashState[]{UNKNOWN, NONE, READY, FIRED};
    }

    static {
        UNKNOWN = new CameraCaptureMetaData$FlashState("UNKNOWN", 0);
        NONE = new CameraCaptureMetaData$FlashState("NONE", 1);
        READY = new CameraCaptureMetaData$FlashState("READY", 2);
        FIRED = new CameraCaptureMetaData$FlashState("FIRED", 3);
        $VALUES = $values();
    }

    CameraCaptureMetaData$FlashState(String r1, int r2) {
    }

    public static CameraCaptureMetaData$FlashState valueOf(String r1) {
        return (CameraCaptureMetaData$FlashState) Enum.valueOf(CameraCaptureMetaData$FlashState.class, r1);
    }

    public static CameraCaptureMetaData$FlashState[] values() {
        return (CameraCaptureMetaData$FlashState[]) $VALUES.clone();
    }

    public int toFlashState() {
        int r3 = ordinal();
        if (r3 != 1) goto L5;
        return 2;
    L5:
        if (r3 == 2) goto L10;
        if (r3 == 3) goto L9;
        return 0;
    L9:
        return 1;
    L10:
        return 3;
    }
}
