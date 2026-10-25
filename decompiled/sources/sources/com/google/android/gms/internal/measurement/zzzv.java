package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzzv implements Iterator {
    final /* synthetic */ zzzw zza;
    private int zzb;

    public zzzv(zzzw r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        this.zzb = 0;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        zzzy r0 = this.zza.zza;
        if (this.zzb >= r0.zzg()) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        int r0 = this.zzb;
        this.zzb = r0 + 1;
        zzzy r2 = this.zza.zza;
        return r2.zzd(r2.zzf()[r0] & 31);
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
