package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.Gifts.GiftMessageBottomSheet;
import org.telegram.ui.Stars.StarGiftSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda157 implements Utilities.Callback2 {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ TL_stars.TL_starGiftUnique f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ TLRPC.TL_textWithEntities f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ GiftMessageBottomSheet f$5;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda157(StarGiftSheet r1, TL_stars.TL_starGiftUnique r2, long r3, TLRPC.TL_textWithEntities r5, boolean r6, GiftMessageBottomSheet r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r10, Object r11) {
        StarGiftSheet.$r8$lambda$Xn_US33YrpoURtAUqw3awCJWTvA(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, (StarGiftSheet.PaymentFormState) r10, (Browser.Progress) r11);
    }
}
