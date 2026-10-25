package com.google.android.gms.signin;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.signin.internal.SignInClientImpl;

/* loaded from: classes5.dex */
final class zaa extends Api.AbstractClientBuilder {
    public zaa() {
    }

    @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
    public final /* bridge */ /* synthetic */ Api.Client buildClient(Context r9, Looper r10, ClientSettings r11, Object r12, GoogleApiClient.ConnectionCallbacks r13, GoogleApiClient.OnConnectionFailedListener r14) {
        SignInOptions r122 = (SignInOptions) r12;
        return new SignInClientImpl(r9, r10, true, r11, SignInClientImpl.createBundleFromClientSettings(r11), r13, r14);
    }
}
