package com.android.dx.io;

/* loaded from: classes4.dex */
public enum IndexType extends Enum<IndexType> {
    private static final /* synthetic */ IndexType[] $VALUES = null;
    public static final IndexType CALL_SITE_REF = null;
    public static final IndexType FIELD_OFFSET = null;
    public static final IndexType FIELD_REF = null;
    public static final IndexType INLINE_METHOD = null;
    public static final IndexType METHOD_AND_PROTO_REF = null;
    public static final IndexType METHOD_HANDLE_REF = null;
    public static final IndexType METHOD_REF = null;
    public static final IndexType NONE = null;
    public static final IndexType PROTO_REF = null;
    public static final IndexType STRING_REF = null;
    public static final IndexType TYPE_REF = null;
    public static final IndexType UNKNOWN = null;
    public static final IndexType VARIES = null;
    public static final IndexType VTABLE_OFFSET = null;

    static {
        IndexType r0 = new IndexType("UNKNOWN", 0);
        UNKNOWN = r0;
        IndexType r1 = new IndexType("NONE", 1);
        NONE = r1;
        IndexType r2 = new IndexType("VARIES", 2);
        VARIES = r2;
        IndexType r3 = new IndexType("TYPE_REF", 3);
        TYPE_REF = r3;
        IndexType r4 = new IndexType("STRING_REF", 4);
        STRING_REF = r4;
        IndexType r5 = new IndexType("METHOD_REF", 5);
        METHOD_REF = r5;
        IndexType r6 = new IndexType("FIELD_REF", 6);
        FIELD_REF = r6;
        IndexType r7 = new IndexType("METHOD_AND_PROTO_REF", 7);
        METHOD_AND_PROTO_REF = r7;
        IndexType r8 = new IndexType("CALL_SITE_REF", 8);
        CALL_SITE_REF = r8;
        IndexType r9 = new IndexType("INLINE_METHOD", 9);
        INLINE_METHOD = r9;
        IndexType r10 = new IndexType("VTABLE_OFFSET", 10);
        VTABLE_OFFSET = r10;
        IndexType r11 = new IndexType("FIELD_OFFSET", 11);
        FIELD_OFFSET = r11;
        IndexType r12 = new IndexType("METHOD_HANDLE_REF", 12);
        METHOD_HANDLE_REF = r12;
        IndexType r13 = new IndexType("PROTO_REF", 13);
        PROTO_REF = r13;
        $VALUES = new IndexType[]{r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13};
    }

    IndexType(String r1, int r2) {
    }

    public static IndexType valueOf(String r1) {
        return (IndexType) Enum.valueOf(IndexType.class, r1);
    }

    public static IndexType[] values() {
        return (IndexType[]) $VALUES.clone();
    }
}
