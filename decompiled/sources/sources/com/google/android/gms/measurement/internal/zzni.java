package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes5.dex */
final /* synthetic */ class zzni implements Runnable {
    private final /* synthetic */ zznl zza;
    private final /* synthetic */ AtomicReference zzb;
    private final /* synthetic */ zzr zzc;
    private final /* synthetic */ zzoo zzd;

    public /* synthetic */ zzni(zznl r1, AtomicReference r2, zzr r3, zzoo r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        this.zza.zzT(this.zzb, this.zzc, this.zzd);
    }
}
