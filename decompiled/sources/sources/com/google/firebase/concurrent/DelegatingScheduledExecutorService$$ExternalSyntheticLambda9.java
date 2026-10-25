package com.google.firebase.concurrent;

import com.google.firebase.concurrent.DelegatingScheduledFuture;
import java.util.concurrent.Callable;

/* loaded from: classes5.dex */
public final /* synthetic */ class DelegatingScheduledExecutorService$$ExternalSyntheticLambda9 implements Runnable {
    public final /* synthetic */ Callable f$0;
    public final /* synthetic */ DelegatingScheduledFuture.Completer f$1;

    public /* synthetic */ DelegatingScheduledExecutorService$$ExternalSyntheticLambda9(Callable r1, DelegatingScheduledFuture.Completer r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        DelegatingScheduledExecutorService.$r8$lambda$DPkf5Azy2quQUi97kfFjV0McX98(this.f$0, this.f$1);
    }
}
