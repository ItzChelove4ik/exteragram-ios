package com.google.mlkit.common.sdkinternal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzrr;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.mlkit.common.MlKitException;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public abstract class ModelResource {
    protected final TaskQueue taskQueue;
    private final AtomicInteger zza;
    private final AtomicBoolean zzb;

    public ModelResource() {
        this.zza = new AtomicInteger(0);
        this.zzb = new AtomicBoolean(false);
        this.taskQueue = new TaskQueue();
    }

    public <T> Task<T> callAfterLoad(Executor r8, Callable<T> r9, CancellationToken r10) {
        if (this.zza.get() <= 0) goto L5;
        boolean r0 = true;
    L6:
        Preconditions.checkState(r0);
        if (r10.isCancellationRequested() == true) goto L9;
        CancellationTokenSource r3 = new CancellationTokenSource();
        TaskCompletionSource r5 = new TaskCompletionSource(r3.getToken());
        zzm r6 = new zzm(r8, r10, r3, r5);
        this.taskQueue.submit(r6, new zzn(this, r10, r3, r9, r5));
        return r5.getTask();
    L9:
        return Tasks.forCanceled();
    L5:
        r0 = false;
        goto L6
    }

    public boolean isLoaded() {
        return this.zzb.get();
    }

    public abstract void load();

    public void pin() {
        this.zza.incrementAndGet();
    }

    public abstract void release();

    public void unpin(Executor r1) {
        unpinWithTask(r1);
    }

    public Task<Void> unpinWithTask(Executor r3) {
        if (this.zza.get() <= 0) goto L5;
        boolean r0 = true;
    L6:
        Preconditions.checkState(r0);
        TaskCompletionSource r02 = new TaskCompletionSource();
        zzl r1 = new zzl(this, r02);
        this.taskQueue.submit(r3, r1);
        return r02.getTask();
    L5:
        r0 = false;
        goto L6
    }

    public final /* synthetic */ void zza(CancellationToken r3, CancellationTokenSource r4, Callable r5, TaskCompletionSource r6) {
        if (r3.isCancellationRequested() == false) goto L33;
        r4.cancel();
        return;
    L33:
    L11:
        e = move-exception;
        throw new MlKitException("Internal error has occurred when executing ML Kit tasks", 13, e);     // Catch: Exception -> L9
    L7:
        if (this.zzb.get() == true) goto L14;
        load();     // Catch: Exception -> L9 RuntimeException -> L11
        this.zzb.set(true);     // Catch: Exception -> L9 RuntimeException -> L11
    L14:
        if (r3.isCancellationRequested() == false) goto L17;
        r4.cancel();     // Catch: Exception -> L9 RuntimeException -> L11
        return;
    L17:
        Object r2 = r5.call();     // Catch: Exception -> L9 RuntimeException -> L11
        if (r3.isCancellationRequested() == false) goto L22;
        r4.cancel();     // Catch: Exception -> L9
        return;
    L22:
        r6.setResult(r2);     // Catch: Exception -> L9
        return;
    L9:
        e = move-exception;
        if (r3.isCancellationRequested() == false) goto L30;
        r4.cancel();
        return;
    L30:
        r6.setException(e);
    }

    public final /* synthetic */ void zzb(TaskCompletionSource r4) {
        int r0 = this.zza.decrementAndGet();
        if (r0 < 0) goto L5;
        boolean r2 = true;
    L6:
        Preconditions.checkState(r2);
        if (r0 != 0) goto L9;
        release();
        this.zzb.set(false);
    L9:
        zzrr.zza();
        r4.setResult(null);
        return;
    L5:
        r2 = false;
        goto L6
    }
}
