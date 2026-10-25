package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a-\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\u0086\u0082\u0004¢\u0006\u0002\u0010\u0004\u001a5\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0006H\u0086\u0082\u0004¢\u0006\u0002\u0010\u0007\u001a.\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\bH\u0086\u0082\u0004\u001a.\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\tH\u0086\u0082\u0004\u001a-\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\u0087\u0088\u0004¢\u0006\u0002\u0010\u0004\u001a-\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\u0086\u0082\u0004¢\u0006\u0002\u0010\u0004\u001a5\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0006H\u0086\u0082\u0004¢\u0006\u0002\u0010\u0007\u001a.\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\bH\u0086\u0082\u0004\u001a.\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\tH\u0086\u0082\u0004\u001a-\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\u0087\u0088\u0004¢\u0006\u0002\u0010\u0004¨\u0006\r"}, d2 = {"minus", _UrlKt.FRAGMENT_ENCODE_SET, "T", "element", "(Ljava/util/Set;Ljava/lang/Object;)Ljava/util/Set;", "elements", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/util/Set;[Ljava/lang/Object;)Ljava/util/Set;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/sequences/Sequence;", "minusElement", "plus", "plusElement", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/collections/SetsKt")
@SourceDebugExtension({"SMAP\n_Sets.kt\nKotlin\n*S Kotlin\n*F\n+ 1 _Sets.kt\nkotlin/collections/SetsKt___SetsKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,141:1\n873#2,2:142\n862#2,2:144\n1#3:146\n*S KotlinDebug\n*F\n+ 1 _Sets.kt\nkotlin/collections/SetsKt___SetsKt\n*L\n30#1:142,2\n54#1:144,2\n*E\n"})
/* loaded from: classes.dex */
class SetsKt___SetsKt extends SetsKt__SetsKt {
    public SetsKt___SetsKt() {
    }

    public static <T> Set<T> minus(Set<? extends T> r3, Iterable<? extends T> r4) {
        Collection<?> r42 = CollectionsKt__MutableCollectionsKt.convertToListIfNotCollection(r4);
        if (r42.isEmpty() == false) goto L7;
        return CollectionsKt___CollectionsKt.toSet(r3);
    L7:
        if ((r42 instanceof Set) == false) goto L15;
        LinkedHashSet r0 = new LinkedHashSet();
        Iterator<T> r32 = r3.iterator();
    L10:
        if (r32.hasNext() == false) goto L14;
        T r1 = r32.next();
        if (((Set) r42).contains(r1) == true) goto L10;
        r0.add(r1);
        goto L10
    L14:
        return r0;
    L15:
        LinkedHashSet r02 = new LinkedHashSet(r3);
        r02.removeAll(r42);
        return r02;
    }

    public static <T> Set<T> minus(Set<? extends T> r6, T r7) {
        LinkedHashSet r0 = new LinkedHashSet(MapsKt__MapsJVMKt.mapCapacity(r6.size()));
        Iterator<T> r62 = r6.iterator();
        boolean r2 = false;
    L4:
        if (r62.hasNext() == false) goto L12;
        T r3 = r62.next();
        boolean r4 = true;
        if (r2 == false) goto L8;
    L10:
        if (r4 == false) goto L4;
        r0.add(r3);
        goto L4
    L8:
        if (Intrinsics.areEqual(r3, r7) == false) goto L10;
        r2 = true;
        r4 = false;
        goto L10
    L12:
        return r0;
    }

    public static final <T> Set<T> minus(Set<? extends T> r1, Sequence<? extends T> r2) {
        LinkedHashSet r0 = new LinkedHashSet(r1);
        CollectionsKt__MutableCollectionsKt.removeAll(r0, r2);
        return r0;
    }

    public static final <T> Set<T> minus(Set<? extends T> r1, T[] r2) {
        LinkedHashSet r0 = new LinkedHashSet(r1);
        CollectionsKt__MutableCollectionsKt.removeAll(r0, r2);
        return r0;
    }

    @InlineOnly
    private static final <T> Set<T> minusElement(Set<? extends T> r0, T r1) {
        return minus(r0, r1);
    }

    public static <T> Set<T> plus(Set<? extends T> r2, Iterable<? extends T> r3) {
        Integer r0 = CollectionsKt__IterablesKt.collectionSizeOrNull(r3);
        if (r0 == null) goto L5;
        int r02 = r0.intValue();
        int r1 = r2.size() + r02;
    L6:
        LinkedHashSet r12 = new LinkedHashSet(MapsKt__MapsJVMKt.mapCapacity(r1));
        r12.addAll(r2);
        CollectionsKt__MutableCollectionsKt.addAll(r12, r3);
        return r12;
    L5:
        r1 = r2.size() * 2;
        goto L6
    }

    public static <T> Set<T> plus(Set<? extends T> r2, T r3) {
        LinkedHashSet r0 = new LinkedHashSet(MapsKt__MapsJVMKt.mapCapacity(r2.size() + 1));
        r0.addAll(r2);
        r0.add(r3);
        return r0;
    }

    public static final <T> Set<T> plus(Set<? extends T> r2, Sequence<? extends T> r3) {
        LinkedHashSet r0 = new LinkedHashSet(MapsKt__MapsJVMKt.mapCapacity(r2.size() * 2));
        r0.addAll(r2);
        CollectionsKt__MutableCollectionsKt.addAll(r0, r3);
        return r0;
    }

    public static final <T> Set<T> plus(Set<? extends T> r3, T[] r4) {
        LinkedHashSet r0 = new LinkedHashSet(MapsKt__MapsJVMKt.mapCapacity(r3.size() + r4.length));
        r0.addAll(r3);
        CollectionsKt__MutableCollectionsKt.addAll(r0, r4);
        return r0;
    }

    @InlineOnly
    private static final <T> Set<T> plusElement(Set<? extends T> r0, T r1) {
        return plus(r0, r1);
    }
}
