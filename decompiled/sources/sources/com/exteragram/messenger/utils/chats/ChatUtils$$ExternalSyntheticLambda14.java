package com.exteragram.messenger.utils.chats;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class ChatUtils$$ExternalSyntheticLambda14 implements Utilities.Callback {
    public final /* synthetic */ ChatUtils f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ Utilities.Callback f$3;

    public /* synthetic */ ChatUtils$$ExternalSyntheticLambda14(ChatUtils r1, String r2, boolean r3, Utilities.Callback r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r4) {
        ChatUtils.$r8$lambda$ktlp8ykPWn1HJ6mcC0_I9lN2KlM(this.f$0, this.f$1, this.f$2, this.f$3, (TLRPC.User) r4);
    }
}
