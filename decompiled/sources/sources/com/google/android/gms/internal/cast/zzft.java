package com.google.android.gms.internal.cast;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zzft implements RemoteCall {
    private final /* synthetic */ zzfu zza;

    public /* synthetic */ zzft(zzfu r1) {
        this.zza = r1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object r2, Object r3) {
        zzfs r0 = new zzfs(this.zza, (TaskCompletionSource) r3);
        ((zzgh) ((zzgm) r2).getService()).zze(r0);
    }
}
