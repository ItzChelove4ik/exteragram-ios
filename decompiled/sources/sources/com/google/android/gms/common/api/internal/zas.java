package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes4.dex */
public final class zas implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    public final Api zaa;
    private final boolean zab;
    private zat zac;

    public zas(Api r1, boolean r2) {
        this.zaa = r1;
        this.zab = r2;
    }

    private final zat zab() {
        Preconditions.checkNotNull(this.zac, "Callbacks must be attached to a ClientConnectionHelper instance before connecting the client.");
        return this.zac;
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnected(Bundle r1) {
        zab().onConnected(r1);
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult r3) {
        Api r0 = this.zaa;
        boolean r1 = this.zab;
        zab().zaa(r3, r0, r1);
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnectionSuspended(int r1) {
        zab().onConnectionSuspended(r1);
    }

    public final void zaa(zat r1) {
        this.zac = r1;
    }
}
