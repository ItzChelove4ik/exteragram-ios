package com.google.android.gms.internal.clearcut;

/* loaded from: classes4.dex */
final class zzez extends zzex<zzey, zzey> {
    public zzez() {
    }

    private static void zza(Object r0, zzey r1) {
        ((zzcg) r0).zzjp = r1;
    }

    @Override // com.google.android.gms.internal.clearcut.zzex
    public final /* synthetic */ void zza(zzey r1, int r2, long r3) {
        r1.zzb(r2 << 3, Long.valueOf(r3));
    }

    @Override // com.google.android.gms.internal.clearcut.zzex
    public final /* synthetic */ void zza(zzey r1, int r2, zzbb r3) {
        r1.zzb((r2 << 3) | 2, r3);
    }

    @Override // com.google.android.gms.internal.clearcut.zzex
    public final /* synthetic */ void zza(zzey r1, zzfr r2) {
        r1.zzb(r2);
    }

    @Override // com.google.android.gms.internal.clearcut.zzex
    public final void zzc(Object r1) {
        ((zzcg) r1).zzjp.zzv();
    }

    @Override // com.google.android.gms.internal.clearcut.zzex
    public final /* synthetic */ void zzc(zzey r1, zzfr r2) {
        r1.zza(r2);
    }

    @Override // com.google.android.gms.internal.clearcut.zzex
    public final /* synthetic */ zzey zzdz() {
        return zzey.zzeb();
    }

    @Override // com.google.android.gms.internal.clearcut.zzex
    public final /* synthetic */ void zze(Object r1, zzey r2) {
        zza(r1, r2);
    }

    @Override // com.google.android.gms.internal.clearcut.zzex
    public final /* synthetic */ void zzf(Object r1, zzey r2) {
        zza(r1, r2);
    }

    @Override // com.google.android.gms.internal.clearcut.zzex
    public final /* synthetic */ zzey zzg(zzey r1, zzey r2) {
        zzey r12 = r1;
        zzey r22 = r2;
        if (r22.equals(zzey.zzea()) == false) goto L6;
        return r12;
    L6:
        return zzey.zza(r12, r22);
    }

    @Override // com.google.android.gms.internal.clearcut.zzex
    public final /* synthetic */ int zzm(zzey r1) {
        return r1.zzas();
    }

    @Override // com.google.android.gms.internal.clearcut.zzex
    public final /* synthetic */ zzey zzq(Object r1) {
        return ((zzcg) r1).zzjp;
    }

    @Override // com.google.android.gms.internal.clearcut.zzex
    public final /* synthetic */ int zzr(zzey r1) {
        return r1.zzec();
    }
}
