package com.google.android.gms.internal.mlkit_common;

import androidx.camera.camera2.config.UseCaseGraphContext$$ExternalSyntheticAutoCloseableForwarder1;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public abstract class zzav extends zzx implements ExecutorService, AutoCloseable {
    public zzav() {
    }

    @Override // java.util.concurrent.ExecutorService
    public final boolean awaitTermination(long r1, TimeUnit r3) {
        return zzb().awaitTermination(r1, r3);
    }

    @Override // java.lang.AutoCloseable
    public /* synthetic */ void close() {
        UseCaseGraphContext$$ExternalSyntheticAutoCloseableForwarder1.m(this);
    }

    @Override // java.util.concurrent.ExecutorService
    public final List invokeAll(Collection r1) {
        return zzb().invokeAll(r1);
    }

    @Override // java.util.concurrent.ExecutorService
    public final List invokeAll(Collection r1, long r2, TimeUnit r4) {
        return zzb().invokeAll(r1, r2, r4);
    }

    @Override // java.util.concurrent.ExecutorService
    public final Object invokeAny(Collection r1) {
        return zzb().invokeAny(r1);
    }

    @Override // java.util.concurrent.ExecutorService
    public final Object invokeAny(Collection r1, long r2, TimeUnit r4) {
        return zzb().invokeAny(r1, r2, r4);
    }

    @Override // java.util.concurrent.ExecutorService
    public final boolean isShutdown() {
        return zzb().isShutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public final boolean isTerminated() {
        return zzb().isTerminated();
    }

    @Override // java.util.concurrent.ExecutorService
    public final void shutdown() {
        zzb().shutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public final List shutdownNow() {
        return zzb().shutdownNow();
    }

    @Override // java.util.concurrent.ExecutorService
    public final Future submit(Runnable r1) {
        return zzb().submit(r1);
    }

    @Override // java.util.concurrent.ExecutorService
    public final Future submit(Runnable r1, Object r2) {
        return zzb().submit(r1, r2);
    }

    @Override // java.util.concurrent.ExecutorService
    public final Future submit(Callable r1) {
        return zzb().submit(r1);
    }

    public abstract ExecutorService zzb();
}
