package org.telegram.ui.Stars;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stars.BotStarsController;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotStarsController$ChannelConnectedBots$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ BotStarsController.ChannelConnectedBots f$0;

    public /* synthetic */ BotStarsController$ChannelConnectedBots$$ExternalSyntheticLambda0(BotStarsController.ChannelConnectedBots r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        BotStarsController.ChannelConnectedBots.$r8$lambda$Ueob_mr4Myv3FZg1bY7xb4kcIoA(this.f$0, r1, r2);
    }
}
