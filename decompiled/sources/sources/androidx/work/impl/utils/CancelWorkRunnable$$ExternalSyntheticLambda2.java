package androidx.work.impl.utils;

import androidx.work.impl.WorkManagerImpl;
import java.util.UUID;

/* loaded from: classes4.dex */
public final /* synthetic */ class CancelWorkRunnable$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ WorkManagerImpl f$0;
    public final /* synthetic */ UUID f$1;

    public /* synthetic */ CancelWorkRunnable$$ExternalSyntheticLambda2(WorkManagerImpl r1, UUID r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CancelWorkRunnable.$r8$lambda$9OOzgm9vmuSGphBLxiraZo1D9nc(this.f$0, this.f$1);
    }
}
