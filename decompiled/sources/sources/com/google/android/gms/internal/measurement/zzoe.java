package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
abstract class zzoe extends zzof {
    private volatile String zza;

    public zzoe(String r1, String r2, zzph r3) {
        super("com.google.android.gms.measurement", r2, r3);
    }

    @Override // com.google.android.gms.internal.measurement.zznp
    public final /* bridge */ /* synthetic */ Object zzc(String r1) {
        return r1;
    }

    @Override // com.google.android.gms.internal.measurement.zznp
    public final /* synthetic */ Object zzd(Object r1) {
        return (String) r1;
    }

    @Override // com.google.android.gms.internal.measurement.zznw
    public final /* synthetic */ Object zze() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.measurement.zznw
    public final /* synthetic */ void zzh(Object r1) {
        this.zza = (String) r1;
    }
}
