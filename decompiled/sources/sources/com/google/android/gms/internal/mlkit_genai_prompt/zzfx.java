package com.google.android.gms.internal.mlkit_genai_prompt;

/* loaded from: classes4.dex */
final /* synthetic */ class zzfx implements Runnable {
    private final /* synthetic */ zzdy zza;
    private final /* synthetic */ zzdq zzb;
    private final /* synthetic */ int zzc;
    private final /* synthetic */ zzdo zzd;

    public /* synthetic */ zzfx(zzga r1, zzdy r2, zzdq r3, int r4, zzdo r5) {
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r5;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        int r1 = 1;
        if (this.zzc == 1) goto L5;
        r1 = 0;
    L5:
        zzdo r0 = this.zzd;
        zzdq r2 = this.zzb;
        this.zza.zzb(r2, r1, r0);
    }
}
