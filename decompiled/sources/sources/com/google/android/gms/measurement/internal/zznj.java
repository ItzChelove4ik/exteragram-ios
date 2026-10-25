package com.google.android.gms.measurement.internal;

/* loaded from: classes5.dex */
final /* synthetic */ class zznj implements Runnable {
    private final /* synthetic */ zznl zza;
    private final /* synthetic */ zzr zzb;
    private final /* synthetic */ zzaf zzc;

    public /* synthetic */ zznj(zznl r1, zzr r2, zzaf r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        this.zza.zzU(this.zzb, this.zzc);
    }
}
