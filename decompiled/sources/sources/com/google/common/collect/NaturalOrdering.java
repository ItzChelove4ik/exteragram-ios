package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.io.Serializable;

/* loaded from: classes5.dex */
final class NaturalOrdering extends Ordering<Comparable<?>> implements Serializable {
    static final NaturalOrdering INSTANCE = null;

    static {
        INSTANCE = new NaturalOrdering();
    }

    private NaturalOrdering() {
    }

    public int compare(Comparable<?> r1, Comparable<?> r2) {
        Preconditions.checkNotNull(r1);
        Preconditions.checkNotNull(r2);
        return r1.compareTo(r2);
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public /* bridge */ /* synthetic */ int compare(Object r1, Object r2) {
        return compare((Comparable) r1, (Comparable) r2);
    }

    @Override // com.google.common.collect.Ordering
    public <S extends Comparable<?>> Ordering<S> reverse() {
        return ReverseNaturalOrdering.INSTANCE;
    }

    public String toString() {
        return "Ordering.natural()";
    }
}
