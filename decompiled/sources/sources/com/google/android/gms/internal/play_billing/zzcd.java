package com.google.android.gms.internal.play_billing;

import java.util.Iterator;

/* loaded from: classes5.dex */
final class zzcd extends zzbx {
    private final transient zzbw zza;
    private final transient zzbt zzb;

    public zzcd(zzbw r1, zzbt r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbq, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object r1) {
        if (this.zza.get(r1) == null) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return this.zzb.zzn(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.play_billing.zzbq
    public final int zza(Object[] r1, int r2) {
        return this.zzb.zza(r1, 0);
    }

    @Override // com.google.android.gms.internal.play_billing.zzbx, com.google.android.gms.internal.play_billing.zzbq
    public final zzbt zzd() {
        return this.zzb;
    }
}
