package com.google.android.gms.measurement.internal;

import android.app.job.JobParameters;

/* loaded from: classes5.dex */
final /* synthetic */ class zznq implements Runnable {
    private final /* synthetic */ zznt zza;
    private final /* synthetic */ zzgu zzb;
    private final /* synthetic */ JobParameters zzc;

    public /* synthetic */ zznq(zznt r1, zzgu r2, JobParameters r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        this.zza.zzg(this.zzb, this.zzc);
    }
}
