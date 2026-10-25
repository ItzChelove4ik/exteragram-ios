package org.telegram.ui.Components.Premium.boosts;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class BoostDialogs$$ExternalSyntheticLambda27 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ Utilities.Callback f$0;
    public final /* synthetic */ TLRPC.TL_premiumGiftCodeOption f$1;

    public /* synthetic */ BoostDialogs$$ExternalSyntheticLambda27(Utilities.Callback r1, TLRPC.TL_premiumGiftCodeOption r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r2, int r3) {
        BoostDialogs.$r8$lambda$GGFFoBNRqPVcAybOz38EqP5kdjQ(this.f$0, this.f$1, r2, r3);
    }
}
