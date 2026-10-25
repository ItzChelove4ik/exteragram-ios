package com.google.android.gms.cast;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zzaz implements RemoteCall {
    private final /* synthetic */ zzbm zza;
    private final /* synthetic */ String zzb;

    public /* synthetic */ zzaz(zzbm r1, String r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object r2, Object r3) {
        zzbm r0 = this.zza;
        String r1 = this.zzb;
        com.google.android.gms.cast.internal.zzy r22 = (com.google.android.gms.cast.internal.zzy) r2;
        r0.zzx(r1, r22, (TaskCompletionSource) r3);
    }
}
