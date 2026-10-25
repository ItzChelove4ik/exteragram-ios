package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* loaded from: classes5.dex */
final /* synthetic */ class zziy implements Runnable {
    private final /* synthetic */ zzjd zza;
    private final /* synthetic */ zzr zzb;
    private final /* synthetic */ Bundle zzc;
    private final /* synthetic */ zzge zzd;
    private final /* synthetic */ String zze;

    public /* synthetic */ zziy(zzjd r1, zzr r2, Bundle r3, zzge r4, String r5) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        this.zza.zzH(this.zzb, this.zzc, this.zzd, this.zze);
    }
}
