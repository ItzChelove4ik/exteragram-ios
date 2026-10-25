package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda330 implements RequestDelegate {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ TLRPC.User f$1;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda330(ChatActivity r1, TLRPC.User r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        ChatActivity.m7543$r8$lambda$rdfnlzliwc01jRjyVW0aBhslhY(this.f$0, this.f$1, r2, r3);
    }
}
