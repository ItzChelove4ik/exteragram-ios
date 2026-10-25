package org.telegram.ui.Stars;

import android.app.Activity;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ProductDetails;
import java.util.List;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda140 implements Runnable {
    public final /* synthetic */ TLObject f$0;
    public final /* synthetic */ ProductDetails f$1;
    public final /* synthetic */ BillingResult f$2;
    public final /* synthetic */ Utilities.Callback2 f$3;
    public final /* synthetic */ Activity f$4;
    public final /* synthetic */ TLRPC.TL_inputStorePaymentStarsGiveaway f$5;
    public final /* synthetic */ List f$6;
    public final /* synthetic */ TLRPC.TL_error f$7;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda140(TLObject r1, ProductDetails r2, BillingResult r3, Utilities.Callback2 r4, Activity r5, TLRPC.TL_inputStorePaymentStarsGiveaway r6, List r7, TLRPC.TL_error r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StarsController.m19138$r8$lambda$qE9Zs7WyGEKPaMBs3dblGFThSk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7);
    }
}
