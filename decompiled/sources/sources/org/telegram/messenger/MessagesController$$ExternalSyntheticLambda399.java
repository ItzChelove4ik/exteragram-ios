package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda399 implements Utilities.Callback2 {
    public final /* synthetic */ Utilities.Callback2 f$0;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda399(Utilities.Callback2 r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        MessagesController.m4478$r8$lambda$02UFwLBkbqPoonmuHBKPdrBaE(this.f$0, (TLRPC.Updates) r1, (TLRPC.TL_error) r2);
    }
}
