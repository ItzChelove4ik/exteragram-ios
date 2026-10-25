package org.telegram.ui.Components.Premium.boosts;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class BoostRepository$$ExternalSyntheticLambda3 implements RequestDelegate {
    public final /* synthetic */ TLRPC.Chat f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ Utilities.Callback f$2;

    public /* synthetic */ BoostRepository$$ExternalSyntheticLambda3(TLRPC.Chat r1, int r2, Utilities.Callback r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        BoostRepository.$r8$lambda$D14eR3Q20yjKqtSujJDqV16f_sM(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
