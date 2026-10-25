package com.google.android.gms.cast;

/* loaded from: classes4.dex */
final /* synthetic */ class zzbg implements Runnable {
    private final /* synthetic */ zzbl zza;
    private final /* synthetic */ int zzb;

    public /* synthetic */ zzbg(zzbl r1, int r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        this.zza.zza.zzS().onApplicationDisconnected(this.zzb);
    }
}
