package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

/* loaded from: classes4.dex */
public final /* synthetic */ class zztj implements Runnable {
    public final /* synthetic */ zztl zza;
    public final /* synthetic */ zzob zzb;
    public final /* synthetic */ Object zzc;
    public final /* synthetic */ long zzd;
    public final /* synthetic */ com.google.mlkit.vision.segmentation.subject.internal.zzh zze;

    public /* synthetic */ zztj(zztl r1, zzob r2, Object r3, long r4, com.google.mlkit.vision.segmentation.subject.internal.zzh r6) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzh(this.zzb, this.zzc, this.zzd, this.zze);
    }
}
