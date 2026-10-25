package com.google.android.gms.internal.location;

import android.location.Location;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final class zzdj extends zzx {
    final /* synthetic */ TaskCompletionSource zza;

    public zzdj(TaskCompletionSource r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.location.zzy
    public final void zzb(Status r1, Location r2) {
        TaskUtil.setResultOrApiException(r1, r2, this.zza);
    }
}
