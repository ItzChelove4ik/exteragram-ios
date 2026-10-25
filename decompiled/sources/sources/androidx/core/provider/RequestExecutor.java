package androidx.core.provider;

import android.os.Handler;
import android.os.Process;
import androidx.core.os.ExecutorCompat$HandlerExecutor$$ExternalSyntheticBUOutline0;
import androidx.core.util.Consumer;
import androidx.core.util.Preconditions;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
abstract class RequestExecutor {

    public static class DefaultThreadFactory implements ThreadFactory {
        private int mPriority;
        private String mThreadName;

        public static class ProcessPriorityThread extends Thread {
            private final int mPriority;

            public ProcessPriorityThread(Runnable r1, String r2, int r3) {
                super(r1, r2);
                this.mPriority = r3;
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Process.setThreadPriority(this.mPriority);
                super.run();
            }
        }

        public DefaultThreadFactory(String r1, int r2) {
            this.mThreadName = r1;
            this.mPriority = r2;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable r3) {
            return new ProcessPriorityThread(r3, this.mThreadName, this.mPriority);
        }
    }

    public static class HandlerExecutor implements Executor {
        private final Handler mHandler;

        public HandlerExecutor(Handler r1) {
            this.mHandler = (Handler) Preconditions.checkNotNull(r1);
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable r2) {
            if (this.mHandler.post((Runnable) Preconditions.checkNotNull(r2)) == false) goto L5;
            return;
        L5:
            ExecutorCompat$HandlerExecutor$$ExternalSyntheticBUOutline0.m(this.mHandler, " is shutting down");
        }
    }

    public static class ReplyRunnable<T> implements Runnable {
        private Callable<T> mCallable;
        private Consumer<T> mConsumer;
        private Handler mHandler;

        /* renamed from: androidx.core.provider.RequestExecutor$ReplyRunnable$1, reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ ReplyRunnable this$0;
            final /* synthetic */ Consumer val$consumer;
            final /* synthetic */ Object val$result;

            public AnonymousClass1(ReplyRunnable r1, Consumer r2, Object r3) {
                this.this$0 = r1;
                this.val$consumer = r2;
                this.val$result = r3;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                this.val$consumer.accept(this.val$result);
            }
        }

        public ReplyRunnable(Handler r1, Callable<T> r2, Consumer<T> r3) {
            this.mCallable = r2;
            this.mConsumer = r3;
            this.mHandler = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            T r0 = this.mCallable.call();     // Catch: Exception -> L4
        L5:
            Consumer<T> r1 = this.mConsumer;
            this.mHandler.post(new AnonymousClass1(this, r1, r0));
            return;
        L4:
            r0 = null;
            goto L5
        }
    }

    public static ThreadPoolExecutor createDefaultExecutor(String r8, int r9, int r10) {
        DefaultThreadFactory r7 = new DefaultThreadFactory(r8, r9);
        ThreadPoolExecutor r0 = new ThreadPoolExecutor(0, 1, r10, TimeUnit.MILLISECONDS, new LinkedBlockingDeque(), r7);
        r0.allowCoreThreadTimeOut(true);
        return r0;
    }

    public static Executor createHandlerExecutor(Handler r1) {
        return new HandlerExecutor(r1);
    }

    public static <T> void execute(Executor r2, Callable<T> r3, Consumer<T> r4) {
        r2.execute(new ReplyRunnable(CalleeHandler.create(), r3, r4));
    }

    public static <T> T submit(ExecutorService r1, Callable<T> r2, int r3) {
        return r1.submit(r2).get(r3, TimeUnit.MILLISECONDS);
    L7:
        e = move-exception;
        throw e;
    L9:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    L6:
        throw new InterruptedException("timeout");
    }
}
