package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class GiftOfferSheet$$ExternalSyntheticLambda12 implements Utilities.Callback2 {
    public final /* synthetic */ GiftOfferSheet f$0;
    public final /* synthetic */ Browser.Progress f$1;
    public final /* synthetic */ AlertDialog f$2;

    public /* synthetic */ GiftOfferSheet$$ExternalSyntheticLambda12(GiftOfferSheet r1, Browser.Progress r2, AlertDialog r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r3, Object r4) {
        GiftOfferSheet.m18893$r8$lambda$ZhRIWHi2Ie9_smWgCZje_c2NZE(this.f$0, this.f$1, this.f$2, (TLRPC.Updates) r3, (TLRPC.TL_error) r4);
    }
}
