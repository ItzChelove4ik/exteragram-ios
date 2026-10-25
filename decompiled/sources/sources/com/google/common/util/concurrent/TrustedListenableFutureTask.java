package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.FluentFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;

/* loaded from: classes5.dex */
class TrustedListenableFutureTask<V> extends FluentFuture.TrustedFuture<V> implements RunnableFuture<V> {
    private volatile InterruptibleTask<?> task;

    public final class TrustedFutureInterruptibleAsyncTask extends InterruptibleTask<ListenableFuture<V>> {
        private final AsyncCallable<V> callable;
        final /* synthetic */ TrustedListenableFutureTask this$0;

        public TrustedFutureInterruptibleAsyncTask(TrustedListenableFutureTask r1, AsyncCallable<V> r2) {
            this.this$0 = r1;
            this.callable = (AsyncCallable) Preconditions.checkNotNull(r2);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public void afterRanInterruptiblyFailure(Throwable r1) {
            this.this$0.setException(r1);
        }

        public void afterRanInterruptiblySuccess(ListenableFuture<V> r1) {
            this.this$0.setFuture(r1);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public /* bridge */ /* synthetic */ void afterRanInterruptiblySuccess(Object r1) {
            afterRanInterruptiblySuccess((ListenableFuture) r1);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public boolean isDone() {
            return this.this$0.isDone();
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public ListenableFuture<V> runInterruptibly() {
            return (ListenableFuture) Preconditions.checkNotNull(this.callable.call(), "AsyncCallable.call returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", this.callable);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public /* bridge */ /* synthetic */ Object runInterruptibly() {
            return runInterruptibly();
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public String toPendingString() {
            return this.callable.toString();
        }
    }

    public final class TrustedFutureInterruptibleTask extends InterruptibleTask<V> {
        private final Callable<V> callable;
        final /* synthetic */ TrustedListenableFutureTask this$0;

        public TrustedFutureInterruptibleTask(TrustedListenableFutureTask r1, Callable<V> r2) {
            this.this$0 = r1;
            this.callable = (Callable) Preconditions.checkNotNull(r2);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public void afterRanInterruptiblyFailure(Throwable r1) {
            this.this$0.setException(r1);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public void afterRanInterruptiblySuccess(V r1) {
            this.this$0.set(r1);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public boolean isDone() {
            return this.this$0.isDone();
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public V runInterruptibly() {
            return this.callable.call();
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public String toPendingString() {
            return this.callable.toString();
        }
    }

    public TrustedListenableFutureTask(AsyncCallable<V> r2) {
        this.task = new TrustedFutureInterruptibleAsyncTask(this, r2);
    }

    public TrustedListenableFutureTask(Callable<V> r2) {
        this.task = new TrustedFutureInterruptibleTask(this, r2);
    }

    public static <V> TrustedListenableFutureTask<V> create(AsyncCallable<V> r1) {
        return new TrustedListenableFutureTask(r1);
    }

    public static <V> TrustedListenableFutureTask<V> create(Runnable r1, V r2) {
        return new TrustedListenableFutureTask(Executors.callable(r1, r2));
    }

    public static <V> TrustedListenableFutureTask<V> create(Callable<V> r1) {
        return new TrustedListenableFutureTask(r1);
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public void afterDone() {
        super.afterDone();
        if (wasInterrupted() == false) goto L7;
        InterruptibleTask<?> r0 = this.task;
        if (r0 == null) goto L7;
        r0.interruptTask();
    L7:
        this.task = null;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public String pendingToString() {
        InterruptibleTask<?> r0 = this.task;
        if (r0 == null) goto L7;
        return "task=[" + r0 + "]";
    L7:
        return super.pendingToString();
    }

    @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
    public void run() {
        InterruptibleTask<?> r0 = this.task;
        if (r0 == null) goto L5;
        r0.run();
    L5:
        this.task = null;
    }
}
