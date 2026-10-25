package com.google.firebase.concurrent;

import com.google.firebase.concurrent.DelegatingScheduledFuture;

/* loaded from: classes5.dex */
public final /* synthetic */ class DelegatingScheduledExecutorService$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ DelegatingScheduledExecutorService f$0;
    public final /* synthetic */ Runnable f$1;
    public final /* synthetic */ DelegatingScheduledFuture.Completer f$2;

    public /* synthetic */ DelegatingScheduledExecutorService$$ExternalSyntheticLambda4(DelegatingScheduledExecutorService r1, Runnable r2, DelegatingScheduledFuture.Completer r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        DelegatingScheduledExecutorService.$r8$lambda$I24AT8SBRztHUrRuo_uZs9aK0oo(this.f$0, this.f$1, this.f$2);
    }
}
