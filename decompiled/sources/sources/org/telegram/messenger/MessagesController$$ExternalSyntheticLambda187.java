package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda187 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ BaseFragment f$2;
    public final /* synthetic */ TLRPC.TL_channels_inviteToChannel f$3;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda187(MessagesController r1, TLRPC.TL_error r2, BaseFragment r3, TLRPC.TL_channels_inviteToChannel r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.$r8$lambda$lRzOmBnDJyaU2RPrGXDq9NLlEBI(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
