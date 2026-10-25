package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes3.dex */
public final /* synthetic */ class StickerCategoriesListView$$ExternalSyntheticLambda0 implements Utilities.Callback {
    public final /* synthetic */ int f$0;

    public /* synthetic */ StickerCategoriesListView$$ExternalSyntheticLambda0(int r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r1) {
        StickerCategoriesListView.m13048$r8$lambda$ZDQ_VKiRHbBVs7puOYEMcaxZQ(this.f$0, (TLRPC.TL_messages_emojiGroups) r1);
    }
}
