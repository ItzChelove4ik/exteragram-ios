package com.google.android.gms.internal.cast;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zzar implements RemoteCall {
    private final /* synthetic */ zzav zza;
    private final /* synthetic */ zzah zzb;

    public /* synthetic */ zzar(zzav r1, zzah r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object r3, Object r4) {
        zzao r1 = new zzao(this.zza, (TaskCompletionSource) r4);
        ((zzai) ((zzaf) r3).getService()).zzf(r1, this.zzb);
    }
}
