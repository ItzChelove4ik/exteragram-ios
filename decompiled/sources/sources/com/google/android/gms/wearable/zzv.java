package com.google.android.gms.wearable;

import com.google.android.gms.wearable.internal.zzao;

/* loaded from: classes5.dex */
final class zzv implements Runnable {
    final /* synthetic */ zzao zza;
    final /* synthetic */ zzaa zzb;

    public zzv(zzaa r1, zzao r2) {
        this.zzb = r1;
        this.zza = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza.onCapabilityChanged(this.zza);
    }
}
