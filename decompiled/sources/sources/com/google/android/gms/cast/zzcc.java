package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzcc extends zzdc {
    final /* synthetic */ TextTrackStyle zza;
    final /* synthetic */ RemoteMediaPlayer zzb;

    public zzcc(RemoteMediaPlayer r1, GoogleApiClient r2, TextTrackStyle r3) {
        this.zza = r3;
        Objects.requireNonNull(r1);
        this.zzb = r1;
        super(r1, r2);
    }

    @Override // com.google.android.gms.cast.zzdc
    public final void zza(com.google.android.gms.cast.internal.zzx r2) {
        this.zzb.zzg().zzu(zzb(), this.zza);
    }
}
