package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.ApiMetadata;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.location.zzv;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final class zzdc extends zzp {
    final /* synthetic */ TaskCompletionSource zza;
    final /* synthetic */ zzv zzb;

    public zzdc(TaskCompletionSource r1, zzv r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.internal.location.zzq
    public final void zzd(zzk r1, ApiMetadata r2) {
        TaskUtil.setResultOrApiException(r1.getStatus(), this.zza, r2);
    }

    @Override // com.google.android.gms.internal.location.zzq
    public final void zze(ApiMetadata r1) {
        this.zzb.zzf();
    }
}
