package com.google.firebase.concurrent;

import com.google.firebase.concurrent.DelegatingScheduledFuture;
import java.util.concurrent.Callable;

/* loaded from: classes5.dex */
public final /* synthetic */ class DelegatingScheduledExecutorService$$ExternalSyntheticLambda6 implements Callable {
    public final /* synthetic */ DelegatingScheduledExecutorService f$0;
    public final /* synthetic */ Callable f$1;
    public final /* synthetic */ DelegatingScheduledFuture.Completer f$2;

    public /* synthetic */ DelegatingScheduledExecutorService$$ExternalSyntheticLambda6(DelegatingScheduledExecutorService r1, Callable r2, DelegatingScheduledFuture.Completer r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        return DelegatingScheduledExecutorService.$r8$lambda$KzftyNrykfqSVPnZs34fOZKBoLI(this.f$0, this.f$1, this.f$2);
    }
}
