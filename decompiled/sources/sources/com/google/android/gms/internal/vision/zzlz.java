package com.google.android.gms.internal.vision;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* loaded from: classes5.dex */
public final class zzlz extends AbstractList<String> implements zzjv, RandomAccess {
    private final zzjv zza;

    public zzlz(zzjv r1) {
        this.zza = r1;
    }

    public static /* synthetic */ zzjv zza(zzlz r0) {
        return r0.zza;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int r1) {
        return (String) this.zza.get(r1);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator<String> iterator() {
        return new zzmb(this);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator<String> listIterator(int r2) {
        return new zzly(this, r2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.vision.zzjv
    public final void zza(zzht r1) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.vision.zzjv
    public final Object zzb(int r1) {
        return this.zza.zzb(r1);
    }

    @Override // com.google.android.gms.internal.vision.zzjv
    public final List<?> zzd() {
        return this.zza.zzd();
    }

    @Override // com.google.android.gms.internal.vision.zzjv
    public final zzjv zze() {
        return this;
    }
}
