package org.telegram.ui.Components;

import org.telegram.messenger.MessageObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_keyboard;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatActivityEnterView$$ExternalSyntheticLambda118 implements Utilities.Callback {
    public final /* synthetic */ ChatActivityEnterView f$0;
    public final /* synthetic */ MessageObject f$1;
    public final /* synthetic */ TL_keyboard.TL_buttonTypeRequestPeer f$2;
    public final /* synthetic */ TLRPC.User f$3;

    public /* synthetic */ ChatActivityEnterView$$ExternalSyntheticLambda118(ChatActivityEnterView r1, MessageObject r2, TL_keyboard.TL_buttonTypeRequestPeer r3, TLRPC.User r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r4) {
        ChatActivityEnterView.$r8$lambda$UjgxsSwN9eeXwIDqNq2sZIh2gKc(this.f$0, this.f$1, this.f$2, this.f$3, (TLRPC.User) r4);
    }
}
