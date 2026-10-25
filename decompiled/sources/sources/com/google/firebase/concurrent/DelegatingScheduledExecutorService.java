package com.google.firebase.concurrent;

import androidx.camera.camera2.config.UseCaseGraphContext$$ExternalSyntheticAutoCloseableForwarder1;
import com.google.firebase.concurrent.DelegatingScheduledFuture;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
class DelegatingScheduledExecutorService implements ScheduledExecutorService, AutoCloseable {
    private final ExecutorService delegate;
    private final ScheduledExecutorService scheduler;

    public static /* synthetic */ ScheduledFuture $r8$lambda$0aEWsbvkxIe7S3fWfUsBDaHnCnM(DelegatingScheduledExecutorService r2, Callable r3, long r4, TimeUnit r6, DelegatingScheduledFuture.Completer r7) {
        return r2.scheduler.schedule(new DelegatingScheduledExecutorService$$ExternalSyntheticLambda6(r2, r3, r7), r4, r6);
    }

    /* renamed from: $r8$lambda$2be14DvaopZ--g6F8z1-fD85XVQ, reason: not valid java name */
    public static /* synthetic */ ScheduledFuture m1923$r8$lambda$2be14DvaopZg6F8z1fD85XVQ(DelegatingScheduledExecutorService r2, Runnable r3, long r4, long r6, TimeUnit r8, DelegatingScheduledFuture.Completer r9) {
        return r2.scheduler.scheduleWithFixedDelay(new DelegatingScheduledExecutorService$$ExternalSyntheticLambda7(r2, r3, r9), r4, r6, r8);
    }

    public static /* synthetic */ ScheduledFuture $r8$lambda$7LIXIr67GGhc0SzKH31CvqIp7a0(DelegatingScheduledExecutorService r2, Runnable r3, long r4, long r6, TimeUnit r8, DelegatingScheduledFuture.Completer r9) {
        return r2.scheduler.scheduleAtFixedRate(new DelegatingScheduledExecutorService$$ExternalSyntheticLambda4(r2, r3, r9), r4, r6, r8);
    }

    public static /* synthetic */ void $r8$lambda$DPkf5Azy2quQUi97kfFjV0McX98(Callable r0, DelegatingScheduledFuture.Completer r1) {
        r1.set(r0.call());     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        r1.setException(e);
    }

    public static /* synthetic */ void $r8$lambda$EbEzOZCjWOxrxgoPIN3wANG2ziw(Runnable r0, DelegatingScheduledFuture.Completer r1) {
        r0.run();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        r1.setException(e);
        throw e;
    }

    public static /* synthetic */ void $r8$lambda$I24AT8SBRztHUrRuo_uZs9aK0oo(DelegatingScheduledExecutorService r1, Runnable r2, DelegatingScheduledFuture.Completer r3) {
        r1.delegate.execute(new DelegatingScheduledExecutorService$$ExternalSyntheticLambda10(r2, r3));
    }

    public static /* synthetic */ Future $r8$lambda$KzftyNrykfqSVPnZs34fOZKBoLI(DelegatingScheduledExecutorService r1, Callable r2, DelegatingScheduledFuture.Completer r3) {
        return r1.delegate.submit(new DelegatingScheduledExecutorService$$ExternalSyntheticLambda9(r2, r3));
    }

    public static /* synthetic */ void $r8$lambda$M3Tx009A2yliSiUlG7LADIaVIu4(Runnable r0, DelegatingScheduledFuture.Completer r1) {
        r0.run();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        r1.setException(e);
    }

    public static /* synthetic */ void $r8$lambda$bhIrgsP1VT57Fl0P5M_YxDzel9c(DelegatingScheduledExecutorService r1, Runnable r2, DelegatingScheduledFuture.Completer r3) {
        r1.delegate.execute(new DelegatingScheduledExecutorService$$ExternalSyntheticLambda8(r2, r3));
    }

    public static /* synthetic */ void $r8$lambda$eeSfYQ6BJhS7zSXRLrNL2MRv5Cc(Runnable r0, DelegatingScheduledFuture.Completer r1) {
        r0.run();     // Catch: Exception -> L4
        r1.set(null);     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        r1.setException(e);
    }

    /* renamed from: $r8$lambda$gHBCMQji5zmK9qP7lOpQ-WXdyRI, reason: not valid java name */
    public static /* synthetic */ void m1924$r8$lambda$gHBCMQji5zmK9qP7lOpQWXdyRI(DelegatingScheduledExecutorService r1, Runnable r2, DelegatingScheduledFuture.Completer r3) {
        r1.delegate.execute(new DelegatingScheduledExecutorService$$ExternalSyntheticLambda11(r2, r3));
    }

    /* renamed from: $r8$lambda$qgPjh0WOMoXy2SB0v0bXtwLQ-yo, reason: not valid java name */
    public static /* synthetic */ ScheduledFuture m1925$r8$lambda$qgPjh0WOMoXy2SB0v0bXtwLQyo(DelegatingScheduledExecutorService r2, Runnable r3, long r4, TimeUnit r6, DelegatingScheduledFuture.Completer r7) {
        return r2.scheduler.schedule(new DelegatingScheduledExecutorService$$ExternalSyntheticLambda5(r2, r3, r7), r4, r6);
    }

    public DelegatingScheduledExecutorService(ExecutorService r1, ScheduledExecutorService r2) {
        this.delegate = r1;
        this.scheduler = r2;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long r1, TimeUnit r3) {
        return this.delegate.awaitTermination(r1, r3);
    }

    @Override // java.lang.AutoCloseable
    public /* synthetic */ void close() {
        UseCaseGraphContext$$ExternalSyntheticAutoCloseableForwarder1.m(this);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable r1) {
        this.delegate.execute(r1);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> r1) {
        return this.delegate.invokeAll(r1);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> r1, long r2, TimeUnit r4) {
        return this.delegate.invokeAll(r1, r2, r4);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> r1) {
        return (T) this.delegate.invokeAny(r1);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> r1, long r2, TimeUnit r4) {
        return (T) this.delegate.invokeAny(r1, r2, r4);
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return this.delegate.isShutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return this.delegate.isTerminated();
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> schedule(Runnable r8, long r9, TimeUnit r11) {
        return new DelegatingScheduledFuture(new DelegatingScheduledExecutorService$$ExternalSyntheticLambda0(this, r8, r9, r11));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public <V> ScheduledFuture<V> schedule(Callable<V> r8, long r9, TimeUnit r11) {
        return new DelegatingScheduledFuture(new DelegatingScheduledExecutorService$$ExternalSyntheticLambda3(this, r8, r9, r11));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable r10, long r11, long r13, TimeUnit r15) {
        return new DelegatingScheduledFuture(new DelegatingScheduledExecutorService$$ExternalSyntheticLambda1(this, r10, r11, r13, r15));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable r10, long r11, long r13, TimeUnit r15) {
        return new DelegatingScheduledFuture(new DelegatingScheduledExecutorService$$ExternalSyntheticLambda2(this, r10, r11, r13, r15));
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        throw new UnsupportedOperationException("Shutting down is not allowed.");
    }

    @Override // java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException("Shutting down is not allowed.");
    }

    @Override // java.util.concurrent.ExecutorService
    public Future<?> submit(Runnable r1) {
        return this.delegate.submit(r1);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Runnable r1, T r2) {
        return this.delegate.submit(r1, r2);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Callable<T> r1) {
        return this.delegate.submit(r1);
    }
}
