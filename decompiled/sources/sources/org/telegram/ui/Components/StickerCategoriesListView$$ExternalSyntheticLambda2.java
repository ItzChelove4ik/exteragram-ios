package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.StickerCategoriesListView;

/* loaded from: classes7.dex */
public final /* synthetic */ class StickerCategoriesListView$$ExternalSyntheticLambda2 implements Utilities.Callback {
    public final /* synthetic */ StickerCategoriesListView f$0;
    public final /* synthetic */ StickerCategoriesListView.EmojiCategory[] f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ StickerCategoriesListView$$ExternalSyntheticLambda2(StickerCategoriesListView r1, StickerCategoriesListView.EmojiCategory[] r2, long r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r5) {
        StickerCategoriesListView.$r8$lambda$XakrOCr4ECRNAxv8ph6efx4LSUQ(this.f$0, this.f$1, this.f$2, (TLRPC.TL_messages_emojiGroups) r5);
    }
}
