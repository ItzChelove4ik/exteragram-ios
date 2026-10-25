package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ChatActivity;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlertPollLayout$$ExternalSyntheticLambda4 implements Utilities.Callback {
    public final /* synthetic */ ChatAttachAlertPollLayout f$0;
    public final /* synthetic */ ChatActivity f$1;
    public final /* synthetic */ TLRPC.TL_messageMediaToDo f$2;

    public /* synthetic */ ChatAttachAlertPollLayout$$ExternalSyntheticLambda4(ChatAttachAlertPollLayout r1, ChatActivity r2, TLRPC.TL_messageMediaToDo r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r3) {
        ChatAttachAlertPollLayout.$r8$lambda$hDFOTjASetvoG5hK0G_5C6HqzK4(this.f$0, this.f$1, this.f$2, (Long) r3);
    }
}
