package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
import java.util.ArrayList;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zaam extends zaaq {
    final /* synthetic */ zaar zaa;
    private final ArrayList zac;

    public zaam(zaar r2, ArrayList r3) {
        Objects.requireNonNull(r2);
        this.zaa = r2;
        super(r2, null);
        this.zac = r3;
    }

    @Override // com.google.android.gms.common.api.internal.zaaq
    public final void zaa() {
        zaar r0 = this.zaa;
        zaaz r1 = r0.zar().zag;
        r1.zad = r0.zap();
        ArrayList r6 = this.zac;
        int r12 = r6.size();
        int r2 = 0;
    L3:
        if (r2 >= r12) goto L5;
        Api.Client r3 = (Api.Client) r6.get(r2);
        zabd r4 = r0.zar();
        r3.getRemoteService(r0.zax(), r4.zag.zad);
        r2 = r2 + 1;
        goto L3
    }
}
