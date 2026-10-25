package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.Gifts.GiftMessageBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda180 implements Utilities.Callback2 {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ Browser.Progress f$1;
    public final /* synthetic */ TL_stars.TL_starGiftUnique f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ GiftMessageBottomSheet f$4;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda180(StarGiftSheet r1, Browser.Progress r2, TL_stars.TL_starGiftUnique r3, long r4, GiftMessageBottomSheet r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r9, Object r10) {
        StarGiftSheet.m18996$r8$lambda$r6scimVQSZo6XVjrfRv8z0NtCc(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, (Boolean) r9, (String) r10);
    }
}
