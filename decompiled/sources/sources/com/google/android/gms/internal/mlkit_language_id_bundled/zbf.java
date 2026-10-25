package com.google.android.gms.internal.mlkit_language_id_bundled;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Spliterator;
import java.util.Spliterators;

/* loaded from: classes.dex */
public abstract class zbf extends AbstractCollection implements Serializable {
    private static final Object[] zba = null;

    static {
        zba = new Object[0];
    }

    public zbf() {
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final boolean add(Object r1) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final boolean addAll(Collection r1) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final boolean remove(Object r1) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final boolean removeAll(Collection r1) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final boolean retainAll(Collection r1) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Spliterator spliterator() {
        return Spliterators.spliterator(this, 1296);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final Object[] toArray() {
        return toArray(zba);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final Object[] toArray(Object[] r5) {
        r5.getClass();
        int r0 = size();
        int r1 = r5.length;
        if (r1 >= r0) goto L11;
        Object[] r3 = zbe();
        if (r3 != null) goto L10;
        if (r1 == 0) goto L8;
        r5 = Arrays.copyOf(r5, 0);
    L8:
        r5 = Arrays.copyOf(r5, r0);
    L13:
        zba(r5, 0);
        return r5;
    L10:
        return Arrays.copyOfRange(r3, zbc(), zbb(), r5.getClass());
    L11:
        if (r1 <= r0) goto L13;
        r5[r0] = null;
        goto L13
    }

    public abstract int zba(Object[] r1, int r2);

    public abstract int zbb();

    public abstract int zbc();

    public abstract Object[] zbe();
}
