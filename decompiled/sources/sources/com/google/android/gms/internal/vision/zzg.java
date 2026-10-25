package com.google.android.gms.internal.vision;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
final class zzg implements zzf {
    private zzg() {
    }

    public /* synthetic */ zzg(zzh r1) {
        this();
    }

    @Override // com.google.android.gms.internal.vision.zzf
    public final ExecutorService zza(int r9, int r10) {
        ThreadFactory r7 = Executors.defaultThreadFactory();
        LinkedBlockingQueue r6 = new LinkedBlockingQueue();
        ThreadPoolExecutor r0 = new ThreadPoolExecutor(2, 2, 60, TimeUnit.SECONDS, r6, r7);
        r0.allowCoreThreadTimeOut(true);
        return Executors.unconfigurableExecutorService(r0);
    }
}
