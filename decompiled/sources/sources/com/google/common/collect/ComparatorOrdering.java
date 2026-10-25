package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Comparator;

/* loaded from: classes5.dex */
final class ComparatorOrdering<T> extends Ordering<T> implements Serializable {
    final Comparator<T> comparator;

    public ComparatorOrdering(Comparator<T> r1) {
        this.comparator = (Comparator) Preconditions.checkNotNull(r1);
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(T r1, T r2) {
        return this.comparator.compare(r1, r2);
    }

    @Override // java.util.Comparator
    public boolean equals(Object r2) {
        if (r2 != this) goto L6;
        return true;
    L6:
        if ((r2 instanceof ComparatorOrdering) == true) goto L8;
        return false;
    L8:
        return this.comparator.equals(((ComparatorOrdering) r2).comparator);
    }

    public int hashCode() {
        return this.comparator.hashCode();
    }

    public String toString() {
        return this.comparator.toString();
    }
}
