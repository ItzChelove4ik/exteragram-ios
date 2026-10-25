package com.google.android.gms.cast;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzf extends com.google.android.gms.cast.internal.zzae {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;

    public zzf(zzm r1, GoogleApiClient r2, String r3, String r4) {
        this.zza = r3;
        this.zzb = r4;
        Objects.requireNonNull(r1);
        super(r2);
    }

    @Override // com.google.android.gms.cast.internal.zzae, com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient r1) {
        zza((com.google.android.gms.cast.internal.zzx) r1);
    }

    @Override // com.google.android.gms.cast.internal.zzae
    public final void zza(com.google.android.gms.cast.internal.zzx r3) {
        r3.zzp(this.zza, this.zzb, this);     // Catch: Throwable -> L4
        return;
    L4:
        zzc(CastStatusCodes.INVALID_REQUEST);
    }
}
