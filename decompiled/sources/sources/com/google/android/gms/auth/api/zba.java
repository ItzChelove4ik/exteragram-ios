package com.google.android.gms.auth.api;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;

/* loaded from: classes4.dex */
final class zba extends Api.AbstractClientBuilder {
    public zba() {
    }

    @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
    public final /* synthetic */ Api.Client buildClient(Context r1, Looper r2, ClientSettings r3, Object r4, GoogleApiClient.ConnectionCallbacks r5, GoogleApiClient.OnConnectionFailedListener r6) {
        return new com.google.android.gms.internal.p000authapi.zbd(r1, r2, r3, (zbd) r4, r5, r6);
    }
}
