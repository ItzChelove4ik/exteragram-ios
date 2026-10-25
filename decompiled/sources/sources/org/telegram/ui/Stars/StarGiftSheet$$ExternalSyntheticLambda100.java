package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;
import org.telegram.messenger.utils.tlutils.AmountUtils$Currency;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.Gifts.GiftMessageBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda100 implements Utilities.Callback {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ GiftMessageBottomSheet f$1;
    public final /* synthetic */ AmountUtils$Currency f$2;
    public final /* synthetic */ TL_stars.TL_starGiftUnique f$3;
    public final /* synthetic */ long f$4;
    public final /* synthetic */ TLRPC.TL_textWithEntities f$5;
    public final /* synthetic */ boolean f$6;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda100(StarGiftSheet r1, GiftMessageBottomSheet r2, AmountUtils$Currency r3, TL_stars.TL_starGiftUnique r4, long r5, TLRPC.TL_textWithEntities r7, boolean r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r7;
        this.f$6 = r8;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r10) {
        StarGiftSheet.m18998$r8$lambda$w1JsQWGxgbOxS052WWI4mM9aR4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, (TLRPC.TL_payments_paymentFormStarGift) r10);
    }
}
