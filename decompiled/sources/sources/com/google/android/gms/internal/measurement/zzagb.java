package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
final class zzagb extends zzafz {
    public zzagb() {
    }

    @Override // com.google.android.gms.internal.measurement.zzafz
    public final /* bridge */ /* synthetic */ void zza(Object r1, int r2, long r3) {
        ((zzaga) r1).zzk(r2 << 3, Long.valueOf(r3));
    }

    @Override // com.google.android.gms.internal.measurement.zzafz
    public final /* bridge */ /* synthetic */ void zzb(Object r1, int r2, int r3) {
        ((zzaga) r1).zzk((r2 << 3) | 5, Integer.valueOf(r3));
    }

    @Override // com.google.android.gms.internal.measurement.zzafz
    public final /* bridge */ /* synthetic */ void zzc(Object r1, int r2, long r3) {
        ((zzaga) r1).zzk((r2 << 3) | 1, Long.valueOf(r3));
    }

    @Override // com.google.android.gms.internal.measurement.zzafz
    public final /* bridge */ /* synthetic */ void zzd(Object r1, int r2, zzacr r3) {
        ((zzaga) r1).zzk((r2 << 3) | 2, r3);
    }

    @Override // com.google.android.gms.internal.measurement.zzafz
    public final /* bridge */ /* synthetic */ void zze(Object r1, int r2, Object r3) {
        ((zzaga) r1).zzk((r2 << 3) | 3, (zzaga) r3);
    }

    @Override // com.google.android.gms.internal.measurement.zzafz
    public final /* synthetic */ Object zzf() {
        return zzaga.zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzafz
    public final /* synthetic */ Object zzg(Object r1) {
        zzaga r12 = (zzaga) r1;
        r12.zzd();
        return r12;
    }

    @Override // com.google.android.gms.internal.measurement.zzafz
    public final /* bridge */ /* synthetic */ Object zzh(Object r2) {
        zzadu r22 = (zzadu) r2;
        zzaga r1 = r22.zzc;
        if (r1 != zzaga.zza()) goto L6;
        zzaga r12 = zzaga.zzb();
        r22.zzc = r12;
        return r12;
    L6:
        return r1;
    }

    @Override // com.google.android.gms.internal.measurement.zzafz
    public final /* synthetic */ void zzi(Object r1, Object r2) {
        ((zzadu) r1).zzc = (zzaga) r2;
    }

    @Override // com.google.android.gms.internal.measurement.zzafz
    public final void zzj(Object r1) {
        ((zzadu) r1).zzc.zzd();
    }
}
