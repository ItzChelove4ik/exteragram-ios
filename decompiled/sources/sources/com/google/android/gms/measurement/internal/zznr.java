package com.google.android.gms.measurement.internal;

import android.app.job.JobParameters;

/* loaded from: classes5.dex */
final /* synthetic */ class zznr implements Runnable {
    private final /* synthetic */ zznt zza;
    private final /* synthetic */ JobParameters zzb;

    public /* synthetic */ zznr(zznt r1, JobParameters r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        this.zza.zzh(this.zzb);
    }
}
