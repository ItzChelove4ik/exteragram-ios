package com.android.dx.rop.type;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.util.Hex;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import okhttp3.internal.url._UrlKt;
import okio.Buffer$$ExternalSyntheticBUOutline4;

/* loaded from: classes.dex */
public final class Type implements TypeBearer, Comparable<Type> {
    public static final Type ANNOTATION = null;
    public static final Type BOOLEAN = null;
    public static final Type BOOLEAN_ARRAY = null;
    public static final Type BOOLEAN_CLASS = null;
    public static final int BT_ADDR = 10;
    public static final int BT_BOOLEAN = 1;
    public static final int BT_BYTE = 2;
    public static final int BT_CHAR = 3;
    public static final int BT_COUNT = 11;
    public static final int BT_DOUBLE = 4;
    public static final int BT_FLOAT = 5;
    public static final int BT_INT = 6;
    public static final int BT_LONG = 7;
    public static final int BT_OBJECT = 9;
    public static final int BT_SHORT = 8;
    public static final int BT_VOID = 0;
    public static final Type BYTE = null;
    public static final Type BYTE_ARRAY = null;
    public static final Type BYTE_CLASS = null;
    public static final Type CHAR = null;
    public static final Type CHARACTER_CLASS = null;
    public static final Type CHAR_ARRAY = null;
    public static final Type CLASS = null;
    public static final Type CLONEABLE = null;
    public static final Type DOUBLE = null;
    public static final Type DOUBLE_ARRAY = null;
    public static final Type DOUBLE_CLASS = null;
    public static final Type FLOAT = null;
    public static final Type FLOAT_ARRAY = null;
    public static final Type FLOAT_CLASS = null;
    public static final Type INT = null;
    public static final Type INTEGER_CLASS = null;
    public static final Type INT_ARRAY = null;
    public static final Type KNOWN_NULL = null;
    public static final Type LONG = null;
    public static final Type LONG_ARRAY = null;
    public static final Type LONG_CLASS = null;
    public static final Type METHOD_HANDLE = null;
    public static final Type METHOD_TYPE = null;
    public static final Type OBJECT = null;
    public static final Type OBJECT_ARRAY = null;
    public static final Type RETURN_ADDRESS = null;
    public static final Type SERIALIZABLE = null;
    public static final Type SHORT = null;
    public static final Type SHORT_ARRAY = null;
    public static final Type SHORT_CLASS = null;
    public static final Type STRING = null;
    public static final Type THROWABLE = null;
    public static final Type VAR_HANDLE = null;
    public static final Type VOID = null;
    public static final Type VOID_CLASS = null;
    private static final ConcurrentMap<String, Type> internTable = null;
    private Type arrayType;
    private final int basicType;
    private String className;
    private Type componentType;
    private final String descriptor;
    private Type initializedType;
    private final int newAt;

