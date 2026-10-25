package com.google.android.gms.common.internal.service;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.TelemetryLoggingOptions;

/* loaded from: classes4.dex */
final class zar extends Api.AbstractClientBuilder {
    public zar() {
    }

    @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
    public final /* synthetic */ Api.Client buildClient(Context r1, Looper r2, ClientSettings r3, Object r4, ConnectionCallbacks r5, OnConnectionFailedListener r6) {
        return new zau(r1, r2, r3, (TelemetryLoggingOptions) r4, r5, r6);
    }
}
