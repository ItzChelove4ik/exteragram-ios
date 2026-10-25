package com.google.common.util.concurrent;

/* loaded from: classes5.dex */
public interface AsyncCallable<V> {
    ListenableFuture<V> call();
}
