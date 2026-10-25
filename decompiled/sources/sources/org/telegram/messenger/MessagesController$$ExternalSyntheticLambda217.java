package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda217 implements RequestDelegate {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ TLRPC.TL_messageMediaPoll f$3;
    public final /* synthetic */ MessageObject f$4;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda217(MessagesController r1, boolean r2, boolean r3, TLRPC.TL_messageMediaPoll r4, MessageObject r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        MessagesController.$r8$lambda$YrPkxv5KImh29QhSVfBfV8O2pmM(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
