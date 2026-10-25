package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zaal extends zaaq {
    final /* synthetic */ zaar zaa;
    private final Map zac;

    public zaal(zaar r2, Map r3) {
        Objects.requireNonNull(r2);
        this.zaa = r2;
        super(r2, null);
        this.zac = r3;
    }

    @Override // com.google.android.gms.common.api.internal.zaaq
    public final void zaa() {
        zaar r0 = this.zaa;
        com.google.android.gms.common.internal.zao r1 = new com.google.android.gms.common.internal.zao(r0.zau());
        ArrayList r2 = new ArrayList();
        ArrayList r3 = new ArrayList();
        Map r4 = this.zac;
        Iterator r5 = r4.keySet().iterator();
    L4:
        if (r5.hasNext() == false) goto L11;
        Api.Client r6 = (Api.Client) r5.next();
        if (r6.requiresGooglePlayServices() == false) goto L10;
        if (((zaai) r4.get(r6)).zaa() == true) goto L10;
        r2.add(r6);
    L10:
        r3.add(r6);
        goto L4
    L11:
        int r62 = 0;
        int r7 = -1;
        if (r2.isEmpty() == false) goto L18;
        int r22 = r3.size();
    L14:
        if (r62 >= r22) goto L22;
        Api.Client r52 = (Api.Client) r3.get(r62);
        r7 = r1.zaa(r0.zat(), r52);
        r62 = r62 + 1;
        if (r7 != 0) goto L14;
    L22:
        if (r7 == 0) goto L26;
        zaaj r23 = new zaaj(this, r0, new ConnectionResult(r7, null));
        r0.zar().zar(r23);
        return;
    L26:
        if (r0.zaw() == true) goto L28;
    L30:
        Iterator r24 = r4.keySet().iterator();
    L32:
        if (r24.hasNext() == false) goto L39;
        Api.Client r32 = (Api.Client) r24.next();
        BaseGmsClient.ConnectionProgressReportCallbacks r53 = (BaseGmsClient.ConnectionProgressReportCallbacks) r4.get(r32);
        if (r32.requiresGooglePlayServices() == false) goto L38;
        if (r1.zaa(r0.zat(), r32) == 0) goto L38;
        zaak r33 = new zaak(this, r0, r53);
        r0.zar().zar(r33);
    L38:
        r32.connect(r53);
        goto L32
    L39:
        return;
    L28:
        if (r0.zav() == null) goto L30;
        r0.zav().zad();
        goto L30
    L18:
        int r34 = r2.size();
    L19:
        if (r62 >= r34) goto L22;
        Api.Client r54 = (Api.Client) r2.get(r62);
        r7 = r1.zaa(r0.zat(), r54);
        r62 = r62 + 1;
        if (r7 == 0) goto L19;
        goto L22
    }
}
