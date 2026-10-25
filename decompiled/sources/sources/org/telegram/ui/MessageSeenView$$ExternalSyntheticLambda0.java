package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class MessageSeenView$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ MessageSeenView f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ TLRPC.Chat f$3;

    public /* synthetic */ MessageSeenView$$ExternalSyntheticLambda0(MessageSeenView r1, long r2, int r4, TLRPC.Chat r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        MessageSeenView.m16110$r8$lambda$eJXkr4v0wJMS8yAhyhIQ_Rfw6o(this.f$0, this.f$1, this.f$2, this.f$3, r8, r9);
    }
}
