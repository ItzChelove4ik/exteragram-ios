package com.google.android.gms.internal.mlkit_language_id_common;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;

/* loaded from: classes.dex */
public abstract class zzq extends AbstractCollection implements Serializable {
    private static final Object[] zza = null;

    static {
        zza = new Object[0];
    }

    public zzq() {
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

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
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

    @Override // java.util.AbstractCollection, java.util.Collection
    public final Object[] toArray() {
        return toArray(zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final Object[] toArray(Object[] r3) {
        r3.getClass();
        int r0 = size();
        int r1 = r3.length;
        if (r1 >= r0) goto L9;
        Object[] r12 = zze();
        if (r12 != null) goto L8;
        r3 = (Object[]) Array.newInstance(r3.getClass().getComponentType(), r0);
    L11:
        zza(r3, 0);
        return r3;
    L8:
        return Arrays.copyOfRange(r12, zzc(), zzb(), r3.getClass());
    L9:
        if (r1 <= r0) goto L11;
        r3[r0] = null;
        goto L11
    }

    public abstract int zza(Object[] r1, int r2);

    public int zzb() {
        throw new UnsupportedOperationException();
    }

    public int zzc() {
        throw new UnsupportedOperationException();
    }

    public Object[] zze() {
        return null;
    }
}
