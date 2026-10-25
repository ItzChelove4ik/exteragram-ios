package org.telegram.messenger;

import org.telegram.messenger.SendMessagesHelper;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda100 implements RequestDelegate {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ TLRPC.InputMedia f$1;
    public final /* synthetic */ SendMessagesHelper.DelayedMessage f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ MessageObject f$4;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda100(SendMessagesHelper r1, TLRPC.InputMedia r2, SendMessagesHelper.DelayedMessage r3, String r4, MessageObject r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        SendMessagesHelper.$r8$lambda$Aun3I_4fXe79IPJDezI3yNefMN4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
