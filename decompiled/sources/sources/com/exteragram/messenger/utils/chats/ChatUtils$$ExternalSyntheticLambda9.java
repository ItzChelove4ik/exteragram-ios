package com.exteragram.messenger.utils.chats;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class ChatUtils$$ExternalSyntheticLambda9 implements Utilities.Callback {
    public final /* synthetic */ Utilities.Callback f$0;
    public final /* synthetic */ TLRPC.TL_channel f$1;

    public /* synthetic */ ChatUtils$$ExternalSyntheticLambda9(Utilities.Callback r1, TLRPC.TL_channel r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        ChatUtils.$r8$lambda$YpYo_S9uo9F5tb15vPZSRnOcYKg(this.f$0, this.f$1, (TLRPC.Chat) r2);
    }
}
