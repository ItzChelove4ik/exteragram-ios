package com.google.android.gms.internal.mlkit_vision_label;

/* loaded from: classes4.dex */
public final /* synthetic */ class zznn implements Runnable {
    public final /* synthetic */ zznp zza;
    public final /* synthetic */ zzkf zzb;
    public final /* synthetic */ com.google.mlkit.vision.label.defaults.thin.zzg zzc;

    public /* synthetic */ zznn(zznp r1, zzkf r2, com.google.mlkit.vision.label.defaults.thin.zzg r3, byte[] r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzf(this.zzb, this.zzc);
    }
}
