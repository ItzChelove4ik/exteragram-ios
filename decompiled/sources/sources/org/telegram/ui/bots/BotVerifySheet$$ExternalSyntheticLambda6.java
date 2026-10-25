package org.telegram.ui.bots;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotVerifySheet$$ExternalSyntheticLambda6 implements RequestDelegate {
    public final /* synthetic */ boolean[] f$0;
    public final /* synthetic */ Utilities.Callback f$1;

    public /* synthetic */ BotVerifySheet$$ExternalSyntheticLambda6(boolean[] r1, Utilities.Callback r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        BotVerifySheet.$r8$lambda$JlUszoSuMSH5jXSHcziYtLxabhI(this.f$0, this.f$1, r2, r3);
    }
}
