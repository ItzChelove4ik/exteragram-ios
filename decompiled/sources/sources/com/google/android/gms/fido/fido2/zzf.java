package com.google.android.gms.fido.fido2;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.internal.fido.zzq;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final class zzf extends zzq {
    final /* synthetic */ TaskCompletionSource zza;

    public zzf(Fido2ApiClient r1, TaskCompletionSource r2) {
        this.zza = r2;
    }

    @Override // com.google.android.gms.internal.fido.zzr
    public final void zzb(Status r1, PendingIntent r2) {
        TaskUtil.setResultOrApiException(r1, r2, this.zza);
    }
}
