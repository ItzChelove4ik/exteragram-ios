package org.telegram.ui.Stars;

import android.app.Activity;
import com.android.billingclient.api.BillingResult;
import java.util.List;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda129 implements Runnable {
    public final /* synthetic */ StarsController f$0;
    public final /* synthetic */ List f$1;
    public final /* synthetic */ Utilities.Callback2 f$2;
    public final /* synthetic */ TLRPC.TL_inputStorePaymentStarsGift f$3;
    public final /* synthetic */ TL_stars.TL_starsGiftOption f$4;
    public final /* synthetic */ BillingResult f$5;
    public final /* synthetic */ Activity f$6;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda129(StarsController r1, List r2, Utilities.Callback2 r3, TLRPC.TL_inputStorePaymentStarsGift r4, TL_stars.TL_starsGiftOption r5, BillingResult r6, Activity r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StarsController.$r8$lambda$0fDOcStockQm92A3BQZI73kXjHk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
