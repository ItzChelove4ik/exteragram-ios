package org.telegram.ui.Components;

import android.view.View;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.emojiview.FoundStickerPackButton;

/* loaded from: classes7.dex */
public final /* synthetic */ class EmojiView$$ExternalSyntheticLambda28 implements View.OnClickListener {
    public final /* synthetic */ EmojiView f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TLRPC.Document f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ FoundStickerPackButton f$4;
    public final /* synthetic */ TLRPC.StickerSet f$5;
    public final /* synthetic */ boolean f$6;

    public /* synthetic */ EmojiView$$ExternalSyntheticLambda28(EmojiView r1, TLObject r2, TLRPC.Document r3, boolean r4, FoundStickerPackButton r5, TLRPC.StickerSet r6, boolean r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r9) {
        EmojiView.m10281$r8$lambda$36RvouQER08BJyVo35zc5hCjFo(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r9);
    }
}
