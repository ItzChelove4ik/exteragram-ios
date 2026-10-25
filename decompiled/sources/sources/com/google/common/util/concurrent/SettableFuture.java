package com.google.common.util.concurrent;

import com.google.common.util.concurrent.AbstractFuture;

/* loaded from: classes5.dex */
public final class SettableFuture<V> extends AbstractFuture.TrustedFuture<V> {
    private SettableFuture() {
    }

    public static <V> SettableFuture<V> create() {
        return new SettableFuture();
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public boolean set(V r1) {
        return super.set(r1);
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public boolean setException(Throwable r1) {
        return super.setException(r1);
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public boolean setFuture(ListenableFuture<? extends V> r1) {
        return super.setFuture(r1);
    }
}
