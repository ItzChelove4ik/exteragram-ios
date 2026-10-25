package org.telegram.messenger;

import java.io.File;
import org.telegram.messenger.SendMessagesHelper;

/* loaded from: classes.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda84 implements Runnable {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ File f$1;
    public final /* synthetic */ MessageObject f$2;
    public final /* synthetic */ SendMessagesHelper.DelayedMessage f$3;
    public final /* synthetic */ String f$4;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda84(SendMessagesHelper r1, File r2, MessageObject r3, SendMessagesHelper.DelayedMessage r4, String r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.m4911$r8$lambda$9ff8bOLGMKjkXa9va_SGxUytCU(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
