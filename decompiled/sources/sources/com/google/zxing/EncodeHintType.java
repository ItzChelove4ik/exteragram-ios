package com.google.zxing;

/* loaded from: classes5.dex */
public enum EncodeHintType extends Enum<EncodeHintType> {
    private static final /* synthetic */ EncodeHintType[] $VALUES = null;
    public static final EncodeHintType AZTEC_LAYERS = null;
    public static final EncodeHintType CHARACTER_SET = null;
    public static final EncodeHintType CODE128_COMPACT = null;
    public static final EncodeHintType DATA_MATRIX_COMPACT = null;
    public static final EncodeHintType DATA_MATRIX_SHAPE = null;
    public static final EncodeHintType ERROR_CORRECTION = null;
    public static final EncodeHintType FORCE_C40 = null;
    public static final EncodeHintType FORCE_CODE_SET = null;
    public static final EncodeHintType GS1_FORMAT = null;
    public static final EncodeHintType MARGIN = null;

    @Deprecated
    public static final EncodeHintType MAX_SIZE = null;

    @Deprecated
    public static final EncodeHintType MIN_SIZE = null;
    public static final EncodeHintType PDF417_AUTO_ECI = null;
    public static final EncodeHintType PDF417_COMPACT = null;
    public static final EncodeHintType PDF417_COMPACTION = null;
    public static final EncodeHintType PDF417_DIMENSIONS = null;
    public static final EncodeHintType QR_COMPACT = null;
    public static final EncodeHintType QR_MASK_PATTERN = null;
    public static final EncodeHintType QR_VERSION = null;

    private static /* synthetic */ EncodeHintType[] $values() {
        return new EncodeHintType[]{ERROR_CORRECTION, CHARACTER_SET, DATA_MATRIX_SHAPE, DATA_MATRIX_COMPACT, MIN_SIZE, MAX_SIZE, MARGIN, PDF417_COMPACT, PDF417_COMPACTION, PDF417_DIMENSIONS, PDF417_AUTO_ECI, AZTEC_LAYERS, QR_VERSION, QR_MASK_PATTERN, QR_COMPACT, GS1_FORMAT, FORCE_CODE_SET, FORCE_C40, CODE128_COMPACT};
    }

    static {
        ERROR_CORRECTION = new EncodeHintType("ERROR_CORRECTION", 0);
        CHARACTER_SET = new EncodeHintType("CHARACTER_SET", 1);
        DATA_MATRIX_SHAPE = new EncodeHintType("DATA_MATRIX_SHAPE", 2);
        DATA_MATRIX_COMPACT = new EncodeHintType("DATA_MATRIX_COMPACT", 3);
        MIN_SIZE = new EncodeHintType("MIN_SIZE", 4);
        MAX_SIZE = new EncodeHintType("MAX_SIZE", 5);
        MARGIN = new EncodeHintType("MARGIN", 6);
        PDF417_COMPACT = new EncodeHintType("PDF417_COMPACT", 7);
        PDF417_COMPACTION = new EncodeHintType("PDF417_COMPACTION", 8);
        PDF417_DIMENSIONS = new EncodeHintType("PDF417_DIMENSIONS", 9);
        PDF417_AUTO_ECI = new EncodeHintType("PDF417_AUTO_ECI", 10);
        AZTEC_LAYERS = new EncodeHintType("AZTEC_LAYERS", 11);
        QR_VERSION = new EncodeHintType("QR_VERSION", 12);
        QR_MASK_PATTERN = new EncodeHintType("QR_MASK_PATTERN", 13);
        QR_COMPACT = new EncodeHintType("QR_COMPACT", 14);
        GS1_FORMAT = new EncodeHintType("GS1_FORMAT", 15);
        FORCE_CODE_SET = new EncodeHintType("FORCE_CODE_SET", 16);
        FORCE_C40 = new EncodeHintType("FORCE_C40", 17);
        CODE128_COMPACT = new EncodeHintType("CODE128_COMPACT", 18);
        $VALUES = $values();
    }

    EncodeHintType(String r1, int r2) {
    }

    public static EncodeHintType valueOf(String r1) {
        return (EncodeHintType) Enum.valueOf(EncodeHintType.class, r1);
    }

    public static EncodeHintType[] values() {
        return (EncodeHintType[]) $VALUES.clone();
    }
}
