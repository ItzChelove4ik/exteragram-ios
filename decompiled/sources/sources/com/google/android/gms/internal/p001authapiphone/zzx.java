package com.google.android.gms.internal.p001authapiphone;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
public final /* synthetic */ class zzx implements RemoteCall {
    public final /* synthetic */ zzab zza;

    public /* synthetic */ zzx(zzab r1) {
        this.zza = r1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object r2, Object r3) {
        zzh r22 = (zzh) ((zzw) r2).getService();
        zzab r1 = this.zza;
        r22.zzg(new zzz(r1, (TaskCompletionSource) r3));
    }
}
