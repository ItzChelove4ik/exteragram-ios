package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.messenger.utils.tlutils.AmountUtils$Currency;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;

/* loaded from: classes6.dex */
public final /* synthetic */ class PeerColorActivity$$ExternalSyntheticLambda14 implements Utilities.Callback {
    public final /* synthetic */ PeerColorActivity f$0;
    public final /* synthetic */ AmountUtils$Currency f$1;
    public final /* synthetic */ TL_stars.TL_starGiftUnique f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ Utilities.Callback f$4;

    public /* synthetic */ PeerColorActivity$$ExternalSyntheticLambda14(PeerColorActivity r1, AmountUtils$Currency r2, TL_stars.TL_starGiftUnique r3, long r4, Utilities.Callback r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r8) {
        PeerColorActivity.m16669$r8$lambda$XE2GqWzbGG0msuL2O7thG3S_bk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, (TLRPC.TL_payments_paymentFormStarGift) r8);
    }
}
