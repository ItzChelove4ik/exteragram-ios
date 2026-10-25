package org.telegram.ui.Stars;

import java.util.ArrayList;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.Stars.StarGiftSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$CraftTopView$$ExternalSyntheticLambda8 implements Utilities.Callback2 {
    public final /* synthetic */ StarGiftSheet.CraftTopView f$0;
    public final /* synthetic */ ArrayList f$1;

    public /* synthetic */ StarGiftSheet$CraftTopView$$ExternalSyntheticLambda8(StarGiftSheet.CraftTopView r1, ArrayList r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        StarGiftSheet.CraftTopView.m19035$r8$lambda$ryY3PhdHcaMAU5g4HGskrqkfuU(this.f$0, this.f$1, (TL_stars.StarGift) r2, (Runnable) r3);
    }
}
