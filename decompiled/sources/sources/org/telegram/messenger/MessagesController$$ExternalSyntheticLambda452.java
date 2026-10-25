package org.telegram.messenger;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda452 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ TLRPC.User f$1;
    public final /* synthetic */ TLRPC.Chat f$2;
    public final /* synthetic */ MessagesController.ErrorDelegate f$3;
    public final /* synthetic */ TLRPC.TL_error f$4;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda452(MessagesController r1, TLRPC.User r2, TLRPC.Chat r3, MessagesController.ErrorDelegate r4, TLRPC.TL_error r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.m4497$r8$lambda$5ASYwnhRWxfyT5gQihQBNVF5Ho(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
