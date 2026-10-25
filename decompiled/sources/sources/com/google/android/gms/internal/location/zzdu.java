package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationResult;

/* loaded from: classes4.dex */
final class zzdu extends com.google.android.gms.location.zzu {
    private final zzdq zza;

    public zzdu(zzdq r1) {
        this.zza = r1;
    }

    public final zzdu zzc(ListenerHolder r2) {
        this.zza.zzb(r2);
        return this;
    }

    @Override // com.google.android.gms.location.zzv
    public final void zzd(LocationResult r3) {
        this.zza.zza().notifyListener(new zzdr(this, r3));
    }

    @Override // com.google.android.gms.location.zzv
    public final void zze(LocationAvailability r3) {
        this.zza.zza().notifyListener(new zzds(this, r3));
    }

    @Override // com.google.android.gms.location.zzv
    public final void zzf() {
        this.zza.zza().notifyListener(new zzdt(this));
    }

    public final void zzg() {
        this.zza.zza().clear();
    }

    public final /* synthetic */ zzdq zzh() {
        return this.zza;
    }
}
