package com.google.protobuf;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public abstract class WireFormat {
    static final int MESSAGE_SET_ITEM_END_TAG = 0;
    static final int MESSAGE_SET_ITEM_TAG = 0;
    static final int MESSAGE_SET_MESSAGE_TAG = 0;
    static final int MESSAGE_SET_TYPE_ID_TAG = 0;

    /* renamed from: com.google.protobuf.WireFormat$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public enum FieldType extends Enum<FieldType> {
        private static final /* synthetic */ FieldType[] $VALUES = null;
        public static final FieldType BOOL = null;
        public static final FieldType BYTES = null;
        public static final FieldType DOUBLE = null;
        public static final FieldType ENUM = null;
        public static final FieldType FIXED32 = null;
        public static final FieldType FIXED64 = null;
        public static final FieldType FLOAT = null;
        public static final FieldType GROUP = null;
        public static final FieldType INT32 = null;
        public static final FieldType INT64 = null;
        public static final FieldType MESSAGE = null;
        public static final FieldType SFIXED32 = null;
        public static final FieldType SFIXED64 = null;
        public static final FieldType SINT32 = null;
        public static final FieldType SINT64 = null;
        public static final FieldType STRING = null;
        public static final FieldType UINT32 = null;
        public static final FieldType UINT64 = null;
        private final JavaType javaType;
        private final int wireType;

        /* renamed from: com.google.protobuf.WireFormat$FieldType$1, reason: invalid class name */
        public enum AnonymousClass1 extends FieldType {
            public AnonymousClass1(String r7, int r8, JavaType r9, int r10) {
                super(r7, r8, r9, r10, null);
            }
        }

        /* renamed from: com.google.protobuf.WireFormat$FieldType$2, reason: invalid class name */
        public enum AnonymousClass2 extends FieldType {
            public AnonymousClass2(String r7, int r8, JavaType r9, int r10) {
                super(r7, r8, r9, r10, null);
            }
        }

        /* renamed from: com.google.protobuf.WireFormat$FieldType$3, reason: invalid class name */
        public enum AnonymousClass3 extends FieldType {
            public AnonymousClass3(String r7, int r8, JavaType r9, int r10) {
                super(r7, r8, r9, r10, null);
            }
        }

        /* renamed from: com.google.protobuf.WireFormat$FieldType$4, reason: invalid class name */
        public enum AnonymousClass4 extends FieldType {
            public AnonymousClass4(String r7, int r8, JavaType r9, int r10) {
                super(r7, r8, r9, r10, null);
            }
        }

        static {
            FieldType r0 = new FieldType("DOUBLE", 0, JavaType.DOUBLE, 1);
            DOUBLE = r0;
            FieldType r1 = new FieldType("FLOAT", 1, JavaType.FLOAT, 5);
            FLOAT = r1;
            JavaType r5 = JavaType.LONG;
            FieldType r2 = new FieldType("INT64", 2, r5, 0);
            INT64 = r2;
            FieldType r7 = new FieldType("UINT64", 3, r5, 0);
            UINT64 = r7;
            JavaType r11 = JavaType.INT;
            FieldType r9 = new FieldType("INT32", 4, r11, 0);
            INT32 = r9;
            FieldType r12 = new FieldType("FIXED64", 5, r5, 1);
            FIXED64 = r12;
            FieldType r14 = new FieldType("FIXED32", 6, r11, 5);
            FIXED32 = r14;
            FieldType r15 = new FieldType("BOOL", 7, JavaType.BOOLEAN, 0);
            BOOL = r15;
            AnonymousClass1 r4 = new AnonymousClass1("STRING", 8, JavaType.STRING, 2);
            STRING = r4;
            JavaType r13 = JavaType.MESSAGE;
            AnonymousClass2 r6 = new AnonymousClass2("GROUP", 9, r13, 3);
            GROUP = r6;
            AnonymousClass3 r3 = new AnonymousClass3("MESSAGE", 10, r13, 2);
            MESSAGE = r3;
            AnonymousClass4 r8 = new AnonymousClass4("BYTES", 11, JavaType.BYTE_STRING, 2);
            BYTES = r8;
            FieldType r02 = new FieldType("UINT32", 12, r11, 0);
            UINT32 = r02;
            FieldType r10 = new FieldType("ENUM", 13, JavaType.ENUM, 0);
            ENUM = r10;
            FieldType r03 = new FieldType("SFIXED32", 14, r11, 5);
            SFIXED32 = r03;
            FieldType r16 = new FieldType("SFIXED64", 15, r5, 1);
            SFIXED64 = r16;
            FieldType r04 = new FieldType("SINT32", 16, r11, 0);
            SINT32 = r04;
            FieldType r22 = new FieldType("SINT64", 17, r5, 0);
            SINT64 = r22;
            $VALUES = new FieldType[]{r0, r1, r2, r7, r9, r12, r14, r15, r4, r6, r3, r8, r02, r10, r03, r16, r04, r22};
        }

        FieldType(String r1, int r2, JavaType r3, int r4) {
            this.javaType = r3;
            this.wireType = r4;
        }

        /* synthetic */ FieldType(String r1, int r2, JavaType r3, int r4, AnonymousClass1 r5) {
            this(r1, r2, r3, r4);
        }

        public static FieldType valueOf(String r1) {
            return (FieldType) Enum.valueOf(FieldType.class, r1);
        }

        public static FieldType[] values() {
            return (FieldType[]) $VALUES.clone();
        }

        public JavaType getJavaType() {
            return this.javaType;
        }

        public int getWireType() {
            return this.wireType;
        }
    }

    public enum JavaType extends Enum<JavaType> {
        private static final /* synthetic */ JavaType[] $VALUES = null;
        public static final JavaType BOOLEAN = null;
        public static final JavaType BYTE_STRING = null;
        public static final JavaType DOUBLE = null;
        public static final JavaType ENUM = null;
        public static final JavaType FLOAT = null;
        public static final JavaType INT = null;
        public static final JavaType LONG = null;
        public static final JavaType MESSAGE = null;
        public static final JavaType STRING = null;
        private final Object defaultDefault;

        static {
            JavaType r0 = new JavaType("INT", 0, 0);
            INT = r0;
            JavaType r1 = new JavaType("LONG", 1, 0L);
            LONG = r1;
            JavaType r2 = new JavaType("FLOAT", 2, Float.valueOf(0.0f));
            FLOAT = r2;
            JavaType r3 = new JavaType("DOUBLE", 3, Double.valueOf(0.0d));
            DOUBLE = r3;
            JavaType r4 = new JavaType("BOOLEAN", 4, Boolean.FALSE);
            BOOLEAN = r4;
            JavaType r5 = new JavaType("STRING", 5, _UrlKt.FRAGMENT_ENCODE_SET);
            STRING = r5;
            JavaType r6 = new JavaType("BYTE_STRING", 6, ByteString.EMPTY);
            BYTE_STRING = r6;
            JavaType r7 = new JavaType("ENUM", 7, null);
            ENUM = r7;
            JavaType r8 = new JavaType("MESSAGE", 8, null);
            MESSAGE = r8;
            $VALUES = new JavaType[]{r0, r1, r2, r3, r4, r5, r6, r7, r8};
        }

        JavaType(String r1, int r2, Object r3) {
            this.defaultDefault = r3;
        }

        public static JavaType valueOf(String r1) {
            return (JavaType) Enum.valueOf(JavaType.class, r1);
        }

        public static JavaType[] values() {
            return (JavaType[]) $VALUES.clone();
        }
    }

    static {
        MESSAGE_SET_ITEM_TAG = makeTag(1, 3);
        MESSAGE_SET_ITEM_END_TAG = makeTag(1, 4);
        MESSAGE_SET_TYPE_ID_TAG = makeTag(2, 0);
        MESSAGE_SET_MESSAGE_TAG = makeTag(3, 2);
    }

    public static int getTagFieldNumber(int r0) {
        return r0 >>> 3;
    }

    public static int getTagWireType(int r0) {
        return r0 & 7;
    }

    public static int makeTag(int r0, int r1) {
        return (r0 << 3) | r1;
    }
}
