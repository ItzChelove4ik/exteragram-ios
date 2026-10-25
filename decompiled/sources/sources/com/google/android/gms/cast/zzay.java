package com.google.android.gms.cast;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zzay implements RemoteCall {
    private final /* synthetic */ zzbm zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;

    public /* synthetic */ zzay(zzbm r1, String r2, String r3, zzbn r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object r7, Object r8) {
        com.google.android.gms.cast.internal.zzy r4 = (com.google.android.gms.cast.internal.zzy) r7;
        zzbm r0 = this.zza;
        String r1 = this.zzb;
        String r2 = this.zzc;
        r0.zzw(r1, r2, null, r4, (TaskCompletionSource) r8);
    }
}
