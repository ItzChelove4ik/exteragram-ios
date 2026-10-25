package com.google.android.gms.cast;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zzaw implements RemoteCall {
    private final /* synthetic */ zzbm zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;

    public /* synthetic */ zzaw(zzbm r1, String r2, String r3, String r4) {
        this.zza = r1;
        this.zzb = r3;
        this.zzc = r4;
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object r7, Object r8) {
        String r2 = this.zzb;
        String r3 = this.zzc;
        com.google.android.gms.cast.internal.zzy r4 = (com.google.android.gms.cast.internal.zzy) r7;
        zzbm r0 = this.zza;
        r0.zzu(null, r2, r3, r4, (TaskCompletionSource) r8);
    }
}
