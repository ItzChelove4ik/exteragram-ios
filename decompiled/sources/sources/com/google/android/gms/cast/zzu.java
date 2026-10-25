package com.google.android.gms.cast;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.internal.cast.zzew;

/* loaded from: classes4.dex */
final class zzu extends Api.AbstractClientBuilder {
    public zzu() {
    }

    @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
    public final /* bridge */ /* synthetic */ Api.Client buildClient(Context r10, Looper r11, ClientSettings r12, Object r13, GoogleApiClient.ConnectionCallbacks r14, GoogleApiClient.OnConnectionFailedListener r15) {
        CastRemoteDisplay.CastRemoteDisplayOptions r132 = (CastRemoteDisplay.CastRemoteDisplayOptions) r13;
        Bundle r5 = new Bundle();
        r5.putInt("configuration", r132.zzc);
        return new zzew(r10, r11, r12, r132.zza, r5, r132.zzb, r14, r15);
    }
}
