package com.google.android.gms.measurement;

import android.app.Service;
import android.app.job.JobParameters;
import android.content.Intent;
import android.os.IBinder;
import androidx.legacy.content.WakefulBroadcastReceiver;
import com.google.android.gms.measurement.internal.zznp;
import com.google.android.gms.measurement.internal.zznt;

/* loaded from: classes5.dex */
public final class AppMeasurementService extends Service implements zznp {
    private zznt zza;

    public AppMeasurementService() {
    }

    private final zznt zzd() {
        if (this.zza != null) goto L6;
        this.zza = new zznt(this);
    L6:
        return this.zza;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent r1) {
        return zzd().zzd(r1);
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

    @Override // android.app.Service
    public int onStartCommand(Intent r1, int r2, int r3) {
        zzd().zzc(r1, r2, r3);
        return 2;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent r1) {
        zzd();
        zznt.zzj(r1);
        return true;
    }

    @Override // com.google.android.gms.measurement.internal.zznp
    public final boolean zza(int r1) {
        return stopSelfResult(r1);
    }

    @Override // com.google.android.gms.measurement.internal.zznp
    public final void zzb(JobParameters r1, boolean r2) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.measurement.internal.zznp
    public final void zzc(Intent r1) {
        WakefulBroadcastReceiver.completeWakefulIntent(r1);
    }
}
