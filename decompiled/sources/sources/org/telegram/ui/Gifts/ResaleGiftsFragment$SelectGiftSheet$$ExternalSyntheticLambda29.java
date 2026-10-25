package org.telegram.ui.Gifts;

import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.Gifts.ResaleGiftsFragment;
import org.telegram.ui.Stars.StarGiftSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda29 implements Utilities.Callback2 {
    public final /* synthetic */ ResaleGiftsFragment.SelectGiftSheet f$0;
    public final /* synthetic */ TL_stars.TL_starGiftUnique f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda29(ResaleGiftsFragment.SelectGiftSheet r1, TL_stars.TL_starGiftUnique r2, long r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r7, Object r8) {
        ResaleGiftsFragment.SelectGiftSheet.$r8$lambda$n8Cl92jiJChXSGAPBXgMh4pfiQY(this.f$0, this.f$1, this.f$2, (StarGiftSheet.PaymentFormState) r7, (Browser.Progress) r8);
    }
}