    static {
        internTable = new ConcurrentHashMap(10000, 0.75f);
        Type r0 = new Type("Z", 1);
        BOOLEAN = r0;
        Type r1 = new Type("B", 2);
        BYTE = r1;
        Type r2 = new Type("C", 3);
        CHAR = r2;
        Type r3 = new Type("D", 4);
        DOUBLE = r3;
        Type r4 = new Type("F", 5);
        FLOAT = r4;
        Type r5 = new Type("I", 6);
        INT = r5;
        Type r6 = new Type("J", 7);
        LONG = r6;
        Type r7 = new Type("S", 8);
        SHORT = r7;
        VOID = new Type("V", 0);
        KNOWN_NULL = new Type("<null>", 9);
        RETURN_ADDRESS = new Type("<addr>", 10);
        ANNOTATION = new Type("Ljava/lang/annotation/Annotation;", 9);
        CLASS = new Type("Ljava/lang/Class;", 9);
        CLONEABLE = new Type("Ljava/lang/Cloneable;", 9);
        METHOD_HANDLE = new Type("Ljava/lang/invoke/MethodHandle;", 9);
        METHOD_TYPE = new Type("Ljava/lang/invoke/MethodType;", 9);
        VAR_HANDLE = new Type("Ljava/lang/invoke/VarHandle;", 9);
        Type r8 = new Type("Ljava/lang/Object;", 9);
        OBJECT = r8;
        SERIALIZABLE = new Type("Ljava/io/Serializable;", 9);
        STRING = new Type("Ljava/lang/String;", 9);
        THROWABLE = new Type("Ljava/lang/Throwable;", 9);
        BOOLEAN_CLASS = new Type("Ljava/lang/Boolean;", 9);
        BYTE_CLASS = new Type("Ljava/lang/Byte;", 9);
        CHARACTER_CLASS = new Type("Ljava/lang/Character;", 9);
        DOUBLE_CLASS = new Type("Ljava/lang/Double;", 9);
        FLOAT_CLASS = new Type("Ljava/lang/Float;", 9);
        INTEGER_CLASS = new Type("Ljava/lang/Integer;", 9);
        LONG_CLASS = new Type("Ljava/lang/Long;", 9);
        SHORT_CLASS = new Type("Ljava/lang/Short;", 9);
        VOID_CLASS = new Type("Ljava/lang/Void;", 9);
        BOOLEAN_ARRAY = new Type("[" + r0.descriptor, 9);
        BYTE_ARRAY = new Type("[" + r1.descriptor, 9);
        CHAR_ARRAY = new Type("[" + r2.descriptor, 9);
        DOUBLE_ARRAY = new Type("[" + r3.descriptor, 9);
        FLOAT_ARRAY = new Type("[" + r4.descriptor, 9);
        INT_ARRAY = new Type("[" + r5.descriptor, 9);
        LONG_ARRAY = new Type("[" + r6.descriptor, 9);
        OBJECT_ARRAY = new Type("[" + r8.descriptor, 9);
        SHORT_ARRAY = new Type("[" + r7.descriptor, 9);
        initInterns();
    }

    private Type(String r2, int r3) {
        this(r2, r3, -1);
    }

    private Type(String r3, int r4, int r5) {
        if (r3 == null) goto L15;
        if (r4 >= 0) goto L6;
    L13:
        f$$ExternalSyntheticBUOutline1.m("bad basicType");
        throw null;
    L6:
        if (r4 >= 11) goto L13;
        if (r5 < (-1)) goto L11;
        this.descriptor = r3;
        this.basicType = r4;
        this.newAt = r5;
        this.arrayType = null;
        this.componentType = null;
        this.initializedType = null;
        return;
    L11:
        f$$ExternalSyntheticBUOutline1.m("newAt < -1");
        throw null;
    L15:
        f$$ExternalSyntheticBUOutline2.m("descriptor == null");
        throw null;
    }

    public static void clearInternTable() {
        internTable.clear();
        initInterns();
    }

    private static void initInterns() {
        putIntern(BOOLEAN);
        putIntern(BYTE);
        putIntern(CHAR);
        putIntern(DOUBLE);
        putIntern(FLOAT);
        putIntern(INT);
        putIntern(LONG);
        putIntern(SHORT);
        putIntern(ANNOTATION);
        putIntern(CLASS);
        putIntern(CLONEABLE);
        putIntern(METHOD_HANDLE);
        putIntern(VAR_HANDLE);
        putIntern(OBJECT);
        putIntern(SERIALIZABLE);
        putIntern(STRING);
        putIntern(THROWABLE);
        putIntern(BOOLEAN_CLASS);
        putIntern(BYTE_CLASS);
        putIntern(CHARACTER_CLASS);
        putIntern(DOUBLE_CLASS);
        putIntern(FLOAT_CLASS);
        putIntern(INTEGER_CLASS);
        putIntern(LONG_CLASS);
        putIntern(SHORT_CLASS);
        putIntern(VOID_CLASS);
        putIntern(BOOLEAN_ARRAY);
        putIntern(BYTE_ARRAY);
        putIntern(CHAR_ARRAY);
        putIntern(DOUBLE_ARRAY);
        putIntern(FLOAT_ARRAY);
        putIntern(INT_ARRAY);
        putIntern(LONG_ARRAY);
        putIntern(OBJECT_ARRAY);
        putIntern(SHORT_ARRAY);
    }

