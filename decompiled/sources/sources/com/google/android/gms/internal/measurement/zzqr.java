package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
final class zzqr {
    private final boolean zza;
    private final int zzb;
    private final int zzc;

    public zzqr(int r2) {
        this.zzb = 2;
        this.zzc = r2;
        this.zza = false;
    }

    public zzqr(int r1, int r2) {
        this.zzb = r1;
        this.zzc = r2;
        this.zza = false;
    }

    public final zzmd zza() {
        if (this.zza == true) goto L5;
        int r0 = this.zzb;
        zzmc r1 = zzmd.zza();
        r1.zza(r0);
        r1.zzb(this.zzc);
        return (zzmd) r1.zzbd();
    L5:
        return zzmd.zzb();
    }

    public final int zzb() {
        return this.zzc;
    }

    public final /* synthetic */ int zzc() {
        return this.zzb;
    }
}
