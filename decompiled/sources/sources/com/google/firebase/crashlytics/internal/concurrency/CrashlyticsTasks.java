package com.google.firebase.crashlytics.internal.concurrency;

import androidx.credentials.CredentialManager$$ExternalSyntheticLambda0;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes5.dex */
public abstract class CrashlyticsTasks {
    private static final Executor DIRECT = null;

    public static /* synthetic */ Task $r8$lambda$OOY_G5nBeAuFGnnaztKKy0tKXb0(TaskCompletionSource r1, AtomicBoolean r2, CancellationTokenSource r3, Task r4) {
        if (r4.isSuccessful() == false) goto L6;
        r1.trySetResult(r4.getResult());
    L12:
        return Tasks.forResult(null);
    L6:
        if (r4.getException() == null) goto L9;
        r1.trySetException(r4.getException());
        goto L12
    L9:
        if (r2.getAndSet(true) == false) goto L12;
        r3.cancel();
        goto L12
    }

    static {
        DIRECT = new CredentialManager$$ExternalSyntheticLambda0();
    }

    public static <T> Task<T> race(Task<T> r4, Task<T> r5) {
        CancellationTokenSource r0 = new CancellationTokenSource();
        TaskCompletionSource r1 = new TaskCompletionSource(r0.getToken());
        CrashlyticsTasks$$ExternalSyntheticLambda0 r3 = new CrashlyticsTasks$$ExternalSyntheticLambda0(r1, new AtomicBoolean(false), r0);
        Executor r02 = DIRECT;
        r4.continueWithTask(r02, r3);
        r5.continueWithTask(r02, r3);
        return r1.getTask();
    }
}
