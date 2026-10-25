package kotlin.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.collections.builders.ListBuilder;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.NotificationBadge;

@Metadata(d1 = {"\u0000\\\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0006\u001a#\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u0002H\u0086\u0080\u0004¢\u0006\u0002\u0010\u0004\u001a1\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0006j\b\u0012\u0004\u0012\u0002H\u0002`\u0007\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\bH\u0081\u0088\u0004¢\u0006\u0002\u0010\t\u001a8\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0001\"\u0004\b\u0000\u0010\u000b2\u001d\u0010\f\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010H\u0081\u0088\u0004ø\u0001\u0000\u001a@\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0001\"\u0004\b\u0000\u0010\u000b2\u0006\u0010\u0011\u001a\u00020\u00122\u001d\u0010\f\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010H\u0081\u0088\u0004ø\u0001\u0000\u001a\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u000e\"\u0004\b\u0000\u0010\u000bH\u0081\u0080\u0004\u001a\u001e\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u000e\"\u0004\b\u0000\u0010\u000b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0081\u0080\u0004\u001a$\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0001\"\u0004\b\u0000\u0010\u000b2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u000eH\u0081\u0080\u0004\u001a \u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0017H\u0087\u0088\u0004\u001a \u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0019H\u0087\u0080\u0004\u001a(\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0087\u0080\u0004\u001a#\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001d0\b2\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001fH\u0081\u0088\u0004¢\u0006\u0002\u0010 \u001a5\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\"\u0004\b\u0000\u0010\u00022\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001f2\f\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u00020\bH\u0081\u0088\u0004¢\u0006\u0002\u0010\"\u001a1\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\"\u0004\b\u0000\u0010\u00022\u0006\u0010$\u001a\u00020\u00122\f\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u00020\bH\u0080\u0080\u0004¢\u0006\u0002\u0010%\u001a3\u0010&\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u001d0\b\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\b2\u0006\u0010'\u001a\u00020(H\u0080\u0080\u0004¢\u0006\u0002\u0010)\u001a\u0012\u0010*\u001a\u00020\u00122\u0006\u0010+\u001a\u00020\u0012H\u0081\u0088\b\u001a\u0012\u0010,\u001a\u00020\u00122\u0006\u0010-\u001a\u00020\u0012H\u0081\u0088\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006."}, d2 = {"listOf", _UrlKt.FRAGMENT_ENCODE_SET, "T", "element", "(Ljava/lang/Object;)Ljava/util/List;", "asArrayList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", _UrlKt.FRAGMENT_ENCODE_SET, "([Ljava/lang/Object;)Ljava/util/ArrayList;", "buildListInternal", "E", "builderAction", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ExtensionFunctionType;", "capacity", _UrlKt.FRAGMENT_ENCODE_SET, "createListBuilder", "build", "builder", "toList", "Ljava/util/Enumeration;", "shuffled", _UrlKt.FRAGMENT_ENCODE_SET, "random", "Ljava/util/Random;", "collectionToArray", _UrlKt.FRAGMENT_ENCODE_SET, "collection", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/util/Collection;)[Ljava/lang/Object;", "array", "(Ljava/util/Collection;[Ljava/lang/Object;)[Ljava/lang/Object;", "terminateCollectionToArray", "collectionSize", "(I[Ljava/lang/Object;)[Ljava/lang/Object;", "copyToArrayOfAny", "isVarargs", _UrlKt.FRAGMENT_ENCODE_SET, "([Ljava/lang/Object;Z)[Ljava/lang/Object;", "checkIndexOverflow", "index", "checkCountOverflow", NotificationBadge.NewHtcHomeBadger.COUNT, "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/collections/CollectionsKt")
@SourceDebugExtension({"SMAP\nCollectionsJVM.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CollectionsJVM.kt\nkotlin/collections/CollectionsKt__CollectionsJVMKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,133:1\n1#2:134\n*E\n"})
/* loaded from: classes.dex */
class CollectionsKt__CollectionsJVMKt {
    public CollectionsKt__CollectionsJVMKt() {
    }

    @InlineOnly
    private static final <T> ArrayList<T> asArrayList(T[] r2) {
        return new ArrayList(CollectionsKt__CollectionsKt.asCollection(r2, true));
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    public static <E> List<E> build(List<E> r0) {
        return ((ListBuilder) r0).build();
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @InlineOnly
    private static final <E> List<E> buildListInternal(int r0, Function1<? super List<E>, Unit> r1) {
        List r02 = createListBuilder(r0);
        r1.invoke(r02);
        return build(r02);
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @InlineOnly
    private static final <E> List<E> buildListInternal(Function1<? super List<E>, Unit> r1) {
        List r0 = createListBuilder();
        r1.invoke(r0);
        return build(r0);
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    @PublishedApi
    @IgnorableReturnValue
    private static final int checkCountOverflow(int r0) {
        if (r0 >= 0) goto L4;
        CollectionsKt__CollectionsKt.throwCountOverflow();
    L4:
        return r0;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    @PublishedApi
    @IgnorableReturnValue
    private static final int checkIndexOverflow(int r0) {
        if (r0 >= 0) goto L4;
        CollectionsKt__CollectionsKt.throwIndexOverflow();
    L4:
        return r0;
    }

    @InlineOnly
    private static final Object[] collectionToArray(Collection<?> r0) {
        return CollectionToArray.toArray(r0);
    }

    @InlineOnly
    private static final <T> T[] collectionToArray(Collection<?> r0, T[] r1) {
        return (T[]) CollectionToArray.toArray(r0, r1);
    }

    public static final <T> Object[] copyToArrayOfAny(T[] r1, boolean r2) {
        if (r2 == false) goto L8;
        if (Intrinsics.areEqual(r1.getClass(), Object[].class) == false) goto L8;
        return r1;
    L8:
        return Arrays.copyOf(r1, r1.length, Object[].class);
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    public static <E> List<E> createListBuilder() {
        return new ListBuilder(0, 1, null);
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    public static <E> List<E> createListBuilder(int r1) {
        return new ListBuilder(r1);
    }

    public static <T> List<T> listOf(T r0) {
        return Collections.singletonList(r0);
    }

    @SinceKotlin(version = "1.2")
    public static <T> List<T> shuffled(Iterable<? extends T> r0) {
        List<T> r02 = CollectionsKt___CollectionsKt.toMutableList(r0);
        Collections.shuffle(r02);
        return r02;
    }

    @SinceKotlin(version = "1.2")
    public static final <T> List<T> shuffled(Iterable<? extends T> r0, Random r1) {
        List<T> r02 = CollectionsKt___CollectionsKt.toMutableList(r0);
        Collections.shuffle(r02, r1);
        return r02;
    }

    public static <T> T[] terminateCollectionToArray(int r1, T[] r2) {
        if (r1 >= r2.length) goto L5;
        r2[r1] = null;
    L5:
        return r2;
    }

    @InlineOnly
    private static final <T> List<T> toList(Enumeration<T> r0) {
        return Collections.list(r0);
    }
}
