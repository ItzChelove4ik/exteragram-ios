package com.android.dx.stock;

import c.f$$ExternalSyntheticBUOutline1;
import com.android.dx.Code;
import com.android.dx.Comparison;
import com.android.dx.DexMaker;
import com.android.dx.Label;
import com.android.dx.Local;
import com.android.dx.MethodId;
import com.android.dx.TypeId;
import com.android.dx.rop.code.RegisterSpec;
import com.sun.jna.Union$$ExternalSyntheticBUOutline0;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import okio.Buffer$$ExternalSyntheticBUOutline2;

/* loaded from: classes4.dex */
public final class ProxyBuilder<T> {
    private static final String FIELD_NAME_HANDLER = "$__handler";
    private static final String FIELD_NAME_METHODS = "$__methodArray";
    private static final Map<Class<?>, Class<?>> PRIMITIVE_TO_BOXED = null;
    private static final Map<Class<?>, MethodId<?, ?>> PRIMITIVE_TO_UNBOX_METHOD = null;
    private static final Map<TypeId<?>, MethodId<?, ?>> PRIMITIVE_TYPE_TO_UNBOX_METHOD = null;
    public static final int VERSION = 1;
    private static final Map<ProxiedClass<?>, Class<?>> generatedProxyClasses = null;
    private final Class<T> baseClass;
    private Class<?>[] constructorArgTypes;
    private Object[] constructorArgValues;
    private File dexCache;
    private InvocationHandler handler;
    private List<Class<?>> interfaces;
    private boolean markTrusted;
    private Method[] methods;
    private ClassLoader parentClassLoader;
    private boolean sharedClassLoader;

    /* renamed from: com.android.dx.stock.ProxyBuilder$1, reason: invalid class name */
    public class AnonymousClass1 implements Comparator<Method> {
        final /* synthetic */ ProxyBuilder this$0;

