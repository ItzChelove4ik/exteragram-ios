package org.telegram.ui;

import org.telegram.messenger.MessagesStorage;
import org.telegram.ui.ChatActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$16$$ExternalSyntheticLambda2 implements MessagesStorage.IntCallback {
    public final /* synthetic */ ChatActivity.AnonymousClass16 f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ ChatActivity$16$$ExternalSyntheticLambda2(ChatActivity.AnonymousClass16 r1, boolean r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.MessagesStorage.IntCallback
    public final void run(int r2) {
        ChatActivity.AnonymousClass16.$r8$lambda$RJKlCggatGQ2CrawMM0rnsqyKak(this.f$0, this.f$1, r2);
    }
}
