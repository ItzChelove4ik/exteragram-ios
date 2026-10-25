package org.telegram.messenger;

import org.telegram.messenger.MessagesController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes.dex */
public final /* synthetic */ class MessagesController$5$$ExternalSyntheticLambda0 implements Utilities.Callback2 {
    public final /* synthetic */ Utilities.Callback4 f$0;

    public /* synthetic */ MessagesController$5$$ExternalSyntheticLambda0(Utilities.Callback4 r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        MessagesController.AnonymousClass5.m4658$r8$lambda$Rn_K7xAG5gCcmoYQuVhI7o4QWM(this.f$0, (TL_account.WebBrowserSettings) r1, (TLRPC.TL_error) r2);
    }
}
