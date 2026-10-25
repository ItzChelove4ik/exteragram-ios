package com.google.android.gms.wearable;

/* loaded from: classes5.dex */
final class zzw implements Runnable {
    final /* synthetic */ com.google.android.gms.wearable.internal.zzl zza;
    final /* synthetic */ zzaa zzb;

    public zzw(zzaa r1, com.google.android.gms.wearable.internal.zzl r2) {
        this.zzb = r1;
        this.zza = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza.onNotificationReceived(this.zza);
    }
}
