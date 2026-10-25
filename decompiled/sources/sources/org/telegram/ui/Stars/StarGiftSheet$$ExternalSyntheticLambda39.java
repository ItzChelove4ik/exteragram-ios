package org.telegram.ui.Stars;

import org.telegram.messenger.utils.tlutils.AmountUtils$Currency;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.Gifts.GiftMessageBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda39 implements GiftMessageBottomSheet.Callback {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ GiftMessageBottomSheet f$1;
    public final /* synthetic */ TL_stars.TL_starGiftUnique f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ AmountUtils$Currency f$4;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda39(StarGiftSheet r1, GiftMessageBottomSheet r2, TL_stars.TL_starGiftUnique r3, long r4, AmountUtils$Currency r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
    }

    @Override // org.telegram.ui.Gifts.GiftMessageBottomSheet.Callback
    public final void performSend(TLRPC.TL_textWithEntities r9, boolean r10) {
        StarGiftSheet.$r8$lambda$8_y3XnSOa4QRATd329f9a_bRmsY(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r9, r10);
    }
}
