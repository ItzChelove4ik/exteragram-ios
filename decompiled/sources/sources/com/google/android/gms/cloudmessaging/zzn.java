package com.google.android.gms.cloudmessaging;

/* loaded from: classes.dex */
final /* synthetic */ class zzn implements Runnable {
    private final /* synthetic */ zzp zza;
    private final /* synthetic */ zzs zzb;

    public /* synthetic */ zzn(zzp r1, zzs r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        zzs r0 = this.zzb;
        this.zza.zzg(r0.zza);
    }
}
