package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ StarsController f$0;
    public final /* synthetic */ boolean[] f$1;
    public final /* synthetic */ TLRPC.TL_payments_paymentFormStarGift f$2;
    public final /* synthetic */ TL_stars.StarGift f$3;
    public final /* synthetic */ long f$4;
    public final /* synthetic */ Utilities.Callback2 f$5;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda3(StarsController r1, boolean[] r2, TLRPC.TL_payments_paymentFormStarGift r3, TL_stars.StarGift r4, long r5, Utilities.Callback2 r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StarsController.m19128$r8$lambda$bWHEmLu_auBJwl0IGbMzuFxgc(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
