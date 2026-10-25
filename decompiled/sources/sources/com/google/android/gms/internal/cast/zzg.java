package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
final /* synthetic */ class zzg implements Runnable {
    private final /* synthetic */ zzj zza;
    private final /* synthetic */ zzqr zzb;
    private final /* synthetic */ int zzc;

    public /* synthetic */ zzg(zzj r1, zzqr r2, int r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        this.zza.zze(this.zzb, this.zzc);
    }
}
