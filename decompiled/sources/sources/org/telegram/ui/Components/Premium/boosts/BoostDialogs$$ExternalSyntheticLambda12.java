package org.telegram.ui.Components.Premium.boosts;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class BoostDialogs$$ExternalSyntheticLambda12 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ TLRPC.TL_payments_giveawayInfoResults f$0;

    public /* synthetic */ BoostDialogs$$ExternalSyntheticLambda12(TLRPC.TL_payments_giveawayInfoResults r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r1, int r2) {
        BoostDialogs.$r8$lambda$e8nUNEQOAbk1f3QlMZPiBpsxQzo(this.f$0, r1, r2);
    }
}
