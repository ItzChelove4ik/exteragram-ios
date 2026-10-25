package com.google.android.gms.internal.mlkit_vision_common;

/* loaded from: classes4.dex */
public final /* synthetic */ class zzmi implements Runnable {
    public final /* synthetic */ zzmj zza;
    public final /* synthetic */ zzmb zzb;
    public final /* synthetic */ zziv zzc;
    public final /* synthetic */ String zzd;

    public /* synthetic */ zzmi(zzmj r1, zzmb r2, zziv r3, String r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzb(this.zzb, this.zzc, this.zzd);
    }
}
