package org.telegram.ui.Components.Premium.boosts;

import org.telegram.messenger.MessagesController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class BoostRepository$$ExternalSyntheticLambda17 implements RequestDelegate {
    public final /* synthetic */ Utilities.Callback f$0;
    public final /* synthetic */ MessagesController f$1;
    public final /* synthetic */ TLRPC.TL_inputInvoicePremiumGiftCode f$2;
    public final /* synthetic */ BaseFragment f$3;
    public final /* synthetic */ Utilities.Callback f$4;

    public /* synthetic */ BoostRepository$$ExternalSyntheticLambda17(Utilities.Callback r1, MessagesController r2, TLRPC.TL_inputInvoicePremiumGiftCode r3, BaseFragment r4, Utilities.Callback r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        BoostRepository.m12079$r8$lambda$3oslJHtVF5JIB7UG6Wwe0YSKaQ(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
