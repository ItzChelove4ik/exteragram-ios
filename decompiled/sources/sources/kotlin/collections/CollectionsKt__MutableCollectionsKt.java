package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.UIntArray$Iterator$$ExternalSyntheticBUOutline0;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000P\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001f\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u001a.\u0010\u0000\u001a\u00020\u0001\"\t\b\u0000\u0010\u0002¢\u0006\u0002\b\u0003*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00042\u0006\u0010\u0005\u001a\u0002H\u0002H\u0087\u0088\b¢\u0006\u0002\u0010\u0006\u001a/\u0010\u0007\u001a\u00020\u0001\"\t\b\u0000\u0010\u0002¢\u0006\u0002\b\u0003*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\tH\u0087\u0088\b\u001a/\u0010\n\u001a\u00020\u0001\"\t\b\u0000\u0010\u0002¢\u0006\u0002\b\u0003*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\tH\u0087\u0088\b\u001a)\u0010\u000b\u001a\u00020\f\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00042\u0006\u0010\u0005\u001a\u0002H\u0002H\u0087\u008a\u0004¢\u0006\u0002\u0010\r\u001a*\u0010\u000b\u001a\u00020\f\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u000eH\u0087\u008a\u0004\u001a/\u0010\u000b\u001a\u00020\f\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u000fH\u0087\u008a\u0004¢\u0006\u0002\u0010\u0010\u001a*\u0010\u000b\u001a\u00020\f\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0011H\u0087\u008a\u0004\u001a)\u0010\u0012\u001a\u00020\f\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00042\u0006\u0010\u0005\u001a\u0002H\u0002H\u0087\u008a\u0004¢\u0006\u0002\u0010\r\u001a*\u0010\u0012\u001a\u00020\f\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u000eH\u0087\u008a\u0004\u001a/\u0010\u0012\u001a\u00020\f\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u000fH\u0087\u008a\u0004¢\u0006\u0002\u0010\u0010\u001a*\u0010\u0012\u001a\u00020\f\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0011H\u0087\u008a\u0004\u001a*\u0010\u0013\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u000eH\u0087\u0080\b\u001a*\u0010\u0013\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0011H\u0087\u0080\b\u001a1\u0010\u0013\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00042\u000e\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u000fH\u0087\u0080\b¢\u0006\u0002\u0010\u0014\u001a \u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00020\t\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000eH\u0080\u0080\u0004\u001a*\u0010\u0007\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u000eH\u0087\u0080\b\u001a*\u0010\u0007\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0011H\u0087\u0080\b\u001a1\u0010\u0007\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00042\u000e\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u000fH\u0087\u0080\b¢\u0006\u0002\u0010\u0014\u001a*\u0010\n\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u000eH\u0087\u0080\b\u001a1\u0010\n\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00042\u000e\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u000fH\u0087\u0080\b¢\u0006\u0002\u0010\u0014\u001a*\u0010\n\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0011H\u0087\u0080\b\u001a\u0017\u0010\u0016\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0004H\u0083\u0080\b¢\u0006\u0002\b\u0017\u001a.\u0010\u0007\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00182\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u001aH\u0087\u0080\b\u001a.\u0010\n\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00182\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u001aH\u0087\u0080\b\u001a;\u0010\u001b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00182\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u001a2\u0006\u0010\u001c\u001a\u00020\u0001H\u0082\u0080\u0004¢\u0006\u0002\b\u001d\u001a'\u0010\u0000\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0087\u0088\u0004¢\u0006\u0002\u0010!\u001a\u001f\u0010\"\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u001eH\u0087\u0080\b¢\u0006\u0002\u0010#\u001a!\u0010$\u001a\u0004\u0018\u0001H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u001eH\u0087\u0080\b¢\u0006\u0002\u0010#\u001a\u001f\u0010%\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u001eH\u0087\u0080\b¢\u0006\u0002\u0010#\u001a!\u0010&\u001a\u0004\u0018\u0001H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u001eH\u0087\u0080\b¢\u0006\u0002\u0010#\u001a.\u0010\u0007\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u001e2\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u001aH\u0087\u0080\b\u001a.\u0010\n\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u001e2\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u001aH\u0087\u0080\b\u001a;\u0010\u001b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u001e2\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u001a2\u0006\u0010\u001c\u001a\u00020\u0001H\u0082\u0080\u0004¢\u0006\u0002\b\u001d¨\u0006'"}, d2 = {"remove", _UrlKt.FRAGMENT_ENCODE_SET, "T", "Lkotlin/internal/OnlyInputTypes;", _UrlKt.FRAGMENT_ENCODE_SET, "element", "(Ljava/util/Collection;Ljava/lang/Object;)Z", "removeAll", "elements", _UrlKt.FRAGMENT_ENCODE_SET, "retainAll", "plusAssign", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/util/Collection;Ljava/lang/Object;)V", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/util/Collection;[Ljava/lang/Object;)V", "Lkotlin/sequences/Sequence;", "minusAssign", "addAll", "(Ljava/util/Collection;[Ljava/lang/Object;)Z", "convertToListIfNotCollection", "retainNothing", "retainNothing$CollectionsKt__MutableCollectionsKt", _UrlKt.FRAGMENT_ENCODE_SET, "predicate", "Lkotlin/Function1;", "filterInPlace", "predicateResultToRemove", "filterInPlace$CollectionsKt__MutableCollectionsKt", _UrlKt.FRAGMENT_ENCODE_SET, "index", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/util/List;I)Ljava/lang/Object;", "removeFirst", "(Ljava/util/List;)Ljava/lang/Object;", "removeFirstOrNull", "removeLast", "removeLastOrNull", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/collections/CollectionsKt")
/* loaded from: classes.dex */
class CollectionsKt__MutableCollectionsKt extends CollectionsKt__MutableCollectionsJVMKt {
    public CollectionsKt__MutableCollectionsKt() {
    }

