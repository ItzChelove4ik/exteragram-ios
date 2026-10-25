package org.telegram.ui.Components;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.StickerCategoriesListView;

/* loaded from: classes7.dex */
public final /* synthetic */ class StickerCategoriesListView$EmojiGroupFetcher$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ TLRPC.TL_messages_emojiGroups f$1;
    public final /* synthetic */ Integer f$2;

    public /* synthetic */ StickerCategoriesListView$EmojiGroupFetcher$$ExternalSyntheticLambda0(int r1, TLRPC.TL_messages_emojiGroups r2, Integer r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StickerCategoriesListView.EmojiGroupFetcher.$r8$lambda$ItTxN5yQgcyJ0ciauXDJGTpsOXk(this.f$0, this.f$1, this.f$2);
    }
}
