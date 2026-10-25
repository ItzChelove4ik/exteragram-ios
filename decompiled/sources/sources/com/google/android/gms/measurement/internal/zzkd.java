package com.google.android.gms.measurement.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zzkd implements Runnable {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ Object zzc;
    final /* synthetic */ long zzd;
    final /* synthetic */ zzlj zze;

    public zzkd(zzlj r1, String r2, String r3, Object r4, long r5) {
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r5;
        Objects.requireNonNull(r1);
        this.zze = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zze.zzM(this.zza, this.zzb, this.zzc, this.zzd);
    }
}
