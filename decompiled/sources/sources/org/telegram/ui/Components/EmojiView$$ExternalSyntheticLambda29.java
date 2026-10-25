package org.telegram.ui.Components;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.emojiview.FoundStickerPackButton;

/* loaded from: classes7.dex */
public final /* synthetic */ class EmojiView$$ExternalSyntheticLambda29 implements Runnable {
    public final /* synthetic */ EmojiView f$0;
    public final /* synthetic */ FoundStickerPackButton f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ TLRPC.StickerSet f$3;
    public final /* synthetic */ TLRPC.Document f$4;
    public final /* synthetic */ boolean f$5;

    public /* synthetic */ EmojiView$$ExternalSyntheticLambda29(EmojiView r1, FoundStickerPackButton r2, TLObject r3, TLRPC.StickerSet r4, TLRPC.Document r5, boolean r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        EmojiView.$r8$lambda$Pku1CKgUeyVrwD0E0C8til5qfPg(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
