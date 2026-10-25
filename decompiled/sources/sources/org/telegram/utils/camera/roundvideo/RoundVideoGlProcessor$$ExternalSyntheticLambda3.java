package org.telegram.utils.camera.roundvideo;

import android.os.HandlerThread;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes6.dex */
public final /* synthetic */ class RoundVideoGlProcessor$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ RoundVideoGlProcessor f$0;
    public final /* synthetic */ HandlerThread f$1;
    public final /* synthetic */ CountDownLatch f$2;

    public /* synthetic */ RoundVideoGlProcessor$$ExternalSyntheticLambda3(RoundVideoGlProcessor r1, HandlerThread r2, CountDownLatch r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        RoundVideoGlProcessor.$r8$lambda$rt7ow4nR2CaQWIcuh_2PxTqRnOM(this.f$0, this.f$1, this.f$2);
    }
}
