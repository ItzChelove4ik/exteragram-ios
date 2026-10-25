package com.google.android.gms.wallet;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.internal.wallet.zzy;
import com.google.android.gms.wallet.Wallet;

/* loaded from: classes5.dex */
final class zzap extends Api.AbstractClientBuilder {
    public zzap() {
    }

    @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
    public final /* bridge */ /* synthetic */ Api.Client buildClient(Context r10, Looper r11, ClientSettings r12, Object r13, GoogleApiClient.ConnectionCallbacks r14, GoogleApiClient.OnConnectionFailedListener r15) {
        Wallet.WalletOptions r132 = (Wallet.WalletOptions) r13;
        if (r132 != null) goto L6;
        r132 = new Wallet.WalletOptions(null);
    L6:
        return new zzy(r10, r11, r12, r14, r15, r132.environment, r132.theme, r132.zzb);
    }
}
