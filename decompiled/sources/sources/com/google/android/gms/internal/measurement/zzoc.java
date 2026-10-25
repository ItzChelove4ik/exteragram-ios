package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
abstract class zzoc extends zzof {
    private volatile long zza;

    public zzoc(String r1, String r2, zzph r3) {
        super("com.google.android.gms.measurement", r2, r3);
    }

    @Override // com.google.android.gms.internal.measurement.zznp
    public final /* synthetic */ Object zzc(String r1) {
        return Long.valueOf(Long.parseLong(r1));
    }

    @Override // com.google.android.gms.internal.measurement.zznp
    public final /* synthetic */ Object zzd(Object r1) {
        return (Long) r1;
    }

    @Override // com.google.android.gms.internal.measurement.zznw
    public final /* synthetic */ Object zze() {
        return Long.valueOf(this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zznw
    public final /* synthetic */ void zzh(Object r3) {
        this.zza = ((Long) r3).longValue();
    }
}
