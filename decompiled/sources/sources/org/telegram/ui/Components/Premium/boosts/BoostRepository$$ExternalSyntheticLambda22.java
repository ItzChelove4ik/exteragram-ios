package org.telegram.ui.Components.Premium.boosts;

import org.telegram.messenger.MessagesController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class BoostRepository$$ExternalSyntheticLambda22 implements Runnable {
    public final /* synthetic */ TLRPC.TL_error f$0;
    public final /* synthetic */ Utilities.Callback f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ MessagesController f$3;
    public final /* synthetic */ TLRPC.TL_inputInvoicePremiumGiftCode f$4;
    public final /* synthetic */ BaseFragment f$5;
    public final /* synthetic */ Utilities.Callback f$6;

    public /* synthetic */ BoostRepository$$ExternalSyntheticLambda22(TLRPC.TL_error r1, Utilities.Callback r2, TLObject r3, MessagesController r4, TLRPC.TL_inputInvoicePremiumGiftCode r5, BaseFragment r6, Utilities.Callback r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BoostRepository.$r8$lambda$VfNNzLz2r9dG197A_eLlLbrOSU8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
