package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.IStatusCallback;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final class zzdi extends IStatusCallback.Stub {
    final /* synthetic */ Object zza;
    final /* synthetic */ TaskCompletionSource zzb;

    public zzdi(Object r1, TaskCompletionSource r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.common.api.internal.IStatusCallback
    public final void onResult(Status r2) {
        TaskUtil.setResultOrApiException(r2, this.zza, this.zzb);
    }
}
