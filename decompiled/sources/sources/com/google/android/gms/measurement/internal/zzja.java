package com.google.android.gms.measurement.internal;

/* loaded from: classes5.dex */
final /* synthetic */ class zzja implements Runnable {
    private final /* synthetic */ zzjd zza;
    private final /* synthetic */ zzr zzb;
    private final /* synthetic */ zzaf zzc;

    public /* synthetic */ zzja(zzjd r1, zzr r2, zzaf r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        this.zza.zzJ(this.zzb, this.zzc);
    }
}
