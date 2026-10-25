package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
final class zzyn extends zzyq {
    final /* synthetic */ zzyq zza;
    final /* synthetic */ zzyq zzb;

    public zzyn(zzyq r1, zzyq r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.internal.measurement.zzyq
    public final void zzb() {
        this.zza.zzb();     // Catch: Throwable -> L5
        this.zzb.zzb();
        return;
    L5:
        th = move-exception;
        this.zzb.zzb();
        throw th;
    }
}
