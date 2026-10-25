package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AbstractFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/* loaded from: classes5.dex */
class ImmediateFuture<V> implements ListenableFuture<V> {
    static final ListenableFuture<?> NULL = null;
    private static final LazyLogger log = null;
    private final V value;

    public static final class ImmediateCancelledFuture<V> extends AbstractFuture.TrustedFuture<V> {
        static final ImmediateCancelledFuture<Object> INSTANCE = null;

        static {
            if (AbstractFutureState.GENERATE_CANCELLATION_CAUSES == false) goto L5;
            ImmediateCancelledFuture<Object> r0 = null;
        L6:
            INSTANCE = r0;
            return;
        L5:
            r0 = new ImmediateCancelledFuture();
            goto L6
        }

        public ImmediateCancelledFuture() {
            cancel(false);
        }
    }

    public static final class ImmediateFailedFuture<V> extends AbstractFuture.TrustedFuture<V> {
        public ImmediateFailedFuture(Throwable r1) {
            setException(r1);
        }
    }

    static {
        NULL = new ImmediateFuture(null);
        log = new LazyLogger(ImmediateFuture.class);
    }

    public ImmediateFuture(V r1) {
        this.value = r1;
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public void addListener(Runnable r5, Executor r6) {
        Preconditions.checkNotNull(r5, "Runnable was null.");
        Preconditions.checkNotNull(r6, "Executor was null.");
        r6.execute(r5);     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        log.get().log(Level.SEVERE, "RuntimeException while executing runnable " + r5 + " with executor " + r6, e);
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean r1) {
        return false;
    }

    @Override // java.util.concurrent.Future
    public V get() {
        return this.value;
    }

    @Override // java.util.concurrent.Future
    public V get(long r1, TimeUnit r3) {
        Preconditions.checkNotNull(r3);
        return get();
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return false;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return true;
    }

    public String toString() {
        return super.toString() + "[status=SUCCESS, result=[" + this.value + "]]";
    }
}
