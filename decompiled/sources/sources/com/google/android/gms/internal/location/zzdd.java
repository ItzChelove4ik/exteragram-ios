package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.ApiMetadata;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final class zzdd extends zzz {
    final /* synthetic */ TaskCompletionSource zza;

    public zzdd(TaskCompletionSource r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.location.zzaa
    public final void zzb(LocationSettingsResult r3, ApiMetadata r4) {
        TaskUtil.setResultOrApiException(r3.getStatus(), new LocationSettingsResponse(r3), this.zza, r4);
    }
}
