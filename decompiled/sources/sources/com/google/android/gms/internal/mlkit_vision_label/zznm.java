package com.google.android.gms.internal.mlkit_vision_label;

/* loaded from: classes4.dex */
public final /* synthetic */ class zznm implements Runnable {
    public final /* synthetic */ zznp zza;
    public final /* synthetic */ zzkf zzb;
    public final /* synthetic */ Object zzc;
    public final /* synthetic */ long zzd;
    public final /* synthetic */ com.google.mlkit.vision.label.defaults.thin.zzg zze;

    public /* synthetic */ zznm(zznp r1, zzkf r2, Object r3, long r4, com.google.mlkit.vision.label.defaults.thin.zzg r6, byte[] r7) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzg(this.zzb, this.zzc, this.zzd, this.zze);
    }
}
