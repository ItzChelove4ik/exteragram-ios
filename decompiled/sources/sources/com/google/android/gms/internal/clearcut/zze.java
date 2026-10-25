package com.google.android.gms.internal.clearcut;

import android.content.Context;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.util.VisibleForTesting;

/* loaded from: classes4.dex */
public final class zze extends GoogleApi<Api.ApiOptions.NoOptions> implements com.google.android.gms.clearcut.zzb {
    @VisibleForTesting
    private zze(Context r4) {
        super(r4, ClearcutLogger.API, null, new ApiExceptionMapper());
    }

    public static com.google.android.gms.clearcut.zzb zzb(Context r1) {
        return new zze(r1);
    }

    @Override // com.google.android.gms.clearcut.zzb
    public final PendingResult<Status> zzb(com.google.android.gms.clearcut.zze r3) {
        return doBestEffortWrite(new zzh(r3, asGoogleApiClient()));
    }
}
