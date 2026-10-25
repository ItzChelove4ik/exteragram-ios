package com.google.android.gms.measurement;

import android.content.Context;
import android.content.Intent;
import androidx.legacy.content.WakefulBroadcastReceiver;
import com.google.android.gms.measurement.internal.zzhl;

/* loaded from: classes5.dex */
public final class AppMeasurementReceiver extends WakefulBroadcastReceiver implements zzhl.zza {
    private zzhl zza;

    public AppMeasurementReceiver() {
    }

    @Override // com.google.android.gms.measurement.internal.zzhl.zza
    public void doStartService(Context r1, Intent r2) {
        WakefulBroadcastReceiver.startWakefulService(r1, r2);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context r2, Intent r3) {
        if (this.zza != null) goto L5;
        this.zza = new zzhl(this);
    L5:
        this.zza.zza(r2, r3);
    }
}
