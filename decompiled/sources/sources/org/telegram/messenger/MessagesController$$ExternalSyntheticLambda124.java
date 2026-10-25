package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda124 implements RequestDelegate {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ BaseFragment f$1;
    public final /* synthetic */ TLRPC.TL_channels_createChannel f$2;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda124(MessagesController r1, BaseFragment r2, TLRPC.TL_channels_createChannel r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        MessagesController.$r8$lambda$HBFLAbqEZIyUlsGCNz0TXhL555A(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
