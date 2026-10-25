package org.telegram.messenger;

import java.util.HashMap;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.SendMessagesHelper;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda123 implements Runnable {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ HashMap f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ SendMessagesHelper.ImportingHistory f$3;
    public final /* synthetic */ MessagesStorage.LongCallback f$4;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda123(SendMessagesHelper r1, HashMap r2, long r3, SendMessagesHelper.ImportingHistory r5, MessagesStorage.LongCallback r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.$r8$lambda$F8qp4XHNCdM7ozayD3di8NQnV_w(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
