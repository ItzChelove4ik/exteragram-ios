package com.google.android.gms.ads.identifier;

import com.google.android.gms.common.util.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@VisibleForTesting
/* loaded from: classes4.dex */
final class zzb extends Thread {
    final CountDownLatch zza;
    boolean zzb;
    private final WeakReference<AdvertisingIdClient> zzc;
    private final long zzd;

    public zzb(AdvertisingIdClient r2, long r3) {
        this.zzc = new WeakReference(r2);
        this.zzd = r3;
        this.zza = new CountDownLatch(1);
        this.zzb = false;
        start();
    }

    private final void zza() {
        AdvertisingIdClient r0 = this.zzc.get();
        if (r0 == null) goto L6;
        r0.zza();
        this.zzb = true;
        return;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
    L6:
        zza();
        return;
    L3:
        if (this.zza.await(this.zzd, TimeUnit.MILLISECONDS) == true) goto L10;
        zza();     // Catch: InterruptedException -> L6
        return;
    }
}
