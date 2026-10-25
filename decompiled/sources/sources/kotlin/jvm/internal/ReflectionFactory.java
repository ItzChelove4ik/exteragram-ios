package kotlin.jvm.internal;

import java.util.List;
import kotlin.SinceKotlin;
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

/* loaded from: classes.dex */
public class ReflectionFactory {
    private static final String KOTLIN_JVM_FUNCTIONS = "kotlin.jvm.functions.";

    public ReflectionFactory() {
    }

    public KClass createKotlinClass(Class r1) {
        return new ClassReference(r1);
    }

    public KClass createKotlinClass(Class r1, String r2) {
        return new ClassReference(r1);
    }

    public KFunction function(FunctionReference r1) {
        return r1;
    }

    public KClass getOrCreateKotlinClass(Class r1) {
        return new ClassReference(r1);
    }

    public KClass getOrCreateKotlinClass(Class r1, String r2) {
        return new ClassReference(r1);
    }

    public KDeclarationContainer getOrCreateKotlinPackage(Class r1, String r2) {
        return new PackageReference(r1, r2);
    }

    @SinceKotlin(version = "1.6")
    public KType mutableCollectionType(KType r4) {
        TypeReference r3 = (TypeReference) r4;
        return new TypeReference(r4.getClassifier(), r4.getArguments(), r3.getPlatformTypeUpperBound$kotlin_stdlib(), r3.getFlags$kotlin_stdlib() | 2);
    }

    public KMutableProperty0 mutableProperty0(MutablePropertyReference0 r1) {
        return r1;
    }

    public KMutableProperty1 mutableProperty1(MutablePropertyReference1 r1) {
        return r1;
    }

    public KMutableProperty2 mutableProperty2(MutablePropertyReference2 r1) {
        return r1;
    }

    @SinceKotlin(version = "1.6")
    public KType nothingType(KType r4) {
        TypeReference r3 = (TypeReference) r4;
        return new TypeReference(r4.getClassifier(), r4.getArguments(), r3.getPlatformTypeUpperBound$kotlin_stdlib(), r3.getFlags$kotlin_stdlib() | 4);
    }

    @SinceKotlin(version = "1.6")
    public KType platformType(KType r3, KType r4) {
        return new TypeReference(r3.getClassifier(), r3.getArguments(), r4, ((TypeReference) r3).getFlags$kotlin_stdlib());
    }

    public KProperty0 property0(PropertyReference0 r1) {
        return r1;
    }

    public KProperty1 property1(PropertyReference1 r1) {
        return r1;
    }

    public KProperty2 property2(PropertyReference2 r1) {
        return r1;
    }

    @SinceKotlin(version = "1.3")
    public String renderLambdaToString(FunctionBase r1) {
        String r0 = r1.getClass().getGenericInterfaces()[0].toString();
        if (r0.startsWith(KOTLIN_JVM_FUNCTIONS) == true) goto L5;
        return r0;
    L5:
        return r0.substring(21);
    }

    @SinceKotlin(version = "1.1")
    public String renderLambdaToString(Lambda r1) {
        return renderLambdaToString(r1);
    }

    @SinceKotlin(version = "1.4")
    public void setUpperBounds(KTypeParameter r1, List<KType> r2) {
        ((TypeParameterReference) r1).setUpperBounds(r2);
    }

    @SinceKotlin(version = "1.4")
    public KType typeOf(KClassifier r1, List<KTypeProjection> r2, boolean r3) {
        return new TypeReference(r1, r2, r3);
    }

    @SinceKotlin(version = "1.4")
    public KTypeParameter typeParameter(Object r1, String r2, KVariance r3, boolean r4) {
        return new TypeParameterReference(r1, r2, r3, r4);
    }
}
