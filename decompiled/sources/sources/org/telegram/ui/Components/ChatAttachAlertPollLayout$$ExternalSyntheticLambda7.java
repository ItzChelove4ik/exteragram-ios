package org.telegram.ui.Components;

import java.util.ArrayList;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ChatActivity;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlertPollLayout$$ExternalSyntheticLambda7 implements Utilities.Callback {
    public final /* synthetic */ ChatAttachAlertPollLayout f$0;
    public final /* synthetic */ ChatActivity f$1;
    public final /* synthetic */ TLRPC.TL_messageMediaPoll f$2;
    public final /* synthetic */ ArrayList f$3;

    public /* synthetic */ ChatAttachAlertPollLayout$$ExternalSyntheticLambda7(ChatAttachAlertPollLayout r1, ChatActivity r2, TLRPC.TL_messageMediaPoll r3, ArrayList r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r4) {
        ChatAttachAlertPollLayout.$r8$lambda$rZOzCJkd3iXCairjDGPnKf9oI0Y(this.f$0, this.f$1, this.f$2, this.f$3, (Long) r4);
    }
}
