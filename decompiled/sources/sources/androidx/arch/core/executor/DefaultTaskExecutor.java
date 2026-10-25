package androidx.arch.core.executor;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class DefaultTaskExecutor extends TaskExecutor {
    private final ExecutorService mDiskIO;
    private final Object mLock;
    private volatile Handler mMainHandler;

    /* renamed from: androidx.arch.core.executor.DefaultTaskExecutor$1, reason: invalid class name */
    public class AnonymousClass1 implements ThreadFactory {
        private final AtomicInteger mThreadId;
        final /* synthetic */ DefaultTaskExecutor this$0;

        public AnonymousClass1(DefaultTaskExecutor r2) {
            this.this$0 = r2;
            this.mThreadId = new AtomicInteger(0);
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable r3) {
            Thread r0 = new Thread(r3);
            r0.setName("arch_disk_io_" + this.mThreadId.getAndIncrement());
            return r0;
        }
    }

    public static class Api28Impl {
        public static Handler createAsync(Looper r0) {
            return Handler.createAsync(r0);
        }
    }

    public DefaultTaskExecutor() {
        this.mLock = new Object();
        this.mDiskIO = Executors.newFixedThreadPool(4, new AnonymousClass1(this));
    }

    private static Handler createAsync(Looper r7) {
        if (Build.VERSION.SDK_INT >= 28) goto L5;
        return (Handler) Handler.class.getDeclaredConstructor(new Class[]{Looper.class, Handler.Callback.class, Boolean.TYPE}).newInstance(new Object[]{r7, null, Boolean.TRUE});
    L11:
        return new Handler(r7);
    L9:
        return new Handler(r7);
    L5:
        return Api28Impl.createAsync(r7);
    }

    @Override // androidx.arch.core.executor.TaskExecutor
    public void executeOnDiskIO(Runnable r1) {
        this.mDiskIO.execute(r1);
    }

    @Override // androidx.arch.core.executor.TaskExecutor
    public boolean isMainThread() {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // androidx.arch.core.executor.TaskExecutor
    public void postToMainThread(Runnable r3) {
        if (this.mMainHandler != null) goto L15;
        Object r0 = this.mLock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L7:
        if (this.mMainHandler != null) goto L11;
        this.mMainHandler = createAsync(Looper.getMainLooper());     // Catch: Throwable -> L9
    L11:
        monitor-exit(r0);     // Catch: Throwable -> L9
    L15:
        this.mMainHandler.post(r3);
    }
}
