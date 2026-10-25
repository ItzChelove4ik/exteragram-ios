package com.google.android.gms.measurement.internal;

import java.util.Iterator;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzbe implements Iterator {
    final Iterator zza;
    final /* synthetic */ zzbf zzb;

    public zzbe(zzbf r1) {
        Objects.requireNonNull(r1);
        this.zzb = r1;
        this.zza = r1.zzg().keySet().iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        return zza();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }

    public final String zza() {
        return (String) this.zza.next();
    }
}
