package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda213 implements Utilities.Callback {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ TLRPC.BotInlineResult f$1;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda213(ChatActivity r1, TLRPC.BotInlineResult r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        ChatActivity.m7507$r8$lambda$hq4_6xDjbJlQFG2fEMqQSEg1Bg(this.f$0, this.f$1, (Long) r2);
    }
}
