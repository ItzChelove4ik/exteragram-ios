package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* loaded from: classes5.dex */
public final class zznt {
    private final Context zza;

    public zznt(Context r1) {
        Preconditions.checkNotNull(r1);
        this.zza = r1;
    }

    public static final void zzi(Intent r2) {
        if (r2 != null) goto L6;
        Log.e("FA", "onRebind called with null intent");
        return;
    L6:
        Log.v("FA", "onRebind called. action: ".concat(String.valueOf(r2.getAction())));
    }

    public static final boolean zzj(Intent r3) {
        if (r3 != null) goto L6;
        Log.e("FA", "onUnbind called with null intent");
        return true;
    L6:
        Log.v("FA", "onUnbind called for intent. action: ".concat(String.valueOf(r3.getAction())));
        return true;
    }

    private final void zzk(zzpg r3, Runnable r4) {
        r3.zzaX().zzj(new zzno(this, r3, r4));
    }

    public final void zza() {
        Log.v("FA", this.zza.getClass().getSimpleName().concat(" is starting up."));
    }

    public final void zzb() {
        Log.v("FA", this.zza.getClass().getSimpleName().concat(" is shutting down."));
    }

    public final int zzc(Intent r7, int r8, int r9) {
        if (r7 != null) goto L6;
        Log.w("FA", "AppMeasurementService started with null intent");
        return 2;
    L6:
        Context r0 = this.zza;
        zzic r1 = zzic.zzy(r0, null, null, null);
        zzgu r2 = r1.zzaW();
        String r3 = r7.getAction();
        r1.zzaV();
        r2.zzk().zzc("Local AppMeasurementService called. startId, action", Integer.valueOf(r9), r3);
        if ("com.google.android.gms.measurement.UPLOAD".equals(r3) == false) goto L9;
        zzns r12 = new zzns(this, r9, r2, r7);
        zzk(zzpg.zza(r0), r12);
    L9:
        return 2;
    }

    public final IBinder zzd(Intent r4) {
        if (r4 != null) goto L6;
        Log.e("FA", "onBind called with null intent");
        return null;
    L6:
        String r42 = r4.getAction();
        if ("com.google.android.gms.measurement.START".equals(r42) == true) goto L9;
        Log.w("FA", "onBind received unknown action: ".concat(String.valueOf(r42)));
        return null;
    L9:
        return new zzjd(zzpg.zza(this.zza), null);
    }

    @TargetApi(24)
    public final boolean zze(JobParameters r7) {
        String r0 = r7.getExtras().getString("action");
        Log.v("FA", "onStartJob received action: ".concat(String.valueOf(r0)));
        if (Objects.equals(r0, "com.google.android.gms.measurement.UPLOAD") == false) goto L6;
        String r1 = (String) Preconditions.checkNotNull(r0);
        zzpg r2 = zzpg.zza(this.zza);
        zzgu r3 = r2.zzaW();
        r2.zzaV();
        r3.zzk().zzb("Local AppMeasurementJobService called. action", r1);
        zzk(r2, new zznq(this, r3, r7));
    L6:
        if (Objects.equals(r0, "com.google.android.gms.measurement.SCION_UPLOAD") == false) goto L10;
        String r02 = (String) Preconditions.checkNotNull(r0);
        com.google.android.gms.internal.measurement.zzez.zza(this.zza, null).zzw(new zznr(this, r7));
        return true;
    L10:
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final /* synthetic */ void zzf(int r3, zzgu r4, Intent r5) {
        Context r2 = this.zza;
        zznp r0 = (zznp) r2;
        if (r0.zza(r3) == false) goto L6;
        r4.zzk().zzb("Local AppMeasurementService processed last upload request. StartId", Integer.valueOf(r3));
        zzic.zzy(r2, null, null, null).zzaW().zzk().zza("Completed wakeful intent.");
        r0.zzc(r5);
        return;
    }

    public final /* synthetic */ void zzg(zzgu r2, JobParameters r3) {
        r2.zzk().zza("AppMeasurementJobService processed last upload request.");
        ((zznp) this.zza).zzb(r3, false);
    }

    public final /* synthetic */ void zzh(JobParameters r3) {
        Log.v("FA", "[sgtm] AppMeasurementJobService processed last Scion upload request.");
        ((zznp) this.zza).zzb(r3, false);
    }
}
