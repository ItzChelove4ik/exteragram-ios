package com.google.android.gms.wallet;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.wallet.zzy;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes5.dex */
public final /* synthetic */ class zzag implements RemoteCall {
    public final /* synthetic */ PaymentDataRequest zza;

    public /* synthetic */ zzag(PaymentDataRequest r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object r1, Object r2) {
        PaymentDataRequest r0 = this.zza;
        ((zzy) r1).zzt(r0, (TaskCompletionSource) r2);
    }
}
