package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_bots;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatEditActivity$$ExternalSyntheticLambda53 implements RequestDelegate {
    public final /* synthetic */ ChatEditActivity f$0;
    public final /* synthetic */ TL_bots.setBotInfo f$1;

    public /* synthetic */ ChatEditActivity$$ExternalSyntheticLambda53(ChatEditActivity r1, TL_bots.setBotInfo r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        ChatEditActivity.m8267$r8$lambda$f9LQQuVKlrqAaA6hMoCKVvsE(this.f$0, this.f$1, r2, r3);
    }
}
