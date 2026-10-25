package com.google.firebase.messaging.threads;

import android.annotation.SuppressLint;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public abstract class PoolableExecutors {
    private static final ExecutorFactory DEFAULT_INSTANCE = null;
    private static volatile ExecutorFactory instance;

    /* renamed from: com.google.firebase.messaging.threads.PoolableExecutors$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static class DefaultExecutorFactory implements ExecutorFactory {
        private DefaultExecutorFactory() {
        }

        public /* synthetic */ DefaultExecutorFactory(AnonymousClass1 r1) {
            this();
        }

        @Override // com.google.firebase.messaging.threads.ExecutorFactory
        public ExecutorService newSingleThreadExecutor(ThreadFactory r2, ThreadPriority r3) {
            return newThreadPool(1, r2, r3);
        }

        @SuppressLint({"ThreadPoolCreation"})
        public ExecutorService newThreadPool(int r9, ThreadFactory r10, ThreadPriority r11) {
            LinkedBlockingQueue r6 = new LinkedBlockingQueue();
            ThreadPoolExecutor r0 = new ThreadPoolExecutor(r9, r9, 60, TimeUnit.SECONDS, r6, r10);
            r0.allowCoreThreadTimeOut(true);
            return Executors.unconfigurableExecutorService(r0);
        }
    }

    static {
        DefaultExecutorFactory r0 = new DefaultExecutorFactory(null);
        DEFAULT_INSTANCE = r0;
        instance = r0;
    }

    public static ExecutorFactory factory() {
        return instance;
    }
}
