package com.google.mlkit.common.sdkinternal;

import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes5.dex */
public class TaskQueue {
    private final Object zza;
    private boolean zzb;
    private final Queue zzc;
    private final AtomicReference zzd;

    public TaskQueue() {
        this.zza = new Object();
        this.zzc = new ArrayDeque();
        this.zzd = new AtomicReference();
    }

    public static /* bridge */ /* synthetic */ AtomicReference zza(TaskQueue r0) {
        return r0.zzd;
    }

    public static /* bridge */ /* synthetic */ void zzb(TaskQueue r0) {
        r0.zzc();
    }

    private final void zzc() {
        Object r0 = this.zza;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.zzc.isEmpty() == false) goto L11;
        this.zzb = false;     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L11:
        zzv r1 = (zzv) this.zzc.remove();     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        zzd(r1.zza, r1.zzb);
    }

    private final void zzd(Executor r2, Runnable r3) {
        r2.execute(new zzt(this, r3));     // Catch: RejectedExecutionException -> L5
        return;
    L5:
        zzc();
    }

    public void checkIsRunningOnCurrentThread() {
        AtomicReference r1 = this.zzd;
        Preconditions.checkState(Thread.currentThread().equals(r1.get()));
    }

    public void submit(Executor r4, Runnable r5) {
        Object r0 = this.zza;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.zzb == false) goto L11;
        this.zzc.add(new zzv(r4, r5, null));     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L11:
        this.zzb = true;     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        zzd(r4, r5);
    }
}
