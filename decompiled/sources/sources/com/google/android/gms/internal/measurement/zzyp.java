package com.google.android.gms.internal.measurement;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
final class zzyp {
    private static final zzyf zza = null;
    private final AtomicBoolean zzb;
    private final AtomicInteger zzc;

    static {
        zza = new zzyo();
    }

    public /* synthetic */ zzyp(byte[] r1) {
        this.zzb = new AtomicBoolean();
        this.zzc = new AtomicInteger();
    }

    public static int zza(zzyq r4, zzyd r5, zzzj r6) {
        zzyp r52 = (zzyp) zza.zzb(r5, r6);
        int r62 = r52.zzc.incrementAndGet();
        if (r4 != zzyq.zzc) goto L5;
    L13:
        return -1;
    L5:
        if (r52.zzb.compareAndSet(false, true) == false) goto L13;
        r4.zzb();     // Catch: Throwable -> L10
        r52.zzb.set(false);
        r52.zzc.addAndGet(-r62);
        return r62 - 1;
    L10:
        th = move-exception;
        r52.zzb.set(false);
        throw th;
    }
}
