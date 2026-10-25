package org.telegram.ui.Components;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.StickerCategoriesListView;

/* loaded from: classes7.dex */
public final /* synthetic */ class StickerCategoriesListView$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ StickerCategoriesListView f$0;
    public final /* synthetic */ StickerCategoriesListView.EmojiCategory[] f$1;
    public final /* synthetic */ TLRPC.TL_messages_emojiGroups f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ StickerCategoriesListView$$ExternalSyntheticLambda4(StickerCategoriesListView r1, StickerCategoriesListView.EmojiCategory[] r2, TLRPC.TL_messages_emojiGroups r3, long r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StickerCategoriesListView.m13049$r8$lambda$zAffNpcQxMQaK40tEAla4DVSqo(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
