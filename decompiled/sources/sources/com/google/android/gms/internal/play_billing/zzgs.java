package com.google.android.gms.internal.play_billing;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes5.dex */
final class zzgs {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final zzgs zzb = null;
    private final zzgw zzc;
    private final ConcurrentMap zzd;

    static {
        zzb = new zzgs();
    }

    private zzgs() {
        this.zzd = new ConcurrentHashMap();
        this.zzc = new zzgd();
    }

    public static zzgs zza() {
        return zzb;
    }

    public final zzgv zzb(Class r4) {
        zzfo.zzc(r4, "messageType");
        ConcurrentMap r1 = this.zzd;
        zzgv r2 = (zzgv) r1.get(r4);
        if (r2 != null) goto L8;
        zzgv r3 = this.zzc.zza(r4);
        zzfo.zzc(r4, "messageType");
        zzgv r42 = (zzgv) r1.putIfAbsent(r4, r3);
        if (r42 == null) goto L7;
        return r42;
    L7:
        return r3;
    L8:
        return r2;
    }
}
