package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;

/* loaded from: classes4.dex */
final class zzjw extends Api.AbstractClientBuilder {
    public zzjw() {
    }

    @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
    public final /* synthetic */ Api.Client buildClient(Context r7, Looper r8, ClientSettings r9, Object r10, GoogleApiClient.ConnectionCallbacks r11, GoogleApiClient.OnConnectionFailedListener r12) {
        Api.ApiOptions.NoOptions r102 = (Api.ApiOptions.NoOptions) r10;
        return new zzku(r7, r8, r9, r11, r12);
    }
}
