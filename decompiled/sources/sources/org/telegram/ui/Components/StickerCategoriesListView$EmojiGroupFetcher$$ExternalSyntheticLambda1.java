package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.StickerCategoriesListView;

/* loaded from: classes3.dex */
public final /* synthetic */ class StickerCategoriesListView$EmojiGroupFetcher$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ Utilities.Callback4 f$0;

    public /* synthetic */ StickerCategoriesListView$EmojiGroupFetcher$$ExternalSyntheticLambda1(Utilities.Callback4 r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        StickerCategoriesListView.EmojiGroupFetcher.$r8$lambda$hoVrwIB0C7JCXIxZyF899CAsQ48(this.f$0, r1, r2);
    }
}
