package kotlin.reflect;

import c.f$$ExternalSyntheticBUOutline1;
import com.android.dx.Constants$$ExternalSyntheticBUOutline0;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.ExperimentalStdlibApi;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UIntArray$Iterator$$ExternalSyntheticBUOutline0;
import kotlin.collections.CollectionsKt;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.KTypeBase;
import kotlin.jvm.internal.KTypeParameterBase;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0018\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\tH\u0083\u0080\u0004\u001a$\u0010\n\u001a\u00020\u00012\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0083\u0080\u0004\u001a\u0012\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0001H\u0082\u0080\u0004\"\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00028FX\u0087\u0084\b¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u000f8BX\u0083\u0084\b¢\u0006\f\u0012\u0004\b\u0003\u0010\u0010\u001a\u0004\b\u0005\u0010\u0011¨\u0006\u0015"}, d2 = {"javaType", "Ljava/lang/reflect/Type;", "Lkotlin/reflect/KType;", "getJavaType$annotations", "(Lkotlin/reflect/KType;)V", "getJavaType", "(Lkotlin/reflect/KType;)Ljava/lang/reflect/Type;", "computeJavaType", "forceWrapper", _UrlKt.FRAGMENT_ENCODE_SET, "createPossiblyInnerType", "jClass", "Ljava/lang/Class;", "arguments", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/reflect/KTypeProjection;", "(Lkotlin/reflect/KTypeProjection;)V", "(Lkotlin/reflect/KTypeProjection;)Ljava/lang/reflect/Type;", "typeToString", _UrlKt.FRAGMENT_ENCODE_SET, "type", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nTypesJVM.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TypesJVM.kt\nkotlin/reflect/TypesJVMKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,244:1\n1#2:245\n3054#3,12:246\n1586#4:258\n1661#4,3:259\n1586#4:262\n1661#4,3:263\n1586#4:266\n1661#4,3:267\n*S KotlinDebug\n*F\n+ 1 TypesJVM.kt\nkotlin/reflect/TypesJVMKt\n*L\n42#1:246,12\n73#1:258\n73#1:259,3\n75#1:262\n75#1:263,3\n81#1:266\n81#1:267,3\n*E\n"})
/* loaded from: classes5.dex */
public final class TypesJVMKt {

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[KVariance.values().length];
            r0[KVariance.IN.ordinal()] = 1;     // Catch: NoSuchFieldError -> L8
        L11:
            r0[KVariance.INVARIANT.ordinal()] = 2;     // Catch: NoSuchFieldError -> L9
        L15:
            r0[KVariance.OUT.ordinal()] = 3;     // Catch: NoSuchFieldError -> L10
        L6:
            $EnumSwitchMapping$0 = r0;
        }
    }

    public static final /* synthetic */ Type access$computeJavaType(KType r0, boolean r1) {
        return computeJavaType(r0, r1);
    }

    public static final /* synthetic */ String access$typeToString(Type r0) {
        return typeToString(r0);
    }

    @ExperimentalStdlibApi
    private static final Type computeJavaType(KType r9, boolean r10) {
        KClassifier r0 = r9.getClassifier();
        int r3 = 0;
        if ((r0 instanceof KTypeParameter) == false) goto L27;
        if ((r0 instanceof KTypeParameterBase) == false) goto L7;
        KTypeParameterBase r02 = (KTypeParameterBase) r0;
        GenericDeclaration r102 = r02.getJavaContainingDeclaration$kotlin_stdlib();
        if (r102 == null) goto L24;
        TypeVariable<?>[] r92 = r102.getTypeParameters();
        int r103 = r92.length;
        TypeVariable<?> r5 = null;
        boolean r1 = false;
    L11:
        if (r3 >= r103) goto L19;
        TypeVariable<?> r6 = r92[r3];
        if (Intrinsics.areEqual(r6.getName(), r02.getName()) == false) goto L18;
        if (r1 == true) goto L16;
        r1 = true;
        r5 = r6;
        goto L18
    L16:
        f$$ExternalSyntheticBUOutline1.m("Array contains more than one matching element.");
        return null;
    L18:
        r3 = r3 + 1;
        goto L11
    L19:
        if (r1 == true) goto L21;
        UIntArray$Iterator$$ExternalSyntheticBUOutline0.m("Array contains no element matching the predicate.");
        return null;
    L21:
        return r5;
    L24:
        Constants$$ExternalSyntheticBUOutline0.m("javaType is not supported for this type: ", r9);
        return null;
    L7:
        return new ObsoleteFallbackTypeVariableImpl((KTypeParameter) r0);
    L27:
        if ((r0 instanceof KClass) == false) goto L64;
        KClass r03 = (KClass) r0;
        if (r10 == false) goto L31;
        Class r104 = JvmClassMappingKt.getJavaObjectType(r03);
    L32:
        List<KTypeProjection> r04 = r9.getArguments();
        if (r04.isEmpty() == false) goto L36;
        return r104;
    L36:
        if (r104.isArray() == false) goto L63;
        if (r104.getComponentType().isPrimitive() == true) goto L72;
        KTypeProjection r05 = (KTypeProjection) CollectionsKt.singleOrNull(r04);
        if (r05 == null) goto L60;
        KVariance r93 = r05.component1();
        KType r06 = r05.component2();
        if (r93 != null) goto L45;
        int r94 = -1;
    L46:
        if (r94 == (-1)) goto L70;
        if (r94 != 1) goto L49;
        return r104;
    L49:
        if (r94 != 2) goto L51;
    L55:
        Type r95 = computeJavaType$default(r06, false, 1, null);
        if ((r95 instanceof Class) == false) goto L59;
        return r104;
    L59:
        return new GenericArrayTypeImpl(r95);
    L51:
        if (r94 == 3) goto L55;
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L70:
        return r104;
    L45:
        r94 = WhenMappings.$EnumSwitchMapping$0[r93.ordinal()];
        goto L46
    L60:
        Native$$ExternalSyntheticBUOutline5.m("kotlin.Array must have exactly one type argument: ", r9);
        return null;
    L72:
        return r104;
    L63:
        return createPossiblyInnerType(r104, r04);
    L31:
        r104 = JvmClassMappingKt.getJavaClass(r03);
        goto L32
    L64:
        Constants$$ExternalSyntheticBUOutline0.m("Unsupported type classifier: ", r9);
        return null;
    }

    public static /* synthetic */ Type computeJavaType$default(KType r0, boolean r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = false;
    L6:
        return computeJavaType(r0, r1);
    }

    @ExperimentalStdlibApi
    private static final Type createPossiblyInnerType(Class<?> r4, List<KTypeProjection> r5) {
        Class<?> r0 = r4.getDeclaringClass();
        if (r0 != null) goto L11;
        List<KTypeProjection> r52 = r5;
        ArrayList r02 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r52, 10));
        Iterator<T> r53 = r52.iterator();
    L6:
        if (r53.hasNext() == false) goto L9;
        r02.add(getJavaType((KTypeProjection) r53.next()));
        goto L6
    L9:
        return new ParameterizedTypeImpl(r4, null, r02);
    L11:
        if (Modifier.isStatic(r4.getModifiers()) == false) goto L18;
        List<KTypeProjection> r54 = r5;
        ArrayList r2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r54, 10));
        Iterator<T> r55 = r54.iterator();
    L14:
        if (r55.hasNext() == false) goto L17;
        r2.add(getJavaType((KTypeProjection) r55.next()));
        goto L14
    L17:
        return new ParameterizedTypeImpl(r4, r0, r2);
    L18:
        int r22 = r4.getTypeParameters().length;
        Type r03 = createPossiblyInnerType(r0, r5.subList(r22, r5.size()));
        List<KTypeProjection> r56 = r5.subList(0, r22);
        ArrayList r23 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r56, 10));
        Iterator<T> r57 = r56.iterator();
    L20:
        if (r57.hasNext() == false) goto L23;
        r23.add(getJavaType((KTypeProjection) r57.next()));
        goto L20
    L23:
        return new ParameterizedTypeImpl(r4, r03, r23);
    }

    public static final Type getJavaType(KType r3) {
        if ((r3 instanceof KTypeBase) == false) goto L8;
        Type r0 = ((KTypeBase) r3).getJavaType();
        if (r0 == null) goto L8;
        return r0;
    L8:
        return computeJavaType$default(r3, false, 1, null);
    }

    private static final Type getJavaType(KTypeProjection r4) {
        KVariance r0 = r4.getVariance();
        if (r0 == null) goto L5;
        KType r42 = r4.getType();
        int r02 = WhenMappings.$EnumSwitchMapping$0[r0.ordinal()];
        if (r02 == 1) goto L19;
        if (r02 == 2) goto L17;
        if (r02 == 3) goto L13;
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L13:
        return new WildcardTypeImpl(computeJavaType(r42, true), null);
    L17:
        return computeJavaType(r42, true);
    L19:
        return new WildcardTypeImpl(null, computeJavaType(r42, true));
    L5:
        return WildcardTypeImpl.Companion.getSTAR();
    }

    @LowPriorityInOverloadResolution
    @SinceKotlin(version = "1.4")
    @ExperimentalStdlibApi
    public static /* synthetic */ void getJavaType$annotations(KType r0) {
    }

    @ExperimentalStdlibApi
    private static /* synthetic */ void getJavaType$annotations(KTypeProjection r0) {
    }

    private static final String typeToString(Type r2) {
        if ((r2 instanceof Class) == false) goto L11;
        Class r0 = (Class) r2;
        if (r0.isArray() == false) goto L9;
        Sequence r22 = SequencesKt.generateSequence(r2, TypesJVMKt$typeToString$unwrap$1.INSTANCE);
        return ((Class) SequencesKt.last(r22)).getName() + StringsKt.repeat(_UrlKt.PATH_SEGMENT_ENCODE_SET_URI, SequencesKt.count(r22));
    L9:
        return r0.getName();
    L11:
        return r2.toString();
    }
}
