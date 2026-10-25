package com.google.android.gms.internal.fido;

import java.util.Iterator;

/* loaded from: classes4.dex */
final class zzci extends zzce {
    final /* synthetic */ zzcj zza;

    public zzci(zzcj r1) {
        this.zza = r1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return zzi().zzk(0);
    }

    @Override // com.google.android.gms.internal.fido.zzcf, com.google.android.gms.internal.fido.zzby
    public final zzdc zzd() {
        return zzi().zzk(0);
    }

    @Override // com.google.android.gms.internal.fido.zzce
    public final zzcd zzf() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.fido.zzcf
    public final zzcc zzj() {
        return new zzch(this);
    }
}
