package com.google.android.gms.common.api.internal;

import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zaj implements GoogleApiClient.OnConnectionFailedListener {
    public final int zaa;
    public final GoogleApiClient zab;
    public final GoogleApiClient.OnConnectionFailedListener zac;
    final /* synthetic */ zak zad;

    public zaj(zak r1, int r2, GoogleApiClient r3, GoogleApiClient.OnConnectionFailedListener r4) {
        Objects.requireNonNull(r1);
        this.zad = r1;
        this.zaa = r2;
        this.zab = r3;
        this.zac = r4;
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult r4) {
        Log.d("AutoManageHelper", "beginFailureResolution for ".concat(String.valueOf(r4)));
        int r0 = this.zaa;
        this.zad.zaf(r4, r0);
    }
}
