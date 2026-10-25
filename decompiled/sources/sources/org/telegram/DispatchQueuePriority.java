package org.telegram;

import androidx.camera.camera2.config.UseCaseGraphContext$$ExternalSyntheticAutoCloseableForwarder1;
import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.telegram.messenger.FileLog;

/* loaded from: classes.dex */
public class DispatchQueuePriority {
    private volatile CountDownLatch pauseLatch;
    ThreadPoolExecutor threadPoolExecutor;

    /* renamed from: org.telegram.DispatchQueuePriority$1, reason: invalid class name */
    public class AnonymousClass1 extends ThreadPoolExecutor implements AutoCloseable {
        final /* synthetic */ DispatchQueuePriority this$0;

        public AnonymousClass1(DispatchQueuePriority r1, int r2, int r3, long r4, TimeUnit r6, BlockingQueue r7) {
            this.this$0 = r1;
            super(r2, r3, r4, r6, r7);
        }

        @Override // java.util.concurrent.ThreadPoolExecutor
        public void beforeExecute(Thread r1, Runnable r2) {
            CountDownLatch r0 = DispatchQueuePriority.m3865$$Nest$fgetpauseLatch(this.this$0);
            if (r0 == null) goto L11;
            r0.await();     // Catch: InterruptedException -> L6
            return;
        L6:
            e = move-exception;
            FileLog.e(e);
            return;
        }

        @Override // java.lang.AutoCloseable
        public /* synthetic */ void close() {
            UseCaseGraphContext$$ExternalSyntheticAutoCloseableForwarder1.m(this);
        }
    }

    /* renamed from: org.telegram.DispatchQueuePriority$2, reason: invalid class name */
    public class AnonymousClass2 implements Comparator<Runnable> {
        final /* synthetic */ DispatchQueuePriority this$0;

        public AnonymousClass2(DispatchQueuePriority r1) {
            this.this$0 = r1;
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(Runnable r1, Runnable r2) {
            return compare2(r1, r2);
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(Runnable r2, Runnable r3) {
            int r0 = 1;
            if ((r2 instanceof PriorityRunnable) == false) goto L5;
            int r1 = ((PriorityRunnable) r2).priority;
        L7:
            if ((r3 instanceof PriorityRunnable) == false) goto L10;
            r0 = ((PriorityRunnable) r3).priority;
        L10:
            return r0 - r1;
        L5:
            r1 = 1;
            goto L7
        }
    }

    public static class PriorityRunnable implements Runnable {
        final int priority;
        final Runnable runnable;

        private PriorityRunnable(int r1, Runnable r2) {
            this.priority = r1;
            this.runnable = r2;
        }

        public /* synthetic */ PriorityRunnable(int r1, Runnable r2, DispatchQueuePriorityIA r3) {
            this(r1, r2);
        }

        @Override // java.lang.Runnable
        public void run() {
            this.runnable.run();
        }
    }

    /* renamed from: -$$Nest$fgetpauseLatch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CountDownLatch m3865$$Nest$fgetpauseLatch(DispatchQueuePriority r0) {
        return r0.pauseLatch;
    }

    public DispatchQueuePriority(String r9) {
        PriorityBlockingQueue r7 = new PriorityBlockingQueue(10, new AnonymousClass2(this));
        this.threadPoolExecutor = new AnonymousClass1(this, 1, 1, 60, TimeUnit.SECONDS, r7);
    }

    public void cancelRunnable(Runnable r1) {
        if (r1 != null) goto L4;
        return;
    L4:
        this.threadPoolExecutor.remove(r1);
    }

    public void pause() {
        if (this.pauseLatch != null) goto L6;
        this.pauseLatch = new CountDownLatch(1);
        return;
    }

    public Runnable postRunnable(Runnable r3, int r4) {
        if (r4 == 1) goto L5;
        r3 = new PriorityRunnable(r4, r3, null);
    L5:
        postRunnable(r3);
        return r3;
    }

    public void postRunnable(Runnable r1) {
        this.threadPoolExecutor.execute(r1);
    }

    public void resume() {
        CountDownLatch r0 = this.pauseLatch;
        if (r0 == null) goto L6;
        r0.countDown();
        this.pauseLatch = null;
        return;
    }
}
