package com.google.android.gms.internal.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import java.util.Objects;

/* loaded from: classes4.dex */
abstract class zzer extends BaseImplementation$ApiMethodImpl {
    final /* synthetic */ zzet zzc;

    public zzer(zzet r1, GoogleApiClient r2) {
        Objects.requireNonNull(r1);
        this.zzc = r1;
        super(r1.zzc(), r2);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status r1) {
        return new zzes(r1);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl, com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder
    public final /* bridge */ /* synthetic */ void setResult(Object r1) {
        super.setResult((Result) r1);
    }

    public abstract void zza(zzew r1);
}
