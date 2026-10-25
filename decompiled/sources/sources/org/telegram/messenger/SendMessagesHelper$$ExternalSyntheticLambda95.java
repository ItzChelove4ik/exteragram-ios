package org.telegram.messenger;

import org.telegram.messenger.SendMessagesHelper;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda95 implements Runnable {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TLRPC.InputFile f$2;
    public final /* synthetic */ TLRPC.InputMedia f$3;
    public final /* synthetic */ SendMessagesHelper.DelayedMessage f$4;
    public final /* synthetic */ int f$5;
    public final /* synthetic */ String f$6;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda95(SendMessagesHelper r1, TLObject r2, TLRPC.InputFile r3, TLRPC.InputMedia r4, SendMessagesHelper.DelayedMessage r5, int r6, String r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.$r8$lambda$dm2JANBaN2MgF54vR_smZH6YbJE(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
