package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.io.Serializable;

/* loaded from: classes5.dex */
final class ReverseNaturalOrdering extends Ordering<Comparable<?>> implements Serializable {
    static final ReverseNaturalOrdering INSTANCE = null;

    static {
        INSTANCE = new ReverseNaturalOrdering();
    }

    private ReverseNaturalOrdering() {
    }

    public int compare(Comparable<?> r1, Comparable<?> r2) {
        Preconditions.checkNotNull(r1);
        if (r1 != r2) goto L7;
        return 0;
    L7:
        return r2.compareTo(r1);
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public /* bridge */ /* synthetic */ int compare(Object r1, Object r2) {
        return compare((Comparable) r1, (Comparable) r2);
    }

    @Override // com.google.common.collect.Ordering
    public <S extends Comparable<?>> Ordering<S> reverse() {
        return Ordering.natural();
    }

    public String toString() {
        return "Ordering.natural().reverse()";
    }
}
