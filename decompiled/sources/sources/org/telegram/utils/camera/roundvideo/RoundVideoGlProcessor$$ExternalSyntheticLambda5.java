package org.telegram.utils.camera.roundvideo;

import android.util.Size;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes6.dex */
public final /* synthetic */ class RoundVideoGlProcessor$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ RoundVideoGlProcessor f$0;
    public final /* synthetic */ Size f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ RuntimeException[] f$4;
    public final /* synthetic */ CountDownLatch f$5;

    public /* synthetic */ RoundVideoGlProcessor$$ExternalSyntheticLambda5(RoundVideoGlProcessor r1, Size r2, int r3, boolean r4, RuntimeException[] r5, CountDownLatch r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        RoundVideoGlProcessor.$r8$lambda$V8xLYOIrG0lh7qmwkOuU5hwa8kM(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
