package com.android.dx;

import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.type.Type;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class TypeId<T> {
    public static final TypeId<Boolean> BOOLEAN = null;
    public static final TypeId<Byte> BYTE = null;
    public static final TypeId<Character> CHAR = null;
    public static final TypeId<Double> DOUBLE = null;
    public static final TypeId<Float> FLOAT = null;
    public static final TypeId<Integer> INT = null;
    public static final TypeId<Long> LONG = null;
    public static final TypeId<Object> OBJECT = null;
    private static final Map<Class<?>, TypeId<?>> PRIMITIVE_TO_TYPE = null;
    public static final TypeId<Short> SHORT = null;
    public static final TypeId<String> STRING = null;
    public static final TypeId<Void> VOID = null;
    final CstType constant;
    final String name;
    final Type ropType;

    static {
        TypeId<Boolean> r0 = new TypeId(Type.BOOLEAN);
        BOOLEAN = r0;
        TypeId<Byte> r1 = new TypeId(Type.BYTE);
        BYTE = r1;
        TypeId<Character> r2 = new TypeId(Type.CHAR);
        CHAR = r2;
        TypeId<Double> r3 = new TypeId(Type.DOUBLE);
        DOUBLE = r3;
        TypeId<Float> r4 = new TypeId(Type.FLOAT);
        FLOAT = r4;
        TypeId<Integer> r5 = new TypeId(Type.INT);
        INT = r5;
        TypeId<Long> r6 = new TypeId(Type.LONG);
        LONG = r6;
        TypeId<Short> r7 = new TypeId(Type.SHORT);
        SHORT = r7;
        TypeId<Void> r8 = new TypeId(Type.VOID);
        VOID = r8;
        OBJECT = new TypeId(Type.OBJECT);
        STRING = new TypeId(Type.STRING);
        HashMap r9 = new HashMap();
        PRIMITIVE_TO_TYPE = r9;
        r9.put(Boolean.TYPE, r0);
        r9.put(Byte.TYPE, r1);
        r9.put(Character.TYPE, r2);
        r9.put(Double.TYPE, r3);
        r9.put(Float.TYPE, r4);
        r9.put(Integer.TYPE, r5);
        r9.put(Long.TYPE, r6);
        r9.put(Short.TYPE, r7);
        r9.put(Void.TYPE, r8);
    }

    public TypeId(Type r2) {
        this(r2.getDescriptor(), r2);
    }

    public TypeId(String r1, Type r2) {
        if (r1 == null) goto L8;
        if (r2 == null) goto L8;
        this.name = r1;
        this.ropType = r2;
        this.constant = CstType.intern(r2);
        return;
    L8:
        throw null;
    }

    public static <T> TypeId<T> get(Class<T> r3) {
        if (r3.isPrimitive() == true) goto L5;
        String r0 = r3.getName().replace('.', '/');
        if (r3.isArray() == true) goto L11;
        r0 = "L" + r0 + ';';
    L11:
        return get(r0);
    L5:
        return (TypeId) PRIMITIVE_TO_TYPE.get(r3);
    }

    public static <T> TypeId<T> get(String r2) {
        return new TypeId(r2, Type.internReturnType(r2));
    }

    public boolean equals(Object r2) {
        if ((r2 instanceof TypeId) == true) goto L5;
        return false;
    L5:
        if (((TypeId) r2).name.equals(this.name) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public MethodId<T, Void> getConstructor(TypeId<?>... r4) {
        return new MethodId(this, VOID, "<init>", new TypeList(r4));
    }

    public <V> FieldId<T, V> getField(TypeId<V> r2, String r3) {
        return new FieldId(this, r2, r3);
    }

    public <R> MethodId<T, R> getMethod(TypeId<R> r3, String r4, TypeId<?>... r5) {
        return new MethodId(this, r3, r4, new TypeList(r5));
    }

    public String getName() {
        return this.name;
    }

    public MethodId<T, Void> getStaticInitializer() {
        return new MethodId(this, VOID, "<clinit>", new TypeList(new TypeId[0]));
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public String toString() {
        return this.name;
    }
}
