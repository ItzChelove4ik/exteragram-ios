package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.poll.PollAttachedMedia;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlertPollLayout$$ExternalSyntheticLambda2 implements Utilities.Callback2 {
    public final /* synthetic */ ChatAttachAlertPollLayout f$0;
    public final /* synthetic */ PollAttachedMedia f$1;

    public /* synthetic */ ChatAttachAlertPollLayout$$ExternalSyntheticLambda2(ChatAttachAlertPollLayout r1, PollAttachedMedia r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        ChatAttachAlertPollLayout.$r8$lambda$Np5AAq0OmnCk7YA__ZwEEeOcsg0(this.f$0, this.f$1, (TLRPC.WebPage) r2, (TLObject) r3);
    }
}
