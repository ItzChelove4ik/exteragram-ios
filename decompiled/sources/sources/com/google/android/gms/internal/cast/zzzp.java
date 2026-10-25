package com.google.android.gms.internal.cast;

import c.f$$ExternalSyntheticBUOutline2;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes4.dex */
final class zzzp {
    private static final zzzp zza = null;
    private final zzzt zzb;
    private final ConcurrentMap zzc;

    static {
        zza = new zzzp();
    }

    private zzzp() {
        this.zzc = new ConcurrentHashMap();
        this.zzb = new zzza();
    }

    public static zzzp zza() {
        return zza;
    }

    public final zzzs zzb(Class r3) {
        byte[] r0 = zzym.zzb;
        if (r3 == null) goto L11;
        ConcurrentMap r02 = this.zzc;
        zzzs r1 = (zzzs) r02.get(r3);
        if (r1 != null) goto L10;
        zzzs r2 = this.zzb.zza(r3);
        zzzs r32 = (zzzs) r02.putIfAbsent(r3, r2);
        if (r32 == null) goto L9;
        return r32;
    L9:
        return r2;
    L10:
        return r1;
    L11:
        f$$ExternalSyntheticBUOutline2.m("messageType");
        return null;
    }
}
