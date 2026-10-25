package kotlin.collections;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.BuilderInference;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000N\n\u0000\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010#\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u001a\u0016\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\u0086\u0080\u0004\u001a/\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0005\"\u0002H\u0002H\u0086\u0080\u0004¢\u0006\u0002\u0010\u0006\u001a\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\u0087\u0088\u0004\u001a\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\"\u0004\b\u0000\u0010\u0002H\u0087\u0088\u0004\u001a/\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\"\u0004\b\u0000\u0010\u00022\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0005\"\u0002H\u0002H\u0086\u0080\u0004¢\u0006\u0002\u0010\u0006\u001a%\u0010\t\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\nj\b\u0012\u0004\u0012\u0002H\u0002`\u000b\"\u0004\b\u0000\u0010\u0002H\u0087\u0088\u0004¢\u0006\u0002\u0010\f\u001a9\u0010\t\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\nj\b\u0012\u0004\u0012\u0002H\u0002`\u000b\"\u0004\b\u0000\u0010\u00022\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0005\"\u0002H\u0002H\u0086\u0080\u0004¢\u0006\u0002\u0010\r\u001a%\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u000fj\b\u0012\u0004\u0012\u0002H\u0002`\u0010\"\u0004\b\u0000\u0010\u0002H\u0087\u0088\u0004¢\u0006\u0002\u0010\u0011\u001a9\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u000fj\b\u0012\u0004\u0012\u0002H\u0002`\u0010\"\u0004\b\u0000\u0010\u00022\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0005\"\u0002H\u0002H\u0086\u0080\u0004¢\u0006\u0002\u0010\u0012\u001a)\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u0001H\u0002H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0016\u001a7\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00142\u0016\u0010\u0004\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u0001H\u00020\u0005\"\u0004\u0018\u0001H\u0002H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0006\u001aG\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00180\u0001\"\u0004\b\u0000\u0010\u00182\u001f\b\u0001\u0010\u0019\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00180\b\u0012\u0004\u0012\u00020\u001b0\u001a¢\u0006\u0002\b\u001cH\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001aO\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00180\u0001\"\u0004\b\u0000\u0010\u00182\u0006\u0010\u001d\u001a\u00020\u001e2\u001f\b\u0001\u0010\u0019\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00180\b\u0012\u0004\u0012\u00020\u001b0\u001a¢\u0006\u0002\b\u001cH\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u001a\"\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001H\u0087\u0088\u0004\u001a \u0010 \u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0080\u0080\u0004\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006!"}, d2 = {"emptySet", _UrlKt.FRAGMENT_ENCODE_SET, "T", "setOf", "elements", _UrlKt.FRAGMENT_ENCODE_SET, "([Ljava/lang/Object;)Ljava/util/Set;", "mutableSetOf", _UrlKt.FRAGMENT_ENCODE_SET, "hashSetOf", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "()Ljava/util/HashSet;", "([Ljava/lang/Object;)Ljava/util/HashSet;", "linkedSetOf", "Ljava/util/LinkedHashSet;", "Lkotlin/collections/LinkedHashSet;", "()Ljava/util/LinkedHashSet;", "([Ljava/lang/Object;)Ljava/util/LinkedHashSet;", "setOfNotNull", _UrlKt.FRAGMENT_ENCODE_SET, "element", "(Ljava/lang/Object;)Ljava/util/Set;", "buildSet", "E", "builderAction", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ExtensionFunctionType;", "capacity", _UrlKt.FRAGMENT_ENCODE_SET, "orEmpty", "optimizeReadOnlySet", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/collections/SetsKt")
/* loaded from: classes.dex */
class SetsKt__SetsKt extends SetsKt__SetsJVMKt {
    public SetsKt__SetsKt() {
    }

    @SinceKotlin(version = "1.6")
    @InlineOnly
    private static final <E> Set<E> buildSet(int r0, @BuilderInference Function1<? super Set<E>, Unit> r1) {
        Set r02 = SetsKt__SetsJVMKt.createSetBuilder(r0);
        r1.invoke(r02);
        return SetsKt__SetsJVMKt.build(r02);
    }

    @SinceKotlin(version = "1.6")
    @InlineOnly
    private static final <E> Set<E> buildSet(@BuilderInference Function1<? super Set<E>, Unit> r1) {
        Set r0 = SetsKt__SetsJVMKt.createSetBuilder();
        r1.invoke(r0);
        return SetsKt__SetsJVMKt.build(r0);
    }

    public static <T> Set<T> emptySet() {
        return EmptySet.INSTANCE;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <T> HashSet<T> hashSetOf() {
        return new HashSet();
    }

    public static final <T> HashSet<T> hashSetOf(T... r2) {
        return (HashSet) ArraysKt___ArraysKt.toCollection(r2, new HashSet(MapsKt__MapsJVMKt.mapCapacity(r2.length)));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <T> LinkedHashSet<T> linkedSetOf() {
        return new LinkedHashSet();
    }

    public static final <T> LinkedHashSet<T> linkedSetOf(T... r2) {
        return (LinkedHashSet) ArraysKt___ArraysKt.toCollection(r2, new LinkedHashSet(MapsKt__MapsJVMKt.mapCapacity(r2.length)));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <T> Set<T> mutableSetOf() {
        return new LinkedHashSet();
    }

    public static <T> Set<T> mutableSetOf(T... r2) {
        return (Set) ArraysKt___ArraysKt.toCollection(r2, new LinkedHashSet(MapsKt__MapsJVMKt.mapCapacity(r2.length)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> Set<T> optimizeReadOnlySet(Set<? extends T> r2) {
        int r0 = r2.size();
        if (r0 == 0) goto L10;
        if (r0 == 1) goto L8;
        return r2;
    L8:
        return SetsKt__SetsJVMKt.setOf(r2.iterator().next());
    L10:
        return emptySet();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @InlineOnly
    private static final <T> Set<T> orEmpty(Set<? extends T> r0) {
        if (r0 == 0) goto L4;
        return r0;
    L4:
        return emptySet();
    }

    @InlineOnly
    private static final <T> Set<T> setOf() {
        return emptySet();
    }

    public static <T> Set<T> setOf(T... r0) {
        return ArraysKt___ArraysKt.toSet(r0);
    }

    @SinceKotlin(version = "1.4")
    public static final <T> Set<T> setOfNotNull(T r0) {
        if (r0 == null) goto L6;
        return SetsKt__SetsJVMKt.setOf(r0);
    L6:
        return emptySet();
    }

    @SinceKotlin(version = "1.4")
    public static final <T> Set<T> setOfNotNull(T... r1) {
        return (Set) ArraysKt___ArraysKt.filterNotNullTo(r1, new LinkedHashSet());
    }
}
