package org.telegram.ui.Business;

import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Business.QuickRepliesController;

/* loaded from: classes6.dex */
public final /* synthetic */ class QuickRepliesController$$ExternalSyntheticLambda23 implements Runnable {
    public final /* synthetic */ QuickRepliesController f$0;
    public final /* synthetic */ MessagesStorage f$1;
    public final /* synthetic */ QuickRepliesController.QuickReply f$2;
    public final /* synthetic */ TLRPC.TL_messages_sendQuickReplyMessages f$3;

    public /* synthetic */ QuickRepliesController$$ExternalSyntheticLambda23(QuickRepliesController r1, MessagesStorage r2, QuickRepliesController.QuickReply r3, TLRPC.TL_messages_sendQuickReplyMessages r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        QuickRepliesController.m6406$r8$lambda$vQGXINKrm_qz1O2pj3k66SOugo(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
