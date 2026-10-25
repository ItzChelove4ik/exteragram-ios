package com.google.android.gms.auth.api.phone;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.internal.p001authapiphone.zzw;

/* loaded from: classes4.dex */
final class zza extends Api.AbstractClientBuilder {
    public zza() {
    }

    @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
    public final /* synthetic */ Api.Client buildClient(Context r7, Looper r8, ClientSettings r9, Object r10, ConnectionCallbacks r11, OnConnectionFailedListener r12) {
        Api.ApiOptions.NoOptions r102 = (Api.ApiOptions.NoOptions) r10;
        return new zzw(r7, r8, r9, r11, r12);
    }
}
