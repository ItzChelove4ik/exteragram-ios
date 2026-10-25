package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda396 implements RequestDelegate {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ TLRPC.TL_messages_setChatAvailableReactions f$2;
    public final /* synthetic */ Runnable f$3;
    public final /* synthetic */ Utilities.Callback f$4;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda396(MessagesController r1, long r2, TLRPC.TL_messages_setChatAvailableReactions r4, Runnable r5, Utilities.Callback r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
        this.f$4 = r6;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        MessagesController.$r8$lambda$t9q6x3AMy9QRtbxX5dGl4fBKX1k(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r9, r10);
    }
}
