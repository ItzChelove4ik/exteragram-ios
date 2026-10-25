package com.google.zxing;

import java.util.List;

/* loaded from: classes5.dex */
public enum DecodeHintType extends Enum<DecodeHintType> {
    private static final /* synthetic */ DecodeHintType[] $VALUES = null;
    public static final DecodeHintType ALLOWED_EAN_EXTENSIONS = null;
    public static final DecodeHintType ALLOWED_LENGTHS = null;
    public static final DecodeHintType ALSO_INVERTED = null;
    public static final DecodeHintType ASSUME_CODE_39_CHECK_DIGIT = null;
    public static final DecodeHintType ASSUME_GS1 = null;
    public static final DecodeHintType CHARACTER_SET = null;
    public static final DecodeHintType NEED_RESULT_POINT_CALLBACK = null;
    public static final DecodeHintType OTHER = null;
    public static final DecodeHintType POSSIBLE_FORMATS = null;
    public static final DecodeHintType PURE_BARCODE = null;
    public static final DecodeHintType RETURN_CODABAR_START_END = null;
    public static final DecodeHintType TRY_HARDER = null;
    private final Class<?> valueType;

    private static /* synthetic */ DecodeHintType[] $values() {
        return new DecodeHintType[]{OTHER, PURE_BARCODE, POSSIBLE_FORMATS, TRY_HARDER, CHARACTER_SET, ALLOWED_LENGTHS, ASSUME_CODE_39_CHECK_DIGIT, ASSUME_GS1, RETURN_CODABAR_START_END, NEED_RESULT_POINT_CALLBACK, ALLOWED_EAN_EXTENSIONS, ALSO_INVERTED};
    }

    static {
        OTHER = new DecodeHintType("OTHER", 0, Object.class);
        PURE_BARCODE = new DecodeHintType("PURE_BARCODE", 1, Void.class);
        POSSIBLE_FORMATS = new DecodeHintType("POSSIBLE_FORMATS", 2, List.class);
        TRY_HARDER = new DecodeHintType("TRY_HARDER", 3, Void.class);
        CHARACTER_SET = new DecodeHintType("CHARACTER_SET", 4, String.class);
        ALLOWED_LENGTHS = new DecodeHintType("ALLOWED_LENGTHS", 5, int[].class);
        ASSUME_CODE_39_CHECK_DIGIT = new DecodeHintType("ASSUME_CODE_39_CHECK_DIGIT", 6, Void.class);
        ASSUME_GS1 = new DecodeHintType("ASSUME_GS1", 7, Void.class);
        RETURN_CODABAR_START_END = new DecodeHintType("RETURN_CODABAR_START_END", 8, Void.class);
        NEED_RESULT_POINT_CALLBACK = new DecodeHintType("NEED_RESULT_POINT_CALLBACK", 9, ResultPointCallback.class);
        ALLOWED_EAN_EXTENSIONS = new DecodeHintType("ALLOWED_EAN_EXTENSIONS", 10, int[].class);
        ALSO_INVERTED = new DecodeHintType("ALSO_INVERTED", 11, Void.class);
        $VALUES = $values();
    }

    DecodeHintType(String r1, int r2, Class r3) {
        this.valueType = r3;
    }

    public static DecodeHintType valueOf(String r1) {
        return (DecodeHintType) Enum.valueOf(DecodeHintType.class, r1);
    }

    public static DecodeHintType[] values() {
        return (DecodeHintType[]) $VALUES.clone();
    }
}
