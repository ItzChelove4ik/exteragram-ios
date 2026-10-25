package org.telegram.ui;

import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.ReactedUsersListView;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$110$$ExternalSyntheticLambda1 implements ReactedUsersListView.OnProfileSelectedListener {
    public final /* synthetic */ ChatActivity.AnonymousClass110 f$0;
    public final /* synthetic */ MessageObject f$1;

    public /* synthetic */ ChatActivity$110$$ExternalSyntheticLambda1(ChatActivity.AnonymousClass110 r1, MessageObject r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.Components.ReactedUsersListView.OnProfileSelectedListener
    public final void onProfileSelected(ReactedUsersListView r7, long r8, TLRPC.MessagePeerReaction r10) {
        ChatActivity.AnonymousClass110.m8175$r8$lambda$ik5oxPqr36PPIEK4NqhagFmJ0(this.f$0, this.f$1, r7, r8, r10);
    }
}
