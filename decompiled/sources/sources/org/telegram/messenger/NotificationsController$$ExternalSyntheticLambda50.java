package org.telegram.messenger;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes.dex */
public final /* synthetic */ class NotificationsController$$ExternalSyntheticLambda50 implements Runnable {
    public final /* synthetic */ NotificationsController f$0;
    public final /* synthetic */ ArrayList f$1;
    public final /* synthetic */ ArrayList f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ CountDownLatch f$5;

    public /* synthetic */ NotificationsController$$ExternalSyntheticLambda50(NotificationsController r1, ArrayList r2, ArrayList r3, boolean r4, boolean r5, CountDownLatch r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        NotificationsController.$r8$lambda$7n_PLpM0jcPV0mVTzOdTr28W3To(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