        public AnonymousClass1(ProxyBuilder r1) {
            this.this$0 = r1;
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(Method r1, Method r2) {
            return compare2(r1, r2);
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(Method r2, Method r3) {
            return (r2.getDeclaringClass() + r2.getName() + Arrays.toString(r2.getParameterTypes()) + r2.getReturnType()).compareTo(r3.getDeclaringClass() + r3.getName() + Arrays.toString(r3.getParameterTypes()) + r3.getReturnType());
        }
    }

    public static class MethodSetEntry {
        public final String name;
        public final Method originalMethod;
        public final Class<?>[] paramTypes;
        public final Class<?> returnType;

        public MethodSetEntry(Method r2) {
            this.originalMethod = r2;
            this.name = r2.getName();
            this.paramTypes = r2.getParameterTypes();
            this.returnType = r2.getReturnType();
        }

        public boolean equals(Object r4) {
            if ((r4 instanceof MethodSetEntry) == false) goto L12;
            MethodSetEntry r42 = (MethodSetEntry) r4;
            if (this.name.equals(r42.name) == false) goto L12;
            if (this.returnType.equals(r42.returnType) == false) goto L12;
            if (Arrays.equals(this.paramTypes, r42.paramTypes) == false) goto L12;
            return true;
        L12:
            return false;
        }

        public int hashCode() {
            int r0 = this.name.hashCode() + 544;
            int r02 = r0 + ((r0 * 31) + this.returnType.hashCode());
            return r02 + ((r02 * 31) + Arrays.hashCode(this.paramTypes));
        }
    }

    public static class ProxiedClass<U> {
        final Class<U> clazz;
        final List<Class<?>> interfaces;
        final ClassLoader requestedClassloader;
        final boolean sharedClassLoader;

        private ProxiedClass(Class<U> r1, List<Class<?>> r2, ClassLoader r3, boolean r4) {
            this.clazz = r1;
            this.interfaces = new ArrayList(r2);
            this.requestedClassloader = r3;
            this.sharedClassLoader = r4;
        }

        public /* synthetic */ ProxiedClass(Class r1, List r2, ClassLoader r3, boolean r4, AnonymousClass1 r5) {
            this(r1, r2, r3, r4);
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if (r5 != null) goto L8;
        L19:
            return false;
        L8:
            if (getClass() != r5.getClass()) goto L19;
            ProxiedClass r52 = (ProxiedClass) r5;
            if (this.clazz != r52.clazz) goto L19;
            if (this.interfaces.equals(r52.interfaces) == false) goto L19;
            if (this.requestedClassloader != r52.requestedClassloader) goto L19;
            if (this.sharedClassLoader != r52.sharedClassLoader) goto L19;
            return true;
        }

        public int hashCode() {
            return ((this.clazz.hashCode() + this.interfaces.hashCode()) + this.requestedClassloader.hashCode()) + (this.sharedClassLoader ? 1 : 0);
        }
    }

    static {
        generatedProxyClasses = Collections.synchronizedMap(new HashMap());
        HashMap r0 = new HashMap();
        PRIMITIVE_TO_BOXED = r0;
        Class r1 = Boolean.TYPE;
        r0.put(r1, Boolean.class);
        Class r3 = Integer.TYPE;
        Class<Integer> r4 = Integer.class;
        r0.put(r3, r4);
        Class r5 = Byte.TYPE;
        Class<Byte> r6 = Byte.class;
        r0.put(r5, r6);
        Class r7 = Long.TYPE;
        r0.put(r7, Long.class);
        Class r9 = Short.TYPE;
        r0.put(r9, Short.class);
        Class r11 = Float.TYPE;
        r0.put(r11, Float.class);
        Class r13 = Double.TYPE;
        r0.put(r13, Double.class);
        Class r15 = Character.TYPE;
        Class<Character> r2 = Character.class;
        r0.put(r15, r2);
        PRIMITIVE_TYPE_TO_UNBOX_METHOD = new HashMap();
        Iterator r02 = r0.entrySet().iterator();
    L4:
        if (r02.hasNext() == false) goto L6;
        Map.Entry r17 = (Map.Entry) r02.next();
        Iterator r19 = r02;
        TypeId<?> r03 = TypeId.get((Class) r17.getKey());
        Class<Character> r18 = r2;
        TypeId r22 = TypeId.get((Class) r17.getValue());
        Class<Integer> r172 = r4;
        Class<Byte> r20 = r6;
        MethodId<?, ?> r23 = r22.getMethod(r22, "valueOf", new TypeId[]{r03});
        PRIMITIVE_TYPE_TO_UNBOX_METHOD.put(r03, r23);
        r4 = r172;
        r2 = r18;
        r02 = r19;
        r6 = r20;
        goto L4
    L6:
        Class<Character> r182 = r2;
        Class<Integer> r173 = r4;
        Class<Byte> r202 = r6;
        HashMap r04 = new HashMap();
        r04.put(r1, TypeId.get(Boolean.class).getMethod(TypeId.BOOLEAN, "booleanValue", new TypeId[0]));
        r04.put(r3, TypeId.get(r173).getMethod(TypeId.INT, "intValue", new TypeId[0]));
        r04.put(r5, TypeId.get(r202).getMethod(TypeId.BYTE, "byteValue", new TypeId[0]));
        r04.put(r7, TypeId.get(Long.class).getMethod(TypeId.LONG, "longValue", new TypeId[0]));
        r04.put(r9, TypeId.get(Short.class).getMethod(TypeId.SHORT, "shortValue", new TypeId[0]));
        r04.put(r11, TypeId.get(Float.class).getMethod(TypeId.FLOAT, "floatValue", new TypeId[0]));
        r04.put(r13, TypeId.get(Double.class).getMethod(TypeId.DOUBLE, "doubleValue", new TypeId[0]));
        r04.put(r15, TypeId.get(r182).getMethod(TypeId.CHAR, "charValue", new TypeId[0]));
        PRIMITIVE_TO_UNBOX_METHOD = r04;
    }

    private ProxyBuilder(Class<T> r3) {
        this.parentClassLoader = ProxyBuilder.class.getClassLoader();
        this.constructorArgTypes = new Class[0];
        this.constructorArgValues = new Object[0];
        this.interfaces = new ArrayList();
        this.baseClass = r3;
    }

    private static Local<?> boxIfRequired(Code r2, Local<?> r3, Local<Object> r4) {
        MethodId<?, ?> r0 = PRIMITIVE_TYPE_TO_UNBOX_METHOD.get(r3.getType());
        if (r0 != null) goto L5;
        return r3;
    L5:
        r2.invokeStatic(r0, r4, new Local[]{r3});
        return r4;
    }

    public static Object callSuper(Object r2, Method r3, Object... r4) {
        return r2.getClass().getMethod(superMethodName(r3), r3.getParameterTypes()).invoke(r2, r4);
    L4:
        e = move-exception;
        throw e.getCause();
    }

    private static void check(boolean r0, String r1) {
        if (r0 == false) goto L4;
        return;
    L4:
        f$$ExternalSyntheticBUOutline1.m(r1);
    }

    private static TypeId<?>[] classArrayToTypeArray(Class<?>[] r3) {
        TypeId<?>[] r0 = new TypeId[r3.length];
        int r1 = 0;
    L4:
        if (r1 >= r3.length) goto L6;
        r0[r1] = TypeId.get(r3[r1]);
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    public static <T> ProxyBuilder<T> forClass(Class<T> r1) {
        return new ProxyBuilder(r1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <T, G extends T> void generateCodeForAllMethods(DexMaker r35, TypeId<G> r36, Method[] r37, TypeId<T> r38) {
        DexMaker r0 = r35;
        TypeId<G> r1 = r36;
        Method[] r2 = r37;
        TypeId<V> r3 = TypeId.get(InvocationHandler.class);
        TypeId<V> r4 = TypeId.get(Method[].class);
        Object r5 = r1.getField(r3, FIELD_NAME_HANDLER);
        Object r6 = r1.getField(r4, FIELD_NAME_METHODS);
        TypeId r7 = TypeId.get(Method.class);
        TypeId r8 = TypeId.get(Object[].class);
        TypeId<Object> r9 = TypeId.OBJECT;
        MethodId r92 = r3.getMethod(r9, "invoke", new TypeId[]{r9, r7, r8});
        int r11 = 0;
        Object r32 = r3;
        Object r42 = r4;
    L4:
        if (r11 >= r2.length) goto L45;
        Method r12 = r2[r11];
        String r13 = r12.getName();
        Class<?>[] r14 = r12.getParameterTypes();
        int r15 = r14.length;
        TypeId<?>[] r10 = new TypeId[r15];
        int r22 = 0;
    L6:
        if (r22 >= r15) goto L8;
        r10[r22] = TypeId.get(r14[r22]);
        r22 = r22 + 1;
        goto L6
    L8:
        Class<?> r23 = r12.getReturnType();
        int r16 = r11;
        TypeId<R> r112 = TypeId.get(r23);
        MethodId<G, R> r122 = r1.getMethod(r112, r13, r10);
        MethodId r19 = r92;
        TypeId<T> r93 = TypeId.get(AbstractMethodError.class);
        int r18 = r15;
        Code r123 = r0.declare(r122, 1);
        Local r152 = r123.getThis(r1);
        Local r02 = r123.newLocal(r32);
        TypeId<Object> r17 = TypeId.OBJECT;
        Local r03 = r123.newLocal(r17);
        TypeId<Integer> r04 = TypeId.INT;
        Object r222 = r5;
        Local r52 = r123.newLocal(r04);
        Local r53 = r123.newLocal(r8);
        TypeId r24 = r8;
        Local r82 = r123.newLocal(r04);
        Local r110 = r123.newLocal(r17);
        Local r111 = r123.newLocal(r112);
        Local r113 = r123.newLocal(r42);
        Object r27 = r42;
        Local r43 = r123.newLocal(r7);
        Local r05 = r123.newLocal(r04);
        TypeId r28 = r7;
        Class<?> r72 = PRIMITIVE_TO_BOXED.get(r23);
        if (r72 == null) goto L12;
        Local r73 = r123.newLocal(TypeId.get(r72));
    L13:
        Local r25 = r123.newLocal(r32);
        Object r30 = r32;
        if ((r12.getModifiers() & 1024) != 0) goto L17;
        Local[] r33 = new Local[r14.length];
        Local r31 = r123.newLocal(r112);
        MethodId r132 = r38.getMethod(r112, r13, r10);
        TypeId<?>[] r332 = r10;
        Local r102 = r31;
        Local r34 = null;
        TypeId<T> r312 = r93;
        MethodId r94 = r132;
        Local[] r133 = r33;
        Class<?>[] r322 = r14;
        Local r142 = null;
    L18:
        r123.loadConstant(r05, Integer.valueOf(r16));
        r123.sget(r6, r113);
        r123.aget(r43, r113, r05);
        r123.loadConstant(r82, Integer.valueOf(r18));
        r123.newArray(r53, r82);
        Object r06 = r222;
        r123.iget(r06, r02, r152);
        r123.loadConstant(r25, null);
        Label r83 = new Label();
        r123.compare(Comparison.EQ, r83, r25, r02);
        int r114 = 0;
    L19:
        int r26 = r18;
        if (r114 >= r26) goto L22;
        Object r223 = r06;
        r18 = r26;
        r123.loadConstant(r52, Integer.valueOf(r114));
        r123.aput(r53, r52, boxIfRequired(r123, r123.getParameter(r114, r332[r114]), r110));
        r114 = r114 + 1;
        r6 = r6;
        r06 = r223;
        goto L19
    L22:
        Object r224 = r06;
        Object r20 = r6;
        r123.invokeInterface(r19, r03, r02, new Local[]{r152, r43, r53});
        generateCodeForReturnStatement(r123, r23, r03, r111, r73);
        r123.mark(r83);
        int r115 = r12.getModifiers() & 1024;
        Class r44 = Void.TYPE;
        if (r115 != 0) goto L33;
        int r116 = 0;
    L26:
        if (r116 >= r133.length) goto L29;
        r133[r116] = r123.getParameter(r116, r332[r116]);
        r116 = r116 + 1;
        goto L26
    L29:
        if (r44.equals(r23) == false) goto L32;
        r123.invokeSuper(r94, null, r152, r133);
        r123.returnVoid();
    L31:
        Method r117 = r12;
    L34:
        TypeId<?>[] r62 = r332;
        Code r39 = r35.declare(r36.getMethod(r112, superMethodName(r117), r62), 1);
        if ((r117.getModifiers() & 1024) != 0) goto L43;
        Local<T> r118 = r39.getThis(r36);
        int r103 = r322.length;
        Local<?>[] r119 = new Local[r103];
        int r124 = 0;
    L37:
        if (r124 >= r103) goto L40;
        r119[r124] = r39.getParameter(r124, r62[r124]);
        r124 = r124 + 1;
        goto L37
    L40:
        if (r44.equals(r23) == false) goto L42;
        r39.invokeSuper(r94, null, r118, r119);
        r39.returnVoid();
    L44:
        r11 = r16 + 1;
        r92 = r19;
        r1 = r36;
        r0 = r35;
        r6 = r20;
        r5 = r224;
        r8 = r24;
        r42 = r27;
        r7 = r28;
        r32 = r30;
        r2 = r37;
        goto L4
    L42:
        Local<T> r07 = r39.newLocal(r112);
        invokeSuper(r94, r39, r118, r119, r07);
        r39.returnValue(r07);
        goto L44
    L43:
        throwAbstractMethodError(r39, r117, r39.newLocal(TypeId.STRING), r39.newLocal(r312));
        goto L44
    L32:
        invokeSuper(r94, r123, r152, r133, r102);
        r123.returnValue(r102);
        goto L31
    L33:
        r117 = r12;
        throwAbstractMethodError(r123, r117, r34, r142);
        goto L34
    L17:
        Local r134 = r123.newLocal(TypeId.STRING);
        r332 = r10;
        r34 = r134;
        r322 = r14;
        r142 = r123.newLocal(r93);
        r102 = null;
        r133 = null;
        r312 = r93;
        r94 = null;
        goto L18
    L12:
        r73 = null;
        goto L13
    }

    private static void generateCodeForReturnStatement(Code r1, Class r2, Local r3, Local r4, Local r5) {
        if (PRIMITIVE_TO_UNBOX_METHOD.containsKey(r2) == false) goto L7;
        r1.cast(r5, r3);
        r1.invokeVirtual(getUnboxMethodForPrimitive(r2), r4, r5, new Local[0]);
        r1.returnValue(r4);
        return;
    L7:
        if (Void.TYPE.equals(r2) == false) goto L10;
        r1.returnVoid();
        return;
    L10:
        r1.cast(r4, r3);
        r1.returnValue(r4);
    }

    private static <T, G extends T> void generateConstructorsAndFields(DexMaker r11, TypeId<G> r12, TypeId<T> r13, Class<T> r14) {
        TypeId<V> r0 = TypeId.get(InvocationHandler.class);
        TypeId<V> r1 = TypeId.get(Method[].class);
        r11.declare(r12.getField(r0, FIELD_NAME_HANDLER), 2, null);
        r11.declare(r12.getField(r1, FIELD_NAME_METHODS), 10, null);
        Constructor[] r142 = getConstructorsToOverwrite(r14);
        int r02 = r142.length;
        int r2 = 0;
    L3:
        if (r2 >= r02) goto L12;
        Constructor r4 = r142[r2];
        if (r4.getModifiers() == 16) goto L11;
        TypeId<?>[] r42 = classArrayToTypeArray(r4.getParameterTypes());
        Code r5 = r11.declare(r12.getConstructor(r42), 1);
        Local<T> r6 = r5.getThis(r12);
        int r7 = r42.length;
        Local<?>[] r8 = new Local[r7];
        int r9 = 0;
    L8:
        if (r9 >= r7) goto L10;
        r8[r9] = r5.getParameter(r9, r42[r9]);
        r9 = r9 + 1;
        goto L8
    L10:
        r5.invokeDirect(r13.getConstructor(r42), null, r6, r8);
        r5.returnVoid();
    L11:
        r2 = r2 + 1;
        goto L3
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <T> Constructor<T>[] getConstructorsToOverwrite(Class<T> r0) {
        return (Constructor<T>[]) r0.getDeclaredConstructors();
    }

    private TypeId<?>[] getInterfacesAsTypeIds() {
        TypeId<?>[] r0 = new TypeId[this.interfaces.size()];
        Iterator<Class<?>> r4 = this.interfaces.iterator();
        int r1 = 0;
    L4:
        if (r4.hasNext() == false) goto L6;
        r0[r1] = TypeId.get(r4.next());
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    public static InvocationHandler getInvocationHandler(Object r2) {
        Field r0 = r2.getClass().getDeclaredField(FIELD_NAME_HANDLER);     // Catch: IllegalAccessException -> L4 NoSuchFieldException -> L7
        r0.setAccessible(true);     // Catch: IllegalAccessException -> L4 NoSuchFieldException -> L7
        return (InvocationHandler) r0.get(r2);
    L4:
        e = move-exception;
        Buffer$$ExternalSyntheticBUOutline2.m(e);
        return null;
    L7:
        e = move-exception;
        throw new IllegalArgumentException("Not a valid proxy instance", e);
    }

    private static <T> String getMethodNameForProxyOf(Class<T> r3, List<Class<?>> r4) {
        return r3.getName().replace(".", "/") + "_" + Integer.toHexString(r4.hashCode()) + "_Proxy";
    }

    private void getMethodsToProxy(Set<MethodSetEntry> r8, Set<MethodSetEntry> r9, Class<?> r10) {
        Method[] r0 = r10.getDeclaredMethods();
        int r1 = r0.length;
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L30;
        Method r4 = r0[r3];
        if ((r4.getModifiers() & 16) == 0) goto L8;
        MethodSetEntry r5 = new MethodSetEntry(r4);
        r9.add(r5);
        r8.remove(r5);
    L28:
        r3 = r3 + 1;
        goto L3
    L8:
        if ((r4.getModifiers() & 8) != 0) goto L28;
        if (Modifier.isPublic(r4.getModifiers()) == true) goto L20;
        if (Modifier.isProtected(r4.getModifiers()) == true) goto L20;
        if (this.sharedClassLoader == false) goto L28;
        if (Modifier.isPrivate(r4.getModifiers()) == true) goto L28;
    L20:
        if (r4.getName().equals("finalize") == true) goto L22;
    L24:
        MethodSetEntry r52 = new MethodSetEntry(r4);
        if (r9.contains(r52) == true) goto L28;
        r8.add(r52);
        goto L28
    L22:
        if (r4.getParameterTypes().length != 0) goto L24;
    L30:
        if (r10.isInterface() == false) goto L34;
        Class<?>[] r102 = r10.getInterfaces();
        int r02 = r102.length;
    L32:
        if (r2 >= r02) goto L44;
        getMethodsToProxy(r8, r9, r102[r2]);
        r2 = r2 + 1;
        goto L32
    L44:
        return;
    }

    private Method[] getMethodsToProxyRecursive() {
        Set<MethodSetEntry> r0 = new HashSet();
        Set<MethodSetEntry> r1 = new HashSet();
        Class<T> r2 = this.baseClass;
    L3:
        if (r2 == null) goto L5;
        getMethodsToProxy(r0, r1, r2);
        r2 = r2.getSuperclass();
        goto L3
    L5:
        Class<T> r22 = this.baseClass;
    L6:
        int r3 = 0;
        if (r22 == null) goto L12;
        Class<?>[] r4 = r22.getInterfaces();
        int r5 = r4.length;
    L9:
        if (r3 >= r5) goto L11;
        getMethodsToProxy(r0, r1, r4[r3]);
        r3 = r3 + 1;
        goto L9
    L11:
        r22 = r22.getSuperclass();
        goto L6
    L12:
        Iterator<Class<?>> r23 = this.interfaces.iterator();
    L14:
        if (r23.hasNext() == false) goto L16;
        getMethodsToProxy(r0, r1, r23.next());
        goto L14
    L16:
        Method[] r7 = new Method[r0.size()];
        Iterator<MethodSetEntry> r02 = r0.iterator();
    L18:
        if (r02.hasNext() == false) goto L20;
        r7[r3] = r02.next().originalMethod;
        r3 = r3 + 1;
        goto L18
    L20:
        return r7;
    }

    private static MethodId<?, ?> getUnboxMethodForPrimitive(Class<?> r1) {
        return PRIMITIVE_TO_UNBOX_METHOD.get(r1);
    }

    private static void invokeSuper(MethodId r0, Code r1, Local r2, Local[] r3, Local r4) {
        r1.invokeSuper(r0, r4, r2, r3);
    }

    public static boolean isProxyClass(Class<?> r1) {
        r1.getDeclaredField(FIELD_NAME_HANDLER);     // Catch: NoSuchFieldException -> L5
        return true;
    L5:
        return false;
    }

    private static RuntimeException launderCause(InvocationTargetException r1) {
        Throwable r12 = r1.getCause();
        if ((r12 instanceof Error) == true) goto L11;
        if ((r12 instanceof RuntimeException) == false) goto L9;
        throw ((RuntimeException) r12);
    L9:
        throw new UndeclaredThrowableException(r12);
    L11:
        throw ((Error) r12);
    }

    private Class<? extends T> loadClass(ClassLoader r1, String r2) {
        return (Class<? extends T>) r1.loadClass(r2);
    }

    public static void setInvocationHandler(Object r2, InvocationHandler r3) {
        Field r0 = r2.getClass().getDeclaredField(FIELD_NAME_HANDLER);     // Catch: IllegalAccessException -> L4 NoSuchFieldException -> L7
        r0.setAccessible(true);     // Catch: IllegalAccessException -> L4 NoSuchFieldException -> L7
        r0.set(r2, r3);     // Catch: IllegalAccessException -> L4 NoSuchFieldException -> L7
        return;
    L4:
        e = move-exception;
        Buffer$$ExternalSyntheticBUOutline2.m(e);
        return;
    L7:
        e = move-exception;
        throw new IllegalArgumentException("Not a valid proxy instance", e);
    }

    private static void setMethodsStaticField(Class<?> r1, Method[] r2) {
        Field r12 = r1.getDeclaredField(FIELD_NAME_METHODS);     // Catch: IllegalAccessException -> L4 NoSuchFieldException -> L7
        r12.setAccessible(true);     // Catch: IllegalAccessException -> L4 NoSuchFieldException -> L7
        r12.set(null, r2);     // Catch: IllegalAccessException -> L4 NoSuchFieldException -> L7
        return;
    L4:
        e = move-exception;
        Buffer$$ExternalSyntheticBUOutline2.m(e);
        return;
    L7:
        e = move-exception;
        Buffer$$ExternalSyntheticBUOutline2.m(e);
    }

    private static String superMethodName(Method r3) {
        return "super$" + r3.getName() + "$" + r3.getReturnType().getName().replace('.', '_').replace('[', '_').replace(';', '_');
    }

    private static void throwAbstractMethodError(Code r3, Method r4, Local<String> r5, Local<AbstractMethodError> r6) {
        MethodId<T, Void> r0 = TypeId.get(AbstractMethodError.class).getConstructor(new TypeId[]{TypeId.STRING});
        r3.loadConstant(r5, "'" + r4 + "' cannot be called");
        r3.newInstance(r6, r0, new Local[]{r5});
        r3.throwValue(r6);
    }

    public T build() {
        boolean r1 = false;
        if (this.handler == null) goto L5;
        boolean r0 = true;
    L6:
        check(r0, "handler == null");
        if (this.constructorArgTypes.length != this.constructorArgValues.length) goto L9;
        r1 = true;
    L9:
        check(r1, "constructorArgValues.length != constructorArgTypes.length");
        T r02 = buildProxyClass().getConstructor(this.constructorArgTypes).newInstance(this.constructorArgValues);     // Catch: InvocationTargetException -> L14 IllegalAccessException -> L17 InstantiationException -> L21
        setInvocationHandler(r02, this.handler);
        return r02;
    L17:
        e = move-exception;
        Buffer$$ExternalSyntheticBUOutline2.m(e);
        return null;
    L21:
        e = move-exception;
        Buffer$$ExternalSyntheticBUOutline2.m(e);
        return null;
    L14:
        e = move-exception;
        throw launderCause(e);
    L23:
        Union$$ExternalSyntheticBUOutline0.m("No constructor for ", this.baseClass.getName(), " with parameter types ", Arrays.toString(this.constructorArgTypes));
        return null;
    L5:
        r0 = false;
        goto L6
    }

    public Class<? extends T> buildProxyClass() {
        if (this.sharedClassLoader == false) goto L6;
        ClassLoader r0 = this.baseClass.getClassLoader();
    L5:
        ClassLoader r4 = r0;
        ProxiedClass<?> r1 = new ProxiedClass(this.baseClass, this.interfaces, r4, this.sharedClassLoader, null);
        Map<ProxiedClass<?>, Class<?>> r02 = generatedProxyClasses;
        Class<? extends T> r2 = (Class) r02.get(r1);
        if (r2 == null) goto L10;
        return r2;
    L10:
        DexMaker r5 = new DexMaker();
        String r22 = getMethodNameForProxyOf(this.baseClass, this.interfaces);
        TypeId<?> r6 = TypeId.get("L" + r22 + ";");
        TypeId<?> r9 = TypeId.get(this.baseClass);
        generateConstructorsAndFields(r5, r6, r9, this.baseClass);
        Method[] r3 = this.methods;
        if (r3 != null) goto L13;
        r3 = getMethodsToProxyRecursive();
    L13:
        Arrays.sort(r3, new AnonymousClass1(this));
        generateCodeForAllMethods(r5, r6, r3, r9);
        r5.declare(r6, r22 + ".generated", 1, r9, getInterfacesAsTypeIds());
        if (this.sharedClassLoader == false) goto L17;
        r5.setSharedClassLoader(r4);
    L17:
        if (this.markTrusted == false) goto L20;
        r5.markAsTrusted();
    L20:
        if (this.sharedClassLoader == false) goto L22;
        ClassLoader r42 = r5.generateAndLoad(null, this.dexCache);
    L32:
        Class<? extends T> r11 = loadClass(r42, r22);     // Catch: ClassNotFoundException -> L26 IllegalAccessError -> L29
        setMethodsStaticField(r11, r3);
        r02.put(r1, r11);
        return r11;
    L26:
        e = move-exception;
        Buffer$$ExternalSyntheticBUOutline2.m(e);
        return null;
    L29:
        e = move-exception;
        throw new UnsupportedOperationException("cannot proxy inaccessible class " + this.baseClass, e);
    L22:
        r42 = r5.generateAndLoad(this.parentClassLoader, this.dexCache);
        goto L32
    L6:
        r0 = this.parentClassLoader;
        goto L5
    }

    public ProxyBuilder<T> constructorArgTypes(Class<?>... r1) {
        this.constructorArgTypes = r1;
        return this;
    }

    public ProxyBuilder<T> constructorArgValues(Object... r1) {
        this.constructorArgValues = r1;
        return this;
    }

    public ProxyBuilder<T> dexCache(File r4) {
        File r0 = new File(r4, RegisterSpec.PREFIX + Integer.toString(1));
        this.dexCache = r0;
        r0.mkdir();
        return this;
    }

    public ProxyBuilder<T> handler(InvocationHandler r1) {
        this.handler = r1;
        return this;
    }

    public ProxyBuilder<T> implementing(Class<?>... r6) {
        List<Class<?>> r0 = this.interfaces;
        int r1 = r6.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L16;
        Class<?> r3 = r6[r2];
        if (r3.isInterface() == false) goto L10;
        if (r0.contains(r3) == true) goto L9;
        r0.add(r3);
    L9:
        r2 = r2 + 1;
        goto L3
    L10:
        f$$ExternalSyntheticBUOutline1.m("Not an interface: ".concat(r3.getName()));
        return null;
    L16:
        return this;
    }

    public ProxyBuilder<T> markTrusted() {
        this.markTrusted = true;
        return this;
    }

    public ProxyBuilder<T> onlyMethods(Method[] r1) {
        this.methods = r1;
        return this;
    }

    public ProxyBuilder<T> parentClassLoader(ClassLoader r1) {
        this.parentClassLoader = r1;
        return this;
    }

    public ProxyBuilder<T> withSharedClassLoader() {
        this.sharedClassLoader = true;
        return this;
    }
}
