package org.telegram.ui.Gifts;

import java.util.Comparator;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.Gifts.ResaleGiftsFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda10 implements Comparator {
    public final /* synthetic */ ResaleGiftsFragment.SelectGiftSheet.State f$0;

    public /* synthetic */ ResaleGiftsFragment$SelectGiftSheet$$ExternalSyntheticLambda10(ResaleGiftsFragment.SelectGiftSheet.State r1) {
        this.f$0 = r1;
    }

    @Override // java.util.Comparator
    public final int compare(Object r1, Object r2) {
        return ResaleGiftsFragment.SelectGiftSheet.m14990$r8$lambda$sn7TqtNUbVqBdAJIr3uNpOA5Lc(this.f$0, (TL_stars.starGiftAttributeModel) r1, (TL_stars.starGiftAttributeModel) r2);
    }
}
