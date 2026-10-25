package com.google.firebase.crashlytics.internal.common;

import android.annotation.SuppressLint;
import com.google.firebase.crashlytics.internal.Logger;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes5.dex */
public abstract class ExecutorUtils {

    /* renamed from: com.google.firebase.crashlytics.internal.common.ExecutorUtils$1, reason: invalid class name */
    public class AnonymousClass1 implements ThreadFactory {
        final /* synthetic */ AtomicLong val$count;
        final /* synthetic */ String val$threadNameTemplate;

        /* renamed from: com.google.firebase.crashlytics.internal.common.ExecutorUtils$1$1, reason: invalid class name and collision with other inner class name */
        public class C00231 extends BackgroundPriorityRunnable {
            final /* synthetic */ AnonymousClass1 this$0;
            final /* synthetic */ Runnable val$runnable;

            public C00231(AnonymousClass1 r1, Runnable r2) {
                this.this$0 = r1;
                this.val$runnable = r2;
            }

            @Override // com.google.firebase.crashlytics.internal.common.BackgroundPriorityRunnable
            public void onRun() {
                this.val$runnable.run();
            }
        }

        public AnonymousClass1(String r1, AtomicLong r2) {
            this.val$threadNameTemplate = r1;
            this.val$count = r2;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable r4) {
            Thread r42 = Executors.defaultThreadFactory().newThread(new C00231(this, r4));
            r42.setName(this.val$threadNameTemplate + this.val$count.getAndIncrement());
            return r42;
        }
    }

    /* renamed from: com.google.firebase.crashlytics.internal.common.ExecutorUtils$2, reason: invalid class name */
    public class AnonymousClass2 extends BackgroundPriorityRunnable {
        final /* synthetic */ ExecutorService val$service;
        final /* synthetic */ String val$serviceName;
        final /* synthetic */ long val$terminationTimeout;
        final /* synthetic */ TimeUnit val$timeUnit;

        public AnonymousClass2(String r1, ExecutorService r2, long r3, TimeUnit r5) {
            this.val$serviceName = r1;
            this.val$service = r2;
            this.val$terminationTimeout = r3;
            this.val$timeUnit = r5;
        }

        @Override // com.google.firebase.crashlytics.internal.common.BackgroundPriorityRunnable
        public void onRun() {
            Logger.getLogger().d("Executing shutdown hook for " + this.val$serviceName);     // Catch: InterruptedException -> L7
            this.val$service.shutdown();     // Catch: InterruptedException -> L7
            if (this.val$service.awaitTermination(this.val$terminationTimeout, this.val$timeUnit) == true) goto L11;
            Logger.getLogger().d(this.val$serviceName + " did not shut down in the allocated time. Requesting immediate shutdown.");     // Catch: InterruptedException -> L7
            this.val$service.shutdownNow();     // Catch: InterruptedException -> L7
            return;
        L11:
            return;
        L7:
            Logger.getLogger().d(String.format(Locale.US, "Interrupted while waiting for %s to shut down. Requesting immediate shutdown.", new Object[]{this.val$serviceName}));
            this.val$service.shutdownNow();
        }
    }

    private static void addDelayedShutdownHook(String r3, ExecutorService r4) {
        addDelayedShutdownHook(r3, r4, 2, TimeUnit.SECONDS);
    }

    @SuppressLint({"ThreadPoolCreation"})
    private static void addDelayedShutdownHook(String r8, ExecutorService r9, long r10, TimeUnit r12) {
        Runtime.getRuntime().addShutdownHook(new Thread(new AnonymousClass2(r8, r9, r10, r12), "Crashlytics Shutdown Hook for " + r8));
    }

    public static ExecutorService buildSingleThreadExecutorService(String r2) {
        ExecutorService r0 = newSingleThreadExecutor(getNamedThreadFactory(r2), new ThreadPoolExecutor.DiscardPolicy());
        addDelayedShutdownHook(r2, r0);
        return r0;
    }

    public static ThreadFactory getNamedThreadFactory(String r3) {
        return new AnonymousClass1(r3, new AtomicLong(1));
    }

    @SuppressLint({"ThreadPoolCreation"})
    private static ExecutorService newSingleThreadExecutor(ThreadFactory r9, RejectedExecutionHandler r10) {
        LinkedBlockingQueue r6 = new LinkedBlockingQueue();
        return Executors.unconfigurableExecutorService(new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, r6, r9, r10));
    }
}
