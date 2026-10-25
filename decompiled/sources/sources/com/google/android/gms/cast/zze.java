package com.google.android.gms.cast;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes4.dex */
final class zze extends Api.AbstractClientBuilder {
    public zze() {
    }

    @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
    public final /* bridge */ /* synthetic */ Api.Client buildClient(Context r13, Looper r14, ClientSettings r15, Object r16, GoogleApiClient.ConnectionCallbacks r17, GoogleApiClient.OnConnectionFailedListener r18) {
        Cast.CastOptions r12 = (Cast.CastOptions) r16;
        Preconditions.checkNotNull(r12, "Setting the API options is required.");
        return new com.google.android.gms.cast.internal.zzx(r13, r14, r15, r12.zza, r12.zzd, r12.zzb, r12.zzc, r17, r18);
    }
}
