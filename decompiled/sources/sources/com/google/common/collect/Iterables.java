package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;
import java.util.SortedSet;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class Iterables {
    public static <T> boolean any(Iterable<T> r0, Predicate<? super T> r1) {
        return Iterators.any(r0.iterator(), r1);
    }

    private static <E> Collection<E> castOrCopyToCollection(Iterable<E> r1) {
        if ((r1 instanceof Collection) == false) goto L7;
        return (Collection) r1;
    L7:
        return Lists.newArrayList(r1.iterator());
    }

    public static <T> T getFirst(Iterable<? extends T> r0, T r1) {
        return (T) Iterators.getNext(r0.iterator(), r1);
    }

    public static <T> T getLast(Iterable<T> r1) {
        if ((r1 instanceof List) == false) goto L11;
        List r12 = (List) r1;
        if (r12.isEmpty() == false) goto L7;
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    L7:
        return (T) getLastInNonemptyList(r12);
    L11:
        if ((r1 instanceof SortedSet) == false) goto L15;
        return (T) ((SortedSet) r1).last();
    L15:
        return (T) Iterators.getLast(r1.iterator());
    }

    public static <T> T getLast(Iterable<? extends T> r1, T r2) {
        if ((r1 instanceof Collection) == false) goto L16;
        if (((Collection) r1).isEmpty() == false) goto L8;
        return r2;
    L8:
        if ((r1 instanceof List) == false) goto L12;
        return (T) getLastInNonemptyList((List) r1);
    L12:
        if ((r1 instanceof SortedSet) == false) goto L16;
        return (T) ((SortedSet) r1).last();
    L16:
        return (T) Iterators.getLast(r1.iterator(), r2);
    }

    private static <T> T getLastInNonemptyList(List<T> r1) {
        return r1.get(r1.size() - 1);
    }

    public static <T> T getOnlyElement(Iterable<T> r0) {
        return (T) Iterators.getOnlyElement(r0.iterator());
    }

    public static <T> boolean removeIf(Iterable<T> r1, Predicate<? super T> r2) {
        if ((r1 instanceof RandomAccess) == false) goto L9;
        if ((r1 instanceof List) == false) goto L9;
        return removeIfFromRandomAccessList((List) r1, (Predicate) Preconditions.checkNotNull(r2));
    L9:
        return Iterators.removeIf(r1.iterator(), r2);
    }

    private static <T> boolean removeIfFromRandomAccessList(List<T> r6, Predicate<? super T> r7) {
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r1 >= r6.size()) goto L16;
        T r3 = r6.get(r1);
        if (r7.apply(r3) == true) goto L15;
        if (r1 > r2) goto L20;
    L14:
        r2 = r2 + 1;
        goto L15
    L20:
        r6.set(r2, r3);     // Catch: IllegalArgumentException -> L10 UnsupportedOperationException -> L12
    L10:
        slowRemoveIfForRemainingElements(r6, r7, r2, r1);
        return true;
    L12:
        slowRemoveIfForRemainingElements(r6, r7, r2, r1);
        return true;
    L15:
        r1 = r1 + 1;
        goto L4
    L16:
        r6.subList(r2, r6.size()).clear();
        if (r1 == r2) goto L19;
        return true;
    L19:
        return false;
    }

    private static <T> void slowRemoveIfForRemainingElements(List<T> r2, Predicate<? super T> r3, int r4, int r5) {
        int r0 = r2.size() - 1;
    L3:
        if (r0 <= r5) goto L8;
        if (r3.apply(r2.get(r0)) == false) goto L7;
        r2.remove(r0);
    L7:
        r0 = r0 - 1;
        goto L3
    L8:
        int r52 = r5 - 1;
    L9:
        if (r52 < r4) goto L11;
        r2.remove(r52);
        r52 = r52 - 1;
        goto L9
    }

    public static Object[] toArray(Iterable<?> r0) {
        return castOrCopyToCollection(r0).toArray();
    }
}
