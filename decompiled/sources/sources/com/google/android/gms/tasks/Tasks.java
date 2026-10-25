package com.google.android.gms.tasks;

import c.f$$ExternalSyntheticBUOutline2;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes.dex */
public abstract class Tasks {
    public static <TResult> TResult await(Task<TResult> r2) {
        Preconditions.checkNotMainThread();
        Preconditions.checkNotGoogleApiHandlerThread();
        Preconditions.checkNotNull(r2, "Task must not be null");
        if (r2.isComplete() == true) goto L5;
        zzaa r0 = new zzaa(null);
        zzb(r2, r0);
        r0.zza();
        return (TResult) zza(r2);
    L5:
        return (TResult) zza(r2);
    }

    public static <TResult> TResult await(Task<TResult> r2, long r3, TimeUnit r5) {
        Preconditions.checkNotMainThread();
        Preconditions.checkNotGoogleApiHandlerThread();
        Preconditions.checkNotNull(r2, "Task must not be null");
        Preconditions.checkNotNull(r5, "TimeUnit must not be null");
        if (r2.isComplete() == true) goto L5;
        zzaa r0 = new zzaa(null);
        zzb(r2, r0);
        if (r0.zzb(r3, r5) == false) goto L11;
        return (TResult) zza(r2);
    L11:
        throw new TimeoutException("Timed out waiting for Task");
    L5:
        return (TResult) zza(r2);
    }

    @Deprecated
    public static <TResult> Task<TResult> call(Executor r2, Callable<TResult> r3) {
        Preconditions.checkNotNull(r2, "Executor must not be null");
        Preconditions.checkNotNull(r3, "Callback must not be null");
        zzw r0 = new zzw();
        r2.execute(new zzx(r0, r3));
        return r0;
    }

    public static <TResult> Task<TResult> forCanceled() {
        zzw r0 = new zzw();
        r0.zze();
        return r0;
    }

    public static <TResult> Task<TResult> forException(Exception r1) {
        zzw r0 = new zzw();
        r0.zzc(r1);
        return r0;
    }

    public static <TResult> Task<TResult> forResult(TResult r1) {
        zzw r0 = new zzw();
        r0.zza(r1);
        return r0;
    }

    public static Task<Void> whenAll(Collection<? extends Task<?>> r3) {
        if (r3 == null) goto L21;
        if (r3.isEmpty() == true) goto L21;
        Iterator<? extends Task<?>> r1 = r3.iterator();
    L9:
        if (r1.hasNext() == false) goto L15;
        if (r1.next() != null) goto L9;
        f$$ExternalSyntheticBUOutline2.m("null tasks are not accepted");
        return null;
    L15:
        zzw r0 = new zzw();
        zzae r12 = new zzae(r3.size(), r0);
        Iterator<? extends Task<?>> r32 = r3.iterator();
    L17:
        if (r32.hasNext() == false) goto L19;
        zzb(r32.next(), r12);
        goto L17
    L19:
        return r0;
    L21:
        return forResult(null);
    }

    public static Task<Void> whenAll(Task<?>... r1) {
        if (r1 == null) goto L9;
        if (r1.length == 0) goto L9;
        return whenAll(Arrays.asList(r1));
    L9:
        return forResult(null);
    }

    public static Task<List<Task<?>>> whenAllComplete(Collection<? extends Task<?>> r1) {
        return whenAllComplete(TaskExecutors.MAIN_THREAD, r1);
    }

    public static Task<List<Task<?>>> whenAllComplete(Executor r2, Collection<? extends Task<?>> r3) {
        if (r3 == null) goto L9;
        if (r3.isEmpty() == true) goto L9;
        return whenAll(r3).continueWithTask(r2, new zzz(r3));
    L9:
        return forResult(Collections.EMPTY_LIST);
    }

    public static Task<List<Task<?>>> whenAllComplete(Task<?>... r1) {
        if (r1 == null) goto L9;
        if (r1.length == 0) goto L9;
        return whenAllComplete(Arrays.asList(r1));
    L9:
        return forResult(Collections.EMPTY_LIST);
    }

    private static Object zza(Task r1) {
        if (r1.isSuccessful() == false) goto L7;
        return r1.getResult();
    L7:
        if (r1.isCanceled() == false) goto L11;
        throw new CancellationException("Task is already canceled");
    L11:
        throw new ExecutionException(r1.getException());
    }

    private static void zzb(Task r1, zzab r2) {
        Executor r0 = TaskExecutors.zza;
        r1.addOnSuccessListener(r0, r2);
        r1.addOnFailureListener(r0, r2);
        r1.addOnCanceledListener(r0, r2);
    }
}
