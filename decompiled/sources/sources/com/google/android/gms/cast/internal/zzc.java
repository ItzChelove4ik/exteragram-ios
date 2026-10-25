package com.google.android.gms.cast.internal;

import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;

/* loaded from: classes4.dex */
public abstract class zzc extends BaseImplementation$ApiMethodImpl {
    public zzc(GoogleApiClient r2) {
        super(Cast.API, r2);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl, com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder
    public final /* bridge */ /* synthetic */ void setResult(Object r1) {
        super.setResult((Result) r1);
    }

    public final void zzc(int r2) {
        setResult(createFailedResult(new Status(CastStatusCodes.INVALID_REQUEST)));
    }
}
