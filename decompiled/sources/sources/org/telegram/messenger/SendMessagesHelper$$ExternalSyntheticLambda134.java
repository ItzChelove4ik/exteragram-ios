package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda134 implements RequestDelegate {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ long f$1;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda134(SendMessagesHelper r1, long r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r4, TLRPC.TL_error r5) {
        SendMessagesHelper.$r8$lambda$qvHlAuCCZunSUbrThg1oZ5DwTLQ(this.f$0, this.f$1, r4, r5);
    }
}
