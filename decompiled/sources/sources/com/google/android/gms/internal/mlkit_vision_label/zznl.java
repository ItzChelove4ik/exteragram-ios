package com.google.android.gms.internal.mlkit_vision_label;

/* loaded from: classes4.dex */
public final /* synthetic */ class zznl implements Runnable {
    public final /* synthetic */ zznp zza;
    public final /* synthetic */ zznf zzb;
    public final /* synthetic */ zzkf zzc;
    public final /* synthetic */ String zzd;

    public /* synthetic */ zznl(zznp r1, zznf r2, zzkf r3, String r4) {
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
