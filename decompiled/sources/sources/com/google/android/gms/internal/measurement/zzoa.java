package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
abstract class zzoa extends zzof {
    private volatile double zza;

    public zzoa(String r1, String r2, zzph r3) {
        super("com.google.android.gms.measurement", "measurement.test.double_flag", r3);
    }

    @Override // com.google.android.gms.internal.measurement.zznp
    public final /* synthetic */ Object zzc(String r1) {
        return Double.valueOf(Double.parseDouble(r1));
    }

    @Override // com.google.android.gms.internal.measurement.zznp
    public final /* synthetic */ Object zzd(Object r1) {
        return (Double) r1;
    }

    @Override // com.google.android.gms.internal.measurement.zznw
    public final /* synthetic */ Object zze() {
        return Double.valueOf(this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zznw
    public final /* synthetic */ void zzh(Object r3) {
        this.zza = ((Double) r3).doubleValue();
    }
}
