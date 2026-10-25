package com.google.android.gms.cast;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzg extends zzo {
    final /* synthetic */ String zza;

    public zzg(zzm r1, GoogleApiClient r2, String r3) {
        this.zza = r3;
        Objects.requireNonNull(r1);
        super(r2);
    }

    @Override // com.google.android.gms.cast.zzo, com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient r1) {
        zza((com.google.android.gms.cast.internal.zzx) r1);
    }

    @Override // com.google.android.gms.cast.zzo
    public final void zza(com.google.android.gms.cast.internal.zzx r4) {
        String r0 = this.zza;     // Catch: IllegalStateException -> L4
        LaunchOptions r1 = new LaunchOptions();     // Catch: IllegalStateException -> L4
        r1.setRelaunchIfRunning(false);     // Catch: IllegalStateException -> L4
        r4.zzq(r0, r1, this);     // Catch: IllegalStateException -> L4
        return;
    L4:
        zzc(CastStatusCodes.INVALID_REQUEST);
    }
}