    @IgnorableReturnValue
    public static <T> boolean addAll(Collection<? super T> r2, Iterable<? extends T> r3) {
        if ((r3 instanceof Collection) == true) goto L5;
        Iterator<? extends T> r32 = r3.iterator();
        boolean r0 = false;
    L8:
        if (r32.hasNext() == false) goto L12;
        if (r2.add(r32.next()) == false) goto L8;
        r0 = true;
        goto L8
    L12:
        return r0;
    L5:
        return r2.addAll((Collection) r3);
    }

    @IgnorableReturnValue
    public static <T> boolean addAll(Collection<? super T> r2, Sequence<? extends T> r3) {
        Iterator<? extends T> r32 = r3.iterator();
        boolean r0 = false;
    L4:
        if (r32.hasNext() == false) goto L8;
        if (r2.add(r32.next()) == false) goto L4;
        r0 = true;
        goto L4
    L8:
        return r0;
    }

    @IgnorableReturnValue
    public static <T> boolean addAll(Collection<? super T> r0, T[] r1) {
        return r0.addAll(ArraysKt___ArraysJvmKt.asList(r1));
    }

    public static <T> Collection<T> convertToListIfNotCollection(Iterable<? extends T> r1) {
        if ((r1 instanceof Collection) == false) goto L7;
        return (Collection) r1;
    L7:
        return CollectionsKt___CollectionsKt.toList(r1);
    }

    private static final <T> boolean filterInPlace$CollectionsKt__MutableCollectionsKt(Iterable<? extends T> r2, Function1<? super T, Boolean> r3, boolean r4) {
        Iterator<? extends T> r22 = r2.iterator();
        boolean r0 = false;
    L4:
        if (r22.hasNext() == false) goto L8;
        if (r3.invoke(r22.next()).booleanValue() != r4) goto L4;
        r22.remove();
        r0 = true;
        goto L4
    L8:
        return r0;
    }

