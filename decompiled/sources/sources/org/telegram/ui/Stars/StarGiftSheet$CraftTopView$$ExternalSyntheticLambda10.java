package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.Stars.StarGiftSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$CraftTopView$$ExternalSyntheticLambda10 implements Utilities.Callback {
    public final /* synthetic */ StarGiftSheet.CraftTopView f$0;
    public final /* synthetic */ StarGiftSheet.CraftTopView.SelectGiftView f$1;

    public /* synthetic */ StarGiftSheet$CraftTopView$$ExternalSyntheticLambda10(StarGiftSheet.CraftTopView r1, StarGiftSheet.CraftTopView.SelectGiftView r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        StarGiftSheet.CraftTopView.m19030$r8$lambda$IXV1dQpCrJKFmKUPPIn6e4cnjE(this.f$0, this.f$1, (TL_stars.StarGift) r2);
    }
}
