package androidx.work.impl.utils;

import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;

/* loaded from: classes4.dex */
public final /* synthetic */ class CancelWorkRunnable$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ WorkDatabase f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ WorkManagerImpl f$2;

    public /* synthetic */ CancelWorkRunnable$$ExternalSyntheticLambda1(WorkDatabase r1, String r2, WorkManagerImpl r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CancelWorkRunnable.m1001$r8$lambda$7CMxqlNCknD_Q1NSUJ5L0sWGBQ(this.f$0, this.f$1, this.f$2);
    }
}
