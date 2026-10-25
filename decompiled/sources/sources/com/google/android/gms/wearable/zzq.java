package com.google.android.gms.wearable;

import com.google.android.gms.common.data.DataHolder;

/* loaded from: classes5.dex */
final class zzq implements Runnable {
    final /* synthetic */ DataHolder zza;
    final /* synthetic */ zzaa zzb;

    public zzq(zzaa r1, DataHolder r2) {
        this.zzb = r1;
        this.zza = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        DataEventBuffer r0 = new DataEventBuffer(this.zza);
        this.zzb.zza.onDataChanged(r0);     // Catch: Throwable -> L6
        r0.release();
        return;
    L6:
        th = move-exception;
        r0.release();
        throw th;
    }
}
