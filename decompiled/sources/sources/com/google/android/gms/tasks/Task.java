package com.google.android.gms.tasks;

import android.app.Activity;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public abstract class Task<TResult> {
    public Task() {
    }

    public abstract Task<TResult> addOnCanceledListener(Executor r1, OnCanceledListener r2);

    public abstract Task<TResult> addOnCompleteListener(Activity r1, OnCompleteListener<TResult> r2);

    public abstract Task<TResult> addOnCompleteListener(OnCompleteListener<TResult> r1);

    public abstract Task<TResult> addOnCompleteListener(Executor r1, OnCompleteListener<TResult> r2);

    public abstract Task<TResult> addOnFailureListener(OnFailureListener r1);

    public abstract Task<TResult> addOnFailureListener(Executor r1, OnFailureListener r2);

    public abstract Task<TResult> addOnSuccessListener(OnSuccessListener<? super TResult> r1);

    public abstract Task<TResult> addOnSuccessListener(Executor r1, OnSuccessListener<? super TResult> r2);

    public abstract <TContinuationResult> Task<TContinuationResult> continueWith(Executor r1, Continuation<TResult, TContinuationResult> r2);

    public abstract <TContinuationResult> Task<TContinuationResult> continueWithTask(Executor r1, Continuation<TResult, Task<TContinuationResult>> r2);

    public abstract Exception getException();

    public abstract TResult getResult();

    public abstract <X extends Throwable> TResult getResult(Class<X> r1);

    public abstract boolean isCanceled();

    public abstract boolean isComplete();

    public abstract boolean isSuccessful();

    public abstract <TContinuationResult> Task<TContinuationResult> onSuccessTask(SuccessContinuation<TResult, TContinuationResult> r1);

    public abstract <TContinuationResult> Task<TContinuationResult> onSuccessTask(Executor r1, SuccessContinuation<TResult, TContinuationResult> r2);
}
