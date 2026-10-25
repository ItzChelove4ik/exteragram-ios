package com.google.firebase.crashlytics.internal.concurrency;

import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes5.dex */
public final /* synthetic */ class CrashlyticsTasks$$ExternalSyntheticLambda0 implements Continuation {
    public final /* synthetic */ TaskCompletionSource f$0;
    public final /* synthetic */ AtomicBoolean f$1;
    public final /* synthetic */ CancellationTokenSource f$2;

    public /* synthetic */ CrashlyticsTasks$$ExternalSyntheticLambda0(TaskCompletionSource r1, AtomicBoolean r2, CancellationTokenSource r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final Object then(Task r3) {
        return CrashlyticsTasks.$r8$lambda$OOY_G5nBeAuFGnnaztKKy0tKXb0(this.f$0, this.f$1, this.f$2, r3);
    }
}
