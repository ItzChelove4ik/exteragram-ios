package com.google.gson.reflect;

import c.f$$ExternalSyntheticBUOutline1;
import com.android.dx.dex.code.CstInsn$$ExternalSyntheticBUOutline0;
import com.google.gson.internal.GsonTypes;
import com.google.gson.internal.TroubleshootingGuide;
import com.sun.jna.Native$$ExternalSyntheticBUOutline2;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import okio.Segment$$ExternalSyntheticBUOutline1;
import retrofit2.Utils$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public class TypeToken<T> {
    private final int hashCode;
    private final Class<? super T> rawType;
    private final Type type;

    public TypeToken() {
        Type r0 = getTypeTokenTypeArgument();
        this.type = r0;
        this.rawType = (Class<? super T>) GsonTypes.getRawType(r0);
        this.hashCode = r0.hashCode();
    }

    private TypeToken(Type r2) {
        Objects.requireNonNull(r2);
        Type r22 = GsonTypes.canonicalize(r2);
        this.type = r22;
        this.rawType = (Class<? super T>) GsonTypes.getRawType(r22);
        this.hashCode = r22.hashCode();
    }

    private static IllegalArgumentException buildUnsupportedTypeException(Type r4, Class<?>... r5) {
        StringBuilder r0 = new StringBuilder("Unsupported type, expected one of: ");
        int r1 = r5.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        r0.append(r5[r2].getName());
        r0.append(", ");
        r2 = r2 + 1;
        goto L3
    L5:
        r0.append("but got: ");
        r0.append(r4.getClass().getName());
        r0.append(", for type token: ");
        r0.append(r4.toString());
        return new IllegalArgumentException(r0.toString());
    }

    public static <T> TypeToken<T> get(Class<T> r1) {
        return new TypeToken(r1);
    }

    public static TypeToken<?> get(Type r1) {
        return new TypeToken(r1);
    }

    public static TypeToken<?> getArray(Type r1) {
        return new TypeToken(GsonTypes.arrayOf(r1));
    }

    public static TypeToken<?> getParameterized(Type r13, Type... r14) {
        Objects.requireNonNull(r13);
        Objects.requireNonNull(r14);
        if ((r13 instanceof Class) == false) goto L28;
        Class r0 = (Class) r13;
        TypeVariable<Class<T>>[] r2 = r0.getTypeParameters();
        int r3 = r2.length;
        int r4 = r14.length;
        if (r4 != r3) goto L27;
        if (r14.length != 0) goto L11;
        return get(r0);
    L11:
        if (GsonTypes.requiresOwnerType(r13) == true) goto L24;
        int r5 = 0;
    L13:
        if (r5 >= r3) goto L23;
        Type r6 = r14[r5];
        Objects.requireNonNull(r6, "Type argument must not be null");
        Type r62 = r6;
        Class<?> r7 = GsonTypes.getRawType(r62);
        TypeVariable<Class<T>> r8 = r2[r5];
        Type[] r9 = r8.getBounds();
        int r10 = r9.length;
        int r11 = 0;
    L15:
        if (r11 >= r10) goto L21;
        if (GsonTypes.getRawType(r9[r11]).isAssignableFrom(r7) == false) goto L20;
        r11 = r11 + 1;
        goto L15
    L20:
        throw new IllegalArgumentException("Type argument " + r62 + " does not satisfy bounds for type variable " + r8 + " declared by " + r13);
    L21:
        r5 = r5 + 1;
        goto L13
    L23:
        return new TypeToken(GsonTypes.newParameterizedTypeWithOwner(null, r0, r14));
    L24:
        Native$$ExternalSyntheticBUOutline2.m("Raw type ", r0.getName(), " is not supported because it requires specifying an owner type");
        return null;
    L27:
        throw new IllegalArgumentException(r0.getName() + " requires " + r3 + " type arguments, but got " + r4);
    L28:
        Native$$ExternalSyntheticBUOutline5.m("rawType must be of type Class, but was ", r13);
        return null;
    }

    private Type getTypeTokenTypeArgument() {
        Type r3 = getClass().getGenericSuperclass();
        if ((r3 instanceof ParameterizedType) == false) goto L10;
        ParameterizedType r32 = (ParameterizedType) r3;
        if (r32.getRawType() != TypeToken.class) goto L13;
        Type r33 = GsonTypes.canonicalize(r32.getActualTypeArguments()[0]);
        if (isCapturingTypeVariablesForbidden() == false) goto L9;
        verifyNoTypeVariable(r33);
    L9:
        return r33;
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("Must only create direct subclasses of TypeToken");
        return null;
    L10:
        if (r3 != TypeToken.class) goto L13;
        CstInsn$$ExternalSyntheticBUOutline0.m("TypeToken must be created with a type argument: new TypeToken<...>() {}; When using code shrinkers (ProGuard, R8, ...) make sure that generic signatures are preserved.\nSee ", TroubleshootingGuide.createUrl("type-token-raw"));
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.reflect.Type] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.Class] */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.lang.reflect.Type] */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.lang.reflect.Type] */
    /* JADX WARN: Type inference failed for: r1v9 */
    private static boolean isAssignableFrom(Type r1, GenericArrayType r2) {
        Type r22 = r2.getGenericComponentType();
        if ((r22 instanceof ParameterizedType) == true) goto L5;
        return true;
    L5:
        if ((r1 instanceof GenericArrayType) == false) goto L8;
        r1 = ((GenericArrayType) r1).getGenericComponentType();
    L14:
        return isAssignableFrom(r1, (ParameterizedType) r22, new HashMap());
    L8:
        if ((r1 instanceof Class) == false) goto L14;
        r1 = (Class) r1;
    L11:
        if (r1.isArray() == false) goto L14;
        r1 = r1.getComponentType();
        goto L11
    }

    private static boolean isAssignableFrom(Type r9, ParameterizedType r10, Map<String, Type> r11) {
        int r0 = 0;
        if (r9 != null) goto L6;
        return false;
    L6:
        if (r10.equals(r9) == false) goto L8;
        return true;
    L8:
        Class<?> r1 = GsonTypes.getRawType(r9);
        if ((r9 instanceof ParameterizedType) == false) goto L11;
        ParameterizedType r92 = (ParameterizedType) r9;
    L12:
        if (r92 == null) goto L24;
        Type[] r3 = r92.getActualTypeArguments();
        TypeVariable<Class<?>>[] r4 = r1.getTypeParameters();
        int r5 = 0;
    L15:
        if (r5 >= r3.length) goto L22;
        Type r6 = r3[r5];
        TypeVariable<Class<?>> r7 = r4[r5];
    L18:
        if ((r6 instanceof TypeVariable) == false) goto L20;
        r6 = r11.get(((TypeVariable) r6).getName());
        goto L18
    L20:
        r11.put(r7.getName(), r6);
        r5 = r5 + 1;
        goto L15
    L22:
        if (typeEquals(r92, r10, r11) == false) goto L24;
        return true;
    L24:
        Type[] r93 = r1.getGenericInterfaces();
        int r32 = r93.length;
    L25:
        if (r0 >= r32) goto L31;
        if (isAssignableFrom(r93[r0], r10, new HashMap(r11)) == true) goto L28;
        r0 = r0 + 1;
        goto L25
    L28:
        return true;
    L31:
        return isAssignableFrom(r1.getGenericSuperclass(), r10, new HashMap(r11));
    L11:
        r92 = null;
        goto L12
    }

    private static boolean isCapturingTypeVariablesForbidden() {
        return !Objects.equals(System.getProperty("gson.allowCapturingTypeVariables"), "true");
    }

    private static boolean matches(Type r1, Type r2, Map<String, Type> r3) {
        if (r2.equals(r1) == false) goto L5;
        return true;
    L5:
        if ((r1 instanceof TypeVariable) == true) goto L7;
        return false;
    L7:
        if (r2.equals(r3.get(((TypeVariable) r1).getName())) == true) goto L14;
        return false;
    L14:
        return true;
    }

    private static boolean typeEquals(ParameterizedType r4, ParameterizedType r5, Map<String, Type> r6) {
        if (r4.getRawType().equals(r5.getRawType()) == false) goto L13;
        Type[] r42 = r4.getActualTypeArguments();
        Type[] r52 = r5.getActualTypeArguments();
        int r0 = 0;
    L6:
        if (r0 >= r42.length) goto L11;
        if (matches(r42[r0], r52[r0], r6) == false) goto L9;
        r0 = r0 + 1;
        goto L6
    L9:
        return false;
    L11:
        return true;
    L13:
        return false;
    }

    private static void verifyNoTypeVariable(Type r5) {
        if ((r5 instanceof TypeVariable) == false) goto L5;
        TypeVariable r52 = (TypeVariable) r5;
        StringBuilder r0 = new StringBuilder("TypeToken type argument must not contain a type variable; captured type variable ");
        r0.append(r52.getName());
        r0.append(" declared by ");
        r0.append(r52.getGenericDeclaration());
        Utils$$ExternalSyntheticBUOutline1.m(r0, "\nSee ", TroubleshootingGuide.createUrl("typetoken-type-variable"));
        return;
    L5:
        if ((r5 instanceof GenericArrayType) == false) goto L8;
        verifyNoTypeVariable(((GenericArrayType) r5).getGenericComponentType());
        return;
    L8:
        int r1 = 0;
        if ((r5 instanceof ParameterizedType) == false) goto L17;
        ParameterizedType r53 = (ParameterizedType) r5;
        Type r02 = r53.getOwnerType();
        if (r02 == null) goto L13;
        verifyNoTypeVariable(r02);
    L13:
        Type[] r54 = r53.getActualTypeArguments();
        int r03 = r54.length;
    L14:
        if (r1 >= r03) goto L24;
        verifyNoTypeVariable(r54[r1]);
        r1 = r1 + 1;
        goto L14
    L24:
        return;
    L17:
        if ((r5 instanceof WildcardType) == false) goto L25;
        WildcardType r55 = (WildcardType) r5;
        Type[] r04 = r55.getLowerBounds();
        int r2 = r04.length;
        int r3 = 0;
    L19:
        if (r3 >= r2) goto L21;
        verifyNoTypeVariable(r04[r3]);
        r3 = r3 + 1;
        goto L19
    L21:
        Type[] r56 = r55.getUpperBounds();
        int r05 = r56.length;
    L22:
        if (r1 >= r05) goto L34;
        verifyNoTypeVariable(r56[r1]);
        r1 = r1 + 1;
        goto L22
    L34:
        return;
    L25:
        if (r5 == null) goto L27;
        return;
    L27:
        f$$ExternalSyntheticBUOutline1.m("TypeToken captured `null` as type argument; probably a compiler / runtime bug");
    }

    public final boolean equals(Object r2) {
        if ((r2 instanceof TypeToken) == true) goto L5;
        return false;
    L5:
        if (GsonTypes.equals(this.type, ((TypeToken) r2).type) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public final Class<? super T> getRawType() {
        return this.rawType;
    }

    public final Type getType() {
        return this.type;
    }

    public final int hashCode() {
        return this.hashCode;
    }

    @Deprecated
    public boolean isAssignableFrom(TypeToken<?> r1) {
        return isAssignableFrom(r1.getType());
    }

    @Deprecated
    public boolean isAssignableFrom(Class<?> r1) {
        return isAssignableFrom(r1);
    }

    @Deprecated
    public boolean isAssignableFrom(Type r5) {
        if (r5 != null) goto L6;
        return false;
    L6:
        if (this.type.equals(r5) == false) goto L8;
        return true;
    L8:
        Type r1 = this.type;
        if ((r1 instanceof Class) == false) goto L13;
        return this.rawType.isAssignableFrom(GsonTypes.getRawType(r5));
    L13:
        if ((r1 instanceof ParameterizedType) == false) goto L17;
        return isAssignableFrom(r5, (ParameterizedType) r1, new HashMap());
    L17:
        if ((r1 instanceof GenericArrayType) == false) goto L25;
        if (this.rawType.isAssignableFrom(GsonTypes.getRawType(r5)) == true) goto L21;
    L23:
        return false;
    L21:
        if (isAssignableFrom(r5, (GenericArrayType) this.type) == false) goto L23;
        return true;
    L25:
        throw buildUnsupportedTypeException(r1, new Class[]{Class.class, ParameterizedType.class, GenericArrayType.class});
    }

    public final String toString() {
        return GsonTypes.typeToString(this.type);
    }
}
