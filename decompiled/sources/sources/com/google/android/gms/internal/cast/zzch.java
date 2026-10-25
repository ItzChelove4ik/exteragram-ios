package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnFailureListener;

/* loaded from: classes4.dex */
final /* synthetic */ class zzch implements OnFailureListener {
    private final /* synthetic */ zzcf zza;

    public /* synthetic */ zzch(zzcf r1, zzcg r2) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final /* synthetic */ void onFailure(Exception r4) {
        Status r0 = new Status(8, "unknown error");
        if ((r4 instanceof ApiException) == false) goto L5;
        ApiException r42 = (ApiException) r4;
        r0 = new Status(r42.getStatusCode(), r42.getMessage());
    L5:
        zzcf r3 = this.zza;
        int r43 = CastSession.zza;
        r3.setResult(r0);
    }
}
