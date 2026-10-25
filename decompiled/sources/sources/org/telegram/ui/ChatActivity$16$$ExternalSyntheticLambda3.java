package org.telegram.ui;

import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ChatActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$16$$ExternalSyntheticLambda3 implements MessagesStorage.BooleanCallback {
    public final /* synthetic */ ChatActivity.AnonymousClass16 f$0;
    public final /* synthetic */ TLRPC.User f$1;

    public /* synthetic */ ChatActivity$16$$ExternalSyntheticLambda3(ChatActivity.AnonymousClass16 r1, TLRPC.User r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.MessagesStorage.BooleanCallback
    public final void run(boolean r2) {
        ChatActivity.AnonymousClass16.m8180$r8$lambda$tI15uXWToOAOcDsAmBcxRRbDUU(this.f$0, this.f$1, r2);
    }
}
