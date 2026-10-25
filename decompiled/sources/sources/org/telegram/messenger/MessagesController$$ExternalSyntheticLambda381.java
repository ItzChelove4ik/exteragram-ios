package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_bots;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda381 implements Utilities.Callback {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ boolean[] f$1;
    public final /* synthetic */ TL_bots.BotInfo[] f$2;
    public final /* synthetic */ TLRPC.User f$3;
    public final /* synthetic */ int f$4;
    public final /* synthetic */ Runnable f$5;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda381(MessagesController r1, boolean[] r2, TL_bots.BotInfo[] r3, TLRPC.User r4, int r5, Runnable r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r8) {
        MessagesController.$r8$lambda$Nu0WhpeQhQqFYWug_FhgL_vLuIg(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, (TL_bots.BotInfo) r8);
    }
}
