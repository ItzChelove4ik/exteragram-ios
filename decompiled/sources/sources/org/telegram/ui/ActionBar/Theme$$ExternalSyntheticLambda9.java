package org.telegram.ui.ActionBar;

import java.util.concurrent.CountDownLatch;

/* loaded from: classes6.dex */
public final /* synthetic */ class Theme$$ExternalSyntheticLambda9 implements Runnable {
    public final /* synthetic */ CountDownLatch f$0;

    public /* synthetic */ Theme$$ExternalSyntheticLambda9(CountDownLatch r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.countDown();
    }
}
