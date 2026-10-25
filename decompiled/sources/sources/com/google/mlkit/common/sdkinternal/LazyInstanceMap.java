package com.google.mlkit.common.sdkinternal;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class LazyInstanceMap<K, V> {
    private final Map zza;

    public LazyInstanceMap() {
        this.zza = new HashMap();
    }

    public abstract V create(K r1);

    public V get(K r3) {
        Map r0 = this.zza;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.zza.containsKey(r3) == false) goto L11;
        V r2 = (V) this.zza.get(r3);
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r2;
    L11:
        V r1 = create(r3);     // Catch: Throwable -> L9
        this.zza.put(r3, r1);     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r1;
    }
}
