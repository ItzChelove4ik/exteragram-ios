package com.exteragram.messenger.utils.chats;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class ChatUtils$$ExternalSyntheticLambda2 implements Utilities.Callback {
    public final /* synthetic */ ChatUtils f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ Utilities.Callback f$2;

    public /* synthetic */ ChatUtils$$ExternalSyntheticLambda2(ChatUtils r1, long r2, Utilities.Callback r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r4) {
        ChatUtils.$r8$lambda$2F046n2VCFYnTQRjQ8eAQwAaIS0(this.f$0, this.f$1, this.f$2, (TLRPC.User) r4);
    }
}
