package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda145 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ BaseFragment f$2;
    public final /* synthetic */ TLRPC.TL_messages_createChat f$3;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda145(MessagesController r1, TLRPC.TL_error r2, BaseFragment r3, TLRPC.TL_messages_createChat r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.$r8$lambda$as_3HP7O5dpgDzW24_94UjQue_E(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
