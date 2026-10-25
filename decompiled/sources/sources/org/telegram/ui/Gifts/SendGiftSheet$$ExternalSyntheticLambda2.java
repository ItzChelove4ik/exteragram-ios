package org.telegram.ui.Gifts;

import android.view.View;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.Components.Premium.GiftPremiumBottomSheet$GiftTier;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes7.dex */
public final /* synthetic */ class SendGiftSheet$$ExternalSyntheticLambda2 implements RecyclerListView.OnItemClickListener {
    public final /* synthetic */ SendGiftSheet f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ TL_stars.StarGift f$3;
    public final /* synthetic */ GiftPremiumBottomSheet$GiftTier f$4;

    public /* synthetic */ SendGiftSheet$$ExternalSyntheticLambda2(SendGiftSheet r1, boolean r2, boolean r3, TL_stars.StarGift r4, GiftPremiumBottomSheet$GiftTier r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.OnItemClickListener
    public final void onItemClick(View r8, int r9) {
        SendGiftSheet.m15010$r8$lambda$nO_St4H3ycYEYJSRUna1kTt0l4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
