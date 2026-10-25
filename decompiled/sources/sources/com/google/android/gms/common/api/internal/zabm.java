package com.google.android.gms.common.api.internal;

import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zabm implements Runnable {
    final /* synthetic */ ConnectionResult zaa;
    final /* synthetic */ zabn zab;

    public zabm(zabn r1, ConnectionResult r2) {
        this.zaa = r2;
        Objects.requireNonNull(r1);
        this.zab = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zabn r0 = this.zab;
        zabk r1 = (zabk) r0.zaa.zaC().get(r0.zaf());
        if (r1 != null) goto L6;
        return;
    L6:
        if (this.zaa.isSuccess() == false) goto L16;
        r0.zag(true);
        if (r0.zae().requiresSignIn() == false) goto L18;
        r0.zad();
        return;
    L18:
        r0.zae().getRemoteService(null, r0.zae().getScopesForConnectionlessNonSignIn());     // Catch: SecurityException -> L11
        return;
    L11:
        e = move-exception;
        Log.e("GoogleApiManager", "Failed to get service from broker. ", e);
        this.zab.zae().disconnect("Failed to get service from broker.");
        r1.zac(new ConnectionResult(10), null);
        return;
    L16:
        r1.zac(this.zaa, null);
    }
}
