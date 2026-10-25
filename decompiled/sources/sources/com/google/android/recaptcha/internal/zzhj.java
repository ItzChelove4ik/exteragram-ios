package com.google.android.recaptcha.internal;

import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public abstract class zzhj {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final ConcurrentHashMap zzb = null;

    static {
        zzb = new ConcurrentHashMap();
    }

    public static final void zza(int r4, long r5) {
        ConcurrentHashMap r0 = zzb;
        Integer r42 = Integer.valueOf(r4);
        Object r1 = r0.get(r42);
        if (r1 != null) goto L5;
        r1 = new zzhi();
    L5:
        zzhi r12 = (zzhi) r1;
        r12.zzb(r12.zza() + 1);
        r12.zzd(r12.zzc() + r5);
        r12.zzf(Math.max(r5, r12.zze()));
        r0.put(r42, r12);
    }
}
