package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda283 implements Runnable {
    public final /* synthetic */ Utilities.Callback f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda283(Utilities.Callback r1, TLRPC.TL_error r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.$r8$lambda$gm08WuV0sDzmD8AcYSOaBtTgrJ0(this.f$0, this.f$1);
    }
}
