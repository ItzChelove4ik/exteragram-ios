package com.google.android.gms.internal.cast;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzen extends zzer {
    public zzen(zzet r1, GoogleApiClient r2) {
        Objects.requireNonNull(r1);
        super(r1, r2);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient r1) {
        zza((zzew) r1);
    }

    @Override // com.google.android.gms.internal.cast.zzer
    public final void zza(zzew r2) {
        r2.zzq(new zzeq(this));
    }
}
