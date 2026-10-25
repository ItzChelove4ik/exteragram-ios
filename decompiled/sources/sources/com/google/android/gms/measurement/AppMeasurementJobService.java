package com.google.android.gms.measurement;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import com.google.android.gms.measurement.internal.zznp;
import com.google.android.gms.measurement.internal.zznt;

@TargetApi(24)
/* loaded from: classes5.dex */
public final class AppMeasurementJobService extends JobService implements zznp {
    private zznt zza;

    public AppMeasurementJobService() {
    }

    private final zznt zzd() {
        if (this.zza != null) goto L6;
        this.zza = new zznt(this);
    L6:
        return this.zza;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        zzd().zza();
    }

    @Override // android.app.Service
    public void onDestroy() {
        zzd().zzb();
        super.onDestroy();
    }

    @Override // android.app.Service
    public void onRebind(Intent r1) {
        zzd();
        zznt.zzi(r1);
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters r1) {
        zzd().zze(r1);
        return true;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters r1) {
        return false;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent r1) {
        zzd();
        zznt.zzj(r1);
        return true;
    }

    @Override // com.google.android.gms.measurement.internal.zznp
    public final boolean zza(int r1) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.measurement.internal.zznp
    @TargetApi(24)
    public final void zzb(JobParameters r1, boolean r2) {
        jobFinished(r1, false);
    }

    @Override // com.google.android.gms.measurement.internal.zznp
    public final void zzc(Intent r1) {
    }
}
