package com.google.common.util.concurrent;

import com.google.common.util.concurrent.ExecutionSequencer;

/* loaded from: classes5.dex */
public final /* synthetic */ class ExecutionSequencer$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ TrustedListenableFutureTask f$0;
    public final /* synthetic */ SettableFuture f$1;
    public final /* synthetic */ ListenableFuture f$2;
    public final /* synthetic */ ListenableFuture f$3;
    public final /* synthetic */ ExecutionSequencer.TaskNonReentrantExecutor f$4;

    public /* synthetic */ ExecutionSequencer$$ExternalSyntheticLambda0(TrustedListenableFutureTask r1, SettableFuture r2, ListenableFuture r3, ListenableFuture r4, ExecutionSequencer.TaskNonReentrantExecutor r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ExecutionSequencer.$r8$lambda$G1fYN83GH2L7tHBoC2wSsN1KFkk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
