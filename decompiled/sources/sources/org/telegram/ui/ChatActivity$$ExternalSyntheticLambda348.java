package org.telegram.ui;

import java.util.ArrayList;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.PollCreateActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda348 implements PollCreateActivity.PollCreateActivityDelegate {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ MessageObject f$2;
    public final /* synthetic */ int f$3;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda348(ChatActivity r1, boolean r2, MessageObject r3, int r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.ui.PollCreateActivity.PollCreateActivityDelegate
    public final void sendPoll(TLRPC.MessageMedia r9, ArrayList r10, boolean r11, int r12) {
        ChatActivity.$r8$lambda$WYE6KgdsG44XGirPXihCmqX7muQ(this.f$0, this.f$1, this.f$2, this.f$3, r9, r10, r11, r12);
    }
}
