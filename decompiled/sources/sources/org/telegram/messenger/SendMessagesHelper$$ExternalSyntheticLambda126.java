package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda126 implements RequestDelegate {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ Runnable f$1;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda126(SendMessagesHelper r1, Runnable r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        SendMessagesHelper.$r8$lambda$e4JsZR3XR0lf0ZFNfNCariyyWng(this.f$0, this.f$1, r2, r3);
    }
}
