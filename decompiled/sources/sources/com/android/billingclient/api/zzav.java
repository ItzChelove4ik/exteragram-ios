package com.android.billingclient.api;

import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
final class zzav implements ThreadFactory {
    private final ThreadFactory zza;
    private final AtomicInteger zzb;

    public zzav(BillingClientImpl r2) {
        Objects.requireNonNull(r2);
        this.zza = Executors.defaultThreadFactory();
        this.zzb = new AtomicInteger(1);
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable r3) {
        AtomicInteger r0 = this.zzb;
        Thread r2 = this.zza.newThread(r3);
        r2.setName("PlayBillingLibrary-" + r0.getAndIncrement());
        return r2;
    }
}
