package com.android.dx.rop.type;

import com.android.dx.util.FixedSizeList;

/* loaded from: classes.dex */
public final class StdTypeList extends FixedSizeList implements TypeList {
    public static final StdTypeList BOOLEANARR_INT = null;
    public static final StdTypeList BYTEARR_INT = null;
    public static final StdTypeList CHARARR_INT = null;
    public static final StdTypeList DOUBLE = null;
    public static final StdTypeList DOUBLEARR_INT = null;
    public static final StdTypeList DOUBLE_DOUBLE = null;
    public static final StdTypeList DOUBLE_DOUBLEARR_INT = null;
    public static final StdTypeList DOUBLE_OBJECT = null;
    public static final StdTypeList EMPTY = null;
    public static final StdTypeList FLOAT = null;
    public static final StdTypeList FLOATARR_INT = null;
    public static final StdTypeList FLOAT_FLOAT = null;
    public static final StdTypeList FLOAT_FLOATARR_INT = null;
    public static final StdTypeList FLOAT_OBJECT = null;
    public static final StdTypeList INT = null;
    public static final StdTypeList INTARR_INT = null;
    public static final StdTypeList INT_BOOLEANARR_INT = null;
    public static final StdTypeList INT_BYTEARR_INT = null;
    public static final StdTypeList INT_CHARARR_INT = null;
    public static final StdTypeList INT_INT = null;
    public static final StdTypeList INT_INTARR_INT = null;
    public static final StdTypeList INT_OBJECT = null;
    public static final StdTypeList INT_SHORTARR_INT = null;
    public static final StdTypeList LONG = null;
    public static final StdTypeList LONGARR_INT = null;
    public static final StdTypeList LONG_INT = null;
    public static final StdTypeList LONG_LONG = null;
    public static final StdTypeList LONG_LONGARR_INT = null;
    public static final StdTypeList LONG_OBJECT = null;
    public static final StdTypeList OBJECT = null;
    public static final StdTypeList OBJECTARR_INT = null;
    public static final StdTypeList OBJECT_OBJECT = null;
    public static final StdTypeList OBJECT_OBJECTARR_INT = null;
    public static final StdTypeList RETURN_ADDRESS = null;
    public static final StdTypeList SHORTARR_INT = null;
    public static final StdTypeList THROWABLE = null;

    static {
        EMPTY = new StdTypeList(0);
        Type r0 = Type.INT;
        INT = make(r0);
        Type r1 = Type.LONG;
        LONG = make(r1);
        Type r2 = Type.FLOAT;
        FLOAT = make(r2);
        Type r3 = Type.DOUBLE;
        DOUBLE = make(r3);
        Type r4 = Type.OBJECT;
        OBJECT = make(r4);
        RETURN_ADDRESS = make(Type.RETURN_ADDRESS);
        THROWABLE = make(Type.THROWABLE);
        INT_INT = make(r0, r0);
        LONG_LONG = make(r1, r1);
        FLOAT_FLOAT = make(r2, r2);
        DOUBLE_DOUBLE = make(r3, r3);
        OBJECT_OBJECT = make(r4, r4);
        INT_OBJECT = make(r0, r4);
        LONG_OBJECT = make(r1, r4);
        FLOAT_OBJECT = make(r2, r4);
        DOUBLE_OBJECT = make(r3, r4);
        LONG_INT = make(r1, r0);
        Type r5 = Type.INT_ARRAY;
        INTARR_INT = make(r5, r0);
        Type r6 = Type.LONG_ARRAY;
        LONGARR_INT = make(r6, r0);
        Type r7 = Type.FLOAT_ARRAY;
        FLOATARR_INT = make(r7, r0);
        Type r8 = Type.DOUBLE_ARRAY;
        DOUBLEARR_INT = make(r8, r0);
        Type r9 = Type.OBJECT_ARRAY;
        OBJECTARR_INT = make(r9, r0);
        Type r10 = Type.BOOLEAN_ARRAY;
        BOOLEANARR_INT = make(r10, r0);
        Type r11 = Type.BYTE_ARRAY;
        BYTEARR_INT = make(r11, r0);
        Type r12 = Type.CHAR_ARRAY;
        CHARARR_INT = make(r12, r0);
        Type r13 = Type.SHORT_ARRAY;
        SHORTARR_INT = make(r13, r0);
        INT_INTARR_INT = make(r0, r5, r0);
        LONG_LONGARR_INT = make(r1, r6, r0);
        FLOAT_FLOATARR_INT = make(r2, r7, r0);
        DOUBLE_DOUBLEARR_INT = make(r3, r8, r0);
        OBJECT_OBJECTARR_INT = make(r4, r9, r0);
        INT_BOOLEANARR_INT = make(r0, r10, r0);
        INT_BYTEARR_INT = make(r0, r11, r0);
        INT_CHARARR_INT = make(r0, r12, r0);
        INT_SHORTARR_INT = make(r0, r13, r0);
    }

