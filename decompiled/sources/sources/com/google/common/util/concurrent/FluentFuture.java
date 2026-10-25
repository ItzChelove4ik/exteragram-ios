package com.google.common.util.concurrent;

import com.google.common.base.Function;
import com.google.common.util.concurrent.AbstractFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public abstract class FluentFuture<V> extends GwtFluentFutureCatchingSpecialization<V> {

    public static abstract class TrustedFuture<V> extends FluentFuture<V> implements AbstractFuture.Trusted<V> {
        public TrustedFuture() {
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, com.google.common.util.concurrent.ListenableFuture
        public final void addListener(Runnable r1, Executor r2) {
            super.addListener(r1, r2);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
        public final boolean cancel(boolean r1) {
            return super.cancel(r1);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
        public final V get() {
            return (V) super.get();
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
        public final V get(long r1, TimeUnit r3) {
            return (V) super.get(r1, r3);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
        public final boolean isCancelled() {
            return super.isCancelled();
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
        public final boolean isDone() {
            return super.isDone();
        }
    }

    public FluentFuture() {
    }

    public static <V> FluentFuture<V> from(ListenableFuture<V> r1) {
        if ((r1 instanceof FluentFuture) == false) goto L7;
        return (FluentFuture) r1;
    L7:
        return new ForwardingFluentFuture(r1);
    }

    public final <X extends Throwable> FluentFuture<V> catching(Class<X> r1, Function<? super X, ? extends V> r2, Executor r3) {
        return (FluentFuture) Futures.catching(this, r1, r2, r3);
    }

    public final <T> FluentFuture<T> transform(Function<? super V, T> r1, Executor r2) {
        return (FluentFuture) Futures.transform(this, r1, r2);
    }

    public final <T> FluentFuture<T> transformAsync(AsyncFunction<? super V, T> r1, Executor r2) {
        return (FluentFuture) Futures.transformAsync(this, r1, r2);
    }
}
