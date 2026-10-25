package com.google.common.collect;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

/* loaded from: classes5.dex */
final class CompoundOrdering<T> extends Ordering<T> implements Serializable {
    final Comparator<? super T>[] comparators;

    public CompoundOrdering(Comparator<? super T> r3, Comparator<? super T> r4) {
        this.comparators = new Comparator[]{r3, r4};
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(T r5, T r6) {
        int r1 = 0;
    L3:
        Comparator<? super T>[] r2 = this.comparators;
        if (r1 >= r2.length) goto L9;
        int r22 = r2[r1].compare(r5, r6);
        if (r22 != 0) goto L7;
        r1 = r1 + 1;
        goto L3
    L7:
        return r22;
    L9:
        return 0;
    }

    @Override // java.util.Comparator
    public boolean equals(Object r2) {
        if (r2 != this) goto L6;
        return true;
    L6:
        if ((r2 instanceof CompoundOrdering) == true) goto L8;
        return false;
    L8:
        return Arrays.equals(this.comparators, ((CompoundOrdering) r2).comparators);
    }

    public int hashCode() {
        return Arrays.hashCode(this.comparators);
    }

    public String toString() {
        return "Ordering.compound(" + Arrays.toString(this.comparators) + ")";
    }
}
