package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import java.util.Collections;
import java.util.Iterator;

/* loaded from: classes4.dex */
public final class zaas implements zaba {
    private final zabd zaa;

    public zaas(zabd r1) {
        this.zaa = r1;
    }

    @Override // com.google.android.gms.common.api.internal.zaba
    public final void zaa() {
        zabd r2 = this.zaa;
        Iterator r0 = r2.zaa.values().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        ((Api.Client) r0.next()).disconnect();
        goto L4
    L6:
        zaaz r22 = r2.zag;
        r22.zad = Collections.EMPTY_SET;
    }

    @Override // com.google.android.gms.common.api.internal.zaba
    public final BaseImplementation$ApiMethodImpl zab(BaseImplementation$ApiMethodImpl r1) {
        this.zaa.zag.zaa.add(r1);
        return r1;
    }

    @Override // com.google.android.gms.common.api.internal.zaba
    public final BaseImplementation$ApiMethodImpl zac(BaseImplementation$ApiMethodImpl r1) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }

    @Override // com.google.android.gms.common.api.internal.zaba
    public final boolean zad() {
        return true;
    }

    @Override // com.google.android.gms.common.api.internal.zaba
    public final void zae() {
        this.zaa.zao();
    }

    @Override // com.google.android.gms.common.api.internal.zaba
    public final void zaf(Bundle r1) {
    }

    @Override // com.google.android.gms.common.api.internal.zaba
    public final void zag(ConnectionResult r1, Api r2, boolean r3) {
    }

    @Override // com.google.android.gms.common.api.internal.zaba
    public final void zah(int r1) {
    }
}
