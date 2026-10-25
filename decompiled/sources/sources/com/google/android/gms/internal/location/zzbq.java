package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zzbq implements RemoteCall {
    private final /* synthetic */ zzcf zza;
    private final /* synthetic */ LocationRequest zzb;

    public /* synthetic */ zzbq(zzcf r1, LocationRequest r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object r2, Object r3) {
        zzec r22 = (zzec) r2;
        Api r0 = zzcg.zzb;
        zzcf r02 = this.zza;
        LocationRequest r1 = this.zzb;
        r22.zzt(r02, r1, (TaskCompletionSource) r3);
    }
}
