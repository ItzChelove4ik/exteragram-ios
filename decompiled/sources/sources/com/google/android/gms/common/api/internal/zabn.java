package com.google.android.gms.common.api.internal;

import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.IAccountAccessor;
import java.util.Objects;
import java.util.Set;

/* loaded from: classes4.dex */
final class zabn implements BaseGmsClient.ConnectionProgressReportCallbacks, zacl {
    final /* synthetic */ GoogleApiManager zaa;
    private final Api.Client zab;
    private final ApiKey zac;
    private IAccountAccessor zad;
    private Set zae;
    private boolean zaf;

    public zabn(GoogleApiManager r1, Api.Client r2, ApiKey r3) {
        Objects.requireNonNull(r1);
        this.zaa = r1;
        this.zad = null;
        this.zae = null;
        this.zaf = false;
        this.zab = r2;
        this.zac = r3;
    }

    private final void zah() {
        if (this.zaf == false) goto L8;
        IAccountAccessor r0 = this.zad;
        if (r0 == null) goto L9;
        this.zab.getRemoteService(r0, this.zae);
        return;
    L9:
        return;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks
    public final void onReportServiceBinding(ConnectionResult r2) {
        zabm r0 = new zabm(this, r2);
        this.zaa.zaF().post(r0);
    }

    @Override // com.google.android.gms.common.api.internal.zacl
    public final void zaa(ConnectionResult r2) {
        zabk r1 = (zabk) this.zaa.zaC().get(this.zac);
        if (r1 == null) goto L6;
        r1.zab(r2);
        return;
    }

    @Override // com.google.android.gms.common.api.internal.zacl
    public final void zab(int r2) {
        zabk r1 = (zabk) this.zaa.zaC().get(this.zac);
        if (r1 != null) goto L5;
        return;
    L5:
        if (r1.zaB() == false) goto L8;
        r1.zab(new ConnectionResult(17));
        return;
    L8:
        r1.onConnectionSuspended(r2);
    }

    @Override // com.google.android.gms.common.api.internal.zacl
    public final void zac(IAccountAccessor r2, Set r3) {
        if (r2 == null) goto L7;
        if (r3 == null) goto L7;
        this.zad = r2;
        this.zae = r3;
        zah();
        return;
    L7:
        Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
        zaa(new ConnectionResult(4));
    }

    public final /* synthetic */ void zad() {
        zah();
    }

    public final /* synthetic */ Api.Client zae() {
        return this.zab;
    }

    public final /* synthetic */ ApiKey zaf() {
        return this.zac;
    }

    public final /* synthetic */ void zag(boolean r1) {
        this.zaf = true;
    }
}
