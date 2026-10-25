package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zznb implements Runnable {
    final /* synthetic */ zznf zza;

    public zznb(zznf r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zznl r3 = this.zza.zza;
        zzic r1 = r3.zzu;
        Context r2 = r1.zzaZ();
        r1.zzaV();
        r3.zzW(new ComponentName(r2, "com.google.android.gms.measurement.AppMeasurementService"));
    }
}
