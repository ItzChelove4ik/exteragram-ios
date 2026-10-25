package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda141 implements Runnable {
    public final /* synthetic */ StarsController f$0;
    public final /* synthetic */ Utilities.Callback2 f$1;
    public final /* synthetic */ TLRPC.TL_payments_paymentFormStarGift f$2;
    public final /* synthetic */ TL_stars.StarGift f$3;
    public final /* synthetic */ long f$4;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda141(StarsController r1, Utilities.Callback2 r2, TLRPC.TL_payments_paymentFormStarGift r3, TL_stars.StarGift r4, long r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StarsController.m19125$r8$lambda$_Do1Vf1yIL6PWRsChVQmHYSI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
