package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes4.dex */
public final class zaag implements zaba {
    private final zabd zaa;
    private boolean zab;

    public zaag(zabd r2) {
        this.zab = false;
        this.zaa = r2;
    }

    @Override // com.google.android.gms.common.api.internal.zaba
    public final void zaa() {
    }

    @Override // com.google.android.gms.common.api.internal.zaba
    public final BaseImplementation$ApiMethodImpl zab(BaseImplementation$ApiMethodImpl r1) {
        zac(r1);
        return r1;
    }

    @Override // com.google.android.gms.common.api.internal.zaba
    public final BaseImplementation$ApiMethodImpl zac(BaseImplementation$ApiMethodImpl r4) {
        zabd r0 = this.zaa;     // Catch: DeadObjectException -> L10
        zaaz r1 = r0.zag;     // Catch: DeadObjectException -> L10
        r1.zai.zaa(r4);     // Catch: DeadObjectException -> L10
        Api.AnyClientKey r2 = r4.getClientKey();     // Catch: DeadObjectException -> L10
        Api.Client r12 = (Api.Client) r1.zac.get(r2);     // Catch: DeadObjectException -> L10
        Preconditions.checkNotNull(r12, "Appropriate Api was not requested.");     // Catch: DeadObjectException -> L10
        if (r12.isConnected() == false) goto L5;
    L8:
        r4.run(r12);     // Catch: DeadObjectException -> L10
        return r4;
    L5:
        if (r0.zab.containsKey(r4.getClientKey()) == false) goto L8;
        r4.setFailedResult(new Status(17));     // Catch: DeadObjectException -> L10
        return r4;
    L10:
        this.zaa.zar(new zaae(this, this));
        return r4;
    }

    @Override // com.google.android.gms.common.api.internal.zaba
    public final boolean zad() {
        if (this.zab == true) goto L13;
        zabd r0 = this.zaa;
        Set r1 = r0.zag.zah;
        if (r1 != null) goto L8;
    L15:
        r0.zaq(null);
        return true;
    L8:
        if (r1.isEmpty() == true) goto L15;
        this.zab = true;
        Iterator r4 = r1.iterator();
    L11:
        if (r4.hasNext() == false) goto L18;
        ((zacs) r4.next()).zab();
        goto L11
    L18:
        return false;
    L13:
        return false;
    }

    @Override // com.google.android.gms.common.api.internal.zaba
    public final void zae() {
        if (this.zab == false) goto L6;
        this.zab = false;
        this.zaa.zar(new zaaf(this, this));
        return;
    }

    @Override // com.google.android.gms.common.api.internal.zaba
    public final void zaf(Bundle r1) {
    }

    @Override // com.google.android.gms.common.api.internal.zaba
    public final void zag(ConnectionResult r1, Api r2, boolean r3) {
    }

    @Override // com.google.android.gms.common.api.internal.zaba
    public final void zah(int r3) {
        zabd r0 = this.zaa;
        r0.zaq(null);
        boolean r2 = this.zab;
        r0.zah.zac(r3, r2);
    }

    public final /* synthetic */ zabd zaj() {
        return this.zaa;
    }
}
