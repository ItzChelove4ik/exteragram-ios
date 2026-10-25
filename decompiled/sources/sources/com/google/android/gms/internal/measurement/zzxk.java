package com.google.android.gms.internal.measurement;

import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes4.dex */
final class zzxk extends zzyq {
    private static final zzyf zza = null;
    private final AtomicLong zzb;

    static {
        zza = new zzxj();
    }

    public zzxk() {
        this.zzb = new AtomicLong(2147483647L);
    }

    public static zzyq zza(zzzj r4, zzyd r5) {
        Integer r0 = (Integer) r4.zzd(zzxx.zzb);
        if (r0 != null) goto L6;
        return null;
    L6:
        zzxk r42 = (zzxk) zza.zzb(r5, r4);
        AtomicLong r52 = r42.zzb;
        if (r52.incrementAndGet() < r0.intValue()) goto L10;
        return r42;
    L10:
        return zzyq.zzc;
    }

    @Override // com.google.android.gms.internal.measurement.zzyq
    public final void zzb() {
        this.zzb.set(0);
    }
}
