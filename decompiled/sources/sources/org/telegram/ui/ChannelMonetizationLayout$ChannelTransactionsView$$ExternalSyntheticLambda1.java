package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ChannelMonetizationLayout;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChannelMonetizationLayout$ChannelTransactionsView$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ ChannelMonetizationLayout.ChannelTransactionsView f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ boolean f$3;

    public /* synthetic */ ChannelMonetizationLayout$ChannelTransactionsView$$ExternalSyntheticLambda1(ChannelMonetizationLayout.ChannelTransactionsView r1, int r2, boolean r3, boolean r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        ChannelMonetizationLayout.ChannelTransactionsView.$r8$lambda$eafSbki6JpJtyaHAzsMrEEsWE1A(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
