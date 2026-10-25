package com.google.android.gms.auth.api;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.internal.zbe;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
final class zbb extends Api.AbstractClientBuilder {
    public zbb() {
    }

    @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
    public final /* synthetic */ Api.Client buildClient(Context r1, Looper r2, ClientSettings r3, Object r4, GoogleApiClient.ConnectionCallbacks r5, GoogleApiClient.OnConnectionFailedListener r6) {
        return new zbe(r1, r2, r3, (GoogleSignInOptions) r4, r5, r6);
    }

    @Override // com.google.android.gms.common.api.Api.BaseClientBuilder
    public final /* bridge */ /* synthetic */ List getImpliedScopes(Object r1) {
        GoogleSignInOptions r12 = (GoogleSignInOptions) r1;
        if (r12 != null) goto L7;
        return Collections.EMPTY_LIST;
    L7:
        return r12.getScopes();
    }
}
