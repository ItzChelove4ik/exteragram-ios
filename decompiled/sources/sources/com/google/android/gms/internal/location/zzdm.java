package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.ApiMetadata;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final class zzdm extends zzp {
    final /* synthetic */ Object zza;
    final /* synthetic */ TaskCompletionSource zzb;

    public zzdm(Object r1, TaskCompletionSource r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.internal.location.zzq
    public final void zzd(zzk r2, ApiMetadata r3) {
        TaskUtil.setResultOrApiException(r2.getStatus(), this.zza, this.zzb, r3);
    }

    @Override // com.google.android.gms.internal.location.zzq
    public final void zze(ApiMetadata r1) {
    }
}
