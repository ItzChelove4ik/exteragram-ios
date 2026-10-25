package com.google.android.gms.measurement.internal;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes5.dex */
final class zzkk implements Runnable {
    final /* synthetic */ AtomicReference zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ zzlj zzd;

    public zzkk(zzlj r1, AtomicReference r2, String r3, String r4, String r5) {
        this.zza = r2;
        this.zzb = r4;
        this.zzc = r5;
        Objects.requireNonNull(r1);
        this.zzd = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String r0 = this.zzb;
        String r1 = this.zzc;
        this.zzd.zzu.zzt().zzq(this.zza, null, r0, r1);
    }
}
