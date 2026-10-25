package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.PersistableBundle;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes5.dex */
public final class zzlq extends zzg {
    private JobScheduler zza;

    public zzlq(zzic r1) {
        super(r1);
    }

    @Override // com.google.android.gms.measurement.internal.zzg
    public final boolean zze() {
        return true;
    }

    @Override // com.google.android.gms.measurement.internal.zzg
    @TargetApi(24)
    public final void zzf() {
        this.zza = (JobScheduler) this.zzu.zzaZ().getSystemService("jobscheduler");
    }

    @TargetApi(24)
    public final void zzh(long r8) {
        zzb();
        zzg();
        JobScheduler r0 = this.zza;
        if (r0 != null) goto L5;
    L8:
        com.google.android.gms.internal.measurement.zzin r02 = zzj();
        com.google.android.gms.internal.measurement.zzin r1 = com.google.android.gms.internal.measurement.zzin.zzb;
        zzic r2 = this.zzu;
        if (r02 != r1) goto L16;
        r2.zzaW().zzk().zzb("[sgtm] Scheduling Scion upload, millis", Long.valueOf(r8));
        PersistableBundle r03 = new PersistableBundle();
        r03.putString("action", "com.google.android.gms.measurement.SCION_UPLOAD");
        JobInfo r82 = new JobInfo.Builder(zzi(), new ComponentName(r2.zzaZ(), "com.google.android.gms.measurement.AppMeasurementJobService")).setRequiredNetworkType(1).setMinimumLatency(r8).setOverrideDeadline(r8 + r8).setExtras(r03).build();
        int r7 = ((JobScheduler) Preconditions.checkNotNull(this.zza)).schedule(r82);
        zzgs r83 = r2.zzaW().zzk();
        if (r7 != 1) goto L13;
        String r72 = "SUCCESS";
    L14:
        r83.zzb("[sgtm] Scion upload job scheduled with result", r72);
        return;
    L13:
        r72 = "FAILURE";
        goto L14
    L16:
        r2.zzaW().zzk().zzb("[sgtm] Not eligible for Scion upload", r02.name());
        return;
    L5:
        if (r0.getPendingJob(zzi()) == null) goto L8;
        this.zzu.zzaW().zzk().zza("[sgtm] There's an existing pending job, skip this schedule.");
    }

    public final int zzi() {
        return "measurement-client".concat(String.valueOf(this.zzu.zzaZ().getPackageName())).hashCode();
    }

    public final com.google.android.gms.internal.measurement.zzin zzj() {
        zzb();
        zzg();
        if (this.zza == null) goto L23;
        zzic r0 = this.zzu;
        if (r0.zzc().zzx() == false) goto L21;
        zzic r5 = this.zzu;
        if (r5.zzv().zzn() < 119000) goto L19;
        if (zzpp.zzT(r0.zzaZ(), "com.google.android.gms.measurement.AppMeasurementJobService") == true) goto L13;
        return com.google.android.gms.internal.measurement.zzin.zzc;
    L13:
        if (r5.zzt().zzK() == true) goto L17;
        return com.google.android.gms.internal.measurement.zzin.zze;
    L17:
        return com.google.android.gms.internal.measurement.zzin.zzb;
    L19:
        return com.google.android.gms.internal.measurement.zzin.zzf;
    L21:
        return com.google.android.gms.internal.measurement.zzin.zzh;
    L23:
        return com.google.android.gms.internal.measurement.zzin.zzg;
    }
}
