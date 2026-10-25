package com.google.android.gms.internal.cast;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzem extends zzer {
    final /* synthetic */ String zza;
    final /* synthetic */ zzet zzb;

    public zzem(zzet r1, GoogleApiClient r2, String r3) {
        this.zza = r3;
        Objects.requireNonNull(r1);
        this.zzb = r1;
        super(r1, r2);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient r1) {
        zza((zzew) r1);
    }

    @Override // com.google.android.gms.internal.cast.zzer
    public final void zza(zzew r3) {
        r3.zzp(new zzep(this, r3), this.zzb.zzf(), this.zza);
    }
}
