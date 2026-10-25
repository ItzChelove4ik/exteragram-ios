package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.util.concurrent.AggregateFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* loaded from: classes5.dex */
final class CombinedFuture<V> extends AggregateFuture<Object, V> {
    private CombinedFuture<V>.CombinedFutureInterruptibleTask<?> task;

    public final class AsyncCallableInterruptibleTask extends CombinedFuture<V>.CombinedFutureInterruptibleTask<ListenableFuture<V>> {
        private final AsyncCallable<V> callable;
        final /* synthetic */ CombinedFuture this$0;

        public AsyncCallableInterruptibleTask(CombinedFuture r1, AsyncCallable<V> r2, Executor r3) {
            this.this$0 = r1;
            super(r1, r3);
            this.callable = (AsyncCallable) Preconditions.checkNotNull(r2);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public ListenableFuture<V> runInterruptibly() {
            return (ListenableFuture) Preconditions.checkNotNull(this.callable.call(), "AsyncCallable.call returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", this.callable);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public /* bridge */ /* synthetic */ Object runInterruptibly() {
            return runInterruptibly();
        }

        public void setValue(ListenableFuture<V> r1) {
            this.this$0.setFutureInternal(r1);
        }

        @Override // com.google.common.util.concurrent.CombinedFuture.CombinedFutureInterruptibleTask
        public /* bridge */ /* synthetic */ void setValue(Object r1) {
            setValue((ListenableFuture) r1);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public String toPendingString() {
            return this.callable.toString();
        }
    }

    public final class CallableInterruptibleTask extends CombinedFuture<V>.CombinedFutureInterruptibleTask<V> {
        private final Callable<V> callable;
        final /* synthetic */ CombinedFuture this$0;

        public CallableInterruptibleTask(CombinedFuture r1, Callable<V> r2, Executor r3) {
            this.this$0 = r1;
            super(r1, r3);
            this.callable = (Callable) Preconditions.checkNotNull(r2);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public V runInterruptibly() {
            return this.callable.call();
        }

        @Override // com.google.common.util.concurrent.CombinedFuture.CombinedFutureInterruptibleTask
        public void setValue(V r1) {
            this.this$0.set(r1);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public String toPendingString() {
            return this.callable.toString();
        }
    }

    public abstract class CombinedFutureInterruptibleTask<T> extends InterruptibleTask<T> {
        private final Executor listenerExecutor;
        final /* synthetic */ CombinedFuture this$0;

        public CombinedFutureInterruptibleTask(CombinedFuture r1, Executor r2) {
            this.this$0 = r1;
            this.listenerExecutor = (Executor) Preconditions.checkNotNull(r2);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public final void afterRanInterruptiblyFailure(Throwable r3) {
            CombinedFuture.access$002(this.this$0, null);
            if ((r3 instanceof ExecutionException) == false) goto L6;
            this.this$0.setException(((ExecutionException) r3).getCause());
            return;
        L6:
            boolean r0 = r3 instanceof CancellationException;
            CombinedFuture r2 = this.this$0;
            if (r0 == false) goto L10;
            r2.cancel(false);
            return;
        L10:
            r2.setException(r3);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public final void afterRanInterruptiblySuccess(T r3) {
            CombinedFuture.access$002(this.this$0, null);
            setValue(r3);
        }

        public final void execute() {
            this.listenerExecutor.execute(this);     // Catch: RejectedExecutionException -> L4
            return;
        L4:
            e = move-exception;
            this.this$0.setException(e);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        public final boolean isDone() {
            return this.this$0.isDone();
        }

        public abstract void setValue(T r1);
    }

    public CombinedFuture(ImmutableCollection<? extends ListenableFuture<?>> r2, boolean r3, Executor r4, AsyncCallable<V> r5) {
        super(r2, r3, false);
        this.task = new AsyncCallableInterruptibleTask(this, r5, r4);
        init();
    }

    public CombinedFuture(ImmutableCollection<? extends ListenableFuture<?>> r2, boolean r3, Executor r4, Callable<V> r5) {
        super(r2, r3, false);
        this.task = new CallableInterruptibleTask(this, r5, r4);
        init();
    }

    public static /* synthetic */ CombinedFutureInterruptibleTask access$002(CombinedFuture r0, CombinedFutureInterruptibleTask r1) {
        r0.task = r1;
        return r1;
    }

    @Override // com.google.common.util.concurrent.AggregateFuture
    public void collectOneValue(int r1, Object r2) {
    }

    @Override // com.google.common.util.concurrent.AggregateFuture
    public void handleAllCompleted() {
        CombinedFuture<V>.CombinedFutureInterruptibleTask<?> r0 = this.task;
        if (r0 == null) goto L6;
        r0.execute();
        return;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public void interruptTask() {
        CombinedFuture<V>.CombinedFutureInterruptibleTask<?> r0 = this.task;
        if (r0 == null) goto L6;
        r0.interruptTask();
        return;
    }

    @Override // com.google.common.util.concurrent.AggregateFuture
    public void releaseResources(AggregateFuture.ReleaseResourcesReason r2) {
        super.releaseResources(r2);
        if (r2 != AggregateFuture.ReleaseResourcesReason.OUTPUT_FUTURE_DONE) goto L6;
        this.task = null;
        return;
    }
}
