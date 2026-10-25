package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.io.Serializable;

/* loaded from: classes5.dex */
final class ReverseOrdering<T> extends Ordering<T> implements Serializable {
    final Ordering<? super T> forwardOrder;

    public ReverseOrdering(Ordering<? super T> r1) {
        this.forwardOrder = (Ordering) Preconditions.checkNotNull(r1);
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(T r1, T r2) {
        return this.forwardOrder.compare(r2, r1);
    }

    @Override // java.util.Comparator
    public boolean equals(Object r2) {
        if (r2 != this) goto L6;
        return true;
    L6:
        if ((r2 instanceof ReverseOrdering) == true) goto L8;
        return false;
    L8:
        return this.forwardOrder.equals(((ReverseOrdering) r2).forwardOrder);
    }

    public int hashCode() {
        return -this.forwardOrder.hashCode();
    }

    @Override // com.google.common.collect.Ordering
    public <S extends T> Ordering<S> reverse() {
        return this.forwardOrder;
    }

    public String toString() {
        return this.forwardOrder + ".reverse()";
    }
}
