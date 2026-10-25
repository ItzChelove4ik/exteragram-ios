package com.google.common.util.concurrent;

import java.util.concurrent.Executor;

/* loaded from: classes5.dex */
public final /* synthetic */ class MoreExecutors$$ExternalSyntheticLambda0 implements Executor {
    public final /* synthetic */ Executor f$0;
    public final /* synthetic */ AbstractFuture f$1;

    public /* synthetic */ MoreExecutors$$ExternalSyntheticLambda0(Executor r1, AbstractFuture r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable r2) {
        MoreExecutors.m1916$r8$lambda$24Lb7_WGbzzKYnIZlepgCofldg(this.f$0, this.f$1, r2);
    }
}
