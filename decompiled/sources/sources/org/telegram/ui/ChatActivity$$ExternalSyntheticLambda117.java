package org.telegram.ui;

import java.util.concurrent.CountDownLatch;
import org.telegram.messenger.MessagesStorage;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda117 implements Runnable {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ MessagesStorage f$1;
    public final /* synthetic */ CountDownLatch f$2;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda117(ChatActivity r1, MessagesStorage r2, CountDownLatch r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChatActivity.m7430$r8$lambda$EO4h6FHReejm4fjP6y7sdtobOs(this.f$0, this.f$1, this.f$2);
    }
}
