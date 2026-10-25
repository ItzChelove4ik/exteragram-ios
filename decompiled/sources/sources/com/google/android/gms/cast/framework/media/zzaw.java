package com.google.android.gms.cast.framework.media;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;

/* loaded from: classes4.dex */
final /* synthetic */ class zzaw implements OnFailureListener {
    private final /* synthetic */ zzax zza;
    private final /* synthetic */ long zzb;

    public /* synthetic */ zzaw(zzax r1, long r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final /* synthetic */ void onFailure(Exception r3) {
        if ((r3 instanceof ApiException) == false) goto L5;
        int r32 = ((ApiException) r3).getStatusCode();
    L6:
        long r0 = this.zzb;
        this.zza.zza.zzt().zzN(r0, r32);
        return;
    L5:
        r32 = 13;
        goto L6
    }
}
