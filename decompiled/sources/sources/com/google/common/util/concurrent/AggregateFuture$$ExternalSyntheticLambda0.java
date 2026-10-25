package com.google.common.util.concurrent;

/* loaded from: classes5.dex */
public final /* synthetic */ class AggregateFuture$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ AggregateFuture f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ ListenableFuture f$2;

    public /* synthetic */ AggregateFuture$$ExternalSyntheticLambda0(AggregateFuture r1, int r2, ListenableFuture r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AggregateFuture.$r8$lambda$b6iaIjtd22FkSuzCmWyTO5V1cdM(this.f$0, this.f$1, this.f$2);
    }
}
