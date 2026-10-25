package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import java.util.Comparator;

/* loaded from: classes5.dex */
public abstract class Ordering<T> implements Comparator<T> {
    public Ordering() {
    }

    public static <T> Ordering<T> from(Comparator<T> r1) {
        if ((r1 instanceof Ordering) == false) goto L7;
        return (Ordering) r1;
    L7:
        return new ComparatorOrdering(r1);
    }

    public static <C extends Comparable> Ordering<C> natural() {
        return NaturalOrdering.INSTANCE;
    }

    @Override // java.util.Comparator
    public abstract int compare(T r1, T r2);

    public <U extends T> Ordering<U> compound(Comparator<? super U> r2) {
        return new CompoundOrdering(this, (Comparator) Preconditions.checkNotNull(r2));
    }

    public <F> Ordering<F> onResultOf(Function<F, ? extends T> r2) {
        return new ByFunctionOrdering(r2, this);
    }

    public <S extends T> Ordering<S> reverse() {
        return new ReverseOrdering(this);
    }
}
