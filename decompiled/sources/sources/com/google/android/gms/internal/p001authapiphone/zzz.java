package com.google.android.gms.internal.p001authapiphone;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final class zzz extends zzi {
    final /* synthetic */ TaskCompletionSource zza;

    public zzz(zzab r1, TaskCompletionSource r2) {
        this.zza = r2;
    }

    @Override // com.google.android.gms.internal.p001authapiphone.zzj
    public final void zzb(Status r1) {
        TaskUtil.setResultOrApiException(r1, this.zza);
    }
}
