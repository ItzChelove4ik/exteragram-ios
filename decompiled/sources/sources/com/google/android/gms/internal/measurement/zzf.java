package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public final class zzf {
    final zzaw zza;
    final zzg zzb;
    final zzg zzc;
    final zzj zzd;

    public zzf() {
        zzaw r0 = new zzaw();
        this.zza = r0;
        zzg r1 = new zzg(null, r0);
        this.zzc = r1;
        this.zzb = r1.zzc();
        zzj r02 = new zzj();
        this.zzd = r02;
        r1.zze("require", new zzw(r02));
        r02.zza("internal.platform", zze.zza);
        r1.zze("runtime.counter", new zzah(Double.valueOf(0.0d)));
    }

    public final zzao zza(zzg r5, zzje... r6) {
        zzao r0 = zzao.zzf;
        int r1 = r6.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L10;
        r0 = zzi.zzb(r6[r2]);
        zzh.zzl(this.zzc);
        if ((r0 instanceof zzap) == false) goto L7;
    L8:
        r0 = this.zza.zzb(r5, r0);
    L9:
        r2 = r2 + 1;
        goto L3
    L7:
        if ((r0 instanceof zzan) == false) goto L9;
    L10:
        return r0;
    }
}
