package com.google.android.gms.measurement.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zzkv implements Runnable {
    final /* synthetic */ zzjl zza;
    final /* synthetic */ long zzb;
    final /* synthetic */ boolean zzc;
    final /* synthetic */ zzlj zzd;

    public zzkv(zzlj r1, zzjl r2, long r3, boolean r5) {
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r5;
        Objects.requireNonNull(r1);
        this.zzd = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzlj r0 = this.zzd;
        zzjl r1 = this.zza;
        r0.zzz(r1);
        boolean r5 = this.zzc;
        r0.zzaj(r1, this.zzb, false, r5);
    }
}
