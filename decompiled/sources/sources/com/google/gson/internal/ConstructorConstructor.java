package com.google.gson.internal;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.savedstate.Recreator$$ExternalSyntheticBUOutline0;
import com.google.gson.JsonIOException;
import com.google.gson.ReflectionAccessFilter$FilterResult;
import com.google.gson.internal.reflect.ReflectionHelper;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public final class ConstructorConstructor {
    private final Map<Type, Object> instanceCreators;
    private final List<Object> reflectionFilters;
    private final boolean useJdkUnsafe;

    /* loaded from: classes5.dex */
    public static final class ThrowingObjectConstructor<T> implements ObjectConstructor<T> {
        private final String exceptionMessage;

        public ThrowingObjectConstructor(String r1) {
            this.exceptionMessage = r1;
        }

        @Override // com.google.gson.internal.ObjectConstructor
        public T construct() {
            throw new JsonIOException(this.exceptionMessage);
        }
    }

    /* renamed from: $r8$lambda$0n_5b0E8lkRJ8hlZ5T79s-ZITQE, reason: not valid java name */
    public static /* synthetic */ Object m1988$r8$lambda$0n_5b0E8lkRJ8hlZ5T79sZITQE(Class r3) {
        return UnsafeAllocator.INSTANCE.newInstance(r3);
    L4:
        e = move-exception;
        Recreator$$ExternalSyntheticBUOutline0.m("Unable to create instance of ", r3, ". Registering an InstanceCreator or a TypeAdapter for this type, or adding a no-args constructor may fix this problem.", e);
        return null;
    }

    public static /* synthetic */ Object $r8$lambda$jTsTLBoJPmQabBAZSPOYP3s_TSs(Type r4) {
        if ((r4 instanceof ParameterizedType) == false) goto L10;
        Type r0 = ((ParameterizedType) r4).getActualTypeArguments()[0];
        if ((r0 instanceof Class) == true) goto L7;
        ConstructorConstructor$$ExternalSyntheticBUOutline0.m("Invalid EnumMap type: ", r4);
        return null;
    L7:
        return new EnumMap((Class) r0);
    L10:
        ConstructorConstructor$$ExternalSyntheticBUOutline0.m("Invalid EnumMap type: ", r4);
        return null;
    }

    /* renamed from: $r8$lambda$lG8pSQcV9Pwb85UOXKOKVdYJ-V8, reason: not valid java name */
    public static /* synthetic */ Map m1989$r8$lambda$lG8pSQcV9Pwb85UOXKOKVdYJV8() {
        return new LinkedTreeMap();
    }

    public static /* synthetic */ Object $r8$lambda$sWQG8nS8otwtpSrrbzem4XNrFzs(Type r4) {
        if ((r4 instanceof ParameterizedType) == false) goto L10;
        Type r0 = ((ParameterizedType) r4).getActualTypeArguments()[0];
        if ((r0 instanceof Class) == true) goto L7;
        ConstructorConstructor$$ExternalSyntheticBUOutline0.m("Invalid EnumSet type: ", r4);
        return null;
    L7:
        return EnumSet.noneOf((Class) r0);
    L10:
        ConstructorConstructor$$ExternalSyntheticBUOutline0.m("Invalid EnumSet type: ", r4);
        return null;
    }

    public static /* synthetic */ Object $r8$lambda$u8caRUOAahiBpbmiNgmseeMobTQ(Constructor r5) {
        return r5.newInstance(null);
    L5:
        e = move-exception;
        throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e);
    L11:
        e = move-exception;
        throw new RuntimeException("Failed to invoke constructor '" + ReflectionHelper.constructorToString(r5) + "' with no args", e);
    L8:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Failed to invoke constructor '" + ReflectionHelper.constructorToString(r5) + "' with no args", e.getCause());
        return null;
    }

    public ConstructorConstructor(Map<Type, Object> r1, boolean r2, List<Object> r3) {
        this.instanceCreators = r1;
        this.useJdkUnsafe = r2;
        this.reflectionFilters = r3;
    }

    public static String checkInstantiable(Class<?> r2) {
        int r0 = r2.getModifiers();
        if (Modifier.isInterface(r0) == false) goto L7;
        return "Interfaces can't be instantiated! Register an InstanceCreator or a TypeAdapter for this type. Interface name: ".concat(r2.getName());
    L7:
        if (Modifier.isAbstract(r0) == true) goto L9;
        return null;
    L9:
        return "Abstract classes can't be instantiated! Adjust the R8 configuration or register an InstanceCreator or a TypeAdapter for this type. Class name: " + r2.getName() + "\nSee " + TroubleshootingGuide.createUrl("r8-abstract-class");
    }

    private static boolean hasStringKeyType(Type r3) {
        if ((r3 instanceof ParameterizedType) == true) goto L5;
        return true;
    L5:
        Type[] r32 = ((ParameterizedType) r3).getActualTypeArguments();
        if (r32.length != 0) goto L9;
        return false;
    L9:
        if (GsonTypes.getRawType(r32[0]) != String.class) goto L11;
        return true;
    L11:
        return false;
    }

    private static ObjectConstructor<? extends Collection<?>> newCollectionConstructor(Class<?> r1) {
        if (r1.isAssignableFrom(ArrayList.class) == false) goto L7;
        return new ConstructorConstructor$$ExternalSyntheticLambda1();
    L7:
        if (r1.isAssignableFrom(LinkedHashSet.class) == false) goto L11;
        return new ConstructorConstructor$$ExternalSyntheticLambda2();
    L11:
        if (r1.isAssignableFrom(TreeSet.class) == false) goto L15;
        return new ConstructorConstructor$$ExternalSyntheticLambda3();
    L15:
        if (r1.isAssignableFrom(ArrayDeque.class) == true) goto L17;
        return null;
    L17:
        return new ConstructorConstructor$$ExternalSyntheticLambda4();
    }

    private static <T> ObjectConstructor<T> newDefaultConstructor(Class<? super T> r3, ReflectionAccessFilter$FilterResult r4) {
        if (Modifier.isAbstract(r3.getModifiers()) == false) goto L25;
        return null;
    L25:
        Constructor<? super T> r0 = r3.getDeclaredConstructor(null);     // Catch: NoSuchMethodException -> L24
        ReflectionAccessFilter$FilterResult r2 = ReflectionAccessFilter$FilterResult.ALLOW;
        if (r4 != r2) goto L9;
    L17:
        if (r4 != r2) goto L23;
        String r32 = ReflectionHelper.tryMakeAccessible(r0);
        if (r32 == null) goto L23;
        return new ThrowingObjectConstructor(r32);
    L23:
        return new ConstructorConstructor$$ExternalSyntheticLambda13(r0);
    L9:
        if (ReflectionAccessFilterHelper.canAccess(r0, null) == false) goto L16;
        if (r4 != ReflectionAccessFilter$FilterResult.BLOCK_ALL) goto L17;
        if (Modifier.isPublic(r0.getModifiers()) == true) goto L17;
    L16:
        return new ThrowingObjectConstructor("Unable to invoke no-args constructor of " + r3 + "; constructor is not accessible and ReflectionAccessFilter does not permit making it accessible. Register an InstanceCreator or a TypeAdapter for this type, change the visibility of the constructor or adjust the access filter.");
    L24:
        return null;
    }

    private static <T> ObjectConstructor<T> newDefaultImplementationConstructor(Type r1, Class<? super T> r2) {
        if (Collection.class.isAssignableFrom(r2) == false) goto L7;
        return (ObjectConstructor<T>) newCollectionConstructor(r2);
    L7:
        if (Map.class.isAssignableFrom(r2) == true) goto L9;
        return null;
    L9:
        return (ObjectConstructor<T>) newMapConstructor(r1, r2);
    }

    private static ObjectConstructor<? extends Map<?, Object>> newMapConstructor(Type r1, Class<?> r2) {
        if (r2.isAssignableFrom(LinkedTreeMap.class) == false) goto L9;
        if (hasStringKeyType(r1) == false) goto L9;
        return new ConstructorConstructor$$ExternalSyntheticLambda8();
    L9:
        if (r2.isAssignableFrom(LinkedHashMap.class) == false) goto L13;
        return new ConstructorConstructor$$ExternalSyntheticLambda9();
    L13:
        if (r2.isAssignableFrom(TreeMap.class) == false) goto L17;
        return new ConstructorConstructor$$ExternalSyntheticLambda10();
    L17:
        if (r2.isAssignableFrom(ConcurrentHashMap.class) == false) goto L21;
        return new ConstructorConstructor$$ExternalSyntheticLambda11();
    L21:
        if (r2.isAssignableFrom(ConcurrentSkipListMap.class) == true) goto L23;
        return null;
    L23:
        return new ConstructorConstructor$$ExternalSyntheticLambda12();
    }

    private static <T> ObjectConstructor<T> newSpecialCollectionConstructor(Type r1, Class<? super T> r2) {
        if (EnumSet.class.isAssignableFrom(r2) == false) goto L7;
        return new ConstructorConstructor$$ExternalSyntheticLambda6(r1);
    L7:
        if (r2 == EnumMap.class) goto L9;
        return null;
    L9:
        return new ConstructorConstructor$$ExternalSyntheticLambda7(r1);
    }

    private <T> ObjectConstructor<T> newUnsafeAllocator(Class<? super T> r2) {
        if (this.useJdkUnsafe == true) goto L5;
        String r1 = "Unable to create instance of " + r2 + "; usage of JDK Unsafe is disabled. Registering an InstanceCreator or a TypeAdapter for this type, adding a no-args constructor, or enabling usage of JDK Unsafe may fix this problem.";
        if (r2.getDeclaredConstructors().length != 0) goto L10;
        r1 = r1.concat(" Or adjust your R8 configuration to keep the no-args constructor of the class.");
    L10:
        return new ThrowingObjectConstructor(r1);
    L5:
        return new ConstructorConstructor$$ExternalSyntheticLambda5(r2);
    }

    public <T> ObjectConstructor<T> get(TypeToken<T> r4, boolean r5) {
        Type r0 = r4.getType();
        Class<? super T> r42 = r4.getRawType();
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.instanceCreators.get(r0));
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.instanceCreators.get(r42));
        ObjectConstructor<T> r1 = newSpecialCollectionConstructor(r0, r42);
        if (r1 == null) goto L5;
        return r1;
    L5:
        ReflectionAccessFilter$FilterResult r12 = ReflectionAccessFilterHelper.getFilterResult(this.reflectionFilters, r42);
        ObjectConstructor<T> r2 = newDefaultConstructor(r42, r12);
        if (r2 == null) goto L8;
        return r2;
    L8:
        ObjectConstructor<T> r02 = newDefaultImplementationConstructor(r0, r42);
        if (r02 == null) goto L11;
        return r02;
    L11:
        String r03 = checkInstantiable(r42);
        if (r03 == null) goto L16;
        return new ThrowingObjectConstructor(r03);
    L16:
        if (r5 == true) goto L20;
        return new ThrowingObjectConstructor("Unable to create instance of " + r42 + "; Register an InstanceCreator or a TypeAdapter for this type.");
    L20:
        if (r12 == ReflectionAccessFilter$FilterResult.ALLOW) goto L24;
        return new ThrowingObjectConstructor("Unable to create instance of " + r42 + "; ReflectionAccessFilter does not permit using reflection or Unsafe. Register an InstanceCreator or a TypeAdapter for this type or adjust the access filter to allow using reflection.");
    L24:
        return newUnsafeAllocator(r42);
    }

    public String toString() {
        return this.instanceCreators.toString();
    }
}
