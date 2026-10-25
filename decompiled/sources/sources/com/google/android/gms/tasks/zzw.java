package com.google.android.gms.tasks;

import android.app.Activity;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
final class zzw<TResult> extends Task<TResult> {
    private final Object zza;
    private final zzr zzb;
    private boolean zzc;
    private volatile boolean zzd;
    private Object zze;
    private Exception zzf;

    public zzw() {
        this.zza = new Object();
        this.zzb = new zzr();
    }

    private final void zzf() {
        Preconditions.checkState(this.zzc, "Task is not yet complete");
    }

    private final void zzg() {
        if (this.zzc == true) goto L6;
        return;
    L6:
        throw DuplicateTaskCompletionException.of(this);
    }

    private final void zzh() {
        if (this.zzd == true) goto L6;
        return;
    L6:
        throw new CancellationException("Task is already canceled.");
    }

    private final void zzi() {
        Object r0 = this.zza;
        monitor-enter(r0);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (this.zzc == true) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        monitor-exit(r0);     // Catch: Throwable -> L8
        this.zzb.zzb(this);
    }

    @Override // com.google.android.gms.tasks.Task
    public final Task<TResult> addOnCanceledListener(Executor r2, OnCanceledListener r3) {
        zzh r0 = new zzh(r2, r3);
        this.zzb.zza(r0);
        zzi();
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public final Task<TResult> addOnCompleteListener(Activity r3, OnCompleteListener<TResult> r4) {
        zzj r0 = new zzj(TaskExecutors.MAIN_THREAD, r4);
        this.zzb.zza(r0);
        zzv.zza(r3).zzb(r0);
        zzi();
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public final Task<TResult> addOnCompleteListener(OnCompleteListener<TResult> r3) {
        zzj r1 = new zzj(TaskExecutors.MAIN_THREAD, r3);
        this.zzb.zza(r1);
        zzi();
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public final Task<TResult> addOnCompleteListener(Executor r2, OnCompleteListener<TResult> r3) {
        zzj r0 = new zzj(r2, r3);
        this.zzb.zza(r0);
        zzi();
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public final Task<TResult> addOnFailureListener(OnFailureListener r2) {
        addOnFailureListener(TaskExecutors.MAIN_THREAD, r2);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public final Task<TResult> addOnFailureListener(Executor r2, OnFailureListener r3) {
        zzl r0 = new zzl(r2, r3);
        this.zzb.zza(r0);
        zzi();
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public final Task<TResult> addOnSuccessListener(OnSuccessListener<? super TResult> r2) {
        addOnSuccessListener(TaskExecutors.MAIN_THREAD, r2);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public final Task<TResult> addOnSuccessListener(Executor r2, OnSuccessListener<? super TResult> r3) {
        zzn r0 = new zzn(r2, r3);
        this.zzb.zza(r0);
        zzi();
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public final <TContinuationResult> Task<TContinuationResult> continueWith(Executor r3, Continuation<TResult, TContinuationResult> r4) {
        zzw r0 = new zzw();
        zzd r1 = new zzd(r3, r4, r0);
        this.zzb.zza(r1);
        zzi();
        return r0;
    }

    @Override // com.google.android.gms.tasks.Task
    public final <TContinuationResult> Task<TContinuationResult> continueWithTask(Executor r3, Continuation<TResult, Task<TContinuationResult>> r4) {
        zzw r0 = new zzw();
        zzf r1 = new zzf(r3, r4, r0);
        this.zzb.zza(r1);
        zzi();
        return r0;
    }

    @Override // com.google.android.gms.tasks.Task
    public final Exception getException() {
        Object r0 = this.zza;
        monitor-enter(r0);
        Exception r1 = this.zzf;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // com.google.android.gms.tasks.Task
    public final TResult getResult() {
        Object r0 = this.zza;
        monitor-enter(r0);
        zzf();     // Catch: Throwable -> L9
        zzh();     // Catch: Throwable -> L9
        Exception r1 = this.zzf;     // Catch: Throwable -> L9
        if (r1 != null) goto L12;
        TResult r2 = (TResult) this.zze;
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r2;
    L12:
        throw new RuntimeExecutionException(r1);     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        throw th;
    }

    @Override // com.google.android.gms.tasks.Task
    public final <X extends Throwable> TResult getResult(Class<X> r4) {
        Object r0 = this.zza;
        monitor-enter(r0);
        zzf();     // Catch: Throwable -> L11
        zzh();     // Catch: Throwable -> L11
        boolean r1 = r4.isInstance(this.zzf);     // Catch: Throwable -> L11
        Exception r2 = this.zzf;
        if (r1 == true) goto L16;
        if (r2 != null) goto L14;
        TResult r3 = (TResult) this.zze;
        monitor-exit(r0);     // Catch: Throwable -> L11
        return r3;
    L14:
        throw new RuntimeExecutionException(r2);     // Catch: Throwable -> L11
    L16:
        throw r4.cast(r2);     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        throw th;
    }

    @Override // com.google.android.gms.tasks.Task
    public final boolean isCanceled() {
        return this.zzd;
    }

    @Override // com.google.android.gms.tasks.Task
    public final boolean isComplete() {
        Object r0 = this.zza;
        monitor-enter(r0);
        boolean r1 = this.zzc;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // com.google.android.gms.tasks.Task
    public final boolean isSuccessful() {
        Object r0 = this.zza;
        monitor-enter(r0);
        boolean r2 = false;
        if (this.zzc == true) goto L7;
    L13:
        monitor-exit(r0);     // Catch: Throwable -> L11
        return r2;
    L7:
        if (this.zzd == true) goto L13;
        if (this.zzf != null) goto L13;
        r2 = true;
    L11:
        th = move-exception;
        throw th;
    }

    @Override // com.google.android.gms.tasks.Task
    public final <TContinuationResult> Task<TContinuationResult> onSuccessTask(SuccessContinuation<TResult, TContinuationResult> r4) {
        Executor r0 = TaskExecutors.MAIN_THREAD;
        zzw r1 = new zzw();
        zzp r2 = new zzp(r0, r4, r1);
        this.zzb.zza(r2);
        zzi();
        return r1;
    }

    @Override // com.google.android.gms.tasks.Task
    public final <TContinuationResult> Task<TContinuationResult> onSuccessTask(Executor r3, SuccessContinuation<TResult, TContinuationResult> r4) {
        zzw r0 = new zzw();
        zzp r1 = new zzp(r3, r4, r0);
        this.zzb.zza(r1);
        zzi();
        return r0;
    }

    public final void zza(Object r3) {
        Object r0 = this.zza;
        monitor-enter(r0);
        zzg();     // Catch: Throwable -> L8
        this.zzc = true;     // Catch: Throwable -> L8
        this.zze = r3;     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        this.zzb.zzb(this);
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public final boolean zzb(Object r3) {
        Object r0 = this.zza;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.zzc == false) goto L11;
        monitor-exit(r0);     // Catch: Throwable -> L9
        return false;
    L11:
        this.zzc = true;     // Catch: Throwable -> L9
        this.zze = r3;     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        this.zzb.zzb(this);
        return true;
    }

    public final void zzc(Exception r3) {
        Preconditions.checkNotNull(r3, "Exception must not be null");
        Object r0 = this.zza;
        monitor-enter(r0);
        zzg();     // Catch: Throwable -> L8
        this.zzc = true;     // Catch: Throwable -> L8
        this.zzf = r3;     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        this.zzb.zzb(this);
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public final boolean zzd(Exception r3) {
        Preconditions.checkNotNull(r3, "Exception must not be null");
        Object r0 = this.zza;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.zzc == false) goto L11;
        monitor-exit(r0);     // Catch: Throwable -> L9
        return false;
    L11:
        this.zzc = true;     // Catch: Throwable -> L9
        this.zzf = r3;     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        this.zzb.zzb(this);
        return true;
    }

    public final boolean zze() {
        Object r0 = this.zza;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.zzc == false) goto L11;
        monitor-exit(r0);     // Catch: Throwable -> L9
        return false;
    L11:
        this.zzc = true;     // Catch: Throwable -> L9
        this.zzd = true;     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        this.zzb.zzb(this);
        return true;
    }
}
