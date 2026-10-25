package org.telegram.messenger.camera;

import java.util.concurrent.CountDownLatch;

/* loaded from: classes5.dex */
public final /* synthetic */ class CameraController$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ Runnable f$0;
    public final /* synthetic */ CameraSession f$1;
    public final /* synthetic */ CountDownLatch f$2;
    public final /* synthetic */ Runnable f$3;

    public /* synthetic */ CameraController$$ExternalSyntheticLambda2(Runnable r1, CameraSession r2, CountDownLatch r3, Runnable r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CameraController.$r8$lambda$XODWAWSQ9G_8VfVA1G31_hllnYo(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
