package com.google.android.gms.internal.cast;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
class zzwp extends zzwd {
    private final ExecutorService zza;

    public zzwp(ExecutorService r1) {
        r1.getClass();
        this.zza = r1;
    }

    @Override // java.util.concurrent.ExecutorService
    public final boolean awaitTermination(long r1, TimeUnit r3) {
        return this.zza.awaitTermination(r1, r3);
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable r1) {
        this.zza.execute(r1);
    }

    @Override // java.util.concurrent.ExecutorService
    public final boolean isShutdown() {
        return this.zza.isShutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public final boolean isTerminated() {
        return this.zza.isTerminated();
    }

    @Override // java.util.concurrent.ExecutorService
    public final void shutdown() {
        this.zza.shutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public final List shutdownNow() {
        return this.zza.shutdownNow();
    }

    public final String toString() {
        ExecutorService r0 = this.zza;
        String r4 = super.toString();
        String r02 = String.valueOf(r0);
        int r1 = String.valueOf(r4).length();
        int r12 = r1 + 1;
        StringBuilder r3 = new StringBuilder((r12 + r02.length()) + 1);
        r3.append(r4);
        r3.append("[");
        r3.append(r02);
        r3.append("]");
        return r3.toString();
    }
}
