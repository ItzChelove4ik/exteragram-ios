package com.google.android.gms.cast;

/* loaded from: classes4.dex */
final /* synthetic */ class zzbh implements Runnable {
    private final /* synthetic */ zzbl zza;
    private final /* synthetic */ com.google.android.gms.cast.internal.zzac zzb;

    public /* synthetic */ zzbh(zzbl r1, com.google.android.gms.cast.internal.zzac r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        this.zza.zza.zzE(this.zzb);
    }
}
