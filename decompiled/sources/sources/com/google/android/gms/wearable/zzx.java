package com.google.android.gms.wearable;

import com.google.android.gms.wearable.internal.zzi;

/* loaded from: classes5.dex */
final class zzx implements Runnable {
    final /* synthetic */ zzi zza;
    final /* synthetic */ zzaa zzb;

    public zzx(zzaa r1, zzi r2) {
        this.zzb = r1;
        this.zza = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza.onEntityUpdate(this.zza);
    }
}
