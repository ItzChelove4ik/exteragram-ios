package com.google.android.gms.cast;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

/* loaded from: classes4.dex */
class zzo extends com.google.android.gms.cast.internal.zzc {
    public zzo(GoogleApiClient r1) {
        super(r1);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status r2) {
        return new zzn(this, r2);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl
    public /* bridge */ /* synthetic */ void doExecute(Api.AnyClient r1) {
        zza((com.google.android.gms.cast.internal.zzx) r1);
    }

    public void zza(com.google.android.gms.cast.internal.zzx r1) {
        throw null;
    }
}
