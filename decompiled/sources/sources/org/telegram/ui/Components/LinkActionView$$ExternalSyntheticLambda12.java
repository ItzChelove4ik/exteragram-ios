package org.telegram.ui.Components;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class LinkActionView$$ExternalSyntheticLambda12 implements Runnable {
    public final /* synthetic */ LinkActionView f$0;
    public final /* synthetic */ TLRPC.TL_chatInviteExported f$1;
    public final /* synthetic */ TLRPC.TL_error f$2;
    public final /* synthetic */ TLObject f$3;

    public /* synthetic */ LinkActionView$$ExternalSyntheticLambda12(LinkActionView r1, TLRPC.TL_chatInviteExported r2, TLRPC.TL_error r3, TLObject r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LinkActionView.$r8$lambda$EwPo92jU7SuNZEpLZ0O1pZQZqzU(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
