package org.telegram.ui.Cells;

import org.telegram.messenger.utils.CountdownTimer;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatMessageCell$$ExternalSyntheticLambda17 implements CountdownTimer.Callback {
    public final /* synthetic */ ChatMessageCell f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ ChatMessageCell$$ExternalSyntheticLambda17(ChatMessageCell r1, boolean r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.utils.CountdownTimer.Callback
    public final void onTimerUpdate(long r2) {
        ChatMessageCell.$r8$lambda$qCzvThK2w_IjuC1wpXdtp5pJuIw(this.f$0, this.f$1, r2);
    }
}
