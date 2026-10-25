package androidx.work.impl.utils;

import androidx.work.impl.utils.taskexecutor.SerialExecutor;
import java.util.ArrayDeque;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public class SerialExecutorImpl implements SerialExecutor {
    private Runnable mActive;
    private final Executor mExecutor;
    final Object mLock;
    private final ArrayDeque<Task> mTasks;

    public static class Task implements Runnable {
        final Runnable mRunnable;
        final SerialExecutorImpl mSerialExecutor;

        public Task(SerialExecutorImpl r1, Runnable r2) {
            this.mSerialExecutor = r1;
            this.mRunnable = r2;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.mRunnable.run();     // Catch: Throwable -> L11
            Object r0 = this.mSerialExecutor.mLock;
            monitor-enter(r0);
            this.mSerialExecutor.scheduleNext();     // Catch: Throwable -> L8
            monitor-exit(r0);     // Catch: Throwable -> L8
            return;
        L8:
            th = move-exception;
            throw th;
        L11:
            th = move-exception;
            monitor-enter(this.mSerialExecutor.mLock);
            this.mSerialExecutor.scheduleNext();     // Catch: Throwable -> L17
            throw th;
        L17:
            th = move-exception;
            throw th;
        }
    }

    public SerialExecutorImpl(Executor r1) {
        this.mExecutor = r1;
        this.mTasks = new ArrayDeque();
        this.mLock = new Object();
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable r4) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        this.mTasks.add(new Task(this, r4));     // Catch: Throwable -> L7
        if (this.mActive != null) goto L9;
        scheduleNext();     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public void scheduleNext() {
        Task r0 = this.mTasks.poll();
        this.mActive = r0;
        if (r0 == null) goto L6;
        this.mExecutor.execute(r0);
        return;
    }
}
