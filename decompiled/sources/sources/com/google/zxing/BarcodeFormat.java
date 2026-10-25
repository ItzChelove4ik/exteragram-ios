package com.google.zxing;

/* loaded from: classes5.dex */
public enum BarcodeFormat extends Enum<BarcodeFormat> {
    private static final /* synthetic */ BarcodeFormat[] $VALUES = null;
    public static final BarcodeFormat AZTEC = null;
    public static final BarcodeFormat CODABAR = null;
    public static final BarcodeFormat CODE_128 = null;
    public static final BarcodeFormat CODE_39 = null;
    public static final BarcodeFormat CODE_93 = null;
    public static final BarcodeFormat DATA_MATRIX = null;
    public static final BarcodeFormat EAN_13 = null;
    public static final BarcodeFormat EAN_8 = null;
    public static final BarcodeFormat ITF = null;
    public static final BarcodeFormat MAXICODE = null;
    public static final BarcodeFormat PDF_417 = null;
    public static final BarcodeFormat QR_CODE = null;
    public static final BarcodeFormat RSS_14 = null;
    public static final BarcodeFormat RSS_EXPANDED = null;
    public static final BarcodeFormat UPC_A = null;
    public static final BarcodeFormat UPC_E = null;
    public static final BarcodeFormat UPC_EAN_EXTENSION = null;

    private static /* synthetic */ BarcodeFormat[] $values() {
        return new BarcodeFormat[]{AZTEC, CODABAR, CODE_39, CODE_93, CODE_128, DATA_MATRIX, EAN_8, EAN_13, ITF, MAXICODE, PDF_417, QR_CODE, RSS_14, RSS_EXPANDED, UPC_A, UPC_E, UPC_EAN_EXTENSION};
    }

    static {
        AZTEC = new BarcodeFormat("AZTEC", 0);
        CODABAR = new BarcodeFormat("CODABAR", 1);
        CODE_39 = new BarcodeFormat("CODE_39", 2);
        CODE_93 = new BarcodeFormat("CODE_93", 3);
        CODE_128 = new BarcodeFormat("CODE_128", 4);
        DATA_MATRIX = new BarcodeFormat("DATA_MATRIX", 5);
        EAN_8 = new BarcodeFormat("EAN_8", 6);
        EAN_13 = new BarcodeFormat("EAN_13", 7);
        ITF = new BarcodeFormat("ITF", 8);
        MAXICODE = new BarcodeFormat("MAXICODE", 9);
        PDF_417 = new BarcodeFormat("PDF_417", 10);
        QR_CODE = new BarcodeFormat("QR_CODE", 11);
        RSS_14 = new BarcodeFormat("RSS_14", 12);
        RSS_EXPANDED = new BarcodeFormat("RSS_EXPANDED", 13);
        UPC_A = new BarcodeFormat("UPC_A", 14);
        UPC_E = new BarcodeFormat("UPC_E", 15);
        UPC_EAN_EXTENSION = new BarcodeFormat("UPC_EAN_EXTENSION", 16);
        $VALUES = $values();
    }

    BarcodeFormat(String r1, int r2) {
    }

    public static BarcodeFormat valueOf(String r1) {
        return (BarcodeFormat) Enum.valueOf(BarcodeFormat.class, r1);
    }

    public static BarcodeFormat[] values() {
        return (BarcodeFormat[]) $VALUES.clone();
    }
}
