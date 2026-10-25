package com.google.android.gms.internal.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
final class zam implements zal {
    public /* synthetic */ zam(byte[] r1) {
    }

    @Override // com.google.android.gms.internal.base.zal
    public final ExecutorService zab(int r9, ThreadFactory r10, int r11) {
        LinkedBlockingQueue r6 = new LinkedBlockingQueue();
        ThreadPoolExecutor r0 = new ThreadPoolExecutor(r9, r9, 60, TimeUnit.SECONDS, r6, r10);
        r0.allowCoreThreadTimeOut(true);
        return Executors.unconfigurableExecutorService(r0);
    }
}
