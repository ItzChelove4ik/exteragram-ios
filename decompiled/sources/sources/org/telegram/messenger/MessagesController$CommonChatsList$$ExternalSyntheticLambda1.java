package org.telegram.messenger;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLObject;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$CommonChatsList$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ MessagesController.CommonChatsList f$0;
    public final /* synthetic */ int[] f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ boolean f$3;

    public /* synthetic */ MessagesController$CommonChatsList$$ExternalSyntheticLambda1(MessagesController.CommonChatsList r1, int[] r2, TLObject r3, boolean r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.CommonChatsList.$r8$lambda$34RDaSxzR8MrBwJEKbL3ebAj4zA(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
