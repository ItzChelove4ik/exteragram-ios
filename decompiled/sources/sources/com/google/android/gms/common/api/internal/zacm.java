package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Set;

/* loaded from: classes4.dex */
public final class zacm extends com.google.android.gms.signin.internal.zac implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private static final Api.AbstractClientBuilder zaa = null;
    private final Context zab;
    private final Handler zac;
    private final Api.AbstractClientBuilder zad;
    private final Set zae;
    private final ClientSettings zaf;
    private com.google.android.gms.signin.zae zag;
    private zacl zah;

    static {
        zaa = com.google.android.gms.signin.zad.zac;
    }

    public zacm(Context r2, Handler r3, ClientSettings r4) {
        Api.AbstractClientBuilder r0 = zaa;
        this.zab = r2;
        this.zac = r3;
        this.zaf = (ClientSettings) Preconditions.checkNotNull(r4, "ClientSettings must not be null");
        this.zae = r4.getRequiredScopes();
        this.zad = r0;
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnected(Bundle r1) {
        this.zag.zaa(this);
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult r1) {
        this.zah.zaa(r1);
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnectionSuspended(int r1) {
        this.zah.zab(r1);
    }

    @Override // com.google.android.gms.signin.internal.zae
    public final void zab(com.google.android.gms.signin.internal.zak r2) {
        zack r0 = new zack(this, r2);
        this.zac.post(r0);
    }

    public final void zac(zacl r9) {
        com.google.android.gms.signin.zae r0 = this.zag;
        if (r0 == null) goto L5;
        r0.disconnect();
    L5:
        ClientSettings r4 = this.zaf;
        r4.zae(Integer.valueOf(System.identityHashCode(this)));
        Api.AbstractClientBuilder r1 = this.zad;
        Context r2 = this.zab;
        Handler r02 = this.zac;
        this.zag = (com.google.android.gms.signin.zae) r1.buildClient(r2, r02.getLooper(), r4, r4.zac(), this, this);
        this.zah = r9;
        Set r8 = this.zae;
        if (r8 != null) goto L8;
    L12:
        r02.post(new zacj(this));
        return;
    L8:
        if (r8.isEmpty() == true) goto L12;
        this.zag.zad();
    }

    public final void zad() {
        com.google.android.gms.signin.zae r0 = this.zag;
        if (r0 == null) goto L6;
        r0.disconnect();
        return;
    }

    public final /* synthetic */ void zae(com.google.android.gms.signin.internal.zak r5) {
        ConnectionResult r0 = r5.zaa();
        if (r0.isSuccess() == false) goto L9;
        com.google.android.gms.common.internal.zay r52 = (com.google.android.gms.common.internal.zay) Preconditions.checkNotNull(r5.zab());
        ConnectionResult r02 = r52.zab();
        if (r02.isSuccess() == true) goto L8;
        String r53 = String.valueOf(r02);
        Exception r1 = new Exception();
        Log.wtf("SignInCoordinator", "Sign-in succeeded with resolve account failure: ".concat(r53), r1);
        this.zah.zaa(r02);
        this.zag.disconnect();
        return;
    L8:
        this.zah.zac(r52.zaa(), this.zae);
    L10:
        this.zag.disconnect();
        return;
    L9:
        this.zah.zaa(r0);
        goto L10
    }

    public final /* synthetic */ zacl zaf() {
        return this.zah;
    }
}
