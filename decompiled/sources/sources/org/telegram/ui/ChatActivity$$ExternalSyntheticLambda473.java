package org.telegram.ui;

import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.ReactedUsersListView;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda473 implements ReactedUsersListView.OnProfileSelectedListener {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ MessageObject f$2;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda473(ChatActivity r1, boolean r2, MessageObject r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.Components.ReactedUsersListView.OnProfileSelectedListener
    public final void onProfileSelected(ReactedUsersListView r8, long r9, TLRPC.MessagePeerReaction r11) {
        ChatActivity.$r8$lambda$bvtdRHOhZNexZx9XXE633Mr44U0(this.f$0, this.f$1, this.f$2, r8, r9, r11);
    }
}
