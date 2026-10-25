package androidx.camera.core.impl.utils.executor;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import androidx.camera.camera2.config.UseCaseGraphContext$$ExternalSyntheticAutoCloseableForwarder1;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes3.dex */
final class HandlerScheduledExecutorService extends AbstractExecutorService implements ScheduledExecutorService, AutoCloseable {
    private static ThreadLocal<ScheduledExecutorService> sThreadLocalInstance;
    private final Handler mHandler;

    /* renamed from: androidx.camera.core.impl.utils.executor.HandlerScheduledExecutorService$1, reason: invalid class name */
    public class AnonymousClass1 extends ThreadLocal<ScheduledExecutorService> {
        public AnonymousClass1() {
        }

        @Override // java.lang.ThreadLocal
        public /* bridge */ /* synthetic */ ScheduledExecutorService initialValue() {
            return initialValue2();
        }

        @Override // java.lang.ThreadLocal
        /* renamed from: initialValue, reason: avoid collision after fix types in other method */
        public ScheduledExecutorService initialValue2() {
            if (Looper.myLooper() != Looper.getMainLooper()) goto L7;
            return CameraXExecutors.mainThreadExecutor();
        L7:
            if (Looper.myLooper() != null) goto L9;
            return null;
        L9:
            return new HandlerScheduledExecutorService(new Handler(Looper.myLooper()));
        }
    }

    /* renamed from: androidx.camera.core.impl.utils.executor.HandlerScheduledExecutorService$2, reason: invalid class name */
    public class AnonymousClass2 implements Callable<Void> {
        final /* synthetic */ HandlerScheduledExecutorService this$0;
        final /* synthetic */ Runnable val$command;

        public AnonymousClass2(HandlerScheduledExecutorService r1, Runnable r2) {
            this.this$0 = r1;
            this.val$command = r2;
        }

        @Override // java.util.concurrent.Callable
        public /* bridge */ /* synthetic */ Void call() {
            return call2();
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: call, reason: avoid collision after fix types in other method */
        public Void call2() {
            this.val$command.run();
            return null;
        }
    }

    public static class HandlerScheduledFuture<V> implements RunnableScheduledFuture<V> {
        final AtomicReference<CallbackToFutureAdapter.Completer<V>> mCompleter;
        private final ListenableFuture<V> mDelegate;
        private final long mRunAtMillis;
        private final Callable<V> mTask;

        /* renamed from: androidx.camera.core.impl.utils.executor.HandlerScheduledExecutorService$HandlerScheduledFuture$1, reason: invalid class name */
        public class AnonymousClass1 implements CallbackToFutureAdapter.Resolver<V> {
            final /* synthetic */ HandlerScheduledFuture this$0;
            final /* synthetic */ Handler val$handler;
            final /* synthetic */ Callable val$task;

            /* renamed from: androidx.camera.core.impl.utils.executor.HandlerScheduledExecutorService$HandlerScheduledFuture$1$1, reason: invalid class name and collision with other inner class name */
            public class RunnableC00061 implements Runnable {
                final /* synthetic */ AnonymousClass1 this$1;

                public RunnableC00061(AnonymousClass1 r1) {
                    this.this$1 = r1;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (this.this$1.this$0.mCompleter.getAndSet(null) == null) goto L6;
                    AnonymousClass1 r2 = this.this$1;
                    r2.val$handler.removeCallbacks(r2.this$0);
                    return;
                }
            }

            public AnonymousClass1(HandlerScheduledFuture r1, Handler r2, Callable r3) {
                this.this$0 = r1;
                this.val$handler = r2;
                this.val$task = r3;
            }

            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public Object attachCompleter(CallbackToFutureAdapter.Completer<V> r3) {
                r3.addCancellationListener(new RunnableC00061(this), CameraXExecutors.directExecutor());
                this.this$0.mCompleter.set(r3);
                return "HandlerScheduledFuture-" + this.val$task.toString();
            }
        }

