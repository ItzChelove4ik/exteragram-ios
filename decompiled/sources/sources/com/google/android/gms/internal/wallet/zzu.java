package com.google.android.gms.internal.wallet;

import android.os.Bundle;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes5.dex */
final class zzu extends zzt {
    private final TaskCompletionSource zza;

    public zzu(TaskCompletionSource r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.wallet.zzt, com.google.android.gms.internal.wallet.zzq
    public final void zzc(Status r1, boolean r2, Bundle r3) {
        TaskUtil.setResultOrApiException(r1, Boolean.valueOf(r2), this.zza);
    }

    @Override // com.google.android.gms.internal.wallet.zzt, com.google.android.gms.internal.wallet.zzq
    public final void zzg(int r1, boolean r2, Bundle r3) {
        TaskUtil.setResultOrApiException(new Status(r1), Boolean.valueOf(r2), this.zza);
    }
}
