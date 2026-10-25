package com.google.android.gms.cast.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.IStatusCallback;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzf extends IStatusCallback.Stub {
    final /* synthetic */ TaskCompletionSource zza;

    public zzf(zzn r1, TaskCompletionSource r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
    }

    @Override // com.google.android.gms.common.api.internal.IStatusCallback
    public final void onResult(Status r1) {
        TaskUtil.setResultOrApiException(r1, this.zza);
    }
}