    public static Type intern(String r9) {
        Type r0 = internTable.get(r9);
        if (r0 == null) goto L45;
        return r0;
    L45:
        char r02 = r9.charAt(0);     // Catch: NullPointerException -> L41 IndexOutOfBoundsException -> L43
    L8:
        if (r02 == '[') goto L10;
        int r4 = r9.length();
        if (r02 != 'L') goto L39;
        int r42 = r4 - 1;
        if (r9.charAt(r42) != ';') goto L39;
        int r03 = 1;
    L16:
        if (r03 >= r42) goto L38;
        char r7 = r9.charAt(r03);
        if (r7 == '(') goto L35;
        if (r7 == ')') goto L35;
        if (r7 == '.') goto L35;
        if (r7 == '/') goto L28;
        if (r7 == ';') goto L35;
        if (r7 == '[') goto L35;
    L32:
        r03 = r03 + 1;
        goto L16
    L28:
        if (r03 == 1) goto L33;
        if (r03 == r42) goto L33;
        if (r9.charAt(r03 - 1) != '/') goto L32;
    L33:
        f$$ExternalSyntheticBUOutline1.m("bad descriptor: ".concat(r9));
        return null;
    L35:
        f$$ExternalSyntheticBUOutline1.m("bad descriptor: ".concat(r9));
        return null;
    L38:
        return putIntern(new Type(r9, 9));
    L39:
        f$$ExternalSyntheticBUOutline1.m("bad descriptor: ".concat(r9));
        return null;
    L10:
        return intern(r9.substring(1)).getArrayType();
    L43:
        f$$ExternalSyntheticBUOutline1.m("descriptor is empty");
        return null;
    L41:
        f$$ExternalSyntheticBUOutline2.m("descriptor == null");
        return null;
    }

    public static Type internClassName(String r2) {
        if (r2 != null) goto L4;
        f$$ExternalSyntheticBUOutline2.m("name == null");
        return null;
    L4:
        if (r2.startsWith("[") == false) goto L8;
        return intern(r2);
    L8:
        return intern("L" + r2 + ';');
    }

    public static Type internReturnType(String r1) {
    L8:
        f$$ExternalSyntheticBUOutline2.m("descriptor == null");
        return null;
    L3:
        if (r1.equals("V") == false) goto L7;
        return VOID;
    L7:
        return intern(r1);
    }

    private static Type putIntern(Type r2) {
        Type r0 = internTable.putIfAbsent(r2.getDescriptor(), r2);
        if (r0 == null) goto L5;
        return r0;
    L5:
        return r2;
    }

