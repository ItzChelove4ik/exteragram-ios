package com.google.android.gms.measurement.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zzn implements Runnable {
    final /* synthetic */ com.google.android.gms.internal.measurement.zzcs zza;
    final /* synthetic */ AppMeasurementDynamiteService zzb;

    public zzn(AppMeasurementDynamiteService r1, com.google.android.gms.internal.measurement.zzcs r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AppMeasurementDynamiteService r0 = this.zzb;
        zzpp r1 = r0.zza.zzk();
        boolean r02 = r0.zza.zzA();
        r1.zzas(this.zza, r02);
    }
}
