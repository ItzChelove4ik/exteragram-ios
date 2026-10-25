package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

/* loaded from: classes4.dex */
public final /* synthetic */ class zztg implements Runnable {
    public final /* synthetic */ zztl zza;
    public final /* synthetic */ zzob zzb;
    public final /* synthetic */ com.google.mlkit.vision.segmentation.subject.internal.zzh zzc;

    public /* synthetic */ zztg(zztl r1, zzob r2, com.google.mlkit.vision.segmentation.subject.internal.zzh r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzg(this.zzb, this.zzc);
    }
}