    public Type asUninitialized(int r4) {
        if (r4 >= 0) goto L5;
        f$$ExternalSyntheticBUOutline1.m("newAt < 0");
        return null;
    L5:
        if (isReference() == true) goto L7;
        Buffer$$ExternalSyntheticBUOutline4.m("not a reference type: ", this.descriptor);
        return null;
    L7:
        if (isUninitialized() == true) goto L10;
        Type r1 = new Type("N" + Hex.u2(r4) + this.descriptor, 9, r4);
        r1.initializedType = this;
        return putIntern(r1);
    L10:
        Buffer$$ExternalSyntheticBUOutline4.m("already uninitialized: ", this.descriptor);
        return null;
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(Type r1) {
        return this.descriptor.compareTo(r1.descriptor);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Type r1) {
        return compareTo2(r1);
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof Type) == true) goto L10;
        return false;
    L10:
        return this.descriptor.equals(((Type) r2).descriptor);
    }

    public Type getArrayType() {
        if (this.arrayType != null) goto L6;
        this.arrayType = putIntern(new Type("[" + this.descriptor, 9));
    L6:
        return this.arrayType;
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public int getBasicFrameType() {
        int r2 = this.basicType;
        if (r2 != 1) goto L5;
    L12:
        return 6;
    L5:
        if (r2 == 2) goto L12;
        if (r2 == 3) goto L12;
        if (r2 == 6) goto L12;
        if (r2 == 8) goto L12;
        return r2;
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public int getBasicType() {
        return this.basicType;
    }

    public int getCategory() {
        int r1 = this.basicType;
        if (r1 != 4) goto L5;
        return 2;
    L5:
        if (r1 == 7) goto L10;
        return 1;
    L10:
        return 2;
    }

    public String getClassName() {
        if (this.className != null) goto L13;
        boolean r0 = isReference();
        String r1 = this.descriptor;
        if (r0 == false) goto L10;
        char r02 = r1.charAt(0);
        String r12 = this.descriptor;
        if (r02 != '[') goto L9;
        this.className = r12;
        goto L13
    L9:
        this.className = r12.substring(1, r12.length() - 1);
        goto L13
    L10:
        Native$$ExternalSyntheticBUOutline5.m("not an object type: ", r1);
        return null;
    L13:
        return this.className;
    }

    public Type getComponentType() {
        if (this.componentType != null) goto L10;
        char r0 = this.descriptor.charAt(0);
        String r1 = this.descriptor;
        if (r0 != '[') goto L7;
        this.componentType = intern(r1.substring(1));
        goto L10
    L7:
        Native$$ExternalSyntheticBUOutline5.m("not an array type: ", r1);
        return null;
    L10:
        return this.componentType;
    }

    public String getDescriptor() {
        return this.descriptor;
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public Type getFrameType() {
        int r0 = this.basicType;
        if (r0 == 1) goto L14;
        if (r0 == 2) goto L14;
        if (r0 == 3) goto L14;
        if (r0 == 6) goto L14;
        if (r0 == 8) goto L14;
        return this;
    L14:
        return INT;
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public /* bridge */ /* synthetic */ TypeBearer getFrameType() {
        return getFrameType();
    }

    public Type getInitializedType() {
        Type r0 = this.initializedType;
        if (r0 == null) goto L5;
        return r0;
    L5:
        Buffer$$ExternalSyntheticBUOutline4.m("initialized type: ", this.descriptor);
        return null;
    }

    public int getNewAt() {
        return this.newAt;
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public Type getType() {
        return this;
    }

    public int hashCode() {
        return this.descriptor.hashCode();
    }

    public boolean isArray() {
        if (this.descriptor.charAt(0) != '[') goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isArrayOrKnownNull() {
        if (isArray() == false) goto L5;
        return true;
    L5:
        if (equals(KNOWN_NULL) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean isCategory1() {
        int r1 = this.basicType;
        if (r1 != 4) goto L5;
        return false;
    L5:
        if (r1 == 7) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isCategory2() {
        int r1 = this.basicType;
        if (r1 != 4) goto L5;
        return true;
    L5:
        if (r1 == 7) goto L10;
        return false;
    L10:
        return true;
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public boolean isConstant() {
        return false;
    }

    public boolean isIntlike() {
        int r2 = this.basicType;
        if (r2 != 1) goto L5;
    L14:
        return true;
    L5:
        if (r2 == 2) goto L14;
        if (r2 == 3) goto L14;
        if (r2 == 6) goto L14;
        if (r2 == 8) goto L14;
        return false;
    }

    public boolean isPrimitive() {
        switch(this.basicType) {
            case 0: goto L6;
            case 1: goto L6;
            case 2: goto L6;
            case 3: goto L6;
            case 4: goto L6;
            case 5: goto L6;
            case 6: goto L6;
            case 7: goto L6;
            case 8: goto L6;
            default: goto L4;
        };
    L4:
        return false;
    L6:
        return true;
    }

    public boolean isReference() {
        if (this.basicType != 9) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isUninitialized() {
        if (this.newAt < 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        switch(this.basicType) {
            case 0: goto L28;
            case 1: goto L26;
            case 2: goto L24;
            case 3: goto L22;
            case 4: goto L20;
            case 5: goto L18;
            case 6: goto L16;
            case 7: goto L14;
            case 8: goto L12;
            case 9: goto L7;
            default: goto L5;
        };
    L12:
        return "short";
    L14:
        return "long";
    L16:
        return "int";
    L18:
        return "float";
    L20:
        return "double";
    L22:
        return "char";
    L24:
        return "byte";
    L26:
        return "boolean";
    L28:
        return "void";
    L5:
        return this.descriptor;
    L7:
        if (isArray() == false) goto L11;
        return getComponentType().toHuman() + _UrlKt.PATH_SEGMENT_ENCODE_SET_URI;
    L11:
        return getClassName().replace("/", ".");
    }

    public String toString() {
        return this.descriptor;
    }
}
