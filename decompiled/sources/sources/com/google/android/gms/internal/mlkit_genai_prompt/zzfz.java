package com.google.android.gms.internal.mlkit_genai_prompt;

/* loaded from: classes4.dex */
final /* synthetic */ class zzfz implements Runnable {
    private final /* synthetic */ zzdy zza;
    private final /* synthetic */ zzdq zzb;
    private final /* synthetic */ long zzc;

    public /* synthetic */ zzfz(zzdy r1, zzdq r2, long r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        this.zza.zza(this.zzb, this.zzc);
    }
}
