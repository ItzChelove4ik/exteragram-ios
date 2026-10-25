package com.google.android.gms.measurement.internal;

/* loaded from: classes5.dex */
final /* synthetic */ class zziz implements Runnable {
    private final /* synthetic */ zzjd zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ zzoo zzc;
    private final /* synthetic */ zzgh zzd;

    public /* synthetic */ zziz(zzjd r1, String r2, zzoo r3, zzgh r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        this.zza.zzI(this.zzb, this.zzc, this.zzd);
    }
}
