package com.google.zxing.qrcode.decoder;

import com.google.zxing.common.BitMatrix;

/* loaded from: classes5.dex */
enum DataMask extends Enum<DataMask> {
    private static final /* synthetic */ DataMask[] $VALUES = null;
    public static final DataMask DATA_MASK_000 = null;
    public static final DataMask DATA_MASK_001 = null;
    public static final DataMask DATA_MASK_010 = null;
    public static final DataMask DATA_MASK_011 = null;
    public static final DataMask DATA_MASK_100 = null;
    public static final DataMask DATA_MASK_101 = null;
    public static final DataMask DATA_MASK_110 = null;
    public static final DataMask DATA_MASK_111 = null;

    /* renamed from: com.google.zxing.qrcode.decoder.DataMask$1, reason: invalid class name */
    public enum AnonymousClass1 extends DataMask {
        public AnonymousClass1(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.google.zxing.qrcode.decoder.DataMask
        public boolean isMasked(int r1, int r2) {
            if (((r1 + r2) & 1) != 0) goto L5;
            return true;
        L5:
            return false;
        }
    }

    /* renamed from: com.google.zxing.qrcode.decoder.DataMask$2, reason: invalid class name */
    public enum AnonymousClass2 extends DataMask {
        public AnonymousClass2(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.google.zxing.qrcode.decoder.DataMask
        public boolean isMasked(int r1, int r2) {
            if ((r1 & 1) != 0) goto L5;
            return true;
        L5:
            return false;
        }
    }

    /* renamed from: com.google.zxing.qrcode.decoder.DataMask$3, reason: invalid class name */
    public enum AnonymousClass3 extends DataMask {
        public AnonymousClass3(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.google.zxing.qrcode.decoder.DataMask
        public boolean isMasked(int r1, int r2) {
            if ((r2 % 3) != 0) goto L6;
            return true;
        L6:
            return false;
        }
    }

    /* renamed from: com.google.zxing.qrcode.decoder.DataMask$4, reason: invalid class name */
    public enum AnonymousClass4 extends DataMask {
        public AnonymousClass4(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.google.zxing.qrcode.decoder.DataMask
        public boolean isMasked(int r1, int r2) {
            if (((r1 + r2) % 3) != 0) goto L6;
            return true;
        L6:
            return false;
        }
    }

    /* renamed from: com.google.zxing.qrcode.decoder.DataMask$5, reason: invalid class name */
    public enum AnonymousClass5 extends DataMask {
        public AnonymousClass5(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.google.zxing.qrcode.decoder.DataMask
        public boolean isMasked(int r1, int r2) {
            if ((((r1 / 2) + (r2 / 3)) & 1) != 0) goto L5;
            return true;
        L5:
            return false;
        }
    }

    /* renamed from: com.google.zxing.qrcode.decoder.DataMask$6, reason: invalid class name */
    public enum AnonymousClass6 extends DataMask {
        public AnonymousClass6(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.google.zxing.qrcode.decoder.DataMask
        public boolean isMasked(int r1, int r2) {
            if (((r1 * r2) % 6) != 0) goto L6;
            return true;
        L6:
            return false;
        }
    }

    /* renamed from: com.google.zxing.qrcode.decoder.DataMask$7, reason: invalid class name */
    public enum AnonymousClass7 extends DataMask {
        public AnonymousClass7(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.google.zxing.qrcode.decoder.DataMask
        public boolean isMasked(int r1, int r2) {
            if (((r1 * r2) % 6) >= 3) goto L6;
            return true;
        L6:
            return false;
        }
    }

    /* renamed from: com.google.zxing.qrcode.decoder.DataMask$8, reason: invalid class name */
    public enum AnonymousClass8 extends DataMask {
        public AnonymousClass8(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.google.zxing.qrcode.decoder.DataMask
        public boolean isMasked(int r1, int r2) {
            if ((((r1 + r2) + ((r1 * r2) % 3)) & 1) != 0) goto L5;
            return true;
        L5:
            return false;
        }
    }

    private static /* synthetic */ DataMask[] $values() {
        return new DataMask[]{DATA_MASK_000, DATA_MASK_001, DATA_MASK_010, DATA_MASK_011, DATA_MASK_100, DATA_MASK_101, DATA_MASK_110, DATA_MASK_111};
    }

    static {
        DATA_MASK_000 = new AnonymousClass1("DATA_MASK_000", 0);
        DATA_MASK_001 = new AnonymousClass2("DATA_MASK_001", 1);
        DATA_MASK_010 = new AnonymousClass3("DATA_MASK_010", 2);
        DATA_MASK_011 = new AnonymousClass4("DATA_MASK_011", 3);
        DATA_MASK_100 = new AnonymousClass5("DATA_MASK_100", 4);
        DATA_MASK_101 = new AnonymousClass6("DATA_MASK_101", 5);
        DATA_MASK_110 = new AnonymousClass7("DATA_MASK_110", 6);
        DATA_MASK_111 = new AnonymousClass8("DATA_MASK_111", 7);
        $VALUES = $values();
    }

    DataMask(String r1, int r2) {
    }

    /* synthetic */ DataMask(String r1, int r2, AnonymousClass1 r3) {
        this(r1, r2);
    }

    public static DataMask valueOf(String r1) {
        return (DataMask) Enum.valueOf(DataMask.class, r1);
    }

    public static DataMask[] values() {
        return (DataMask[]) $VALUES.clone();
    }

    public abstract boolean isMasked(int r1, int r2);

    public final void unmaskBitMatrix(BitMatrix r5, int r6) {
        int r1 = 0;
    L3:
        if (r1 >= r6) goto L11;
        int r2 = 0;
    L5:
        if (r2 >= r6) goto L10;
        if (isMasked(r1, r2) == false) goto L9;
        r5.flip(r2, r1);
    L9:
        r2 = r2 + 1;
        goto L5
    L10:
        r1 = r1 + 1;
        goto L3
    }
}
