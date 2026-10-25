package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes5.dex */
final /* synthetic */ class zzli implements Runnable {
    private final /* synthetic */ zzlj zza;
    private final /* synthetic */ AtomicReference zzb;

    public /* synthetic */ zzli(zzlj r1, AtomicReference r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        zznl r0 = this.zza.zzu.zzt();
        zzoo r1 = zzoo.zza(new zzls[]{zzls.zzd});
        r0.zzx(this.zzb, r1);
    }
}
