package com.google.android.gms.phenotype;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;

/* loaded from: classes5.dex */
final class zzl extends Api.AbstractClientBuilder<com.google.android.gms.internal.phenotype.zze, Api.ApiOptions.NoOptions> {
    public zzl() {
    }

    @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
    public final /* synthetic */ Api.Client buildClient(Context r1, Looper r2, ClientSettings r3, Api.ApiOptions.NoOptions r4, GoogleApiClient.ConnectionCallbacks r5, GoogleApiClient.OnConnectionFailedListener r6) {
        return new com.google.android.gms.internal.phenotype.zze(r1, r2, r3, r5, r6);
    }
}
