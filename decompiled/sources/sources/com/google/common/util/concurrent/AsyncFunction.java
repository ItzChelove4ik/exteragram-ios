package com.google.common.util.concurrent;

/* loaded from: classes5.dex */
public interface AsyncFunction<I, O> {
    ListenableFuture<O> apply(I r1);
}
