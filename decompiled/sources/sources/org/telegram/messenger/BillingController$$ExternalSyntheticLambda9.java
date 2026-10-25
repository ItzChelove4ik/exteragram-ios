package org.telegram.messenger;

import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import java.util.concurrent.atomic.AtomicInteger;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes5.dex */
public final /* synthetic */ class BillingController$$ExternalSyntheticLambda9 implements RequestDelegate {
    public final /* synthetic */ BillingController f$0;
    public final /* synthetic */ AlertDialog[] f$1;
    public final /* synthetic */ Purchase f$2;
    public final /* synthetic */ TLRPC.TL_payments_assignPlayMarketTransaction f$3;
    public final /* synthetic */ AccountInstance f$4;
    public final /* synthetic */ BillingResult f$5;
    public final /* synthetic */ AtomicInteger f$6;
    public final /* synthetic */ AtomicInteger f$7;
    public final /* synthetic */ Runnable f$8;

    public /* synthetic */ BillingController$$ExternalSyntheticLambda9(BillingController r1, AlertDialog[] r2, Purchase r3, TLRPC.TL_payments_assignPlayMarketTransaction r4, AccountInstance r5, BillingResult r6, AtomicInteger r7, AtomicInteger r8, Runnable r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r9;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r12, TLRPC.TL_error r13) {
        BillingController.$r8$lambda$vPOwYM7KOubDsonnpybn96rjcAI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, r12, r13);
    }
}
