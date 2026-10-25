package com.google.android.gms.internal.measurement;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
final class zzyt extends zzyq {
    private static final zzyf zzb = null;
    private static final ThreadLocal zze = null;
    final AtomicInteger zza;

    static {
        zzb = new zzyr();
        zze = new zzys();
    }

    public zzyt() {
        this.zza = new AtomicInteger();
    }

    public static zzyq zza(zzzj r2, zzyd r3) {
        Integer r0 = (Integer) r2.zzd(zzxx.zzc);
        if (r0 != null) goto L5;
        return null;
    L5:
        if (r0.intValue() <= 0) goto L17;
        zzyt r22 = (zzyt) zzb.zzb(r3, r2);
        int r32 = r0.intValue();
        if (((Random) zze.get()).nextInt(r32) != 0) goto L10;
        int r33 = r22.zza.incrementAndGet();
    L11:
        if (r33 <= 0) goto L14;
        return r22;
    L14:
        return zzyq.zzc;
    L10:
        r33 = r22.zza.get();
        goto L11
    L17:
        return null;
    }

    @Override // com.google.android.gms.internal.measurement.zzyq
    public final void zzb() {
        this.zza.decrementAndGet();
    }
}
