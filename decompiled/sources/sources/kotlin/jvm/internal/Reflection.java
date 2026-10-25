package kotlin.jvm.internal;

import java.util.Arrays;
import java.util.Collections;
import kotlin.SinceKotlin;
import kotlin.collections.ArraysKt;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty0;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KMutableProperty2;
import kotlin.reflect.KProperty0;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KProperty2;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.KVariance;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public class Reflection {
    private static final KClass[] EMPTY_K_CLASS_ARRAY = null;
    static final String REFLECTION_NOT_AVAILABLE = " (Kotlin reflection is not available)";
    private static final ReflectionFactory factory = null;

    static {
        ReflectionFactory r0 = null;
        r0 = (ReflectionFactory) Class.forName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl").newInstance();     // Catch: Throwable -> L10
    L5:
        if (r0 != null) goto L8;
        r0 = new ReflectionFactory();
    L8:
        factory = r0;
        EMPTY_K_CLASS_ARRAY = new KClass[0];
    }

    public Reflection() {
    }

    public static KClass createKotlinClass(Class r1) {
        return factory.createKotlinClass(r1);
    }

    public static KClass createKotlinClass(Class r1, String r2) {
        return factory.createKotlinClass(r1, r2);
    }

    public static KFunction function(FunctionReference r1) {
        return factory.function(r1);
    }

    public static KClass getOrCreateKotlinClass(Class r1) {
        return factory.getOrCreateKotlinClass(r1);
    }

    public static KClass getOrCreateKotlinClass(Class r1, String r2) {
        return factory.getOrCreateKotlinClass(r1, r2);
    }

    public static KClass[] getOrCreateKotlinClasses(Class[] r4) {
        int r0 = r4.length;
        if (r0 == 0) goto L5;
        KClass[] r1 = new KClass[r0];
        int r2 = 0;
    L7:
        if (r2 >= r0) goto L9;
        r1[r2] = getOrCreateKotlinClass(r4[r2]);
        r2 = r2 + 1;
        goto L7
    L9:
        return r1;
    L5:
        return EMPTY_K_CLASS_ARRAY;
    }

    @SinceKotlin(version = "1.4")
    public static KDeclarationContainer getOrCreateKotlinPackage(Class r2) {
        return factory.getOrCreateKotlinPackage(r2, _UrlKt.FRAGMENT_ENCODE_SET);
    }

    public static KDeclarationContainer getOrCreateKotlinPackage(Class r1, String r2) {
        return factory.getOrCreateKotlinPackage(r1, r2);
    }

    @SinceKotlin(version = "1.6")
    public static KType mutableCollectionType(KType r1) {
        return factory.mutableCollectionType(r1);
    }

    public static KMutableProperty0 mutableProperty0(MutablePropertyReference0 r1) {
        return factory.mutableProperty0(r1);
    }

    public static KMutableProperty1 mutableProperty1(MutablePropertyReference1 r1) {
        return factory.mutableProperty1(r1);
    }

    public static KMutableProperty2 mutableProperty2(MutablePropertyReference2 r1) {
        return factory.mutableProperty2(r1);
    }

    @SinceKotlin(version = "1.6")
    public static KType nothingType(KType r1) {
        return factory.nothingType(r1);
    }

    @SinceKotlin(version = "1.4")
    public static KType nullableTypeOf(Class r3) {
        return factory.typeOf(getOrCreateKotlinClass(r3), Collections.EMPTY_LIST, true);
    }

    @SinceKotlin(version = "1.4")
    public static KType nullableTypeOf(Class r2, KTypeProjection r3) {
        return factory.typeOf(getOrCreateKotlinClass(r2), Collections.singletonList(r3), true);
    }

    @SinceKotlin(version = "1.4")
    public static KType nullableTypeOf(Class r1, KTypeProjection r2, KTypeProjection r3) {
        return factory.typeOf(getOrCreateKotlinClass(r1), Arrays.asList(new KTypeProjection[]{r2, r3}), true);
    }

    @SinceKotlin(version = "1.4")
    public static KType nullableTypeOf(Class r2, KTypeProjection... r3) {
        return factory.typeOf(getOrCreateKotlinClass(r2), ArraysKt.toList(r3), true);
    }

    @SinceKotlin(version = "1.4")
    public static KType nullableTypeOf(KClassifier r3) {
        return factory.typeOf(r3, Collections.EMPTY_LIST, true);
    }

    @SinceKotlin(version = "1.6")
    public static KType platformType(KType r1, KType r2) {
        return factory.platformType(r1, r2);
    }

    public static KProperty0 property0(PropertyReference0 r1) {
        return factory.property0(r1);
    }

    public static KProperty1 property1(PropertyReference1 r1) {
        return factory.property1(r1);
    }

    public static KProperty2 property2(PropertyReference2 r1) {
        return factory.property2(r1);
    }

    @SinceKotlin(version = "1.3")
    public static String renderLambdaToString(FunctionBase r1) {
        return factory.renderLambdaToString(r1);
    }

    @SinceKotlin(version = "1.1")
    public static String renderLambdaToString(Lambda r1) {
        return factory.renderLambdaToString(r1);
    }

    @SinceKotlin(version = "1.4")
    public static void setUpperBounds(KTypeParameter r1, KType r2) {
        factory.setUpperBounds(r1, Collections.singletonList(r2));
    }

    @SinceKotlin(version = "1.4")
    public static void setUpperBounds(KTypeParameter r1, KType... r2) {
        factory.setUpperBounds(r1, ArraysKt.toList(r2));
    }

    @SinceKotlin(version = "1.4")
    public static KType typeOf(Class r3) {
        return factory.typeOf(getOrCreateKotlinClass(r3), Collections.EMPTY_LIST, false);
    }

    @SinceKotlin(version = "1.4")
    public static KType typeOf(Class r2, KTypeProjection r3) {
        return factory.typeOf(getOrCreateKotlinClass(r2), Collections.singletonList(r3), false);
    }

    @SinceKotlin(version = "1.4")
    public static KType typeOf(Class r1, KTypeProjection r2, KTypeProjection r3) {
        return factory.typeOf(getOrCreateKotlinClass(r1), Arrays.asList(new KTypeProjection[]{r2, r3}), false);
    }

    @SinceKotlin(version = "1.4")
    public static KType typeOf(Class r2, KTypeProjection... r3) {
        return factory.typeOf(getOrCreateKotlinClass(r2), ArraysKt.toList(r3), false);
    }

    @SinceKotlin(version = "1.4")
    public static KType typeOf(KClassifier r3) {
        return factory.typeOf(r3, Collections.EMPTY_LIST, false);
    }

    @SinceKotlin(version = "1.4")
    public static KTypeParameter typeParameter(Object r1, String r2, KVariance r3, boolean r4) {
        return factory.typeParameter(r1, r2, r3, r4);
    }
}
