package org.telegram.ui.Gifts;

import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.Gifts.ResaleGiftsFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda0 implements Utilities.Callback2 {
    public final /* synthetic */ ResaleGiftsFragment.SelectGiftSheet f$0;
    public final /* synthetic */ Browser.Progress f$1;
    public final /* synthetic */ TL_stars.TL_starGiftUnique f$2;

    public /* synthetic */ ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda0(ResaleGiftsFragment.SelectGiftSheet r1, Browser.Progress r2, TL_stars.TL_starGiftUnique r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r3, Object r4) {
        ResaleGiftsFragment.SelectGiftSheet.m14983$r8$lambda$7e_21KuIzds4YOefFeh4DlN2r4(this.f$0, this.f$1, this.f$2, (Boolean) r3, (String) r4);
    }
}
