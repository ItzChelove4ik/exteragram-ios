package org.telegram.ui.Stars;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotStarsController$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ BotStarsController f$0;
    public final /* synthetic */ long f$1;

    public /* synthetic */ BotStarsController$$ExternalSyntheticLambda1(BotStarsController r1, long r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r4, TLRPC.TL_error r5) {
        BotStarsController.m18884$r8$lambda$nuVT8yrOM7X6PN5dH7oYToUxD4(this.f$0, this.f$1, r4, r5);
    }
}
