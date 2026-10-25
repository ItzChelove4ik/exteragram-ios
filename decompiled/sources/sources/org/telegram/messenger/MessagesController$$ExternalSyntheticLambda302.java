package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda302 implements Utilities.Callback2 {
    public final /* synthetic */ MessagesController f$0;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda302(MessagesController r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        MessagesController.$r8$lambda$6taDzoCWTbZlcvR2rY2PHkjEACs(this.f$0, (TLRPC.Updates) r1, (TLRPC.TL_error) r2);
    }
}
