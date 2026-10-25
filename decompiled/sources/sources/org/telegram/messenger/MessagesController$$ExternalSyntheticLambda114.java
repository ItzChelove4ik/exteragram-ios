package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda114 implements Utilities.Callback2 {
    public final /* synthetic */ MessagesController f$0;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda114(MessagesController r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        MessagesController.$r8$lambda$p3TIA4fZ8Krun5JstGfOH2DYLrc(this.f$0, (TL_account.WebBrowserSettings) r1, (TLRPC.TL_error) r2);
    }
}
