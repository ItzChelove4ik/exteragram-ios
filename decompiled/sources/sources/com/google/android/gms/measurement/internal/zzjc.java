package com.google.android.gms.measurement.internal;

/* loaded from: classes5.dex */
final /* synthetic */ class zzjc implements Runnable {
    private final /* synthetic */ zzjd zza;
    private final /* synthetic */ zzr zzb;

    public /* synthetic */ zzjc(zzjd r1, zzr r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        this.zza.zzF(this.zzb);
    }
}
