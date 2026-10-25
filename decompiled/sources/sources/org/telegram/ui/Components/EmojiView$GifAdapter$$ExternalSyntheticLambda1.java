package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.EmojiView;

/* loaded from: classes7.dex */
public final /* synthetic */ class EmojiView$GifAdapter$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ EmojiView.GifAdapter f$0;

    public /* synthetic */ EmojiView$GifAdapter$$ExternalSyntheticLambda1(EmojiView.GifAdapter r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        EmojiView.GifAdapter.$r8$lambda$oJrI4IQsw4y6SlAnZ4G_r4XTZPw(this.f$0, r1, r2);
    }
}
