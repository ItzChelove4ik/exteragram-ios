package androidx.work.impl;

import androidx.work.Configuration;
import androidx.work.impl.model.WorkGenerationalId;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public final /* synthetic */ class Schedulers$$ExternalSyntheticLambda0 implements ExecutionListener {
    public final /* synthetic */ Executor f$0;
    public final /* synthetic */ List f$1;
    public final /* synthetic */ Configuration f$2;
    public final /* synthetic */ WorkDatabase f$3;

    public /* synthetic */ Schedulers$$ExternalSyntheticLambda0(Executor r1, List r2, Configuration r3, WorkDatabase r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // androidx.work.impl.ExecutionListener
    public final void onExecuted(WorkGenerationalId r7, boolean r8) {
        Schedulers.$r8$lambda$1z_T5hxAFjQEk6BflvLItKL7TzY(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
