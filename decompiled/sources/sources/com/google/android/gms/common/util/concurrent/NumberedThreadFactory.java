package com.google.android.gms.common.util.concurrent;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public class NumberedThreadFactory implements ThreadFactory {
    private final String zza;
    private final AtomicInteger zzb;
    private final ThreadFactory zzc;

    public NumberedThreadFactory(String r2) {
        this.zzb = new AtomicInteger();
        this.zzc = Executors.defaultThreadFactory();
        Preconditions.checkNotNull(r2, "Name must not be null");
        this.zza = r2;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable r5) {
        zza r0 = new zza(r5, 0);
        Thread r52 = this.zzc.newThread(r0);
        int r02 = this.zzb.getAndIncrement();
        int r1 = String.valueOf(r02).length();
        String r4 = this.zza;
        StringBuilder r2 = new StringBuilder(((r4.length() + 1) + r1) + 1);
        r2.append(r4);
        r2.append("[");
        r2.append(r02);
        r2.append("]");
        r52.setName(r2.toString());
        return r52;
    }
}
