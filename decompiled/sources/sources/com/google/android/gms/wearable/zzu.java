package com.google.android.gms.wearable;

import java.util.List;

/* loaded from: classes5.dex */
final class zzu implements Runnable {
    final /* synthetic */ List zza;
    final /* synthetic */ zzaa zzb;

    public zzu(zzaa r1, List r2) {
        this.zzb = r1;
        this.zza = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza.onConnectedNodes(this.zza);
    }
}
