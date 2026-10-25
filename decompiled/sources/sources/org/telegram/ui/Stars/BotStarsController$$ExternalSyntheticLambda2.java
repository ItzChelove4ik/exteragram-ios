package org.telegram.ui.Stars;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stars.BotStarsController;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotStarsController$$ExternalSyntheticLambda2 implements RequestDelegate {
    public final /* synthetic */ BotStarsController f$0;
    public final /* synthetic */ BotStarsController.TransactionsState f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ BotStarsController$$ExternalSyntheticLambda2(BotStarsController r1, BotStarsController.TransactionsState r2, int r3, long r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        BotStarsController.m18882$r8$lambda$fnBuZ8JjoxSpxZ9ozd9QQZ7fQo(this.f$0, this.f$1, this.f$2, this.f$3, r8, r9);
    }
}
