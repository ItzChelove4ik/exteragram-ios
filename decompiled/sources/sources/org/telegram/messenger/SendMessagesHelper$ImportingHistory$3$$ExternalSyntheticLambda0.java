package org.telegram.messenger;

import org.telegram.messenger.SendMessagesHelper;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$ImportingHistory$3$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ SendMessagesHelper.ImportingHistory.AnonymousClass3 f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLRPC.TL_messages_startHistoryImport f$2;

    public /* synthetic */ SendMessagesHelper$ImportingHistory$3$$ExternalSyntheticLambda0(SendMessagesHelper.ImportingHistory.AnonymousClass3 r1, TLRPC.TL_error r2, TLRPC.TL_messages_startHistoryImport r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.ImportingHistory.AnonymousClass3.$r8$lambda$rrQUi2MD07VQn8a03laH48ec6M4(this.f$0, this.f$1, this.f$2);
    }
}
