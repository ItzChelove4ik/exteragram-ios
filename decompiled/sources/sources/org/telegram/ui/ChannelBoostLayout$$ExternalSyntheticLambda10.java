package org.telegram.ui;

import java.util.concurrent.CountDownLatch;
import org.telegram.tgnet.TLObject;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChannelBoostLayout$$ExternalSyntheticLambda10 implements Runnable {
    public final /* synthetic */ ChannelBoostLayout f$0;
    public final /* synthetic */ CountDownLatch f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ Runnable f$3;

    public /* synthetic */ ChannelBoostLayout$$ExternalSyntheticLambda10(ChannelBoostLayout r1, CountDownLatch r2, TLObject r3, Runnable r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChannelBoostLayout.m7257$r8$lambda$5ub_VTUrQ5rbCQ5mD5Wermerno(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
