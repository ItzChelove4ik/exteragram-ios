package com.google.protobuf;

import java.lang.reflect.Type;

/* loaded from: classes5.dex */
public enum FieldType extends Enum<FieldType> {
    private static final /* synthetic */ FieldType[] $VALUES = null;
    public static final FieldType BOOL = null;
    public static final FieldType BOOL_LIST = null;
    public static final FieldType BOOL_LIST_PACKED = null;
    public static final FieldType BYTES = null;
    public static final FieldType BYTES_LIST = null;
    public static final FieldType DOUBLE = null;
    public static final FieldType DOUBLE_LIST = null;
    public static final FieldType DOUBLE_LIST_PACKED = null;
    private static final Type[] EMPTY_TYPES = null;
    public static final FieldType ENUM = null;
    public static final FieldType ENUM_LIST = null;
    public static final FieldType ENUM_LIST_PACKED = null;
    public static final FieldType FIXED32 = null;
    public static final FieldType FIXED32_LIST = null;
    public static final FieldType FIXED32_LIST_PACKED = null;
    public static final FieldType FIXED64 = null;
    public static final FieldType FIXED64_LIST = null;
    public static final FieldType FIXED64_LIST_PACKED = null;
    public static final FieldType FLOAT = null;
    public static final FieldType FLOAT_LIST = null;
    public static final FieldType FLOAT_LIST_PACKED = null;
    public static final FieldType GROUP = null;
    public static final FieldType GROUP_LIST = null;
    public static final FieldType INT32 = null;
    public static final FieldType INT32_LIST = null;
    public static final FieldType INT32_LIST_PACKED = null;
    public static final FieldType INT64 = null;
    public static final FieldType INT64_LIST = null;
    public static final FieldType INT64_LIST_PACKED = null;
    public static final FieldType MAP = null;
    public static final FieldType MESSAGE = null;
    public static final FieldType MESSAGE_LIST = null;
    public static final FieldType SFIXED32 = null;
    public static final FieldType SFIXED32_LIST = null;
    public static final FieldType SFIXED32_LIST_PACKED = null;
    public static final FieldType SFIXED64 = null;
    public static final FieldType SFIXED64_LIST = null;
    public static final FieldType SFIXED64_LIST_PACKED = null;
    public static final FieldType SINT32 = null;
    public static final FieldType SINT32_LIST = null;
    public static final FieldType SINT32_LIST_PACKED = null;
    public static final FieldType SINT64 = null;
    public static final FieldType SINT64_LIST = null;
    public static final FieldType SINT64_LIST_PACKED = null;
    public static final FieldType STRING = null;
    public static final FieldType STRING_LIST = null;
    public static final FieldType UINT32 = null;
    public static final FieldType UINT32_LIST = null;
    public static final FieldType UINT32_LIST_PACKED = null;
    public static final FieldType UINT64 = null;
    public static final FieldType UINT64_LIST = null;
    public static final FieldType UINT64_LIST_PACKED = null;
    private static final FieldType[] VALUES = null;
    private final Collection collection;
    private final Class<?> elementType;
    private final int id;
    private final JavaType javaType;
    private final boolean primitiveScalar;

    /* renamed from: com.google.protobuf.FieldType$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$FieldType$Collection = null;
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$JavaType = null;

        static {
            int[] r0 = new int[JavaType.values().length];
            $SwitchMap$com$google$protobuf$JavaType = r0;
            r0[JavaType.BYTE_STRING.ordinal()] = 1;     // Catch: NoSuchFieldError -> L13
        L21:
            $SwitchMap$com$google$protobuf$JavaType[JavaType.MESSAGE.ordinal()] = 2;     // Catch: NoSuchFieldError -> L14
        L23:
            $SwitchMap$com$google$protobuf$JavaType[JavaType.STRING.ordinal()] = 3;     // Catch: NoSuchFieldError -> L15
        L8:
            int[] r3 = new int[Collection.values().length];
            $SwitchMap$com$google$protobuf$FieldType$Collection = r3;
            r3[Collection.MAP.ordinal()] = 1;     // Catch: NoSuchFieldError -> L16
        L29:
            $SwitchMap$com$google$protobuf$FieldType$Collection[Collection.VECTOR.ordinal()] = 2;     // Catch: NoSuchFieldError -> L17
        L19:
            $SwitchMap$com$google$protobuf$FieldType$Collection[Collection.SCALAR.ordinal()] = 3;     // Catch: NoSuchFieldError -> L18
            return;
        }
    }

    public enum Collection extends Enum<Collection> {
        private static final /* synthetic */ Collection[] $VALUES = null;
        public static final Collection MAP = null;
        public static final Collection PACKED_VECTOR = null;
        public static final Collection SCALAR = null;
        public static final Collection VECTOR = null;
        private final boolean isList;

