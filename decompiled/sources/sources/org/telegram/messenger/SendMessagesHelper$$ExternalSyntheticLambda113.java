package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda113 implements Runnable {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ TLRPC.TL_messages_editMessage f$1;
    public final /* synthetic */ MessageObject f$2;
    public final /* synthetic */ BaseFragment f$3;
    public final /* synthetic */ boolean f$4;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda113(SendMessagesHelper r1, TLRPC.TL_messages_editMessage r2, MessageObject r3, BaseFragment r4, boolean r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.m4932$r8$lambda$fBfgmUB1IRblzpNi_EqI098nUA(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
