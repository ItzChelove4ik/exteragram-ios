package org.telegram.messenger;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$DialogPhotos$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ MessagesController.DialogPhotos f$0;
    public final /* synthetic */ TLRPC.messages_Messages f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ int f$3;

    public /* synthetic */ MessagesController$DialogPhotos$$ExternalSyntheticLambda5(MessagesController.DialogPhotos r1, TLRPC.messages_Messages r2, int r3, int r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.DialogPhotos.$r8$lambda$BqXj9Uycn0uKJWnfQA8SGVnDi7s(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
