package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

/* loaded from: classes4.dex */
public final /* synthetic */ class zztf implements Runnable {
    public final /* synthetic */ zztl zza;
    public final /* synthetic */ zzta zzb;
    public final /* synthetic */ zzob zzc;
    public final /* synthetic */ String zzd;

    public /* synthetic */ zztf(zztl r1, zzta r2, zzob r3, String r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzc(this.zzb, this.zzc, this.zzd);
    }
}
