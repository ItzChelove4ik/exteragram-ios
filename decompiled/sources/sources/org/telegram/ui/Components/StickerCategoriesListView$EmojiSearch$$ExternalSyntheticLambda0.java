package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.StickerCategoriesListView;

/* loaded from: classes7.dex */
public final /* synthetic */ class StickerCategoriesListView$EmojiSearch$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ Utilities.Callback4 f$0;

    public /* synthetic */ StickerCategoriesListView$EmojiSearch$$ExternalSyntheticLambda0(Utilities.Callback4 r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        StickerCategoriesListView.EmojiSearch.m13066$r8$lambda$S8qTPEYrUlxmW8suBrCSOYi_S0(this.f$0, r1, r2);
    }
}