        public HandlerScheduledFuture(Handler r3, long r4, Callable<V> r6) {
            this.mCompleter = new AtomicReference(null);
            this.mRunAtMillis = r4;
            this.mTask = r6;
            this.mDelegate = CallbackToFutureAdapter.getFuture(new AnonymousClass1(this, r3, r6));
        }

        @Override // java.util.concurrent.Future
        public boolean cancel(boolean r1) {
            return this.mDelegate.cancel(r1);
        }

        @Override // java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(Delayed r1) {
            return compareTo2(r1);
        }

        /* renamed from: compareTo, reason: avoid collision after fix types in other method */
        public int compareTo2(Delayed r4) {
            TimeUnit r0 = TimeUnit.MILLISECONDS;
            return Long.compare(getDelay(r0), r4.getDelay(r0));
        }

        @Override // java.util.concurrent.Future
        public V get() {
            return this.mDelegate.get();
        }

        @Override // java.util.concurrent.Future
        public V get(long r1, TimeUnit r3) {
            return this.mDelegate.get(r1, r3);
        }

        @Override // java.util.concurrent.Delayed
        public long getDelay(TimeUnit r5) {
            return r5.convert(this.mRunAtMillis - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override // java.util.concurrent.Future
        public boolean isCancelled() {
            return this.mDelegate.isCancelled();
        }

        @Override // java.util.concurrent.Future
        public boolean isDone() {
            return this.mDelegate.isDone();
        }

        @Override // java.util.concurrent.RunnableScheduledFuture
        public boolean isPeriodic() {
            return false;
        }

        @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
        public void run() {
            CallbackToFutureAdapter.Completer r0 = this.mCompleter.getAndSet(null);
            if (r0 == null) goto L11;
            r0.set(this.mTask.call());     // Catch: Exception -> L6
            return;
        L6:
            e = move-exception;
            r0.setException(e);
            return;
        }
    }

    static {
        sThreadLocalInstance = new AnonymousClass1();
    }

    public HandlerScheduledExecutorService(Handler r1) {
        this.mHandler = r1;
    }

    private RejectedExecutionException createPostFailedException() {
        return new RejectedExecutionException(this.mHandler + " is shutting down");
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long r1, TimeUnit r3) {
        throw new UnsupportedOperationException(HandlerScheduledExecutorService.class.getSimpleName().concat(" cannot be shut down. Use Looper.quitSafely()."));
    }

    @Override // java.lang.AutoCloseable
    public /* synthetic */ void close() {
        UseCaseGraphContext$$ExternalSyntheticAutoCloseableForwarder1.m(this);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable r2) {
        if (this.mHandler.post(r2) == false) goto L6;
        return;
    L6:
        throw createPostFailedException();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return false;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return false;
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> schedule(Runnable r2, long r3, TimeUnit r5) {
        return schedule(new AnonymousClass2(this, r2), r3, r5);
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public <V> ScheduledFuture<V> schedule(Callable<V> r4, long r5, TimeUnit r7) {
        long r0 = SystemClock.uptimeMillis() + TimeUnit.MILLISECONDS.convert(r5, r7);
        HandlerScheduledFuture r52 = new HandlerScheduledFuture(this.mHandler, r0, r4);
        if (this.mHandler.postAtTime(r52, r0) == false) goto L6;
        return r52;
    L6:
        return Futures.immediateFailedScheduledFuture(createPostFailedException());
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable r1, long r2, long r4, TimeUnit r6) {
        throw new UnsupportedOperationException(HandlerScheduledExecutorService.class.getSimpleName().concat(" does not yet support fixed-rate scheduling."));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable r1, long r2, long r4, TimeUnit r6) {
        throw new UnsupportedOperationException(HandlerScheduledExecutorService.class.getSimpleName().concat(" does not yet support fixed-delay scheduling."));
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        throw new UnsupportedOperationException(HandlerScheduledExecutorService.class.getSimpleName().concat(" cannot be shut down. Use Looper.quitSafely()."));
    }

    @Override // java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException(HandlerScheduledExecutorService.class.getSimpleName().concat(" cannot be shut down. Use Looper.quitSafely()."));
    }
}