        static {
            Collection r0 = new Collection("SCALAR", 0, false);
            SCALAR = r0;
            Collection r1 = new Collection("VECTOR", 1, true);
            VECTOR = r1;
            Collection r3 = new Collection("PACKED_VECTOR", 2, true);
            PACKED_VECTOR = r3;
            Collection r4 = new Collection("MAP", 3, false);
            MAP = r4;
            $VALUES = new Collection[]{r0, r1, r3, r4};
        }

        Collection(String r1, int r2, boolean r3) {
            this.isList = r3;
        }

        public static Collection valueOf(String r1) {
            return (Collection) Enum.valueOf(Collection.class, r1);
        }

        public static Collection[] values() {
            return (Collection[]) $VALUES.clone();
        }
    }

    static {
        Collection r5 = Collection.SCALAR;
        JavaType r11 = JavaType.DOUBLE;
        FieldType r0 = new FieldType("DOUBLE", 0, 0, r5, r11);
        DOUBLE = r0;
        JavaType r17 = JavaType.FLOAT;
        FieldType r1 = new FieldType("FLOAT", 1, 1, r5, r17);
        FLOAT = r1;
        JavaType r24 = JavaType.LONG;
        FieldType r12 = new FieldType("INT64", 2, 2, r5, r24);
        INT64 = r12;
        FieldType r13 = new FieldType("UINT64", 3, 3, r5, r24);
        UINT64 = r13;
        JavaType r32 = JavaType.INT;
        FieldType r14 = new FieldType("INT32", 4, 4, r5, r32);
        INT32 = r14;
        FieldType r15 = new FieldType("FIXED64", 5, 5, r5, r24);
        FIXED64 = r15;
        FieldType r16 = new FieldType("FIXED32", 6, 6, r5, r32);
        FIXED32 = r16;
        JavaType r41 = JavaType.BOOLEAN;
        FieldType r18 = new FieldType("BOOL", 7, 7, r5, r41);
        BOOL = r18;
        JavaType r48 = JavaType.STRING;
        FieldType r19 = new FieldType("STRING", 8, 8, r5, r48);
        STRING = r19;
        JavaType r6 = JavaType.MESSAGE;
        FieldType r110 = new FieldType("MESSAGE", 9, 9, r5, r6);
        MESSAGE = r110;
        JavaType r62 = JavaType.BYTE_STRING;
        FieldType r111 = new FieldType("BYTES", 10, 10, r5, r62);
        BYTES = r111;
        FieldType r112 = new FieldType("UINT32", 11, 11, r5, r32);
        UINT32 = r112;
        JavaType r70 = JavaType.ENUM;
        FieldType r113 = new FieldType("ENUM", 12, 12, r5, r70);
        ENUM = r113;
        FieldType r114 = new FieldType("SFIXED32", 13, 13, r5, r32);
        SFIXED32 = r114;
        FieldType r115 = new FieldType("SFIXED64", 14, 14, r5, r24);
        SFIXED64 = r115;
        FieldType r162 = new FieldType("SINT32", 15, 15, r5, r32);
        SINT32 = r162;
        FieldType r116 = new FieldType("SINT64", 16, 16, r5, r24);
        SINT64 = r116;
        FieldType r117 = new FieldType("GROUP", 17, 17, r5, r6);
        GROUP = r117;
        Collection r23 = Collection.VECTOR;
        FieldType r192 = new FieldType("DOUBLE_LIST", 18, 18, r23, r11);
        DOUBLE_LIST = r192;
        FieldType r20 = new FieldType("FLOAT_LIST", 19, 19, r23, r17);
        FLOAT_LIST = r20;
        FieldType r193 = new FieldType("INT64_LIST", 20, 20, r23, r24);
        INT64_LIST = r193;
        FieldType r194 = new FieldType("UINT64_LIST", 21, 21, r23, r24);
        UINT64_LIST = r194;
        FieldType r27 = new FieldType("INT32_LIST", 22, 22, r23, r32);
        INT32_LIST = r27;
        FieldType r195 = new FieldType("FIXED64_LIST", 23, 23, r23, r24);
        FIXED64_LIST = r195;
        FieldType r272 = new FieldType("FIXED32_LIST", 24, 24, r23, r32);
        FIXED32_LIST = r272;
        FieldType r36 = new FieldType("BOOL_LIST", 25, 25, r23, r41);
        BOOL_LIST = r36;
        FieldType r43 = new FieldType("STRING_LIST", 26, 26, r23, r48);
        STRING_LIST = r43;
        FieldType r50 = new FieldType("MESSAGE_LIST", 27, 27, r23, r6);
        MESSAGE_LIST = r50;
        FieldType r57 = new FieldType("BYTES_LIST", 28, 28, r23, r62);
        BYTES_LIST = r57;
        FieldType r273 = new FieldType("UINT32_LIST", 29, 29, r23, r32);
        UINT32_LIST = r273;
        FieldType r65 = new FieldType("ENUM_LIST", 30, 30, r23, r70);
        ENUM_LIST = r65;
        FieldType r274 = new FieldType("SFIXED32_LIST", 31, 31, r23, r32);
        SFIXED32_LIST = r274;
        FieldType r196 = new FieldType("SFIXED64_LIST", 32, 32, r23, r24);
        SFIXED64_LIST = r196;
        FieldType r275 = new FieldType("SINT32_LIST", 33, 33, r23, r32);
        SINT32_LIST = r275;
        FieldType r197 = new FieldType("SINT64_LIST", 34, 34, r23, r24);
        SINT64_LIST = r197;
        Collection r232 = Collection.PACKED_VECTOR;
        FieldType r362 = new FieldType("DOUBLE_LIST_PACKED", 35, 35, r232, r11);
        DOUBLE_LIST_PACKED = r362;
        FieldType r37 = new FieldType("FLOAT_LIST_PACKED", 36, 36, r232, r17);
        FLOAT_LIST_PACKED = r37;
        FieldType r198 = new FieldType("INT64_LIST_PACKED", 37, 37, r232, r24);
        INT64_LIST_PACKED = r198;
        FieldType r199 = new FieldType("UINT64_LIST_PACKED", 38, 38, r232, r24);
        UINT64_LIST_PACKED = r199;
        FieldType r40 = new FieldType("INT32_LIST_PACKED", 39, 39, r232, r32);
        INT32_LIST_PACKED = r40;
        FieldType r1910 = new FieldType("FIXED64_LIST_PACKED", 40, 40, r232, r24);
        FIXED64_LIST_PACKED = r1910;
        FieldType r276 = new FieldType("FIXED32_LIST_PACKED", 41, 41, r232, r32);
        FIXED32_LIST_PACKED = r276;
        FieldType r363 = new FieldType("BOOL_LIST_PACKED", 42, 42, r232, r41);
        BOOL_LIST_PACKED = r363;
        FieldType r277 = new FieldType("UINT32_LIST_PACKED", 43, 43, r232, r32);
        UINT32_LIST_PACKED = r277;
        FieldType r652 = new FieldType("ENUM_LIST_PACKED", 44, 44, r232, r70);
        ENUM_LIST_PACKED = r652;
        FieldType r278 = new FieldType("SFIXED32_LIST_PACKED", 45, 45, r232, r32);
        SFIXED32_LIST_PACKED = r278;
        FieldType r1911 = new FieldType("SFIXED64_LIST_PACKED", 46, 46, r232, r24);
        SFIXED64_LIST_PACKED = r1911;
        FieldType r279 = new FieldType("SINT32_LIST_PACKED", 47, 47, r232, r32);
        SINT32_LIST_PACKED = r279;
        FieldType r1912 = new FieldType("SINT64_LIST_PACKED", 48, 48, r232, r24);
        SINT64_LIST_PACKED = r1912;
        FieldType r502 = new FieldType("GROUP_LIST", 49, 49, r23, r6);
        GROUP_LIST = r502;
        FieldType r80 = new FieldType("MAP", 50, 50, Collection.MAP, JavaType.VOID);
        MAP = r80;
        $VALUES = new FieldType[]{r0, r1, r12, r13, r14, r15, r16, r18, r19, r110, r111, r112, r113, r114, r115, r162, r116, r117, r192, r20, r193, r194, r27, r195, r272, r36, r43, r50, r57, r273, r65, r274, r196, r275, r197, r362, r37, r198, r199, r40, r1910, r276, r363, r277, r652, r278, r1911, r279, r1912, r502, r80};
        int r02 = 0;
        EMPTY_TYPES = new Type[0];
        FieldType[] r118 = values();
        VALUES = new FieldType[r118.length];
        int r2 = r118.length;
    L3:
        if (r02 >= r2) goto L5;
        FieldType r3 = r118[r02];
        VALUES[r3.id] = r3;
        r02 = r02 + 1;
        goto L3
    }

    FieldType(String r1, int r2, int r3, Collection r4, JavaType r5) {
        this.id = r3;
        this.collection = r4;
        this.javaType = r5;
        int r12 = AnonymousClass1.$SwitchMap$com$google$protobuf$FieldType$Collection[r4.ordinal()];
        boolean r32 = true;
        if (r12 == 1) goto L7;
        if (r12 == 2) goto L6;
        this.elementType = null;
    L9:
        if (r4 != Collection.SCALAR) goto L16;
        int r13 = AnonymousClass1.$SwitchMap$com$google$protobuf$JavaType[r5.ordinal()];
        if (r13 == 1) goto L16;
        if (r13 == 2) goto L16;
        if (r13 == 3) goto L16;
    L17:
        this.primitiveScalar = r32;
        return;
    L16:
        r32 = false;
        goto L17
    L6:
        this.elementType = r5.getBoxedType();
        goto L9
    L7:
        this.elementType = r5.getBoxedType();
        goto L9
    }

    public static FieldType valueOf(String r1) {
        return (FieldType) Enum.valueOf(FieldType.class, r1);
    }

    public static FieldType[] values() {
        return (FieldType[]) $VALUES.clone();
    }

    public int id() {
        return this.id;
    }
}
