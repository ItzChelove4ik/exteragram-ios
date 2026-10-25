package com.google.firebase.crashlytics.internal.concurrency;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.gms.tasks.zzw;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* loaded from: classes.dex */
public class CrashlyticsWorker implements Executor {
    private final ExecutorService executor;
    private Task<?> tail;
    private final Object tailLock;

    public static /* synthetic */ Task $r8$lambda$QmZlDkBlDR5w6hZfr1BjiCUvNrE(Runnable r0, Task r1) {
        r0.run();
        return Tasks.forResult(null);
    }

    public static /* synthetic */ Task $r8$lambda$ppA3js9NHAaANozeGWXwdEyYIWw(Callable r0, Task r1) {
        return (Task) r0.call();
    }

    public CrashlyticsWorker(ExecutorService r2) {
        this.tailLock = new Object();
        this.tail = Tasks.forResult(null);
        this.executor = r2;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable r1) {
        this.executor.execute(r1);
    }

    public ExecutorService getExecutor() {
        return this.executor;
    }

    public Task<Void> submit(Runnable r5) {
        Object r0 = this.tailLock;
        monitor-enter(r0);
        Task r52 = this.tail.continueWithTask(this.executor, new CrashlyticsWorker$$ExternalSyntheticLambda1(r5));     // Catch: Throwable -> L7
        this.tail = r52;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r52;
    L7:
        th = move-exception;
        throw th;
    }

    public <T> Task<T> submitTask(Callable<Task<T>> r5) {
        Object r0 = this.tailLock;
        monitor-enter(r0);
        zzw r52 = (Task<T>) this.tail.continueWithTask(this.executor, new CrashlyticsWorker$$ExternalSyntheticLambda0(r5));
        this.tail = r52;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r52;
    L7:
        th = move-exception;
        throw th;
    }
}
