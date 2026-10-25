package com.google.common.util.concurrent;

import com.google.common.base.Function;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.internal.InternalFutureFailureAccess;
import com.google.common.util.concurrent.internal.InternalFutures;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/* loaded from: classes5.dex */
public abstract class Futures extends GwtFuturesCatchingSpecialization {

    /* renamed from: com.google.common.util.concurrent.Futures$1, reason: invalid class name */
    abstract class AnonymousClass1 implements Future<Object> {
    }

    public static final class CallbackListener<V> implements Runnable {
        final FutureCallback<? super V> callback;
        final ListenableFuture<V> future;

        public CallbackListener(ListenableFuture<V> r1, FutureCallback<? super V> r2) {
            this.future = r1;
            this.callback = r2;
        }

        @Override // java.lang.Runnable
        public void run() {
            ListenableFuture<V> r0 = this.future;
            if ((r0 instanceof InternalFutureFailureAccess) == false) goto L19;
            Throwable r02 = InternalFutures.tryInternalFastPathGetFailure((InternalFutureFailureAccess) r0);
            if (r02 == null) goto L19;
            this.callback.onFailure(r02);
            return;
        L19:
            Object r03 = Futures.getDone(this.future);     // Catch: Throwable -> L11 ExecutionException -> L13
            this.callback.onSuccess(r03);
            return;
        L13:
            e = move-exception;
            this.callback.onFailure(e.getCause());
            return;
        L11:
            th = move-exception;
            this.callback.onFailure(th);
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).addValue(this.callback).toString();
        }
    }

    public static final class FutureCombiner<V> {
        private final boolean allMustSucceed;
        private final ImmutableList<ListenableFuture<? extends V>> futures;

        private FutureCombiner(boolean r1, ImmutableList<ListenableFuture<? extends V>> r2) {
            this.allMustSucceed = r1;
            this.futures = r2;
        }

        public /* synthetic */ FutureCombiner(boolean r1, ImmutableList r2, AnonymousClass1 r3) {
            this(r1, r2);
        }

        public <C> ListenableFuture<C> call(Callable<C> r3, Executor r4) {
            return new CombinedFuture(this.futures, this.allMustSucceed, r4, r3);
        }

        public <C> ListenableFuture<C> callAsync(AsyncCallable<C> r3, Executor r4) {
            return new CombinedFuture(this.futures, this.allMustSucceed, r4, r3);
        }
    }

    public static final class NonCancellationPropagatingFuture<V> extends AbstractFuture.TrustedFuture<V> implements Runnable {
        private ListenableFuture<V> delegate;

        public NonCancellationPropagatingFuture(ListenableFuture<V> r1) {
            this.delegate = r1;
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        public void afterDone() {
            this.delegate = null;
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        public String pendingToString() {
            ListenableFuture<V> r2 = this.delegate;
            if (r2 != null) goto L5;
            return null;
        L5:
            return "delegate=[" + r2 + "]";
        }

        @Override // java.lang.Runnable
        public void run() {
            ListenableFuture<V> r0 = this.delegate;
            if (r0 == null) goto L6;
            setFuture(r0);
            return;
        }
    }

    public static <V> void addCallback(ListenableFuture<V> r1, FutureCallback<? super V> r2, Executor r3) {
        Preconditions.checkNotNull(r2);
        r1.addListener(new CallbackListener(r1, r2), r3);
    }

    public static <V, X extends Throwable> ListenableFuture<V> catching(ListenableFuture<? extends V> r0, Class<X> r1, Function<? super X, ? extends V> r2, Executor r3) {
        return AbstractCatchingFuture.create(r0, r1, r2, r3);
    }

    public static <V, X extends Throwable> ListenableFuture<V> catchingAsync(ListenableFuture<? extends V> r0, Class<X> r1, AsyncFunction<? super X, ? extends V> r2, Executor r3) {
        return AbstractCatchingFuture.createAsync(r0, r1, r2, r3);
    }

    public static <V> V getDone(Future<V> r2) {
        Preconditions.checkState(r2.isDone(), "Future was expected to be done: %s", r2);
        return (V) Uninterruptibles.getUninterruptibly(r2);
    }

    public static <V> ListenableFuture<V> immediateCancelledFuture() {
        ImmediateFuture.ImmediateCancelledFuture<Object> r0 = ImmediateFuture.ImmediateCancelledFuture.INSTANCE;
        if (r0 == null) goto L6;
        return r0;
    L6:
        return new ImmediateFuture.ImmediateCancelledFuture();
    }

    public static <V> ListenableFuture<V> immediateFailedFuture(Throwable r1) {
        Preconditions.checkNotNull(r1);
        return new ImmediateFuture.ImmediateFailedFuture(r1);
    }

    public static <V> ListenableFuture<V> immediateFuture(V r1) {
        if (r1 != null) goto L6;
        return (ListenableFuture<V>) ImmediateFuture.NULL;
    L6:
        return new ImmediateFuture(r1);
    }

    public static ListenableFuture<Void> immediateVoidFuture() {
        return ImmediateFuture.NULL;
    }

    public static <V> ListenableFuture<V> nonCancellationPropagating(ListenableFuture<V> r2) {
        if (r2.isDone() == false) goto L5;
        return r2;
    L5:
        NonCancellationPropagatingFuture r0 = new NonCancellationPropagatingFuture(r2);
        r2.addListener(r0, MoreExecutors.directExecutor());
        return r0;
    }

    public static <O> ListenableFuture<O> submit(Callable<O> r0, Executor r1) {
        TrustedListenableFutureTask r02 = TrustedListenableFutureTask.create(r0);
        r1.execute(r02);
        return r02;
    }

    public static <O> ListenableFuture<O> submitAsync(AsyncCallable<O> r0, Executor r1) {
        TrustedListenableFutureTask r02 = TrustedListenableFutureTask.create(r0);
        r1.execute(r02);
        return r02;
    }

    public static <I, O> ListenableFuture<O> transform(ListenableFuture<I> r0, Function<? super I, ? extends O> r1, Executor r2) {
        return AbstractTransformFuture.create(r0, r1, r2);
    }

    public static <I, O> ListenableFuture<O> transformAsync(ListenableFuture<I> r0, AsyncFunction<? super I, ? extends O> r1, Executor r2) {
        return AbstractTransformFuture.createAsync(r0, r1, r2);
    }

    public static <V> FutureCombiner<V> whenAllComplete(Iterable<? extends ListenableFuture<? extends V>> r3) {
        return new FutureCombiner(false, ImmutableList.copyOf(r3), null);
    }

    public static <V> FutureCombiner<V> whenAllSucceed(Iterable<? extends ListenableFuture<? extends V>> r3) {
        return new FutureCombiner(true, ImmutableList.copyOf(r3), null);
    }
}
