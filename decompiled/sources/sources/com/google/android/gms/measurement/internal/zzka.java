package com.google.android.gms.measurement.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zzka implements Runnable {
    final /* synthetic */ long zza;
    final /* synthetic */ zzlj zzb;

    public zzka(zzlj r1, long r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzic r0 = this.zzb.zzu;
        zzhe r1 = r0.zzd().zzf;
        long r2 = this.zza;
        r1.zzb(r2);
        r0.zzaW().zzj().zzb("Session timeout duration set", Long.valueOf(r2));
    }
}
