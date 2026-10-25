package org.telegram.messenger;

import java.io.File;
import org.telegram.messenger.SendMessagesHelper;

/* loaded from: classes.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda85 implements Runnable {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ SendMessagesHelper.DelayedMessage f$1;
    public final /* synthetic */ File f$2;
    public final /* synthetic */ MessageObject f$3;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda85(SendMessagesHelper r1, SendMessagesHelper.DelayedMessage r2, File r3, MessageObject r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.$r8$lambda$LldrgMwurgfZtJuZu4jQseZTl8o(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
