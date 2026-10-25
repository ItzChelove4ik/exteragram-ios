package com.google.android.gms.wallet;

import android.content.Context;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.Wallet;

/* loaded from: classes5.dex */
public class PaymentsClient extends GoogleApi<Wallet.WalletOptions> {
    public PaymentsClient(Context r3, Wallet.WalletOptions r4) {
        super(r3, Wallet.API, r4, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public Task<Boolean> isReadyToPay(IsReadyToPayRequest r3) {
        return doRead(TaskApiCall.builder().setMethodKey(23705).run(new zzah(r3)).build());
    }

    public Task<PaymentData> loadPaymentData(PaymentDataRequest r3) {
        return doWrite(TaskApiCall.builder().run(new zzag(r3)).setFeatures(new Feature[]{zzk.zzc}).setAutoResolveMissingFeatures(true).setMethodKey(23707).build());
    }
}
