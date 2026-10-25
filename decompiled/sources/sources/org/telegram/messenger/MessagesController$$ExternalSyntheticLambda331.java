package org.telegram.messenger;

import org.telegram.tgnet.tl.TL_update;

/* loaded from: classes.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda331 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ TL_update.TL_updateUserTyping f$1;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda331(MessagesController r1, TL_update.TL_updateUserTyping r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.m4542$r8$lambda$Nfa2sufRhIrRLxvaB4LTcFh4RA(this.f$0, this.f$1);
    }
}
