package com.google.android.gms.auth.api;

import android.content.Context;
import android.os.Looper;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.internal.auth.zzbe;

/* loaded from: classes4.dex */
final class zza extends Api.AbstractClientBuilder {
    public zza() {
    }

    @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
    public final /* synthetic */ Api.Client buildClient(Context r1, Looper r2, ClientSettings r3, Object r4, ConnectionCallbacks r5, OnConnectionFailedListener r6) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r4);
        return new zzbe(r1, r2, r3, null, r5, r6);
    }
}
