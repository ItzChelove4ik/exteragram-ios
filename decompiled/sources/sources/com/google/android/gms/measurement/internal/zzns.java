package com.google.android.gms.measurement.internal;

import android.content.Intent;

/* loaded from: classes5.dex */
final /* synthetic */ class zzns implements Runnable {
    private final /* synthetic */ zznt zza;
    private final /* synthetic */ int zzb;
    private final /* synthetic */ zzgu zzc;
    private final /* synthetic */ Intent zzd;

    public /* synthetic */ zzns(zznt r1, int r2, zzgu r3, Intent r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        this.zza.zzf(this.zzb, this.zzc, this.zzd);
    }
}
