package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnSuccessListener;

/* loaded from: classes4.dex */
final /* synthetic */ class zzci implements OnSuccessListener {
    private final /* synthetic */ zzcf zza;

    public /* synthetic */ zzci(zzcf r1, zzcg r2) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final /* synthetic */ void onSuccess(Object r2) {
        Void r22 = (Void) r2;
        int r23 = CastSession.zza;
        Status r24 = new Status(0);
        this.zza.setResult(r24);
    }
}
