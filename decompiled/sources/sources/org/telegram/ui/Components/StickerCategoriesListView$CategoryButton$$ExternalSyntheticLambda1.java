package org.telegram.ui.Components;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.StickerCategoriesListView;

/* loaded from: classes7.dex */
public final /* synthetic */ class StickerCategoriesListView$CategoryButton$$ExternalSyntheticLambda1 implements AnimatedEmojiDrawable.ReceivedDocument {
    public final /* synthetic */ StickerCategoriesListView.CategoryButton f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ StickerCategoriesListView$CategoryButton$$ExternalSyntheticLambda1(StickerCategoriesListView.CategoryButton r1, boolean r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.Components.AnimatedEmojiDrawable.ReceivedDocument
    public final void run(TLRPC.Document r2) {
        StickerCategoriesListView.CategoryButton.$r8$lambda$fNuYMzTPIbUE2XpvTa9nANANcvE(this.f$0, this.f$1, r2);
    }
}
