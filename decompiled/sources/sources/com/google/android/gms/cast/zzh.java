package com.google.android.gms.cast;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzh extends zzo {
    final /* synthetic */ String zza;
    final /* synthetic */ LaunchOptions zzb;

    public zzh(zzm r1, GoogleApiClient r2, String r3, LaunchOptions r4) {
        this.zza = r3;
        this.zzb = r4;
        Objects.requireNonNull(r1);
        super(r2);
    }

    @Override // com.google.android.gms.cast.zzo, com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient r1) {
        zza((com.google.android.gms.cast.internal.zzx) r1);
    }

    @Override // com.google.android.gms.cast.zzo
    public final void zza(com.google.android.gms.cast.internal.zzx r3) {
        r3.zzq(this.zza, this.zzb, this);     // Catch: IllegalStateException -> L4
        return;
    L4:
        zzc(CastStatusCodes.INVALID_REQUEST);
    }
}
