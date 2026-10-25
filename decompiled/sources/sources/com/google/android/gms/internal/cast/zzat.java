package com.google.android.gms.internal.cast;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zzat implements RemoteCall {
    private final /* synthetic */ zzav zza;

    public /* synthetic */ zzat(zzav r1) {
        this.zza = r1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object r2, Object r3) {
        zzaq r0 = new zzaq(this.zza, (TaskCompletionSource) r3);
        ((zzai) ((zzaf) r2).getService()).zzh(r0);
    }
}