    public StdTypeList(int r1) {
        super(r1);
    }

    public static int compareContents(TypeList r7, TypeList r8) {
        int r0 = r7.size();
        int r1 = r8.size();
        int r2 = Math.min(r0, r1);
        int r4 = 0;
    L3:
        if (r4 >= r2) goto L8;
        int r5 = r7.getType(r4).compareTo2(r8.getType(r4));
        if (r5 != 0) goto L6;
        r4 = r4 + 1;
        goto L3
    L6:
        return r5;
    L8:
        if (r0 != r1) goto L10;
        return 0;
    L10:
        if (r0 >= r1) goto L13;
        return -1;
    L13:
        return 1;
    }

    public static boolean equalContents(TypeList r5, TypeList r6) {
        int r0 = r5.size();
        if (r6.size() == r0) goto L5;
        return false;
    L5:
        int r1 = 0;
    L6:
        if (r1 >= r0) goto L11;
        if (r5.getType(r1).equals(r6.getType(r1)) == false) goto L9;
        r1 = r1 + 1;
        goto L6
    L9:
        return false;
    L11:
        return true;
    }

    public static int hashContents(TypeList r4) {
        int r0 = r4.size();
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r2 = (r2 * 31) + r4.getType(r1).hashCode();
        r1 = r1 + 1;
        goto L3
    L5:
        return r2;
    }

    public static StdTypeList make(Type r2) {
        StdTypeList r0 = new StdTypeList(1);
        r0.set(0, r2);
        return r0;
    }

    public static StdTypeList make(Type r2, Type r3) {
        StdTypeList r0 = new StdTypeList(2);
        r0.set(0, r2);
        r0.set(1, r3);
        return r0;
    }

    public static StdTypeList make(Type r2, Type r3, Type r4) {
        StdTypeList r0 = new StdTypeList(3);
        r0.set(0, r2);
        r0.set(1, r3);
        r0.set(2, r4);
        return r0;
    }

    public static StdTypeList make(Type r2, Type r3, Type r4, Type r5) {
        StdTypeList r0 = new StdTypeList(4);
        r0.set(0, r2);
        r0.set(1, r3);
        r0.set(2, r4);
        r0.set(3, r5);
        return r0;
    }

    public static String toHuman(TypeList r4) {
        int r0 = r4.size();
        if (r0 != 0) goto L6;
        return "<empty>";
    L6:
        StringBuilder r1 = new StringBuilder(100);
        int r2 = 0;
    L7:
        if (r2 >= r0) goto L12;
        if (r2 == 0) goto L10;
        r1.append(", ");
    L10:
        r1.append(r4.getType(r2).toHuman());
        r2 = r2 + 1;
        goto L7
    L12:
        return r1.toString();
    }

    public Type get(int r1) {
        return (Type) get0(r1);
    }

    @Override // com.android.dx.rop.type.TypeList
    public Type getType(int r1) {
        return get(r1);
    }

    @Override // com.android.dx.rop.type.TypeList
    public int getWordCount() {
        int r0 = size();
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r2 = r2 + get(r1).getCategory();
        r1 = r1 + 1;
        goto L3
    L5:
        return r2;
    }

    public void set(int r1, Type r2) {
        set0(r1, r2);
    }

    @Override // com.android.dx.rop.type.TypeList
    public TypeList withAddedType(Type r5) {
        int r0 = size();
        StdTypeList r1 = new StdTypeList(r0 + 1);
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        r1.set0(r2, get0(r2));
        r2 = r2 + 1;
        goto L3
    L5:
        r1.set(r0, r5);
        r1.setImmutable();
        return r1;
    }

    public StdTypeList withFirst(Type r4) {
        int r0 = size();
        StdTypeList r1 = new StdTypeList(r0 + 1);
        int r2 = 0;
        r1.set0(0, r4);
    L3:
        if (r2 >= r0) goto L5;
        int r42 = r2 + 1;
        r1.set0(r42, getOrNull0(r2));
        r2 = r42;
        goto L3
    L5:
        return r1;
    }
}
