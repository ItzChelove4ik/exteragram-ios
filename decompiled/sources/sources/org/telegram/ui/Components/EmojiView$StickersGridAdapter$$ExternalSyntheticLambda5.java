package org.telegram.ui.Components;

import android.view.View;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.EmojiView;

/* loaded from: classes7.dex */
public final /* synthetic */ class EmojiView$StickersGridAdapter$$ExternalSyntheticLambda5 implements View.OnClickListener {
    public final /* synthetic */ EmojiView.StickersGridAdapter f$0;
    public final /* synthetic */ TLRPC.TL_messages_stickerSet f$1;

    public /* synthetic */ EmojiView$StickersGridAdapter$$ExternalSyntheticLambda5(EmojiView.StickersGridAdapter r1, TLRPC.TL_messages_stickerSet r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r2) {
        EmojiView.StickersGridAdapter.m10547$r8$lambda$R7R9cwR1sYuihoT0LOCXt6BUvw(this.f$0, this.f$1, r2);
    }
}
