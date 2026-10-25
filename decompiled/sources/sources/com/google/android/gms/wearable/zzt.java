package com.google.android.gms.wearable;

import com.google.android.gms.wearable.internal.zzgm;

/* loaded from: classes5.dex */
final class zzt implements Runnable {
    final /* synthetic */ zzgm zza;
    final /* synthetic */ zzaa zzb;

    public zzt(zzaa r1, zzgm r2) {
        this.zzb = r1;
        this.zza = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza.onPeerDisconnected(this.zza);
    }
}
