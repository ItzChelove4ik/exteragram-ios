package org.telegram.ui.Stars;

import org.telegram.messenger.utils.tlutils.AmountUtils$Amount;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda118 implements RequestDelegate {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ TL_stars.TL_starGiftUnique f$1;
    public final /* synthetic */ AmountUtils$Amount f$2;
    public final /* synthetic */ Runnable f$3;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda118(StarGiftSheet r1, TL_stars.TL_starGiftUnique r2, AmountUtils$Amount r3, Runnable r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        StarGiftSheet.$r8$lambda$hFEuHqqSrETBDiwzfg8P8aIuYQQ(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
