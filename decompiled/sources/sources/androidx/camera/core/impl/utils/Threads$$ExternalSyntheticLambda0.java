package androidx.camera.core.impl.utils;

import java.util.concurrent.CountDownLatch;

/* loaded from: classes3.dex */
public final /* synthetic */ class Threads$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Runnable f$0;
    public final /* synthetic */ CountDownLatch f$1;

    public /* synthetic */ Threads$$ExternalSyntheticLambda0(Runnable r1, CountDownLatch r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Threads.$r8$lambda$QhFdgKsW1QVHuVXVeUeDa68bDs0(this.f$0, this.f$1);
    }
}
