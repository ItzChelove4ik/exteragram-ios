package androidx.work.impl;

import androidx.work.impl.WorkerWrapper;
import java.util.concurrent.Callable;

/* loaded from: classes4.dex */
public final /* synthetic */ class WorkerWrapper$launch$1$$ExternalSyntheticLambda0 implements Callable {
    public final /* synthetic */ WorkerWrapper.Resolution f$0;
    public final /* synthetic */ WorkerWrapper f$1;

    public /* synthetic */ WorkerWrapper$launch$1$$ExternalSyntheticLambda0(WorkerWrapper.Resolution r1, WorkerWrapper r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        return WorkerWrapper.AnonymousClass1.$r8$lambda$Y7GYLbRKyArUGZkgQLkfflh3BxY(this.f$0, this.f$1);
    }
}
