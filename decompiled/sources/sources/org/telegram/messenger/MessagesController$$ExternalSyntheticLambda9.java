package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda9 implements RequestDelegate {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ TLRPC.messages_SavedReactionTags f$2;
    public final /* synthetic */ TLRPC.TL_messages_getSavedReactionTags f$3;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda9(MessagesController r1, long r2, TLRPC.messages_SavedReactionTags r4, TLRPC.TL_messages_getSavedReactionTags r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        MessagesController.$r8$lambda$yFP6aSZi80Nlb7jZvfLzGQWViJA(this.f$0, this.f$1, this.f$2, this.f$3, r8, r9);
    }
}
