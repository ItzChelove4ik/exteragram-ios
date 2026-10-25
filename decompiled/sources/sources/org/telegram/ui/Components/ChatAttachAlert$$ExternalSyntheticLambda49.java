package org.telegram.ui.Components;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.ChatAttachAlertPollLayout;
import org.telegram.ui.Components.poll.PollAttachedMediaPack;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlert$$ExternalSyntheticLambda49 implements ChatAttachAlertPollLayout.PollCreateActivityDelegate {
    public final /* synthetic */ ChatAttachAlert f$0;

    public /* synthetic */ ChatAttachAlert$$ExternalSyntheticLambda49(ChatAttachAlert r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlertPollLayout.PollCreateActivityDelegate
    public final void sendPoll(TLRPC.MessageMedia r1, CharSequence r2, PollAttachedMediaPack r3, ArrayList r4, boolean r5, int r6, long r7) {
        ChatAttachAlert.m9415$r8$lambda$ggtfeulugIeRQSEZ7soEE2BIbc(this.f$0, r1, r2, r3, r4, r5, r6, r7);
    }
}
