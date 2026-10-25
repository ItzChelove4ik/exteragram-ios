package androidx.camera.core.impl;

/* loaded from: classes3.dex */
public enum CameraCaptureMetaData$AeMode extends Enum<CameraCaptureMetaData$AeMode> {
    private static final /* synthetic */ CameraCaptureMetaData$AeMode[] $VALUES = null;
    public static final CameraCaptureMetaData$AeMode OFF = null;
    public static final CameraCaptureMetaData$AeMode ON = null;
    public static final CameraCaptureMetaData$AeMode ON_ALWAYS_FLASH = null;
    public static final CameraCaptureMetaData$AeMode ON_AUTO_FLASH = null;
    public static final CameraCaptureMetaData$AeMode ON_AUTO_FLASH_REDEYE = null;
    public static final CameraCaptureMetaData$AeMode ON_EXTERNAL_FLASH = null;
    public static final CameraCaptureMetaData$AeMode UNKNOWN = null;

    private static /* synthetic */ CameraCaptureMetaData$AeMode[] $values() {
        return new CameraCaptureMetaData$AeMode[]{UNKNOWN, OFF, ON, ON_AUTO_FLASH, ON_ALWAYS_FLASH, ON_AUTO_FLASH_REDEYE, ON_EXTERNAL_FLASH};
    }

    static {
        UNKNOWN = new CameraCaptureMetaData$AeMode("UNKNOWN", 0);
        OFF = new CameraCaptureMetaData$AeMode("OFF", 1);
        ON = new CameraCaptureMetaData$AeMode("ON", 2);
        ON_AUTO_FLASH = new CameraCaptureMetaData$AeMode("ON_AUTO_FLASH", 3);
        ON_ALWAYS_FLASH = new CameraCaptureMetaData$AeMode("ON_ALWAYS_FLASH", 4);
        ON_AUTO_FLASH_REDEYE = new CameraCaptureMetaData$AeMode("ON_AUTO_FLASH_REDEYE", 5);
        ON_EXTERNAL_FLASH = new CameraCaptureMetaData$AeMode("ON_EXTERNAL_FLASH", 6);
        $VALUES = $values();
    }

    CameraCaptureMetaData$AeMode(String r1, int r2) {
    }

    public static CameraCaptureMetaData$AeMode valueOf(String r1) {
        return (CameraCaptureMetaData$AeMode) Enum.valueOf(CameraCaptureMetaData$AeMode.class, r1);
    }

    public static CameraCaptureMetaData$AeMode[] values() {
        return (CameraCaptureMetaData$AeMode[]) $VALUES.clone();
    }
}
