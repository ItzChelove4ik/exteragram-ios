package com.google.android.gms.internal.p000authapi;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.auth.api.identity.zbv;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;

/* loaded from: classes4.dex */
final class zbak extends Api.AbstractClientBuilder {
    public zbak() {
    }

    @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
    public final /* synthetic */ Api.Client buildClient(Context r2, Looper r3, ClientSettings r4, Object r5, ConnectionCallbacks r6, OnConnectionFailedListener r7) {
        return new zbau(r2, r3, (zbv) r5, r4, r6, r7);
    }
}
