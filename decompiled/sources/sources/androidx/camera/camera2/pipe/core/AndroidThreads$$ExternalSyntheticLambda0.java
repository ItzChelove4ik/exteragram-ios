package androidx.camera.camera2.pipe.core;

import java.util.concurrent.ThreadFactory;

/* loaded from: classes3.dex */
public final /* synthetic */ class AndroidThreads$$ExternalSyntheticLambda0 implements ThreadFactory {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ ThreadFactory f$1;

    public /* synthetic */ AndroidThreads$$ExternalSyntheticLambda0(int r1, ThreadFactory r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable r2) {
        return AndroidThreads.m494$r8$lambda$jRA0i05wL10EV15Dmt8_svRyC4(this.f$0, this.f$1, r2);
    }
}
