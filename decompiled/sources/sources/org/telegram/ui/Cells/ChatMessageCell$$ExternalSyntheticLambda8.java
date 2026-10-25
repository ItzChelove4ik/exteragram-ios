package org.telegram.ui.Cells;

import org.telegram.ui.Components.AnimatedEmojiSpan;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatMessageCell$$ExternalSyntheticLambda8 implements AnimatedEmojiSpan.InvalidateHolder {
    public final /* synthetic */ ChatMessageCell f$0;

    public /* synthetic */ ChatMessageCell$$ExternalSyntheticLambda8(ChatMessageCell r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.Components.AnimatedEmojiSpan.InvalidateHolder
    public final void invalidate() {
        this.f$0.invalidateOutbounds();
    }
}
