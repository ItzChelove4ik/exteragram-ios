package com.google.android.gms.wallet;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.wallet.zzy;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes5.dex */
public final /* synthetic */ class zzah implements RemoteCall {
    public final /* synthetic */ IsReadyToPayRequest zza;

    public /* synthetic */ zzah(IsReadyToPayRequest r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object r1, Object r2) {
        IsReadyToPayRequest r0 = this.zza;
        ((zzy) r1).zzs(r0, (TaskCompletionSource) r2);
    }
}
