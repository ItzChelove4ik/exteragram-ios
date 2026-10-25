package com.exteragram.messenger.pillstack.ui.pills.crypto;

import com.exteragram.messenger.pillstack.ui.pills.crypto.utils.ExchangeRates;
import org.telegram.messenger.Utilities;

/* loaded from: classes.dex */
public final /* synthetic */ class RatePill$$ExternalSyntheticLambda0 implements Utilities.Callback {
    public final /* synthetic */ RatePill f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ RatePill$$ExternalSyntheticLambda0(RatePill r1, String r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        RatePill.$r8$lambda$aOvc9kfKRxqx4UTn09bT7iJRFXI(this.f$0, this.f$1, (ExchangeRates.State) r2);
    }
}
