package com.google.android.gms.cast;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zzba implements RemoteCall {
    private final /* synthetic */ zzbm zza;
    private final /* synthetic */ double zzb;

    public /* synthetic */ zzba(zzbm r1, double r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object r4, Object r5) {
        zzbm r0 = this.zza;
        double r1 = this.zzb;
        com.google.android.gms.cast.internal.zzy r42 = (com.google.android.gms.cast.internal.zzy) r4;
        r0.zzy(r1, r42, (TaskCompletionSource) r5);
    }
}
