package org.telegram.messenger;

import org.telegram.tgnet.tl.TL_update;

/* loaded from: classes.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda335 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ TL_update.TL_updatePeerBlocked f$1;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda335(MessagesController r1, TL_update.TL_updatePeerBlocked r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.m4604$r8$lambda$jAJIk5En5vjxubm4a6K2xpKeU(this.f$0, this.f$1);
    }
}
