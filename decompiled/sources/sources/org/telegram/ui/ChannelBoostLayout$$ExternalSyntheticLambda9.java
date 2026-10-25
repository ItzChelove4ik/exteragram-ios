package org.telegram.ui;

import java.util.concurrent.CountDownLatch;
import org.telegram.tgnet.TLObject;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChannelBoostLayout$$ExternalSyntheticLambda9 implements Runnable {
    public final /* synthetic */ ChannelBoostLayout f$0;
    public final /* synthetic */ CountDownLatch f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ Runnable f$3;

    public /* synthetic */ ChannelBoostLayout$$ExternalSyntheticLambda9(ChannelBoostLayout r1, CountDownLatch r2, TLObject r3, Runnable r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChannelBoostLayout.$r8$lambda$mXlJx1UoTPbDHqk58ySSIzSX1KE(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
