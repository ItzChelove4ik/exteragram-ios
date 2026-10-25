package org.telegram.messenger;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda254 implements Runnable {
    public final /* synthetic */ MessagesController.ErrorDelegate f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda254(MessagesController.ErrorDelegate r1, TLRPC.TL_error r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.$r8$lambda$jMjfGlfRMftkXuUWNjEbhFKoko8(this.f$0, this.f$1);
    }
}
