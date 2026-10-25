package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;

/* loaded from: classes5.dex */
public final class zzok extends zzos {
    private final AlarmManager zza;
    private zzaz zzb;
    private Integer zzc;

    public zzok(zzpg r2) {
        super(r2);
        this.zza = (AlarmManager) this.zzu.zzaZ().getSystemService("alarm");
    }

    @TargetApi(24)
    private final void zzh() {
        JobScheduler r0 = (JobScheduler) this.zzu.zzaZ().getSystemService("jobscheduler");
        if (r0 == null) goto L6;
        r0.cancel(zzi());
        return;
    }

    private final int zzi() {
        if (this.zzc != null) goto L6;
        this.zzc = Integer.valueOf("measurement".concat(String.valueOf(this.zzu.zzaZ().getPackageName())).hashCode());
    L6:
        return this.zzc.intValue();
    }

    private final PendingIntent zzj() {
        Context r3 = this.zzu.zzaZ();
        return com.google.android.gms.internal.measurement.zzce.zza(r3, 0, new Intent().setClassName(r3, "com.google.android.gms.measurement.AppMeasurementReceiver").setAction("com.google.android.gms.measurement.UPLOAD"), com.google.android.gms.internal.measurement.zzce.zza);
    }

    @Override // com.google.android.gms.measurement.internal.zzos
    public final boolean zzbc() {
        AlarmManager r0 = this.zza;
        if (r0 == null) goto L5;
        r0.cancel(zzj());
    L5:
        zzh();
        return false;
    }

    public final zzaz zzc() {
        if (this.zzb != null) goto L6;
        this.zzb = new zzoj(this, this.zzg.zzah());
    L6:
        return this.zzb;
    }

    public final void zzd(long r6) {
        zzay();
        zzic r0 = this.zzu;
        r0.zzaV();
        Context r1 = r0.zzaZ();
        if (zzpp.zzax(r1) == true) goto L6;
        r0.zzaW().zzj().zza("Receiver not registered/enabled");
    L6:
        if (zzpp.zzS(r1, false) == true) goto L8;
        r0.zzaW().zzj().zza("Service not registered/enabled");
    L8:
        zzf();
        r0.zzaW().zzk().zzb("Scheduling upload, millis", Long.valueOf(r6));
        r0.zzba().elapsedRealtime();
        r0.zzc();
        if (r6 < Math.max(0, ((Long) zzfy.zzL.zzb(null)).longValue())) goto L11;
    L13:
        r0.zzaV();
        Context r02 = r0.zzaZ();
        ComponentName r12 = new ComponentName(r02, "com.google.android.gms.measurement.AppMeasurementJobService");
        int r5 = zzi();
        PersistableBundle r2 = new PersistableBundle();
        r2.putString("action", "com.google.android.gms.measurement.UPLOAD");
        com.google.android.gms.internal.measurement.zzcf.zza(r02, new JobInfo.Builder(r5, r12).setMinimumLatency(r6).setOverrideDeadline(r6 + r6).setExtras(r2).build(), "com.google.android.gms", "UploadAlarm");
        return;
    L11:
        if (zzc().zzc() == true) goto L13;
        zzc().zzb(r6);
        goto L13
    }

    public final void zzf() {
        zzay();
        this.zzu.zzaW().zzk().zza("Unscheduling upload");
        AlarmManager r0 = this.zza;
        if (r0 == null) goto L5;
        r0.cancel(zzj());
    L5:
        zzc().zzd();
        zzh();
    }
}
