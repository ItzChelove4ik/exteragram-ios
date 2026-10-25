package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;
import org.telegram.messenger.utils.tlutils.AmountUtils$Currency;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stars.StarGiftSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$ResaleBuyTransferAlert$$ExternalSyntheticLambda4 implements Utilities.Callback {
    public final /* synthetic */ StarGiftSheet.ResaleBuyTransferAlert f$0;
    public final /* synthetic */ AmountUtils$Currency f$1;

    public /* synthetic */ StarGiftSheet$ResaleBuyTransferAlert$$ExternalSyntheticLambda4(StarGiftSheet.ResaleBuyTransferAlert r1, AmountUtils$Currency r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        StarGiftSheet.ResaleBuyTransferAlert.$r8$lambda$LOV2QiXXD9CtDCYjrDIYFXlIlOk(this.f$0, this.f$1, (TLRPC.TL_payments_paymentFormStarGift) r2);
    }
}
