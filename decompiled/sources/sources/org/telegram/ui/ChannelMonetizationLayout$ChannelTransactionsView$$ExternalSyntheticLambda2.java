package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ChannelMonetizationLayout;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChannelMonetizationLayout$ChannelTransactionsView$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ ChannelMonetizationLayout.ChannelTransactionsView f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ TLRPC.TL_error f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ boolean f$5;

    public /* synthetic */ ChannelMonetizationLayout$ChannelTransactionsView$$ExternalSyntheticLambda2(ChannelMonetizationLayout.ChannelTransactionsView r1, TLObject r2, int r3, TLRPC.TL_error r4, boolean r5, boolean r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChannelMonetizationLayout.ChannelTransactionsView.$r8$lambda$DpbOIbWUvudBR06qW4VYLeEASvw(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
