package org.telegram.ui.Stars;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stars.BotStarsController;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotStarsController$ChannelSuggestedBots$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ BotStarsController.ChannelSuggestedBots f$0;

    public /* synthetic */ BotStarsController$ChannelSuggestedBots$$ExternalSyntheticLambda0(BotStarsController.ChannelSuggestedBots r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        BotStarsController.ChannelSuggestedBots.m18886$r8$lambda$XtflGBZ_mU6sdPNMEuziHtII(this.f$0, r1, r2);
    }
}
