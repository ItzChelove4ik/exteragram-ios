package org.telegram.messenger;

import org.telegram.messenger.UnconfirmedAuthController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class UnconfirmedAuthController$UnconfirmedAuth$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ UnconfirmedAuthController.UnconfirmedAuth f$0;
    public final /* synthetic */ Utilities.Callback f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ TLRPC.TL_error f$3;

    public /* synthetic */ UnconfirmedAuthController$UnconfirmedAuth$$ExternalSyntheticLambda4(UnconfirmedAuthController.UnconfirmedAuth r1, Utilities.Callback r2, TLObject r3, TLRPC.TL_error r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        UnconfirmedAuthController.UnconfirmedAuth.m5043$r8$lambda$UA4TOt8epU7aIzNLxftpyNsenE(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
