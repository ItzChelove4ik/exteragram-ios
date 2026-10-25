package com.google.android.gms.internal.vision;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes5.dex */
final class zzky {
    private static final zzky zza = null;
    private final zzlf zzb;
    private final ConcurrentMap<Class<?>, zzlc<?>> zzc;

    static {
        zza = new zzky();
    }

    private zzky() {
        this.zzc = new ConcurrentHashMap();
        this.zzb = new zzkb();
    }

    public static zzky zza() {
        return zza;
    }

    public final <T> zzlc<T> zza(Class<T> r3) {
        zzjf.zza(r3, "messageType");
        zzlc<T> r1 = (zzlc) this.zzc.get(r3);
        if (r1 != null) goto L7;
        r1 = this.zzb.zza(r3);
        zzjf.zza(r3, "messageType");
        zzjf.zza(r1, "schema");
        zzlc<T> r2 = (zzlc) this.zzc.putIfAbsent(r3, r1);
        if (r2 == null) goto L7;
        return r2;
    L7:
        return r1;
    }

    public final <T> zzlc<T> zza(T r1) {
        return zza(r1.getClass());
    }
}
