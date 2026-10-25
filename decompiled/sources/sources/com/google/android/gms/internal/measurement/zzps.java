package com.google.android.gms.internal.measurement;

import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public final class zzps {
    private final AtomicInteger zza;

    public zzps() {
        this.zza = new AtomicInteger();
    }

    public final int zza() {
        return this.zza.get();
    }

    public final void zzb() {
        this.zza.incrementAndGet();
    }
}
