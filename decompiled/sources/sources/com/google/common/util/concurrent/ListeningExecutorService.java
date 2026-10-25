package com.google.common.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/* loaded from: classes5.dex */
public interface ListeningExecutorService extends ExecutorService, AutoCloseable {
    ListenableFuture<?> submit(Runnable r1);

    <T> ListenableFuture<T> submit(Callable<T> r1);
}
