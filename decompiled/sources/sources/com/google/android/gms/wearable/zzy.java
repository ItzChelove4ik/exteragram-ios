package com.google.android.gms.wearable;

import com.google.android.gms.wearable.internal.zzbf;

/* loaded from: classes5.dex */
final class zzy implements Runnable {
    final /* synthetic */ zzbf zza;
    final /* synthetic */ zzaa zzb;

    public zzy(zzaa r1, zzbf r2) {
        this.zzb = r1;
        this.zza = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zza(this.zzb.zza);
        this.zza.zza(WearableListenerService.zzd(this.zzb.zza));
    }
}
