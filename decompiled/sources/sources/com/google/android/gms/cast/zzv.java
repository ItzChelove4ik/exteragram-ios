package com.google.android.gms.cast;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.internal.cast.zzeu;

/* loaded from: classes4.dex */
final class zzv extends Api.AbstractClientBuilder {
    public zzv() {
    }

    @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
    public final /* synthetic */ Api.Client buildClient(Context r7, Looper r8, ClientSettings r9, Object r10, GoogleApiClient.ConnectionCallbacks r11, GoogleApiClient.OnConnectionFailedListener r12) {
        Api.ApiOptions.NoOptions r102 = (Api.ApiOptions.NoOptions) r10;
        return new zzeu(r7, r8, r9, r11, r12);
    }
}
