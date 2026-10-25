package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zaap implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    final /* synthetic */ zaar zaa;

    public /* synthetic */ zaap(zaar r1, byte[] r2) {
        Objects.requireNonNull(r1);
        this.zaa = r1;
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnected(Bundle r2) {
        zaar r1 = this.zaa;
        ClientSettings r22 = (ClientSettings) Preconditions.checkNotNull(r1.zay());
        ((com.google.android.gms.signin.zae) Preconditions.checkNotNull(r1.zav())).zaa(new zaao(r1));
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult r3) {
        zaar r0 = this.zaa;
        r0.zas().lock();
    L6:
        th = move-exception;
        this.zaa.zas().unlock();
        throw th;
    L4:
        if (r0.zan(r3) == false) goto L8;
        r0.zam();     // Catch: Throwable -> L6
        r0.zak();     // Catch: Throwable -> L6
    L9:
        this.zaa.zas().unlock();
        return;
    L8:
        r0.zao(r3);     // Catch: Throwable -> L6
        goto L9
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnectionSuspended(int r1) {
    }
}
