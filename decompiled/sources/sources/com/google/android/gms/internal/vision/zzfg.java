package com.google.android.gms.internal.vision;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
final class zzfg {
    private final ConcurrentHashMap<zzff, List<Throwable>> zza;
    private final ReferenceQueue<Throwable> zzb;

    public zzfg() {
        this.zza = new ConcurrentHashMap(16, 0.75f, 10);
        this.zzb = new ReferenceQueue();
    }

    public final List<Throwable> zza(Throwable r3, boolean r4) {
        Reference<? extends Throwable> r0 = this.zzb.poll();
    L3:
        if (r0 == null) goto L5;
        this.zza.remove(r0);
        r0 = this.zzb.poll();
        goto L3
    L5:
        zzff r02 = new zzff(r3, null);
        List<Throwable> r03 = this.zza.get(r02);
        if (r4 == true) goto L8;
        return r03;
    L8:
        if (r03 == null) goto L10;
        return r03;
    L10:
        Vector r42 = new Vector(2);
        List<Throwable> r2 = this.zza.putIfAbsent(new zzff(r3, this.zzb), r42);
        if (r2 != null) goto L13;
        return r42;
    L13:
        return r2;
    }
}
