package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
abstract class zzny extends zzof {
    private volatile boolean zza;

    public zzny(String r1, String r2, zzph r3) {
        super("com.google.android.gms.measurement", r2, r3);
    }

    @Override // com.google.android.gms.internal.measurement.zznp
    public final /* synthetic */ Object zzc(String r1) {
        return Boolean.valueOf(Boolean.parseBoolean(r1));
    }

    @Override // com.google.android.gms.internal.measurement.zznp
    public final /* synthetic */ Object zzd(Object r1) {
        return (Boolean) r1;
    }

    @Override // com.google.android.gms.internal.measurement.zznw
    public final /* synthetic */ Object zze() {
        return Boolean.valueOf(this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zznw
    public final /* synthetic */ void zzh(Object r1) {
        this.zza = ((Boolean) r1).booleanValue();
    }
}