    private static final <T> boolean filterInPlace$CollectionsKt__MutableCollectionsKt(List<T> r6, Function1<? super T, Boolean> r7, boolean r8) {
        if ((r6 instanceof RandomAccess) == false) goto L5;
        int r0 = CollectionsKt__CollectionsKt.getLastIndex(r6);
        if (r0 < 0) goto L17;
        int r2 = 0;
        int r3 = 0;
    L9:
        T r4 = r6.get(r2);
        if (r7.invoke(r4).booleanValue() == r8) goto L15;
        if (r3 == r2) goto L14;
        r6.set(r3, r4);
    L14:
        r3 = r3 + 1;
    L15:
        if (r2 == r0) goto L19;
        r2 = r2 + 1;
    L19:
        if (r3 >= r6.size()) goto L27;
        int r72 = CollectionsKt__CollectionsKt.getLastIndex(r6);
        if (r3 > r72) goto L25;
    L22:
        r6.remove(r72);
        if (r72 == r3) goto L30;
        r72 = r72 - 1;
        goto L22
    L30:
        return true;
    L25:
        return true;
    L27:
        return false;
    L17:
        r3 = 0;
        goto L19
    L5:
        return filterInPlace$CollectionsKt__MutableCollectionsKt(TypeIntrinsics.asMutableIterable(r6), r7, r8);
    }

    @InlineOnly
    private static final <T> void minusAssign(Collection<? super T> r0, Iterable<? extends T> r1) {
        removeAll(r0, r1);
    }

    @InlineOnly
    private static final <T> void minusAssign(Collection<? super T> r0, T r1) {
        r0.remove(r1);
    }

    @InlineOnly
    private static final <T> void minusAssign(Collection<? super T> r0, Sequence<? extends T> r1) {
        removeAll(r0, r1);
    }

    @InlineOnly
    private static final <T> void minusAssign(Collection<? super T> r0, T[] r1) {
        removeAll(r0, r1);
    }

