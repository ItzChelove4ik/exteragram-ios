package org.telegram.ui;

import java.util.concurrent.CountDownLatch;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChannelBoostLayout$$ExternalSyntheticLambda5 implements RequestDelegate {
    public final /* synthetic */ ChannelBoostLayout f$0;
    public final /* synthetic */ CountDownLatch f$1;
    public final /* synthetic */ Runnable f$2;

    public /* synthetic */ ChannelBoostLayout$$ExternalSyntheticLambda5(ChannelBoostLayout r1, CountDownLatch r2, Runnable r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        ChannelBoostLayout.m7259$r8$lambda$MbNqT3nOR2EdEcngSMP2g8ILT8(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
