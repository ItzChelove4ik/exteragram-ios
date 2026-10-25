package com.google.android.gms.wearable;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.internal.zzim;

/* loaded from: classes5.dex */
final class zzh extends Api.AbstractClientBuilder {
    public zzh() {
    }

    @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
    public final /* synthetic */ Api.Client buildClient(Context r7, Looper r8, ClientSettings r9, Object r10, GoogleApiClient.ConnectionCallbacks r11, GoogleApiClient.OnConnectionFailedListener r12) {
        Wearable.WearableOptions r102 = (Wearable.WearableOptions) r10;
        return new zzim(r7, r8, r11, r12, r9);
    }
}
