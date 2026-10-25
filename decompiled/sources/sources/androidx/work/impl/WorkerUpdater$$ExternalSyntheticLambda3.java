package androidx.work.impl;

import androidx.work.impl.model.WorkSpec;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public final /* synthetic */ class WorkerUpdater$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ WorkDatabase f$0;
    public final /* synthetic */ WorkSpec f$1;
    public final /* synthetic */ WorkSpec f$2;
    public final /* synthetic */ List f$3;
    public final /* synthetic */ String f$4;
    public final /* synthetic */ Set f$5;
    public final /* synthetic */ boolean f$6;

    public /* synthetic */ WorkerUpdater$$ExternalSyntheticLambda3(WorkDatabase r1, WorkSpec r2, WorkSpec r3, List r4, String r5, Set r6, boolean r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        WorkerUpdater.m979$r8$lambda$5dx0Z4eBB8i1jJkoxlWJyGBKxI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
