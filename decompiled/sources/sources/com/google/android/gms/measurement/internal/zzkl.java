package com.google.android.gms.measurement.internal;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes5.dex */
final class zzkl implements Runnable {
    final /* synthetic */ AtomicReference zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ boolean zzd;
    final /* synthetic */ zzlj zze;

    public zzkl(zzlj r1, AtomicReference r2, String r3, String r4, String r5, boolean r6) {
        this.zza = r2;
        this.zzb = r4;
        this.zzc = r5;
        this.zzd = r6;
        Objects.requireNonNull(r1);
        this.zze = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String r3 = this.zzb;
        String r4 = this.zzc;
        boolean r5 = this.zzd;
        this.zze.zzu.zzt().zzt(this.zza, null, r3, r4, r5);
    }
}
