package org.telegram.ui.Gifts;

import org.telegram.messenger.Utilities;
import org.telegram.messenger.utils.tlutils.AmountUtils$Currency;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.Gifts.ResaleGiftsFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda28 implements Utilities.Callback {
    public final /* synthetic */ ResaleGiftsFragment.SelectGiftSheet f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ AmountUtils$Currency f$2;
    public final /* synthetic */ TL_stars.TL_starGiftUnique f$3;
    public final /* synthetic */ long f$4;

    public /* synthetic */ ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda28(ResaleGiftsFragment.SelectGiftSheet r1, AlertDialog r2, AmountUtils$Currency r3, TL_stars.TL_starGiftUnique r4, long r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r8) {
        ResaleGiftsFragment.SelectGiftSheet.$r8$lambda$JGP3VTxFSY5ZesaZD3GWvNGw370(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, (TLRPC.TL_payments_paymentFormStarGift) r8);
    }
}
