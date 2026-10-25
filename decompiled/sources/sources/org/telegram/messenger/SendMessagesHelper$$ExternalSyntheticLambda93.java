package org.telegram.messenger;

import java.io.File;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda93 implements Runnable {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ SendMessagesHelper.DelayedMessage f$1;
    public final /* synthetic */ File f$2;
    public final /* synthetic */ TLRPC.Document f$3;
    public final /* synthetic */ MessageObject f$4;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda93(SendMessagesHelper r1, SendMessagesHelper.DelayedMessage r2, File r3, TLRPC.Document r4, MessageObject r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.$r8$lambda$aXQ2OE6ys1m1CQTYxExn2sgeD3U(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
