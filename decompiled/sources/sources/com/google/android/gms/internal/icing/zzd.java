package com.google.android.gms.internal.icing;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;

/* loaded from: classes4.dex */
final class zzd extends Api.AbstractClientBuilder<zzae, Api.ApiOptions.NoOptions> {
    public zzd() {
    }

    @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
    public final /* bridge */ /* synthetic */ Api.Client buildClient(Context r7, Looper r8, ClientSettings r9, Api.ApiOptions.NoOptions r10, ConnectionCallbacks r11, OnConnectionFailedListener r12) {
        return new zzae(r7, r8, r9, r11, r12);
    }
}
