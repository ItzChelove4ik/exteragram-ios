package org.telegram.ui.Stars;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda66 implements RequestDelegate {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ TLRPC.Document f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ TL_stars.saveStarGift f$4;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda66(StarGiftSheet r1, boolean r2, TLRPC.Document r3, boolean r4, TL_stars.saveStarGift r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        StarGiftSheet.$r8$lambda$5r00nOIv2gp2xaTwal2qxjQyN1k(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
