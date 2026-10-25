package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zzkf implements RemoteCall {
    private final /* synthetic */ zzkk zza;

    public /* synthetic */ zzkf(zzkk r1) {
        this.zza = r1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object r2, Object r3) {
        zzkt r22 = (zzkt) ((zzku) r2).getService();
        zzkk r1 = this.zza;
        r22.zzi(new zzjy(r1, (TaskCompletionSource) r3));
    }
}
