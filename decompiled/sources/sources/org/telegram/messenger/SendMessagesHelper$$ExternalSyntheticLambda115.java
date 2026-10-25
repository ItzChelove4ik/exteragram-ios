package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda115 implements Runnable {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ BaseFragment f$2;
    public final /* synthetic */ TLRPC.TL_messages_editMessage f$3;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda115(SendMessagesHelper r1, TLRPC.TL_error r2, BaseFragment r3, TLRPC.TL_messages_editMessage r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.$r8$lambda$fDDXNOCkU3TBl68PMC4rIkQ3jfM(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
