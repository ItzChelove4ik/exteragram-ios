package com.google.android.gms.internal.cast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzfs extends zzgj {
    final /* synthetic */ TaskCompletionSource zza;

    public zzfs(zzfu r1, TaskCompletionSource r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
    }

    @Override // com.google.android.gms.internal.cast.zzgf
    public final void zzb(Status r4, zzgc r5) {
        TaskUtil.setResultOrApiException(r4, new zzfv(new zzgi(Status.RESULT_SUCCESS, r5)), this.zza);
    }
}
