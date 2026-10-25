package com.google.android.gms.internal.cloudmessaging;

import com.google.android.gms.common.api.ApiMetadata;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzi extends zzf {
    final /* synthetic */ TaskCompletionSource zza;

    public zzi(zzm r1, TaskCompletionSource r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
    }

    @Override // com.google.android.gms.internal.cloudmessaging.zzg
    public final void zzb(Status r1, String r2, ApiMetadata r3) {
        TaskUtil.setResultOrApiException(r1, r2, this.zza);
    }
}
