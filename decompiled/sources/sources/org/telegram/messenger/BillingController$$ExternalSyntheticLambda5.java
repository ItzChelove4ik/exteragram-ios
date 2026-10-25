package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_update;

/* loaded from: classes5.dex */
public final /* synthetic */ class BillingController$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ AccountInstance f$0;
    public final /* synthetic */ TLRPC.TL_payments_assignPlayMarketTransaction f$1;
    public final /* synthetic */ TL_update.TL_updateSentPhoneCode f$2;

    public /* synthetic */ BillingController$$ExternalSyntheticLambda5(AccountInstance r1, TLRPC.TL_payments_assignPlayMarketTransaction r2, TL_update.TL_updateSentPhoneCode r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BillingController.m3890$r8$lambda$5TXidx5jCxPGUeApezhyTjxJI(this.f$0, this.f$1, this.f$2);
    }
}
