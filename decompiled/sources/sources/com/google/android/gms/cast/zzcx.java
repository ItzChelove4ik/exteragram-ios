package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzcx extends zzdc {
    final /* synthetic */ RemoteMediaPlayer zza;

    public zzcx(RemoteMediaPlayer r1, GoogleApiClient r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        super(r1, r2);
    }

    @Override // com.google.android.gms.cast.zzdc
    public final void zza(com.google.android.gms.cast.internal.zzx r1) {
        this.zza.zzg().zzs(zzb());
    }
}
