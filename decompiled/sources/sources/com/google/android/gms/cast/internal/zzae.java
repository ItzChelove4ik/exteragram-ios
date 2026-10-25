package com.google.android.gms.cast.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

/* loaded from: classes4.dex */
public class zzae extends zzc {
    public zzae(GoogleApiClient r1) {
        super(r1);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* bridge */ /* synthetic */ Result createFailedResult(Status r1) {
        return r1;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl
    public /* bridge */ /* synthetic */ void doExecute(Api.AnyClient r1) {
        zza((zzx) r1);
    }

    public void zza(zzx r1) {
        throw null;
    }
}
