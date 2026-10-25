package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ForwardingListenableFuture;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public abstract class MoreExecutors {

    public static class ListeningDecorator extends AbstractListeningExecutorService {
        private final ExecutorService delegate;

        public ListeningDecorator(ExecutorService r1) {
            this.delegate = (ExecutorService) Preconditions.checkNotNull(r1);
        }

        @Override // java.util.concurrent.ExecutorService
        public final boolean awaitTermination(long r1, TimeUnit r3) {
            return this.delegate.awaitTermination(r1, r3);
        }

        @Override // java.util.concurrent.Executor
        public final void execute(Runnable r1) {
            this.delegate.execute(r1);
        }

        @Override // java.util.concurrent.ExecutorService
        public final boolean isShutdown() {
            return this.delegate.isShutdown();
        }

        @Override // java.util.concurrent.ExecutorService
        public final boolean isTerminated() {
            return this.delegate.isTerminated();
        }

        @Override // java.util.concurrent.ExecutorService
        public final void shutdown() {
            this.delegate.shutdown();
        }

        @Override // java.util.concurrent.ExecutorService
        public final List<Runnable> shutdownNow() {
            return this.delegate.shutdownNow();
        }

        public final String toString() {
            return super.toString() + "[" + this.delegate + "]";
        }
    }

    public static final class ScheduledListeningDecorator extends ListeningDecorator implements ListeningScheduledExecutorService {
        final ScheduledExecutorService delegate;

        public static final class ListenableScheduledTask<V> extends ForwardingListenableFuture.SimpleForwardingListenableFuture<V> implements ListenableScheduledFuture<V> {
            private final ScheduledFuture<?> scheduledDelegate;

            public ListenableScheduledTask(ListenableFuture<V> r1, ScheduledFuture<?> r2) {
                super(r1);
                this.scheduledDelegate = r2;
            }

            @Override // com.google.common.util.concurrent.ForwardingFuture, java.util.concurrent.Future
            public boolean cancel(boolean r2) {
                boolean r0 = super.cancel(r2);
                if (r0 == false) goto L5;
                this.scheduledDelegate.cancel(r2);
            L5:
                return r0;
            }

            @Override // java.lang.Comparable
            public /* bridge */ /* synthetic */ int compareTo(Delayed r1) {
                return compareTo2(r1);
            }

            /* renamed from: compareTo, reason: avoid collision after fix types in other method */
            public int compareTo2(Delayed r1) {
                return this.scheduledDelegate.compareTo(r1);
            }

            @Override // java.util.concurrent.Delayed
            public long getDelay(TimeUnit r1) {
                return this.scheduledDelegate.getDelay(r1);
            }
        }

        public static final class NeverSuccessfulListenableFutureTask extends AbstractFuture.TrustedFuture<Void> implements Runnable {
            private final Runnable delegate;

            public NeverSuccessfulListenableFutureTask(Runnable r1) {
                this.delegate = (Runnable) Preconditions.checkNotNull(r1);
            }

            @Override // com.google.common.util.concurrent.AbstractFuture
            public String pendingToString() {
                return "task=[" + this.delegate + "]";
            }

            @Override // java.lang.Runnable
            public void run() {
                this.delegate.run();     // Catch: Throwable -> L4
                return;
            L4:
                th = move-exception;
                setException(th);
                throw th;
            }
        }

        public ScheduledListeningDecorator(ScheduledExecutorService r1) {
            super(r1);
            this.delegate = (ScheduledExecutorService) Preconditions.checkNotNull(r1);
        }

        @Override // com.google.common.util.concurrent.ListeningScheduledExecutorService, java.util.concurrent.ScheduledExecutorService
        public ListenableScheduledFuture<?> schedule(Runnable r2, long r3, TimeUnit r5) {
            TrustedListenableFutureTask r22 = TrustedListenableFutureTask.create(r2, null);
            return new ListenableScheduledTask(r22, this.delegate.schedule(r22, r3, r5));
        }

        @Override // com.google.common.util.concurrent.ListeningScheduledExecutorService, java.util.concurrent.ScheduledExecutorService
        public <V> ListenableScheduledFuture<V> schedule(Callable<V> r1, long r2, TimeUnit r4) {
            TrustedListenableFutureTask r12 = TrustedListenableFutureTask.create(r1);
            return new ListenableScheduledTask(r12, this.delegate.schedule(r12, r2, r4));
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public /* bridge */ /* synthetic */ ScheduledFuture schedule(Runnable r1, long r2, TimeUnit r4) {
            return schedule(r1, r2, r4);
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public /* bridge */ /* synthetic */ ScheduledFuture schedule(Callable r1, long r2, TimeUnit r4) {
            return schedule(r1, r2, r4);
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public ListenableScheduledFuture<?> scheduleAtFixedRate(Runnable r2, long r3, long r5, TimeUnit r7) {
            NeverSuccessfulListenableFutureTask r22 = new NeverSuccessfulListenableFutureTask(r2);
            return new ListenableScheduledTask(r22, this.delegate.scheduleAtFixedRate(r22, r3, r5, r7));
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public /* bridge */ /* synthetic */ ScheduledFuture scheduleAtFixedRate(Runnable r1, long r2, long r4, TimeUnit r6) {
            return scheduleAtFixedRate(r1, r2, r4, r6);
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public ListenableScheduledFuture<?> scheduleWithFixedDelay(Runnable r2, long r3, long r5, TimeUnit r7) {
            NeverSuccessfulListenableFutureTask r22 = new NeverSuccessfulListenableFutureTask(r2);
            return new ListenableScheduledTask(r22, this.delegate.scheduleWithFixedDelay(r22, r3, r5, r7));
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public /* bridge */ /* synthetic */ ScheduledFuture scheduleWithFixedDelay(Runnable r1, long r2, long r4, TimeUnit r6) {
            return scheduleWithFixedDelay(r1, r2, r4, r6);
        }
    }

    /* renamed from: $r8$lambda$24Lb7_WGbzzKYnIZlepg-Cofldg, reason: not valid java name */
    public static /* synthetic */ void m1916$r8$lambda$24Lb7_WGbzzKYnIZlepgCofldg(Executor r0, AbstractFuture r1, Runnable r2) {
        r0.execute(r2);     // Catch: RejectedExecutionException -> L4
        return;
    L4:
        e = move-exception;
        r1.setExceptionInternal(e);
    }

    public static Executor directExecutor() {
        return DirectExecutor.INSTANCE;
    }

    public static ListeningExecutorService listeningDecorator(ExecutorService r1) {
        if ((r1 instanceof ListeningExecutorService) == false) goto L7;
        return (ListeningExecutorService) r1;
    L7:
        if ((r1 instanceof ScheduledExecutorService) == false) goto L11;
        return new ScheduledListeningDecorator((ScheduledExecutorService) r1);
    L11:
        return new ListeningDecorator(r1);
    }

    public static ListeningScheduledExecutorService listeningDecorator(ScheduledExecutorService r1) {
        if ((r1 instanceof ListeningScheduledExecutorService) == false) goto L7;
        return (ListeningScheduledExecutorService) r1;
    L7:
        return new ScheduledListeningDecorator(r1);
    }

    public static Executor newSequentialExecutor(Executor r1) {
        return new SequentialExecutor(r1);
    }

    public static Executor rejectionPropagatingExecutor(Executor r1, AbstractFuture<?> r2) {
        Preconditions.checkNotNull(r1);
        Preconditions.checkNotNull(r2);
        if (r1 != directExecutor()) goto L6;
        return r1;
    L6:
        return new MoreExecutors$$ExternalSyntheticLambda0(r1, r2);
    }
}
