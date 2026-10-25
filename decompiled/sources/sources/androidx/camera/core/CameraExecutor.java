package androidx.camera.core;

import android.os.Process;
import androidx.camera.camera2.config.UseCaseGraphContext$$ExternalSyntheticAutoCloseableForwarder1;
import androidx.camera.core.impl.CameraFactory;
import androidx.core.util.Preconditions;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes3.dex */
public class CameraExecutor implements Executor, ScheduledExecutorService, AutoCloseable {
    private static final ThreadFactory THREAD_FACTORY = null;
    private final Object mExecutorLock;
    private ScheduledThreadPoolExecutor mThreadPoolExecutor;

    /* renamed from: androidx.camera.core.CameraExecutor$1, reason: invalid class name */
    public class AnonymousClass1 implements ThreadFactory {
        private final AtomicInteger mThreadId;

        public static /* synthetic */ void $r8$lambda$aeeeXmCRyKT8UqtEOW_p1AJz2G8(Runnable r1) {
            Process.setThreadPriority(-3);
            r1.run();
        }

        public AnonymousClass1() {
            this.mThreadId = new AtomicInteger(0);
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable r3) {
            Thread r0 = new Thread(new CameraExecutor$1$$ExternalSyntheticLambda0(r3));
            r0.setPriority(7);
            r0.setName(String.format(Locale.US, "CameraX-core_camera_%d", new Object[]{Integer.valueOf(this.mThreadId.getAndIncrement())}));
            return r0;
        }
    }

    /* renamed from: $r8$lambda$Wehj_cm1PBPepIY3nXav-82LtWw, reason: not valid java name */
    public static /* synthetic */ void m582$r8$lambda$Wehj_cm1PBPepIY3nXav82LtWw(Runnable r0, ThreadPoolExecutor r1) {
        Logger.w("CameraExecutor", "A rejected execution occurred in CameraExecutor!");
    }

    static {
        THREAD_FACTORY = new AnonymousClass1();
    }

    public CameraExecutor() {
        this.mExecutorLock = new Object();
        this.mThreadPoolExecutor = createExecutor();
    }

    private static ScheduledThreadPoolExecutor createExecutor() {
        ScheduledThreadPoolExecutor r0 = new ScheduledThreadPoolExecutor(1, THREAD_FACTORY);
        r0.setKeepAliveTime(0, TimeUnit.MILLISECONDS);
        r0.setRejectedExecutionHandler(new CameraExecutor$$ExternalSyntheticLambda0());
        return r0;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long r2, TimeUnit r4) {
        Object r0 = this.mExecutorLock;
        monitor-enter(r0);
        boolean r1 = this.mThreadPoolExecutor.awaitTermination(r2, r4);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // java.lang.AutoCloseable
    public /* synthetic */ void close() {
        UseCaseGraphContext$$ExternalSyntheticAutoCloseableForwarder1.m(this);
    }

    public void deinit() {
        Object r0 = this.mExecutorLock;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (this.mThreadPoolExecutor.isShutdown() == true) goto L9;
        this.mThreadPoolExecutor.shutdown();     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable r2) {
        Preconditions.checkNotNull(r2);
        Object r0 = this.mExecutorLock;
        monitor-enter(r0);
        this.mThreadPoolExecutor.execute(r2);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public void init(CameraFactory r3) {
        Preconditions.checkNotNull(r3);
        Object r0 = this.mExecutorLock;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (this.mThreadPoolExecutor.isShutdown() == false) goto L9;
        this.mThreadPoolExecutor = createExecutor();     // Catch: Throwable -> L7
    L9:
        ScheduledThreadPoolExecutor r2 = this.mThreadPoolExecutor;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        r2.setCorePoolSize(Math.max(1, r3.getAvailableCameraIds().size()));
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> r2) {
        Object r0 = this.mExecutorLock;
        monitor-enter(r0);
        List<Future<T>> r1 = this.mThreadPoolExecutor.invokeAll(r2);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> r2, long r3, TimeUnit r5) {
        Object r0 = this.mExecutorLock;
        monitor-enter(r0);
        List<Future<T>> r1 = this.mThreadPoolExecutor.invokeAll(r2, r3, r5);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> r2) {
        Object r0 = this.mExecutorLock;
        monitor-enter(r0);
        T r1 = (T) this.mThreadPoolExecutor.invokeAny(r2);
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> r2, long r3, TimeUnit r5) {
        Object r0 = this.mExecutorLock;
        monitor-enter(r0);
        T r1 = (T) this.mThreadPoolExecutor.invokeAny(r2, r3, r5);
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        Object r0 = this.mExecutorLock;
        monitor-enter(r0);
        boolean r1 = this.mThreadPoolExecutor.isShutdown();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        Object r0 = this.mExecutorLock;
        monitor-enter(r0);
        boolean r1 = this.mThreadPoolExecutor.isTerminated();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> schedule(Runnable r2, long r3, TimeUnit r5) {
        Object r0 = this.mExecutorLock;
        monitor-enter(r0);
        ScheduledFuture<?> r1 = this.mThreadPoolExecutor.schedule(r2, r3, r5);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public <V> ScheduledFuture<V> schedule(Callable<V> r2, long r3, TimeUnit r5) {
        Object r0 = this.mExecutorLock;
        monitor-enter(r0);
        ScheduledFuture<V> r1 = this.mThreadPoolExecutor.schedule(r2, r3, r5);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable r3, long r4, long r6, TimeUnit r8) {
        Object r1 = this.mExecutorLock;
        monitor-enter(r1);
        ScheduledFuture<?> r2 = this.mThreadPoolExecutor.scheduleAtFixedRate(r3, r4, r6, r8);     // Catch: Throwable -> L7
        monitor-exit(r1);     // Catch: Throwable -> L7
        return r2;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable r3, long r4, long r6, TimeUnit r8) {
        Object r1 = this.mExecutorLock;
        monitor-enter(r1);
        ScheduledFuture<?> r2 = this.mThreadPoolExecutor.scheduleWithFixedDelay(r3, r4, r6, r8);     // Catch: Throwable -> L7
        monitor-exit(r1);     // Catch: Throwable -> L7
        return r2;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        Object r0 = this.mExecutorLock;
        monitor-enter(r0);
        this.mThreadPoolExecutor.shutdown();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        Object r0 = this.mExecutorLock;
        monitor-enter(r0);
        List<Runnable> r1 = this.mThreadPoolExecutor.shutdownNow();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // java.util.concurrent.ExecutorService
    public Future<?> submit(Runnable r2) {
        Object r0 = this.mExecutorLock;
        monitor-enter(r0);
        Future<?> r1 = this.mThreadPoolExecutor.submit(r2);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Runnable r2, T r3) {
        Object r0 = this.mExecutorLock;
        monitor-enter(r0);
        Future<T> r1 = this.mThreadPoolExecutor.submit(r2, r3);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Callable<T> r2) {
        Object r0 = this.mExecutorLock;
        monitor-enter(r0);
        Future<T> r1 = this.mThreadPoolExecutor.submit(r2);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }
}