    @InlineOnly
    private static final <T> void plusAssign(Collection<? super T> r0, Iterable<? extends T> r1) {
        addAll(r0, r1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @InlineOnly
    private static final <T> void plusAssign(Collection<? super T> r0, T r1) {
        r0.add(r1);
    }

    @InlineOnly
    private static final <T> void plusAssign(Collection<? super T> r0, Sequence<? extends T> r1) {
        addAll(r0, r1);
    }

    @InlineOnly
    private static final <T> void plusAssign(Collection<? super T> r0, T[] r1) {
        addAll(r0, r1);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use removeAt(index) instead.", replaceWith = @ReplaceWith(expression = "removeAt(index)", imports = {}))
    @InlineOnly
    private static final <T> T remove(List<T> r0, int r1) {
        return r0.remove(r1);
    }

    @IgnorableReturnValue
    @InlineOnly
    private static final <T> boolean remove(Collection<? extends T> r0, T r1) {
        return TypeIntrinsics.asMutableCollection(r0).remove(r1);
    }

    @IgnorableReturnValue
    public static final <T> boolean removeAll(Iterable<? extends T> r1, Function1<? super T, Boolean> r2) {
        return filterInPlace$CollectionsKt__MutableCollectionsKt(r1, r2, true);
    }

    @IgnorableReturnValue
    public static final <T> boolean removeAll(Collection<? super T> r0, Iterable<? extends T> r1) {
        return r0.removeAll(convertToListIfNotCollection(r1));
    }

    @IgnorableReturnValue
    @InlineOnly
    private static final <T> boolean removeAll(Collection<? extends T> r0, Collection<? extends T> r1) {
        return TypeIntrinsics.asMutableCollection(r0).removeAll(r1);
    }

    @IgnorableReturnValue
    public static final <T> boolean removeAll(Collection<? super T> r1, Sequence<? extends T> r2) {
        List r22 = SequencesKt.toList(r2);
        if (r22.isEmpty() == false) goto L5;
        return false;
    L5:
        if (r1.removeAll(r22) == false) goto L10;
        return true;
    L10:
        return false;
    }

    @IgnorableReturnValue
    public static final <T> boolean removeAll(Collection<? super T> r3, T[] r4) {
        if (r4.length != 0) goto L5;
        boolean r0 = true;
    L6:
        if (r0 == false) goto L8;
    L10:
        return false;
    L8:
        if (r3.removeAll(ArraysKt___ArraysJvmKt.asList(r4)) == false) goto L10;
        return true;
    L5:
        r0 = false;
        goto L6
    }

    @IgnorableReturnValue
    public static <T> boolean removeAll(List<T> r1, Function1<? super T, Boolean> r2) {
        return filterInPlace$CollectionsKt__MutableCollectionsKt(r1, r2, true);
    }

    @SinceKotlin(version = "1.4")
    @IgnorableReturnValue
    public static <T> T removeFirst(List<T> r1) {
        if (r1.isEmpty() == false) goto L5;
        UIntArray$Iterator$$ExternalSyntheticBUOutline0.m("List is empty.");
        return null;
    L5:
        return r1.remove(0);
    }

    @SinceKotlin(version = "1.4")
    @IgnorableReturnValue
    public static <T> T removeFirstOrNull(List<T> r1) {
        if (r1.isEmpty() == false) goto L7;
        return null;
    L7:
        return r1.remove(0);
    }

    @SinceKotlin(version = "1.4")
    @IgnorableReturnValue
    public static <T> T removeLast(List<T> r1) {
        if (r1.isEmpty() == false) goto L5;
        UIntArray$Iterator$$ExternalSyntheticBUOutline0.m("List is empty.");
        return null;
    L5:
        return r1.remove(CollectionsKt__CollectionsKt.getLastIndex(r1));
    }

    @SinceKotlin(version = "1.4")
    @IgnorableReturnValue
    public static <T> T removeLastOrNull(List<T> r1) {
        if (r1.isEmpty() == false) goto L7;
        return null;
    L7:
        return r1.remove(CollectionsKt__CollectionsKt.getLastIndex(r1));
    }

    @IgnorableReturnValue
    public static <T> boolean retainAll(Iterable<? extends T> r1, Function1<? super T, Boolean> r2) {
        return filterInPlace$CollectionsKt__MutableCollectionsKt(r1, r2, false);
    }

    @IgnorableReturnValue
    public static final <T> boolean retainAll(Collection<? super T> r0, Iterable<? extends T> r1) {
        return r0.retainAll(convertToListIfNotCollection(r1));
    }

    @IgnorableReturnValue
    @InlineOnly
    private static final <T> boolean retainAll(Collection<? extends T> r0, Collection<? extends T> r1) {
        return TypeIntrinsics.asMutableCollection(r0).retainAll(r1);
    }

    @IgnorableReturnValue
    public static final <T> boolean retainAll(Collection<? super T> r1, Sequence<? extends T> r2) {
        List r22 = SequencesKt.toList(r2);
        if (r22.isEmpty() == true) goto L7;
        return r1.retainAll(r22);
    L7:
        return retainNothing$CollectionsKt__MutableCollectionsKt(r1);
    }

    @IgnorableReturnValue
    public static final <T> boolean retainAll(Collection<? super T> r1, T[] r2) {
        if (r2.length != 0) goto L5;
        boolean r0 = true;
    L6:
        if (r0 == true) goto L10;
        return r1.retainAll(ArraysKt___ArraysJvmKt.asList(r2));
    L10:
        return retainNothing$CollectionsKt__MutableCollectionsKt(r1);
    L5:
        r0 = false;
        goto L6
    }

    @IgnorableReturnValue
    public static final <T> boolean retainAll(List<T> r1, Function1<? super T, Boolean> r2) {
        return filterInPlace$CollectionsKt__MutableCollectionsKt(r1, r2, false);
    }

    @IgnorableReturnValue
    private static final boolean retainNothing$CollectionsKt__MutableCollectionsKt(Collection<?> r1) {
        boolean r0 = !r1.isEmpty();
        r1.clear();
        return r0;
    }
}
