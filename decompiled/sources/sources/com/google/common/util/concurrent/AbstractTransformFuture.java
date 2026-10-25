package com.google.common.util.concurrent;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.FluentFuture;
import java.util.concurrent.Executor;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
abstract class AbstractTransformFuture<I, O, F, T> extends FluentFuture.TrustedFuture<O> implements Runnable {
    F function;
    ListenableFuture<? extends I> inputFuture;

    public static final class AsyncTransformFuture<I, O> extends AbstractTransformFuture<I, O, AsyncFunction<? super I, ? extends O>, ListenableFuture<? extends O>> {
        public AsyncTransformFuture(ListenableFuture<? extends I> r1, AsyncFunction<? super I, ? extends O> r2) {
            super(r1, r2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public ListenableFuture<? extends O> doTransform(AsyncFunction<? super I, ? extends O> r1, I r2) {
            ListenableFuture<? extends O> r0 = r1.apply(r2);
            Preconditions.checkNotNull(r0, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", r1);
            return r0;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.util.concurrent.AbstractTransformFuture
        public /* bridge */ /* synthetic */ Object doTransform(Object r1, Object r2) {
            return doTransform((AsyncFunction) r1, r2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void setResult(ListenableFuture<? extends O> r1) {
            setFuture(r1);
        }

        @Override // com.google.common.util.concurrent.AbstractTransformFuture
        public /* bridge */ /* synthetic */ void setResult(Object r1) {
            setResult((ListenableFuture) r1);
        }
    }

    public static final class TransformFuture<I, O> extends AbstractTransformFuture<I, O, Function<? super I, ? extends O>, O> {
        public TransformFuture(ListenableFuture<? extends I> r1, Function<? super I, ? extends O> r2) {
            super(r1, r2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public O doTransform(Function<? super I, ? extends O> r1, I r2) {
            return r1.apply(r2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.util.concurrent.AbstractTransformFuture
        public /* bridge */ /* synthetic */ Object doTransform(Object r1, Object r2) {
            return doTransform((Function) r1, r2);
        }

        @Override // com.google.common.util.concurrent.AbstractTransformFuture
        public void setResult(O r1) {
            set(r1);
        }
    }

    public AbstractTransformFuture(ListenableFuture<? extends I> r1, F r2) {
        this.inputFuture = (ListenableFuture) Preconditions.checkNotNull(r1);
        this.function = (F) Preconditions.checkNotNull(r2);
    }

    public static <I, O> ListenableFuture<O> create(ListenableFuture<I> r1, Function<? super I, ? extends O> r2, Executor r3) {
        TransformFuture r0 = new TransformFuture(r1, r2);
        r1.addListener(r0, MoreExecutors.rejectionPropagatingExecutor(r3, r0));
        return r0;
    }

    public static <I, O> ListenableFuture<O> createAsync(ListenableFuture<I> r1, AsyncFunction<? super I, ? extends O> r2, Executor r3) {
        AsyncTransformFuture r0 = new AsyncTransformFuture(r1, r2);
        r1.addListener(r0, MoreExecutors.rejectionPropagatingExecutor(r3, r0));
        return r0;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public final void afterDone() {
        maybePropagateCancellationTo(this.inputFuture);
        this.inputFuture = null;
        this.function = null;
    }

    public abstract T doTransform(F r1, I r2);

    @Override // com.google.common.util.concurrent.AbstractFuture
    public String pendingToString() {
        ListenableFuture<? extends I> r0 = this.inputFuture;
        F r1 = this.function;
        String r4 = super.pendingToString();
        if (r0 == null) goto L5;
        String r02 = "inputFuture=[" + r0 + "], ";
    L6:
        if (r1 != null) goto L8;
        if (r4 != null) goto L11;
        return null;
    L11:
        return r02.concat(r4);
    L8:
        return r02 + "function=[" + r1 + "]";
    L5:
        r02 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public final void run() {
        ListenableFuture<? extends I> r0 = this.inputFuture;
        F r1 = this.function;
        boolean r2 = isCancelled();
        boolean r3 = true;
        if (r0 != null) goto L5;
        boolean r5 = true;
    L6:
        boolean r22 = r2 | r5;
        if (r1 == null) goto L11;
        r3 = false;
    L11:
        if ((r22 | r3) == false) goto L13;
        return;
    L13:
        this.inputFuture = null;
        if (r0.isCancelled() == false) goto L43;
        setFuture(r0);
        return;
    L43:
        Object r02 = doTransform(r1, Futures.getDone(r0));     // Catch: Throwable -> L21
        this.function = null;
        setResult(r02);
        return;
    L21:
        th = move-exception;
        Platform.restoreInterruptIfIsInterruptedException(th);     // Catch: Throwable -> L25
        setException(th);     // Catch: Throwable -> L25
        this.function = null;
        return;
    L25:
        th = move-exception;
        this.function = null;
        throw th;
    L28:
        e = move-exception;
        setException(e);
        return;
    L37:
        cancel(false);
        return;
    L34:
        e = move-exception;
        setException(e.getCause());
        return;
    L31:
        e = move-exception;
        setException(e);
        return;
    L5:
        r5 = false;
        goto L6
    }

    public abstract void setResult(T r1);
}
