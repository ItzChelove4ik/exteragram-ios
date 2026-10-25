package org.telegram.ui.Gifts;

import java.util.Comparator;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.Gifts.ResaleGiftsFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda16 implements Comparator {
    public final /* synthetic */ ResaleGiftsFragment.SelectGiftSheet.State f$0;

    public /* synthetic */ ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda16(ResaleGiftsFragment.SelectGiftSheet.State r1) {
        this.f$0 = r1;
    }

    @Override // java.util.Comparator
    public final int compare(Object r1, Object r2) {
        return ResaleGiftsFragment.SelectGiftSheet.$r8$lambda$oUj5czK9SfWwZGzQYnwGVC91nKk(this.f$0, (TL_stars.starGiftAttributeBackdrop) r1, (TL_stars.starGiftAttributeBackdrop) r2);
    }
}
