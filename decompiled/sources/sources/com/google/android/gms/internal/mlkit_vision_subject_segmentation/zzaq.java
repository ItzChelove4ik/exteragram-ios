package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;

/* loaded from: classes.dex */
public abstract class zzaq extends AbstractCollection implements Serializable {
    private static final Object[] zza = null;

    static {
        zza = new Object[0];
    }

    public zzaq() {
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
        Object[] r3 = zze();
        if (r3 != null) goto L10;
        if (r1 == 0) goto L8;
        r5 = Arrays.copyOf(r5, 0);
    L8:
        r5 = Arrays.copyOf(r5, r0);
    L13:
        zza(r5, 0);
        return r5;
    L10:
        return Arrays.copyOfRange(r3, zzc(), zzb(), r5.getClass());
    L11:
        if (r1 <= r0) goto L13;
        r5[r0] = null;
        goto L13
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
