package com.google.zxing;

/* loaded from: classes5.dex */
public enum ResultMetadataType extends Enum<ResultMetadataType> {
    private static final /* synthetic */ ResultMetadataType[] $VALUES = null;
    public static final ResultMetadataType BYTE_SEGMENTS = null;
    public static final ResultMetadataType ERASURES_CORRECTED = null;
    public static final ResultMetadataType ERRORS_CORRECTED = null;
    public static final ResultMetadataType ERROR_CORRECTION_LEVEL = null;
    public static final ResultMetadataType ISSUE_NUMBER = null;
    public static final ResultMetadataType ORIENTATION = null;
    public static final ResultMetadataType OTHER = null;
    public static final ResultMetadataType PDF417_EXTRA_METADATA = null;
    public static final ResultMetadataType POSSIBLE_COUNTRY = null;
    public static final ResultMetadataType STRUCTURED_APPEND_PARITY = null;
    public static final ResultMetadataType STRUCTURED_APPEND_SEQUENCE = null;
    public static final ResultMetadataType SUGGESTED_PRICE = null;
    public static final ResultMetadataType SYMBOLOGY_IDENTIFIER = null;
    public static final ResultMetadataType UPC_EAN_EXTENSION = null;

    private static /* synthetic */ ResultMetadataType[] $values() {
        return new ResultMetadataType[]{OTHER, ORIENTATION, BYTE_SEGMENTS, ERROR_CORRECTION_LEVEL, ERRORS_CORRECTED, ERASURES_CORRECTED, ISSUE_NUMBER, SUGGESTED_PRICE, POSSIBLE_COUNTRY, UPC_EAN_EXTENSION, PDF417_EXTRA_METADATA, STRUCTURED_APPEND_SEQUENCE, STRUCTURED_APPEND_PARITY, SYMBOLOGY_IDENTIFIER};
    }

    static {
        OTHER = new ResultMetadataType("OTHER", 0);
        ORIENTATION = new ResultMetadataType("ORIENTATION", 1);
        BYTE_SEGMENTS = new ResultMetadataType("BYTE_SEGMENTS", 2);
        ERROR_CORRECTION_LEVEL = new ResultMetadataType("ERROR_CORRECTION_LEVEL", 3);
        ERRORS_CORRECTED = new ResultMetadataType("ERRORS_CORRECTED", 4);
        ERASURES_CORRECTED = new ResultMetadataType("ERASURES_CORRECTED", 5);
        ISSUE_NUMBER = new ResultMetadataType("ISSUE_NUMBER", 6);
        SUGGESTED_PRICE = new ResultMetadataType("SUGGESTED_PRICE", 7);
        POSSIBLE_COUNTRY = new ResultMetadataType("POSSIBLE_COUNTRY", 8);
        UPC_EAN_EXTENSION = new ResultMetadataType("UPC_EAN_EXTENSION", 9);
        PDF417_EXTRA_METADATA = new ResultMetadataType("PDF417_EXTRA_METADATA", 10);
        STRUCTURED_APPEND_SEQUENCE = new ResultMetadataType("STRUCTURED_APPEND_SEQUENCE", 11);
        STRUCTURED_APPEND_PARITY = new ResultMetadataType("STRUCTURED_APPEND_PARITY", 12);
        SYMBOLOGY_IDENTIFIER = new ResultMetadataType("SYMBOLOGY_IDENTIFIER", 13);
        $VALUES = $values();
    }

    ResultMetadataType(String r1, int r2) {
    }

    public static ResultMetadataType valueOf(String r1) {
        return (ResultMetadataType) Enum.valueOf(ResultMetadataType.class, r1);
    }

    public static ResultMetadataType[] values() {
        return (ResultMetadataType[]) $VALUES.clone();
    }
}
