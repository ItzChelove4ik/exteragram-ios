package com.google.android.gms.internal.measurement;

import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
final class zzafl {
    private static final zzafl zza = null;
    private final zzaet zzb;
    private final ConcurrentHashMap zzc;

    static {
        zza = new zzafl();
    }

    private zzafl() {
        this.zzc = new ConcurrentHashMap();
        this.zzb = new zzaet();
    }

    public static zzafl zza() {
        return zza;
    }

    private <T> zzafp<T> zzc(Class<T> r2) {
        ConcurrentHashMap r0 = this.zzc;
        zzafp<T> r1 = this.zzb.zza(r2);
        zzafp<T> r22 = (zzafp) r0.putIfAbsent(r2, r1);
        if (r22 == null) goto L5;
        return r22;
    L5:
        return r1;
    }

    public final zzafp zzb(Class r2) {
        Object r0 = this.zzc.get(r2);
        if (r0 != null) goto L7;
        return zzc(r2);
    L7:
        return (zzafp) r0;
    }
}
