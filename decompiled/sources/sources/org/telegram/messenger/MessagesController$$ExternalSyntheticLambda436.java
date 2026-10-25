package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_bots;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda436 implements Utilities.Callback {
    public final /* synthetic */ boolean[] f$0;
    public final /* synthetic */ TL_bots.BotInfo[] f$1;
    public final /* synthetic */ Runnable f$2;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda436(boolean[] r1, TL_bots.BotInfo[] r2, Runnable r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r3) {
        MessagesController.$r8$lambda$iQ11VJCDmkis1vgsW5O1EXciBVQ(this.f$0, this.f$1, this.f$2, (TLRPC.UserFull) r3);
    }
}
