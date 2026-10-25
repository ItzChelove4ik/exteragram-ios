package com.google.gson.internal;

import c.f$$ExternalSyntheticBUOutline1;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import okhttp3.internal.url._UrlKt;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;
import retrofit2.Utils$$ExternalSyntheticBUOutline2;

/* loaded from: classes.dex */
public abstract class GsonTypes {
    static final Type[] EMPTY_TYPE_ARRAY = null;

    public static final class GenericArrayTypeImpl implements GenericArrayType {
        private final Type componentType;

        public GenericArrayTypeImpl(Type r1) {
            Objects.requireNonNull(r1);
            this.componentType = GsonTypes.canonicalize(r1);
        }

        public boolean equals(Object r2) {
            if ((r2 instanceof GenericArrayType) == true) goto L5;
            return false;
        L5:
            if (GsonTypes.equals(this, (GenericArrayType) r2) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // java.lang.reflect.GenericArrayType
        public Type getGenericComponentType() {
            return this.componentType;
        }

        public int hashCode() {
            return this.componentType.hashCode();
        }

        public String toString() {
            return GsonTypes.typeToString(this.componentType) + _UrlKt.PATH_SEGMENT_ENCODE_SET_URI;
        }
    }

    public static final class ParameterizedTypeImpl implements ParameterizedType {
        private final Type ownerType;
        private final Type rawType;
        private final Type[] typeArguments;

        public ParameterizedTypeImpl(Type r3, Class<?> r4, Type... r5) {
            Objects.requireNonNull(r4);
            Type r0 = null;
            if (r3 == null) goto L5;
        L9:
            if (r3 == null) goto L12;
            r0 = GsonTypes.canonicalize(r3);
        L12:
            this.ownerType = r0;
            this.rawType = GsonTypes.canonicalize(r4);
            Type[] r32 = (Type[]) r5.clone();
            this.typeArguments = r32;
            int r33 = r32.length;
            int r42 = 0;
        L13:
            if (r42 >= r33) goto L15;
            Objects.requireNonNull(this.typeArguments[r42]);
            GsonTypes.checkNotPrimitive(this.typeArguments[r42]);
            Type[] r52 = this.typeArguments;
            r52[r42] = GsonTypes.canonicalize(r52[r42]);
            r42 = r42 + 1;
            goto L13
        L15:
            return;
        L5:
            if (GsonTypes.requiresOwnerType(r4) == false) goto L9;
            Native$$ExternalSyntheticBUOutline5.m("Must specify owner type for ", r4);
            throw null;
        }

        private static int hashCodeOrZero(Object r0) {
            if (r0 != null) goto L4;
            return 0;
        L4:
            return r0.hashCode();
        }

        public boolean equals(Object r2) {
            if ((r2 instanceof ParameterizedType) == true) goto L5;
            return false;
        L5:
            if (GsonTypes.equals(this, (ParameterizedType) r2) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type[] getActualTypeArguments() {
            return (Type[]) this.typeArguments.clone();
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getOwnerType() {
            return this.ownerType;
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getRawType() {
            return this.rawType;
        }

        public int hashCode() {
            int r0 = Arrays.hashCode(this.typeArguments) ^ this.rawType.hashCode();
            return hashCodeOrZero(this.ownerType) ^ r0;
        }

        public String toString() {
            int r0 = this.typeArguments.length;
            if (r0 == 0) goto L5;
            StringBuilder r1 = new StringBuilder((r0 + 1) * 30);
            r1.append(GsonTypes.typeToString(this.rawType));
            r1.append("<");
            r1.append(GsonTypes.typeToString(this.typeArguments[0]));
            int r2 = 1;
        L7:
            if (r2 >= r0) goto L9;
            r1.append(", ");
            r1.append(GsonTypes.typeToString(this.typeArguments[r2]));
            r2 = r2 + 1;
            goto L7
        L9:
            r1.append(">");
            return r1.toString();
        L5:
            return GsonTypes.typeToString(this.rawType);
        }
    }

    public static final class WildcardTypeImpl implements WildcardType {
        private final Type lowerBound;
        private final Type upperBound;

        public WildcardTypeImpl(Type[] r5, Type[] r6) {
            if (r6.length <= 1) goto L5;
            f$$ExternalSyntheticBUOutline1.m("At most one lower bound is supported");
            throw null;
        L5:
            if (r5.length == 1) goto L7;
            f$$ExternalSyntheticBUOutline1.m("Exactly one upper bound must be specified");
            throw null;
        L7:
            if (r6.length != 1) goto L14;
            Objects.requireNonNull(r6[0]);
            GsonTypes.checkNotPrimitive(r6[0]);
            if (r5[0] != Object.class) goto L12;
            this.lowerBound = GsonTypes.canonicalize(r6[0]);
            this.upperBound = Object.class;
            return;
        L12:
            f$$ExternalSyntheticBUOutline1.m("When lower bound is specified, upper bound must be Object");
            throw null;
        L14:
            Objects.requireNonNull(r5[0]);
            GsonTypes.checkNotPrimitive(r5[0]);
            this.lowerBound = null;
            this.upperBound = GsonTypes.canonicalize(r5[0]);
        }

        public boolean equals(Object r2) {
            if ((r2 instanceof WildcardType) == true) goto L5;
            return false;
        L5:
            if (GsonTypes.equals(this, (WildcardType) r2) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // java.lang.reflect.WildcardType
        public Type[] getLowerBounds() {
            Type r2 = this.lowerBound;
            if (r2 == null) goto L7;
            return new Type[]{r2};
        L7:
            return GsonTypes.EMPTY_TYPE_ARRAY;
        }

        @Override // java.lang.reflect.WildcardType
        public Type[] getUpperBounds() {
            return new Type[]{this.upperBound};
        }

        public int hashCode() {
            Type r0 = this.lowerBound;
            if (r0 == null) goto L5;
            int r02 = r0.hashCode() + 31;
        L7:
            return (this.upperBound.hashCode() + 31) ^ r02;
        L5:
            r02 = 1;
            goto L7
        }

        public String toString() {
            if (this.lowerBound == null) goto L7;
            return "? super " + GsonTypes.typeToString(this.lowerBound);
        L7:
            if (this.upperBound != Object.class) goto L11;
            return "?";
        L11:
            return "? extends " + GsonTypes.typeToString(this.upperBound);
        }
    }

    static {
        EMPTY_TYPE_ARRAY = new Type[0];
    }

    public static GenericArrayType arrayOf(Type r1) {
        return new GenericArrayTypeImpl(r1);
    }

    public static Type canonicalize(Type r3) {
        if ((r3 instanceof Class) == false) goto L10;
        Class r32 = (Class) r3;
        if (r32.isArray() == true) goto L7;
        return r32;
    L7:
        return new GenericArrayTypeImpl(canonicalize(r32.getComponentType()));
    L10:
        if ((r3 instanceof ParameterizedType) == false) goto L14;
        ParameterizedType r33 = (ParameterizedType) r3;
        return new ParameterizedTypeImpl(r33.getOwnerType(), (Class) r33.getRawType(), r33.getActualTypeArguments());
    L14:
        if ((r3 instanceof GenericArrayType) == false) goto L18;
        return new GenericArrayTypeImpl(((GenericArrayType) r3).getGenericComponentType());
    L18:
        if ((r3 instanceof WildcardType) == false) goto L21;
        WildcardType r34 = (WildcardType) r3;
        return new WildcardTypeImpl(r34.getUpperBounds(), r34.getLowerBounds());
    L21:
        return r3;
    }

    public static void checkNotPrimitive(Type r1) {
        if ((r1 instanceof Class) == true) goto L5;
        return;
    L5:
        if (((Class) r1).isPrimitive() == false) goto L10;
        f$$ExternalSyntheticBUOutline1.m("Primitive type is not allowed");
        return;
    }

    private static Class<?> declaringClassOf(TypeVariable<?> r1) {
        GenericDeclaration r12 = r1.getGenericDeclaration();
        if ((r12 instanceof Class) == true) goto L5;
        return null;
    L5:
        return (Class) r12;
    }

    private static boolean equal(Object r0, Object r1) {
        return Objects.equals(r0, r1);
    }

    public static boolean equals(Type r4, Type r5) {
        if (r4 != r5) goto L6;
        return true;
    L6:
        if ((r4 instanceof Class) == false) goto L10;
        return r4.equals(r5);
    L10:
        if ((r4 instanceof ParameterizedType) == false) goto L23;
        if ((r5 instanceof ParameterizedType) == true) goto L14;
        return false;
    L14:
        ParameterizedType r42 = (ParameterizedType) r4;
        ParameterizedType r52 = (ParameterizedType) r5;
        if (equal(r42.getOwnerType(), r52.getOwnerType()) == true) goto L17;
    L21:
        return false;
    L17:
        if (r42.getRawType().equals(r52.getRawType()) == false) goto L21;
        if (Arrays.equals(r42.getActualTypeArguments(), r52.getActualTypeArguments()) == false) goto L21;
        return true;
    L23:
        if ((r4 instanceof GenericArrayType) == false) goto L30;
        if ((r5 instanceof GenericArrayType) == true) goto L28;
        return false;
    L28:
        return equals(((GenericArrayType) r4).getGenericComponentType(), ((GenericArrayType) r5).getGenericComponentType());
    L30:
        if ((r4 instanceof WildcardType) == false) goto L41;
        if ((r5 instanceof WildcardType) == true) goto L34;
        return false;
    L34:
        WildcardType r43 = (WildcardType) r4;
        WildcardType r53 = (WildcardType) r5;
        if (Arrays.equals(r43.getUpperBounds(), r53.getUpperBounds()) == true) goto L37;
    L39:
        return false;
    L37:
        if (Arrays.equals(r43.getLowerBounds(), r53.getLowerBounds()) == false) goto L39;
        return true;
    L41:
        if ((r4 instanceof TypeVariable) == true) goto L43;
    L50:
        return false;
    L43:
        if ((r5 instanceof TypeVariable) == true) goto L45;
        return false;
    L45:
        TypeVariable r44 = (TypeVariable) r4;
        TypeVariable r54 = (TypeVariable) r5;
        if (Objects.equals(r44.getGenericDeclaration(), r54.getGenericDeclaration()) == false) goto L50;
        if (r44.getName().equals(r54.getName()) == false) goto L50;
        return true;
    }

    public static Type getArrayComponentType(Type r1) {
        if ((r1 instanceof GenericArrayType) == false) goto L7;
        return ((GenericArrayType) r1).getGenericComponentType();
    L7:
        return ((Class) r1).getComponentType();
    }

    public static Type getCollectionElementType(Type r1, Class<?> r2) {
        Type r12 = getSupertype(r1, r2, Collection.class);
        if ((r12 instanceof ParameterizedType) == true) goto L5;
        return Object.class;
    L5:
        return ((ParameterizedType) r12).getActualTypeArguments()[0];
    }

    private static Type getGenericSupertype(Type r3, Class<?> r4, Class<?> r5) {
        if (r5 != r4) goto L5;
        return r3;
    L5:
        if (r5.isInterface() == false) goto L18;
        Class<?>[] r32 = r4.getInterfaces();
        int r0 = r32.length;
        int r1 = 0;
    L7:
        if (r1 >= r0) goto L18;
        Class<?> r2 = r32[r1];
        if (r2 == r5) goto L11;
        if (r5.isAssignableFrom(r2) == true) goto L15;
        r1 = r1 + 1;
        goto L7
    L15:
        return getGenericSupertype(r4.getGenericInterfaces()[r1], r32[r1], r5);
    L11:
        return r4.getGenericInterfaces()[r1];
    L18:
        if (r4.isInterface() == false) goto L20;
    L30:
        return r5;
    L20:
        if (r4 == Object.class) goto L30;
        Class<? super Object> r33 = r4.getSuperclass();
        if (r33 == r5) goto L24;
        if (r5.isAssignableFrom(r33) == true) goto L28;
        r4 = r33;
        goto L20
    L28:
        return getGenericSupertype(r4.getGenericSuperclass(), r33, r5);
    L24:
        return r4.getGenericSuperclass();
    }

    public static Type[] getMapKeyAndValueTypes(Type r4, Class<?> r5) {
        if (Properties.class.isAssignableFrom(r5) == true) goto L5;
        Type r42 = getSupertype(r4, r5, Map.class);
        if ((r42 instanceof ParameterizedType) == false) goto L11;
        return ((ParameterizedType) r42).getActualTypeArguments();
    L11:
        return new Type[]{Object.class, Object.class};
    L5:
        return new Type[]{String.class, String.class};
    }

    public static Class<?> getRawType(Type r3) {
        if ((r3 instanceof Class) == false) goto L7;
        return (Class) r3;
    L7:
        if ((r3 instanceof ParameterizedType) == false) goto L11;
        return (Class) ((ParameterizedType) r3).getRawType();
    L11:
        if ((r3 instanceof GenericArrayType) == false) goto L15;
        return Array.newInstance(getRawType(((GenericArrayType) r3).getGenericComponentType()), 0).getClass();
    L15:
        if ((r3 instanceof TypeVariable) == false) goto L19;
        return Object.class;
    L19:
        if ((r3 instanceof WildcardType) == true) goto L21;
        if (r3 != null) goto L24;
        String r0 = "null";
    L25:
        Utils$$ExternalSyntheticBUOutline2.m("Expected a Class, ParameterizedType, or GenericArrayType, but <", r3, "> is of type ", r0);
        return null;
    L24:
        r0 = r3.getClass().getName();
        goto L25
    L21:
        return getRawType(((WildcardType) r3).getUpperBounds()[0]);
    }

    private static Type getSupertype(Type r1, Class<?> r2, Class<?> r3) {
        if ((r1 instanceof WildcardType) == false) goto L6;
        r1 = ((WildcardType) r1).getUpperBounds()[0];
    L6:
        if (r3.isAssignableFrom(r2) == false) goto L10;
        return resolve(r1, r2, getGenericSupertype(r1, r2, r3));
    L10:
        throw new IllegalArgumentException(r2 + " is not the same as or a subtype of " + r3);
    }

    private static int indexOf(Object[] r4, Object r5) {
        int r0 = r4.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        if (r5.equals(r4[r2]) == true) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return r2;
    L8:
        Utils$$ExternalSyntheticBUOutline0.m();
        return 0;
    }

    public static ParameterizedType newParameterizedTypeWithOwner(Type r1, Class<?> r2, Type... r3) {
        return new ParameterizedTypeImpl(r1, r2, r3);
    }

    public static boolean requiresOwnerType(Type r2) {
        if ((r2 instanceof Class) == false) goto L10;
        Class r22 = (Class) r2;
        if (Modifier.isStatic(r22.getModifiers()) == true) goto L10;
        if (r22.getDeclaringClass() == null) goto L10;
        return true;
    L10:
        return false;
    }

    public static Type resolve(Type r1, Class<?> r2, Type r3) {
        return resolve(r1, r2, r3, new HashMap());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.lang.reflect.Type] */
    /* JADX WARN: Type inference failed for: r12v1, types: [java.lang.reflect.Type] */
    /* JADX WARN: Type inference failed for: r12v10, types: [java.lang.Object, java.lang.reflect.Type] */
    /* JADX WARN: Type inference failed for: r12v11, types: [java.lang.reflect.Type] */
    /* JADX WARN: Type inference failed for: r12v2, types: [java.lang.reflect.WildcardType] */
    /* JADX WARN: Type inference failed for: r12v3, types: [java.lang.reflect.WildcardType] */
    /* JADX WARN: Type inference failed for: r12v4, types: [java.lang.reflect.WildcardType] */
    /* JADX WARN: Type inference failed for: r12v5, types: [java.lang.reflect.ParameterizedType] */
    /* JADX WARN: Type inference failed for: r12v6, types: [java.lang.reflect.GenericArrayType] */
    /* JADX WARN: Type inference failed for: r12v7 */
    /* JADX WARN: Type inference failed for: r12v9 */
    /* JADX WARN: Type inference failed for: r13v0, types: [java.util.Map, java.util.Map<java.lang.reflect.TypeVariable<?>, java.lang.reflect.Type>] */
    private static Type resolve(Type r10, Class<?> r11, TypeVariable r12, Map<TypeVariable<?>, Type> r13) {
        TypeVariable r0 = null;
    L4:
        if ((r12 instanceof TypeVariable) == false) goto L17;
        TypeVariable r1 = r12;
        Type r2 = (Type) r13.get(r1);
        Class r3 = Void.TYPE;
        if (r2 != null) goto L7;
        r13.put(r1, r3);
        if (r0 != null) goto L13;
        r0 = r1;
    L13:
        r12 = resolveTypeVariable(r10, r11, r1);
        if (r12 != r1) goto L4;
    L56:
        if (r0 == null) goto L58;
        r13.put(r0, r12);
    L58:
        return r12;
    L7:
        if (r2 != r3) goto L9;
        return r12;
    L9:
        return r2;
    L17:
        if ((r12 instanceof Class) == false) goto L26;
        Class r14 = r12;
        if (r14.isArray() == false) goto L26;
        Class<?> r122 = r14.getComponentType();
        Type r102 = resolve(r10, r11, r122, r13);
        if (equal(r122, r102) == false) goto L23;
        r12 = r14;
        goto L56
    L23:
        Type r103 = arrayOf(r102);
    L24:
        r12 = r103;
    L26:
        if ((r12 instanceof GenericArrayType) == false) goto L31;
        r12 = (GenericArrayType) r12;
        Type r15 = r12.getGenericComponentType();
        Type r104 = resolve(r10, r11, r15, r13);
        if (equal(r15, r104) == true) goto L56;
        r103 = arrayOf(r104);
        goto L24
    L31:
        int r32 = 0;
        if ((r12 instanceof ParameterizedType) == false) goto L45;
        r12 = (ParameterizedType) r12;
        Type r16 = r12.getOwnerType();
        Type r4 = resolve(r10, r11, r16, r13);
        boolean r17 = equal(r4, r16);
        Type[] r5 = r12.getActualTypeArguments();
        int r6 = r5.length;
        Type[] r7 = r5;
        boolean r52 = false;
    L34:
        if (r32 >= r6) goto L41;
        Type r8 = resolve(r10, r11, r7[r32], r13);
        if (equal(r8, r7[r32]) == true) goto L40;
        if (r52 == true) goto L39;
        r7 = (Type[]) r7.clone();
        r52 = true;
    L39:
        r7[r32] = r8;
    L40:
        r32 = r32 + 1;
        goto L34
    L41:
        if (r17 == false) goto L43;
        if (r52 == false) goto L56;
    L43:
        r103 = newParameterizedTypeWithOwner(r4, (Class) r12.getRawType(), r7);
        goto L24
    L45:
        if ((r12 instanceof WildcardType) == false) goto L56;
        r12 = (WildcardType) r12;
        Type[] r18 = r12.getLowerBounds();
        Type[] r42 = r12.getUpperBounds();
        if (r18.length != 1) goto L52;
        Type r105 = resolve(r10, r11, r18[0], r13);
        if (r105 == r18[0]) goto L56;
        r12 = supertypeOf(r105);
        goto L56
    L52:
        if (r42.length != 1) goto L56;
        Type r106 = resolve(r10, r11, r42[0], r13);
        if (r106 == r42[0]) goto L56;
        r12 = subtypeOf(r106);
        goto L56
    }

    private static Type resolveTypeVariable(Type r1, Class<?> r2, TypeVariable<?> r3) {
        Class<?> r0 = declaringClassOf(r3);
        if (r0 == null) goto L9;
        Type r12 = getGenericSupertype(r1, r2, r0);
        if ((r12 instanceof ParameterizedType) == false) goto L9;
        int r22 = indexOf(r0.getTypeParameters(), r3);
        return ((ParameterizedType) r12).getActualTypeArguments()[r22];
    L9:
        return r3;
    }

    public static WildcardType subtypeOf(Type r2) {
        if ((r2 instanceof WildcardType) == false) goto L5;
        Type[] r22 = ((WildcardType) r2).getUpperBounds();
    L7:
        return new WildcardTypeImpl(r22, EMPTY_TYPE_ARRAY);
    L5:
        r22 = new Type[]{r2};
        goto L7
    }

    public static WildcardType supertypeOf(Type r4) {
        if ((r4 instanceof WildcardType) == false) goto L5;
        Type[] r42 = ((WildcardType) r4).getLowerBounds();
    L7:
        return new WildcardTypeImpl(new Type[]{Object.class}, r42);
    L5:
        r42 = new Type[]{r4};
        goto L7
    }

    public static String typeToString(Type r1) {
        if ((r1 instanceof Class) == false) goto L7;
        return ((Class) r1).getName();
    L7:
        return r1.toString();
    }
}
