package com.google.mlkit.common.sdkinternal;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* loaded from: classes5.dex */
public class MLTaskExecutor {
    private static final Object zza = null;
    private static MLTaskExecutor zzb;
    private final Handler zzc;

    static {
        zza = new Object();
    }

    private MLTaskExecutor(Looper r2) {
        this.zzc = new com.google.android.gms.internal.mlkit_common.zza(r2);
    }

    public static MLTaskExecutor getInstance() {
        Object r0 = zza;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (zzb != null) goto L9;
        HandlerThread r1 = new HandlerThread("MLHandler", 9);     // Catch: Throwable -> L7
        r1.start();     // Catch: Throwable -> L7
        zzb = new MLTaskExecutor(r1.getLooper());     // Catch: Throwable -> L7
    L9:
        MLTaskExecutor r12 = zzb;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r12;
    }

    public static Executor workerThreadExecutor() {
        return zzh.zza;
    }

    public static /* bridge */ /* synthetic */ Handler zza(MLTaskExecutor r0) {
        return r0.zzc;
    }

    public <ResultT> Task<ResultT> scheduleCallable(Callable<ResultT> r3) {
        TaskCompletionSource r0 = new TaskCompletionSource();
        scheduleRunnable(new zzf(r3, r0));
        return r0.getTask();
    }

    public void scheduleRunnable(Runnable r1) {
        workerThreadExecutor().execute(r1);
    }
}
