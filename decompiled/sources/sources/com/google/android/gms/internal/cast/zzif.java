package com.google.android.gms.internal.cast;

import java.util.Iterator;

/* loaded from: classes4.dex */
final class zzif extends zzhz {
    private final transient zzhy zza;
    private final transient zzhv zzb;

    public zzif(zzhy r1, zzhv r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.internal.cast.zzhr, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object r1) {
        if (this.zza.get(r1) == null) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return this.zzb.zzl(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.cast.zzhz, com.google.android.gms.internal.cast.zzhr
    public final zzhv zze() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.cast.zzhr
    public final int zzg(Object[] r1, int r2) {
        return this.zzb.zzg(r1, 0);
    }
}
