package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Comparator;
import java.util.SortedSet;

/* loaded from: classes5.dex */
abstract class SortedIterables {
    public static <E> Comparator<? super E> comparator(SortedSet<E> r0) {
        Comparator<? super E> r02 = r0.comparator();
        if (r02 == null) goto L5;
        return r02;
    L5:
        return Ordering.natural();
    }

    public static boolean hasSameComparator(Comparator<?> r1, Iterable<?> r2) {
        Preconditions.checkNotNull(r1);
        Preconditions.checkNotNull(r2);
        if ((r2 instanceof SortedSet) == false) goto L6;
        Comparator r22 = comparator((SortedSet) r2);
    L9:
        return r1.equals(r22);
    L6:
        if ((r2 instanceof SortedIterable) == false) goto L10;
        r22 = ((SortedIterable) r2).comparator();
        goto L9
    L10:
        return false;
    }
}
