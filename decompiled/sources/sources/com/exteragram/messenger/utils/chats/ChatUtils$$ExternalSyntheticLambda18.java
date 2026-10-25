package com.exteragram.messenger.utils.chats;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class ChatUtils$$ExternalSyntheticLambda18 implements Utilities.Callback {
    public final /* synthetic */ ChatUtils f$0;
    public final /* synthetic */ Utilities.Callback f$1;

    public /* synthetic */ ChatUtils$$ExternalSyntheticLambda18(ChatUtils r1, Utilities.Callback r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        ChatUtils.$r8$lambda$0FiI7QD8hr21i5zbWvUFGDW_h4s(this.f$0, this.f$1, (TLRPC.messages_BotResults) r2);
    }
}
