package kotlin.jvm.internal;

import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.KVariance;
import okhttp3.internal.url._UrlKt;

@SinceKotlin(version = "1.4")
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u001b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 *2\u00020\u0001:\u0001*B1\bG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bB'\bV\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\n\u0010\u000eJ\u0014\u0010\u001e\u001a\u00020\r2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0096\u0082\u0004J\n\u0010!\u001a\u00020\tH\u0096\u0080\u0004J\n\u0010\"\u001a\u00020#H\u0096\u0080\u0004J\u0012\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020\rH\u0082\u0080\u0004J\u000e\u0010$\u001a\u00020#*\u00020\u0006H\u0082\u0080\u0004R\u0015\u0010\u0002\u001a\u00020\u0003X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001f\u0010\u0007\u001a\u0004\u0018\u00010\u00018\u0000X\u0081\u0084\b¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u001d\u0010\b\u001a\u00020\t8\u0000X\u0081\u0084\b¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0014\u001a\u0004\b\u0018\u0010\u0019R\u001b\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0012R\u0015\u0010\f\u001a\u00020\r8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\f\u0010\u001dR\u001d\u0010&\u001a\u00020#*\u0006\u0012\u0002\b\u00030'8BX\u0082\u0084\b¢\u0006\u0006\u001a\u0004\b(\u0010)¨\u0006+"}, d2 = {"Lkotlin/jvm/internal/TypeReference;", "Lkotlin/reflect/KType;", "classifier", "Lkotlin/reflect/KClassifier;", "arguments", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/reflect/KTypeProjection;", "platformTypeUpperBound", "flags", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lkotlin/reflect/KClassifier;Ljava/util/List;Lkotlin/reflect/KType;I)V", "isMarkedNullable", _UrlKt.FRAGMENT_ENCODE_SET, "(Lkotlin/reflect/KClassifier;Ljava/util/List;Z)V", "getClassifier", "()Lkotlin/reflect/KClassifier;", "getArguments", "()Ljava/util/List;", "getPlatformTypeUpperBound$kotlin_stdlib$annotations", "()V", "getPlatformTypeUpperBound$kotlin_stdlib", "()Lkotlin/reflect/KType;", "getFlags$kotlin_stdlib$annotations", "getFlags$kotlin_stdlib", "()I", "annotations", _UrlKt.FRAGMENT_ENCODE_SET, "getAnnotations", "()Z", "equals", "other", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "asString", "convertPrimitiveToWrapper", "arrayClassName", "Ljava/lang/Class;", "getArrayClassName", "(Ljava/lang/Class;)Ljava/lang/String;", "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class TypeReference implements KType {
    public static final Companion Companion = null;
    public static final int IS_MARKED_NULLABLE = 1;
    public static final int IS_MUTABLE_COLLECTION_TYPE = 2;
    public static final int IS_NOTHING_TYPE = 4;
    private final List<KTypeProjection> arguments;
    private final KClassifier classifier;
    private final int flags;
    private final KType platformTypeUpperBound;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u000f\u0010\u0004\u001a\u00020\u0005X\u0080Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0006\u001a\u00020\u0005X\u0080Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0007\u001a\u00020\u0005X\u0080Ô\b¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lkotlin/jvm/internal/TypeReference$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "IS_MARKED_NULLABLE", _UrlKt.FRAGMENT_ENCODE_SET, "IS_MUTABLE_COLLECTION_TYPE", "IS_NOTHING_TYPE", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[KVariance.values().length];
            r0[KVariance.INVARIANT.ordinal()] = 1;     // Catch: NoSuchFieldError -> L8
        L11:
            r0[KVariance.IN.ordinal()] = 2;     // Catch: NoSuchFieldError -> L9
        L15:
            r0[KVariance.OUT.ordinal()] = 3;     // Catch: NoSuchFieldError -> L10
        L6:
            $EnumSwitchMapping$0 = r0;
        }
    }

    public static CharSequence $r8$lambda$TqUhj1JlPuVAfFHzMnjOlMDw418(TypeReference r0, KTypeProjection r1) {
        return r0.asString(r1);
    }

    static {
        Companion = new Companion(null);
    }

    @SinceKotlin(version = "1.6")
    public TypeReference(KClassifier r1, List<KTypeProjection> r2, KType r3, int r4) {
        this.classifier = r1;
        this.arguments = r2;
        this.platformTypeUpperBound = r3;
        this.flags = r4;
    }

    public TypeReference(KClassifier r2, List<KTypeProjection> r3, boolean r4) {
        this(r2, r3, null, r4 ? 1 : 0);
    }

    private final String asString(KTypeProjection r4) {
        if (r4.getVariance() != null) goto L6;
        return "*";
    L6:
        KType r3 = r4.getType();
        if ((r3 instanceof TypeReference) == false) goto L9;
        TypeReference r32 = (TypeReference) r3;
    L11:
        if (r32 == null) goto L14;
        String r33 = r32.asString(true);
        if (r33 == null) goto L14;
    L15:
        int r42 = WhenMappings.$EnumSwitchMapping$0[r4.getVariance().ordinal()];
        if (r42 != 1) goto L18;
        return r33;
    L18:
        if (r42 == 2) goto L26;
        if (r42 == 3) goto L22;
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L22:
        return "out ".concat(r33);
    L26:
        return "in ".concat(r33);
    L14:
        r33 = String.valueOf(r4.getType());
        goto L15
    L9:
        r32 = null;
        goto L11
    }

    private final String asString(boolean r12) {
        KClassifier r0 = getClassifier();
        Class<?> r2 = null;
        if ((r0 instanceof KClass) == false) goto L5;
        KClass r02 = (KClass) r0;
    L6:
        if (r02 == null) goto L8;
        r2 = JvmClassMappingKt.getJavaClass(r02);
    L8:
        if (r2 != null) goto L11;
        String r122 = getClassifier().toString();
    L21:
        boolean r03 = getArguments().isEmpty();
        String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r03 == false) goto L24;
        String r04 = _UrlKt.FRAGMENT_ENCODE_SET;
    L26:
        if (isMarkedNullable() == false) goto L28;
        r1 = "?";
    L28:
        String r123 = r122 + r04 + r1;
        KType r11 = this.platformTypeUpperBound;
        if ((r11 instanceof TypeReference) == false) goto L39;
        String r112 = ((TypeReference) r11).asString(true);
        if (Intrinsics.areEqual(r112, r123) == true) goto L39;
        if (Intrinsics.areEqual(r112, r123.concat("?")) == false) goto L38;
        return r123.concat("!");
    L38:
        return "(" + r123 + ".." + r112 + ')';
    L39:
        return r123;
    L24:
        r04 = CollectionsKt.joinToString$default(getArguments(), ", ", "<", ">", 0, null, new TypeReference$$ExternalSyntheticLambda0(this), 24, null);
        goto L26
    L11:
        if ((this.flags & 4) == 0) goto L14;
        r122 = "kotlin.Nothing";
        goto L21
    L14:
        if (r2.isArray() == false) goto L16;
        r122 = getArrayClassName(r2);
        goto L21
    L16:
        if (r12 == true) goto L18;
    L20:
        r122 = r2.getName();
        goto L21
    L18:
        if (r2.isPrimitive() == false) goto L20;
        r122 = JvmClassMappingKt.getJavaObjectType((KClass) getClassifier()).getName();
        goto L21
    L5:
        r02 = null;
        goto L6
    }

    private final String getArrayClassName(Class<?> r1) {
        if (Intrinsics.areEqual(r1, boolean[].class) == false) goto L7;
        return "kotlin.BooleanArray";
    L7:
        if (Intrinsics.areEqual(r1, char[].class) == false) goto L11;
        return "kotlin.CharArray";
    L11:
        if (Intrinsics.areEqual(r1, byte[].class) == false) goto L15;
        return "kotlin.ByteArray";
    L15:
        if (Intrinsics.areEqual(r1, short[].class) == false) goto L19;
        return "kotlin.ShortArray";
    L19:
        if (Intrinsics.areEqual(r1, int[].class) == false) goto L23;
        return "kotlin.IntArray";
    L23:
        if (Intrinsics.areEqual(r1, float[].class) == false) goto L27;
        return "kotlin.FloatArray";
    L27:
        if (Intrinsics.areEqual(r1, long[].class) == false) goto L31;
        return "kotlin.LongArray";
    L31:
        if (Intrinsics.areEqual(r1, double[].class) == false) goto L34;
        return "kotlin.DoubleArray";
    L34:
        return "kotlin.Array";
    }

    @SinceKotlin(version = "1.6")
    public static /* synthetic */ void getFlags$kotlin_stdlib$annotations() {
    }

    @SinceKotlin(version = "1.6")
    public static /* synthetic */ void getPlatformTypeUpperBound$kotlin_stdlib$annotations() {
    }

    public boolean equals(Object r3) {
        if ((r3 instanceof TypeReference) == false) goto L14;
        TypeReference r32 = (TypeReference) r3;
        if (Intrinsics.areEqual(getClassifier(), r32.getClassifier()) == true) goto L7;
        return false;
    L7:
        if (Intrinsics.areEqual(getArguments(), r32.getArguments()) == true) goto L9;
        return false;
    L9:
        if (Intrinsics.areEqual(this.platformTypeUpperBound, r32.platformTypeUpperBound) == true) goto L11;
        return false;
    L11:
        if (this.flags != r32.flags) goto L19;
        return true;
    L19:
        return false;
    L14:
        return false;
    }

    @Override // kotlin.reflect.KAnnotatedElement
    public List<Annotation> getAnnotations() {
        return CollectionsKt.emptyList();
    }

    @Override // kotlin.reflect.KType
    public List<KTypeProjection> getArguments() {
        return this.arguments;
    }

    @Override // kotlin.reflect.KType
    public KClassifier getClassifier() {
        return this.classifier;
    }

    public final int getFlags$kotlin_stdlib() {
        return this.flags;
    }

    public final KType getPlatformTypeUpperBound$kotlin_stdlib() {
        return this.platformTypeUpperBound;
    }

    public int hashCode() {
        return (((getClassifier().hashCode() * 31) + getArguments().hashCode()) * 31) + Integer.hashCode(this.flags);
    }

    @Override // kotlin.reflect.KType
    public boolean isMarkedNullable() {
        if ((this.flags & 1) == 0) goto L5;
        return true;
    L5:
        return false;
    }

    public String toString() {
        return asString(false) + " (Kotlin reflection is not available)";
    }
}
