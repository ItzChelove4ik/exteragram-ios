package com.google.android.gms.internal.mlkit_genai_prompt;

/* loaded from: classes4.dex */
final /* synthetic */ class zzauz implements Runnable {
    private final /* synthetic */ zzavc zza;
    private final /* synthetic */ zzauu zzb;
    private final /* synthetic */ zzanu zzc;
    private final /* synthetic */ String zzd;

    public /* synthetic */ zzauz(zzavc r1, zzauu r2, zzanu r3, String r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        this.zza.zzc(this.zzb, this.zzc, this.zzd);
    }
}
