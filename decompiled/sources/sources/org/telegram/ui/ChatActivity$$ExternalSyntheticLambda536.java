package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda536 implements RequestDelegate {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ TLRPC.TL_attachMenuBot f$1;
    public final /* synthetic */ TLRPC.User f$2;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda536(ChatActivity r1, TLRPC.TL_attachMenuBot r2, TLRPC.User r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        ChatActivity.$r8$lambda$ZONRPNWw30ErzmNJT_kxVIjFHHk(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
