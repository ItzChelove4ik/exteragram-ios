package com.google.android.recaptcha.internal;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Spliterator;
import java.util.Spliterators;

/* loaded from: classes5.dex */
public abstract class zzpq<E> extends AbstractCollection<E> implements Serializable {
    private static final Object[] zza = null;

    static {
        zza = new Object[0];
    }

    public zzpq() {
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
    public abstract boolean contains(Object r1);

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
        return toArray(zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final Object[] toArray(Object[] r5) {
        r5.getClass();
        int r0 = size();
        int r1 = r5.length;
        if (r1 >= r0) goto L11;
        Object[] r3 = zzb();
        if (r3 != null) goto L10;
        if (r1 == 0) goto L8;
        r5 = Arrays.copyOf(r5, 0);
    L8:
        r5 = Arrays.copyOf(r5, r0);
    L13:
        zzf(r5, 0);
        return r5;
    L10:
        return Arrays.copyOfRange(r3, zzc(), zzd(), r5.getClass());
    L11:
        if (r1 <= r0) goto L13;
        r5[r0] = null;
        goto L13
    }

    public abstract zzqu zza();

    public Object[] zzb() {
        return null;
    }

    public int zzc() {
        throw new UnsupportedOperationException();
    }

    public int zzd() {
        throw new UnsupportedOperationException();
    }

    public abstract boolean zze();

    public int zzf(Object[] r3, int r4) {
        zzqu r2 = zza();
        int r42 = 0;
    L4:
        if (r2.hasNext() == false) goto L6;
        r3[r42] = r2.next();
        r42 = r42 + 1;
        goto L4
    L6:
        return r42;
    }
}
