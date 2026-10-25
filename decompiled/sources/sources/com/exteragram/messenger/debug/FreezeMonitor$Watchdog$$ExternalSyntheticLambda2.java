package com.exteragram.messenger.debug;

import com.exteragram.messenger.debug.FreezeMonitor;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes4.dex */
public final /* synthetic */ class FreezeMonitor$Watchdog$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ CountDownLatch f$0;

    public /* synthetic */ FreezeMonitor$Watchdog$$ExternalSyntheticLambda2(CountDownLatch r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        FreezeMonitor.Watchdog.$r8$lambda$S_pSjC2swheQoZFMbm_beXGVDIQ(this.f$0);
    }
}
