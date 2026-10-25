package org.telegram.messenger;

import org.telegram.messenger.UnconfirmedAuthController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class UnconfirmedAuthController$UnconfirmedAuth$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ UnconfirmedAuthController.UnconfirmedAuth f$0;
    public final /* synthetic */ Utilities.Callback f$1;

    public /* synthetic */ UnconfirmedAuthController$UnconfirmedAuth$$ExternalSyntheticLambda1(UnconfirmedAuthController.UnconfirmedAuth r1, Utilities.Callback r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        UnconfirmedAuthController.UnconfirmedAuth.m5042$r8$lambda$HQb9HkZdQOe50D53sydcjDPWR0(this.f$0, this.f$1, r2, r3);
    }
}
