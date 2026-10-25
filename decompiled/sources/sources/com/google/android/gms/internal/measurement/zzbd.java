package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
final class zzbd implements zzbe {
    private final zzg zza;
    private final String zzb;

    public zzbd(zzg r1, String r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.internal.measurement.zzbe
    public final zzg zza(zzao r2) {
        zzg r0 = this.zza.zzc();
        r0.zzf(this.zzb, r2);
        return r0;
    }
}
