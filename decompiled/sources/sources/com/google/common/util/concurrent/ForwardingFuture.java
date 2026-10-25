package com.google.common.util.concurrent;

import com.google.common.collect.ForwardingObject;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public abstract class ForwardingFuture<V> extends ForwardingObject implements Future<V> {
    public ForwardingFuture() {
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean r1) {
        return delegate().cancel(r1);
    }

    @Override // com.google.common.collect.ForwardingObject
    public abstract Future<? extends V> delegate();

    @Override // java.util.concurrent.Future
    public V get() {
        return delegate().get();
    }

    @Override // java.util.concurrent.Future
    public V get(long r1, TimeUnit r3) {
        return delegate().get(r1, r3);
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return delegate().isCancelled();
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return delegate().isDone();
    }
}
