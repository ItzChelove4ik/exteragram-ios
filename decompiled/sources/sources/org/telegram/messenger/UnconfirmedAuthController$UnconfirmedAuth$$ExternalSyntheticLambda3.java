package org.telegram.messenger;

import org.telegram.messenger.UnconfirmedAuthController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class UnconfirmedAuthController$UnconfirmedAuth$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ UnconfirmedAuthController.UnconfirmedAuth f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ Utilities.Callback f$2;
    public final /* synthetic */ TLRPC.TL_error f$3;

    public /* synthetic */ UnconfirmedAuthController$UnconfirmedAuth$$ExternalSyntheticLambda3(UnconfirmedAuthController.UnconfirmedAuth r1, TLObject r2, Utilities.Callback r3, TLRPC.TL_error r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        UnconfirmedAuthController.UnconfirmedAuth.$r8$lambda$Ze6KclJN_8c4FhKGVN_p7o0Dc6M(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
