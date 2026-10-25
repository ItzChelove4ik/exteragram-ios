package com.google.android.gms.wearable;

import com.google.android.gms.wearable.internal.zzfx;

/* loaded from: classes5.dex */
final class zzr implements Runnable {
    final /* synthetic */ zzfx zza;
    final /* synthetic */ zzaa zzb;

    public zzr(zzaa r1, zzfx r2) {
        this.zzb = r1;
        this.zza = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza.onMessageReceived(this.zza);
    }
}
