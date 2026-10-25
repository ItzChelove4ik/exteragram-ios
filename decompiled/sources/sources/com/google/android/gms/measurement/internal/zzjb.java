package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* loaded from: classes5.dex */
final /* synthetic */ class zzjb implements Runnable {
    private final /* synthetic */ zzjd zza;
    private final /* synthetic */ Bundle zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ zzr zzd;

    public /* synthetic */ zzjb(zzjd r1, Bundle r2, String r3, zzr r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        this.zza.zzK(this.zzb, this.zzc, this.zzd);
    }
}
