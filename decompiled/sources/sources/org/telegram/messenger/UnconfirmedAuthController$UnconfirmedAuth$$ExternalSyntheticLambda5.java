package org.telegram.messenger;

import org.telegram.messenger.UnconfirmedAuthController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class UnconfirmedAuthController$UnconfirmedAuth$$ExternalSyntheticLambda5 implements Utilities.Callback2 {
    public final /* synthetic */ UnconfirmedAuthController.UnconfirmedAuth f$0;
    public final /* synthetic */ Utilities.Callback f$1;

    public /* synthetic */ UnconfirmedAuthController$UnconfirmedAuth$$ExternalSyntheticLambda5(UnconfirmedAuthController.UnconfirmedAuth r1, Utilities.Callback r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        UnconfirmedAuthController.UnconfirmedAuth.$r8$lambda$i3qqUBtAEmUvZz7kHxF6Vngbhi4(this.f$0, this.f$1, (TLRPC.Bool) r2, (TLRPC.TL_error) r3);
    }
}
