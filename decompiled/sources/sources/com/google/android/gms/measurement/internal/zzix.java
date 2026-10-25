package com.google.android.gms.measurement.internal;

/* loaded from: classes5.dex */
final /* synthetic */ class zzix implements Runnable {
    private final /* synthetic */ zzjd zza;
    private final /* synthetic */ zzr zzb;

    public /* synthetic */ zzix(zzjd r1, zzr r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        this.zza.zzG(this.zzb);
    }
}
