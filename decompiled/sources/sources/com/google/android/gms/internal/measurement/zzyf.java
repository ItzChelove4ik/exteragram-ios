package com.google.android.gms.internal.measurement;

import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public abstract class zzyf {
    private final ConcurrentHashMap zza;

    public zzyf() {
        this.zza = new ConcurrentHashMap();
    }

    public abstract Object zza();

    public final Object zzb(zzyd r4, zzzj r5) {
        ConcurrentHashMap r0 = this.zza;
        Object r1 = r0.get(r4);
        if (r1 == null) goto L5;
        return r1;
    L5:
        Object r3 = zza();
        Object r42 = r0.putIfAbsent(r4, r3);
        if (r42 != null) goto L14;
        int r43 = r5.zza();
        int r02 = 0;
    L8:
        if (r02 >= r43) goto L13;
        if (zzxx.zzf.equals(r5.zzb(r02)) == false) goto L12;
        r5.zzc(r02);
    L12:
        r02 = r02 + 1;
        goto L8
    L13:
        return r3;
    L14:
        return r42;
    }
}
