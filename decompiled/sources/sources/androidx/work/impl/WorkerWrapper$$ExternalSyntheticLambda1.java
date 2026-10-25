package androidx.work.impl;

import androidx.work.ListenableWorker;
import kotlin.jvm.functions.Function1;

/* loaded from: classes4.dex */
public final /* synthetic */ class WorkerWrapper$$ExternalSyntheticLambda1 implements Function1 {
    public final /* synthetic */ ListenableWorker f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ WorkerWrapper f$3;

    public /* synthetic */ WorkerWrapper$$ExternalSyntheticLambda1(ListenableWorker r1, boolean r2, String r3, WorkerWrapper r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r4) {
        return WorkerWrapper.$r8$lambda$a0mi6STqFu3M5cm3Gtv6LbwfItA(this.f$0, this.f$1, this.f$2, this.f$3, (Throwable) r4);
    }
}
