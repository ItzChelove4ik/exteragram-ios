package org.webrtc;

import java.util.concurrent.CountDownLatch;
import org.webrtc.EglRenderer;

/* loaded from: classes6.dex */
public final /* synthetic */ class EglRenderer$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ EglRenderer f$0;
    public final /* synthetic */ CountDownLatch f$1;
    public final /* synthetic */ EglRenderer.FrameListener f$2;

    public /* synthetic */ EglRenderer$$ExternalSyntheticLambda7(EglRenderer r1, CountDownLatch r2, EglRenderer.FrameListener r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        EglRenderer.$r8$lambda$8ptpXprPQ1TTclYjTSMzvC_VkXA(this.f$0, this.f$1, this.f$2);
    }
}
