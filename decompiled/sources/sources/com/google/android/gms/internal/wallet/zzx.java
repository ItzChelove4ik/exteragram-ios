package com.google.android.gms.internal.wallet;

import android.os.Bundle;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.wallet.AutoResolveHelper;
import com.google.android.gms.wallet.PaymentData;

/* loaded from: classes5.dex */
final class zzx extends zzt {
    private final TaskCompletionSource zza;

    public zzx(TaskCompletionSource r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.wallet.zzt, com.google.android.gms.internal.wallet.zzq
    public final void zzf(Status r1, PaymentData r2, Bundle r3) {
        AutoResolveHelper.zzd(r1, r2, this.zza);
    }
}
