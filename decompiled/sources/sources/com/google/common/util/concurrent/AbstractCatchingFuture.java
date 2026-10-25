package com.google.common.util.concurrent;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.internal.InternalFutureFailureAccess;
import com.google.common.util.concurrent.internal.InternalFutures;
import java.lang.Throwable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
abstract class AbstractCatchingFuture<V, X extends Throwable, F, T> extends FluentFuture.TrustedFuture<V> implements Runnable {
    Class<X> exceptionType;
    F fallback;
    ListenableFuture<? extends V> inputFuture;

    public static final class AsyncCatchingFuture<V, X extends Throwable> extends AbstractCatchingFuture<V, X, AsyncFunction<? super X, ? extends V>, ListenableFuture<? extends V>> {
        public AsyncCatchingFuture(ListenableFuture<? extends V> r1, Class<X> r2, AsyncFunction<? super X, ? extends V> r3) {
            super(r1, r2, r3);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public ListenableFuture<? extends V> doFallback(AsyncFunction<? super X, ? extends V> r1, X r2) {
            ListenableFuture<? extends V> r0 = r1.apply(r2);
            Preconditions.checkNotNull(r0, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", r1);
            return r0;
        }

        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        public /* bridge */ /* synthetic */ Object doFallback(Object r1, Throwable r2) {
            return doFallback((AsyncFunction) r1, r2);
        }

        public void setResult(ListenableFuture<? extends V> r1) {
            setFuture(r1);
        }

        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        public /* bridge */ /* synthetic */ void setResult(Object r1) {
            setResult((ListenableFuture) r1);
        }
    }

    public static final class CatchingFuture<V, X extends Throwable> extends AbstractCatchingFuture<V, X, Function<? super X, ? extends V>, V> {
        public CatchingFuture(ListenableFuture<? extends V> r1, Class<X> r2, Function<? super X, ? extends V> r3) {
            super(r1, r2, r3);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public V doFallback(Function<? super X, ? extends V> r1, X r2) {
            return r1.apply(r2);
        }

        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        public /* bridge */ /* synthetic */ Object doFallback(Object r1, Throwable r2) {
            return doFallback((Function) r1, r2);
        }

        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        public void setResult(V r1) {
            set(r1);
        }
    }

    public AbstractCatchingFuture(ListenableFuture<? extends V> r1, Class<X> r2, F r3) {
        this.inputFuture = (ListenableFuture) Preconditions.checkNotNull(r1);
        this.exceptionType = (Class) Preconditions.checkNotNull(r2);
        this.fallback = (F) Preconditions.checkNotNull(r3);
    }

    public static <V, X extends Throwable> ListenableFuture<V> create(ListenableFuture<? extends V> r1, Class<X> r2, Function<? super X, ? extends V> r3, Executor r4) {
        CatchingFuture r0 = new CatchingFuture(r1, r2, r3);
        r1.addListener(r0, MoreExecutors.rejectionPropagatingExecutor(r4, r0));
        return r0;
    }

    public static <X extends Throwable, V> ListenableFuture<V> createAsync(ListenableFuture<? extends V> r1, Class<X> r2, AsyncFunction<? super X, ? extends V> r3, Executor r4) {
        AsyncCatchingFuture r0 = new AsyncCatchingFuture(r1, r2, r3);
        r1.addListener(r0, MoreExecutors.rejectionPropagatingExecutor(r4, r0));
        return r0;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public final void afterDone() {
        maybePropagateCancellationTo(this.inputFuture);
        this.inputFuture = null;
        this.exceptionType = null;
        this.fallback = null;
    }

    public abstract T doFallback(F r1, X r2);

    @Override // com.google.common.util.concurrent.AbstractFuture
    public String pendingToString() {
        ListenableFuture<? extends V> r0 = this.inputFuture;
        Class<X> r1 = this.exceptionType;
        F r2 = this.fallback;
        String r5 = super.pendingToString();
        if (r0 == null) goto L5;
        String r02 = "inputFuture=[" + r0 + "], ";
    L6:
        if (r1 == null) goto L10;
        if (r2 == null) goto L10;
        return r02 + "exceptionType=[" + r1 + "], fallback=[" + r2 + "]";
    L10:
        if (r5 != null) goto L12;
        return null;
    L12:
        return r02.concat(r5);
    L5:
        r02 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public final void run() {
        ListenableFuture<? extends V> r0 = this.inputFuture;
        Class<X> r1 = this.exceptionType;
        F r2 = this.fallback;
        boolean r3 = false;
        if (r0 != 0) goto L5;
        boolean r5 = true;
    L6:
        if (r1 != null) goto L8;
        boolean r6 = true;
    L9:
        boolean r52 = r5 | r6;
        if (r2 != null) goto L13;
        r3 = true;
    L13:
        if ((r3 | r52) == false) goto L15;
        return;
    L15:
        if (isCancelled() == true) goto L58;
        this.inputFuture = null;
    L23:
        e = move-exception;
        Throwable r53 = e.getCause();
        if (r53 != null) goto L33;
        r53 = new NullPointerException("Future type " + r0.getClass() + " threw " + e.getClass() + " without a cause");
    L33:
        th = r53;
    L29:
        Object r54 = null;
    L34:
        if (th != null) goto L38;
        set(NullnessCasts.uncheckedCastNullableTToT(r54));
        return;
    L38:
        if (Platform.isInstanceOfThrowableClass(th, r1) == true) goto L54;
        setFuture(r0);
        return;
    L54:
        Object r02 = doFallback(r2, th);     // Catch: Throwable -> L44
        this.exceptionType = null;
        this.fallback = null;
        setResult(r02);
        return;
    L44:
        th = move-exception;
        Platform.restoreInterruptIfIsInterruptedException(th);     // Catch: Throwable -> L48
        setException(th);     // Catch: Throwable -> L48
        this.exceptionType = null;
        this.fallback = null;
        return;
    L48:
        th = move-exception;
        this.exceptionType = null;
        this.fallback = null;
        throw th;
    L21:
        th = th;
        goto L29
    L19:
        if ((r0 instanceof InternalFutureFailureAccess) == false) goto L25;
        th = InternalFutures.tryInternalFastPathGetFailure((InternalFutureFailureAccess) r0);     // Catch: Throwable -> L21 ExecutionException -> L23
    L26:
        if (th != null) goto L29;
        r54 = Futures.getDone(r0);     // Catch: Throwable -> L21 ExecutionException -> L23
    L25:
        th = null;
        goto L26
    L58:
        return;
    L8:
        r6 = false;
        goto L9
    L5:
        r5 = false;
        goto L6
    }

    public abstract void setResult(T r1);
}
