package org.telegram.ui.Stars;

import android.app.Activity;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ProductDetails;
import java.util.List;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda136 implements RequestDelegate {
    public final /* synthetic */ ProductDetails f$0;
    public final /* synthetic */ BillingResult f$1;
    public final /* synthetic */ Utilities.Callback2 f$2;
    public final /* synthetic */ Activity f$3;
    public final /* synthetic */ TLRPC.TL_inputStorePaymentStarsGift f$4;
    public final /* synthetic */ List f$5;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda136(ProductDetails r1, BillingResult r2, Utilities.Callback2 r3, Activity r4, TLRPC.TL_inputStorePaymentStarsGift r5, List r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        StarsController.$r8$lambda$aYsmyMnEjMg7MiWuBLzaPs9t8Bg(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r9, r10);
    }
}
