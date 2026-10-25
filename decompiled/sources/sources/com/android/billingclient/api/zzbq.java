package com.android.billingclient.api;

import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
final class zzbq implements BillingClientStateListener, ConsumeResponseListener, PurchasesResponseListener, PurchasesUpdatedListener {
    private final long zza;

    public static native void nativeOnAcknowledgePurchaseResponse(int r0, String r1, long r2);

    public static native void nativeOnBillingServiceDisconnected();

    public static native void nativeOnBillingSetupFinished(int r0, String r1, long r2);

    public static native void nativeOnConsumePurchaseResponse(int r0, String r1, String r2, long r3);

    public static native void nativeOnPriceChangeConfirmationResult(int r0, String r1, long r2);

    public static native void nativeOnPurchaseHistoryResponse(int r0, String r1, PurchaseHistoryRecord[] r2, long r3);

    public static native void nativeOnPurchasesUpdated(int r0, String r1, Purchase[] r2);

    public static native void nativeOnQueryPurchasesResponse(int r0, String r1, Purchase[] r2, long r3);

    public static native void nativeOnSkuDetailsResponse(int r0, String r1, SkuDetails[] r2, long r3);

    @Override // com.android.billingclient.api.BillingClientStateListener
    public final void onBillingServiceDisconnected() {
        nativeOnBillingServiceDisconnected();
    }

    @Override // com.android.billingclient.api.BillingClientStateListener
    public final void onBillingSetupFinished(BillingResult r4) {
        nativeOnBillingSetupFinished(r4.getResponseCode(), r4.getDebugMessage(), this.zza);
    }

    @Override // com.android.billingclient.api.ConsumeResponseListener
    public final void onConsumeResponse(BillingResult r3, String r4) {
        long r0 = this.zza;
        nativeOnConsumePurchaseResponse(r3.getResponseCode(), r3.getDebugMessage(), r4, r0);
    }

    @Override // com.android.billingclient.api.PurchasesUpdatedListener
    public final void onPurchasesUpdated(BillingResult r1, List<Purchase> r2) {
        if (r2 != null) goto L4;
        r2 = Collections.EMPTY_LIST;
    L4:
        Purchase[] r0 = (Purchase[]) r2.toArray(new Purchase[r2.size()]);
        nativeOnPurchasesUpdated(r1.getResponseCode(), r1.getDebugMessage(), r0);
    }

    @Override // com.android.billingclient.api.PurchasesResponseListener
    public final void onQueryPurchasesResponse(BillingResult r3, List<Purchase> r4) {
        Purchase[] r42 = (Purchase[]) r4.toArray(new Purchase[r4.size()]);
        long r0 = this.zza;
        nativeOnQueryPurchasesResponse(r3.getResponseCode(), r3.getDebugMessage(), r42, r0);
    }
}
