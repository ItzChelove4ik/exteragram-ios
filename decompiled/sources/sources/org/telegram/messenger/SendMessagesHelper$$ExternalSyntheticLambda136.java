package org.telegram.messenger;

import org.telegram.messenger.SendMessagesHelper;
import org.telegram.tgnet.TLObject;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda136 implements Runnable {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ SendMessagesHelper.DelayedMessage f$2;
    public final /* synthetic */ String f$3;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda136(SendMessagesHelper r1, TLObject r2, SendMessagesHelper.DelayedMessage r3, String r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.$r8$lambda$Yg4cZ17LA6RVARo7CEtG6pp2sY0(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
