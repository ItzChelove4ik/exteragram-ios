package com.google.android.gms.clearcut;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.internal.clearcut.zzj;

/* loaded from: classes4.dex */
final class zza extends Api.AbstractClientBuilder<zzj, Api.ApiOptions.NoOptions> {
    public zza() {
    }

    @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
    public final /* synthetic */ Api.Client buildClient(Context r1, Looper r2, ClientSettings r3, Api.ApiOptions.NoOptions r4, GoogleApiClient.ConnectionCallbacks r5, GoogleApiClient.OnConnectionFailedListener r6) {
        return new zzj(r1, r2, r3, r5, r6);
    }
}
