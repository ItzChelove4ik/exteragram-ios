package org.telegram.messenger;

import org.telegram.messenger.SendMessagesHelper;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ TLRPC.Message f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ TLObject f$3;
    public final /* synthetic */ SendMessagesHelper.DelayedMessage f$4;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda3(SendMessagesHelper r1, TLRPC.Message r2, boolean r3, TLObject r4, SendMessagesHelper.DelayedMessage r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.m4934$r8$lambda$j6xIectMBx4kuaPB1GQLnp6vyM(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
