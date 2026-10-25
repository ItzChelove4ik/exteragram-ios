package org.telegram.messenger;

import org.telegram.messenger.SendMessagesHelper;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda45 implements Runnable {
    public final /* synthetic */ SendMessagesHelper.MediaSendPrepareWorker f$0;
    public final /* synthetic */ AccountInstance f$1;
    public final /* synthetic */ SendMessagesHelper.SendingMediaInfo f$2;
    public final /* synthetic */ boolean f$3;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda45(SendMessagesHelper.MediaSendPrepareWorker r1, AccountInstance r2, SendMessagesHelper.SendingMediaInfo r3, boolean r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.m4943$r8$lambda$v9qneWod6eXcbW7STyl4PFTyW8(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
