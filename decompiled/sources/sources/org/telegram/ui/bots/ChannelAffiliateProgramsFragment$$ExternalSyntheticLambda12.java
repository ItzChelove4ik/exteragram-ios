package org.telegram.ui.bots;

import android.content.Context;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_payments;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda12 implements Runnable {
    public final /* synthetic */ ChannelAffiliateProgramsFragment f$0;
    public final /* synthetic */ Context f$1;
    public final /* synthetic */ TLRPC.User f$2;
    public final /* synthetic */ TL_payments.connectedBotStarRef f$3;

    public /* synthetic */ ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda12(ChannelAffiliateProgramsFragment r1, Context r2, TLRPC.User r3, TL_payments.connectedBotStarRef r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChannelAffiliateProgramsFragment.$r8$lambda$JyLqYpBbt_sueTIyA3qWtGeOAoU(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
