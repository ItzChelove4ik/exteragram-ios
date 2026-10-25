package org.telegram.messenger;

import org.telegram.messenger.SendMessagesHelper;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$ImportingHistory$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ SendMessagesHelper.ImportingHistory.AnonymousClass1 f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TLRPC.TL_messages_initHistoryImport f$2;
    public final /* synthetic */ TLRPC.TL_error f$3;

    public /* synthetic */ SendMessagesHelper$ImportingHistory$1$$ExternalSyntheticLambda0(SendMessagesHelper.ImportingHistory.AnonymousClass1 r1, TLObject r2, TLRPC.TL_messages_initHistoryImport r3, TLRPC.TL_error r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.ImportingHistory.AnonymousClass1.$r8$lambda$yJtQqwUPRCOTkM9NBNtUAT8ddL4(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
