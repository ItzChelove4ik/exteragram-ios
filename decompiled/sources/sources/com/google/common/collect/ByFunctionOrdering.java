package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Objects;

/* loaded from: classes5.dex */
final class ByFunctionOrdering<F, T> extends Ordering<F> implements Serializable {
    final Function<F, ? extends T> function;
    final Ordering<T> ordering;

    public ByFunctionOrdering(Function<F, ? extends T> r1, Ordering<T> r2) {
        this.function = (Function) Preconditions.checkNotNull(r1);
        this.ordering = (Ordering) Preconditions.checkNotNull(r2);
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(F r3, F r4) {
        return this.ordering.compare(this.function.apply(r3), this.function.apply(r4));
    }

    @Override // java.util.Comparator
    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof ByFunctionOrdering) == false) goto L12;
        ByFunctionOrdering r52 = (ByFunctionOrdering) r5;
        if (this.function.equals(r52.function) == false) goto L12;
        if (this.ordering.equals(r52.ordering) == false) goto L12;
        return true;
    L12:
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.function, this.ordering});
    }

    public String toString() {
        return this.ordering + ".onResultOf(" + this.function + ")";
    }
}
