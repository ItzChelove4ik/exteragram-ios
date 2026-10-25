package com.google.android.gms.cast;

import com.google.android.gms.common.api.ApiMetadata;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzx extends zzy {
    final /* synthetic */ TaskCompletionSource zza;
    final /* synthetic */ CastRemoteDisplayClient zzb;

    public zzx(CastRemoteDisplayClient r1, TaskCompletionSource r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
        super(null);
    }

    @Override // com.google.android.gms.cast.zzy, com.google.android.gms.internal.cast.zzey
    public final void zzd(int r4, ApiMetadata r5) {
        Object[] r42 = {Integer.valueOf(r4)};
        CastRemoteDisplayClient r0 = this.zzb;
        r0.zzb().d("onError: %d", r42);
        r0.zza();
        TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, this.zza, r5);
    }

    @Override // com.google.android.gms.cast.zzy, com.google.android.gms.internal.cast.zzey
    public final void zzf(ApiMetadata r5) {
        CastRemoteDisplayClient r0 = this.zzb;
        r0.zzb().d("onDisconnected", new Object[0]);
        r0.zza();
        TaskUtil.setResultOrApiException(Status.RESULT_SUCCESS, this.zza, r5);
    }
}
