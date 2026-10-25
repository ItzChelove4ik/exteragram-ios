package org.telegram.messenger;

import org.telegram.tgnet.tl.TL_update;

/* loaded from: classes.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda332 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ TL_update.TL_updateChatUserTyping f$1;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda332(MessagesController r1, TL_update.TL_updateChatUserTyping r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.$r8$lambda$4s_0yqWplcfhlmHrE_8IvuJP4Zc(this.f$0, this.f$1);
    }
}
