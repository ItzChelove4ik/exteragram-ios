package com.google.protobuf;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
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
    public static final JavaType VOID = null;
    private final Class<?> boxedType;
    private final Object defaultDefault;
    private final Class<?> type;

    static {
        JavaType r0 = new JavaType("VOID", 0, Void.class, Void.class, null);
        VOID = r0;
        Class r4 = Integer.TYPE;
        JavaType r1 = new JavaType("INT", 1, r4, Integer.class, 0);
        INT = r1;
        JavaType r2 = new JavaType("LONG", 2, Long.TYPE, Long.class, 0L);
        LONG = r2;
        Float r10 = Float.valueOf(0.0f);
        JavaType r3 = new JavaType("FLOAT", 3, Float.TYPE, Float.class, r10);
        FLOAT = r3;
        Double r102 = Double.valueOf(0.0d);
        JavaType r5 = new JavaType("DOUBLE", 4, Double.TYPE, Double.class, r102);
        DOUBLE = r5;
        Boolean r11 = Boolean.FALSE;
        JavaType r6 = new JavaType("BOOLEAN", 5, Boolean.TYPE, Boolean.class, r11);
        BOOLEAN = r6;
        JavaType r7 = new JavaType("STRING", 6, String.class, String.class, _UrlKt.FRAGMENT_ENCODE_SET);
        STRING = r7;
        JavaType r72 = new JavaType("BYTE_STRING", 7, ByteString.class, ByteString.class, ByteString.EMPTY);
        BYTE_STRING = r72;
        JavaType r73 = new JavaType("ENUM", 8, r4, Integer.class, null);
        ENUM = r73;
        JavaType r9 = new JavaType("MESSAGE", 9, Object.class, Object.class, null);
        MESSAGE = r9;
        $VALUES = new JavaType[]{r0, r1, r2, r3, r5, r6, r7, r72, r73, r9};
    }

    JavaType(String r1, int r2, Class r3, Class r4, Object r5) {
        this.type = r3;
        this.boxedType = r4;
        this.defaultDefault = r5;
    }

    public static JavaType valueOf(String r1) {
        return (JavaType) Enum.valueOf(JavaType.class, r1);
    }

    public static JavaType[] values() {
        return (JavaType[]) $VALUES.clone();
    }

    public Class<?> getBoxedType() {
        return this.boxedType;
    }
}
