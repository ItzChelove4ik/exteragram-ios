package org.telegram.ui.Components.Premium.boosts;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class BoostRepository$$ExternalSyntheticLambda18 implements RequestDelegate {
    public final /* synthetic */ Utilities.Callback f$0;
    public final /* synthetic */ Utilities.Callback f$1;

    public /* synthetic */ BoostRepository$$ExternalSyntheticLambda18(Utilities.Callback r1, Utilities.Callback r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        BoostRepository.m12093$r8$lambda$dZaHPtXWt_GUT1qGhgn6yh2A(this.f$0, this.f$1, r2, r3);
    }
}
