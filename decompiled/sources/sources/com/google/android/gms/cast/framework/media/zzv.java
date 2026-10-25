package com.google.android.gms.cast.framework.media;

import java.util.Objects;

/* loaded from: classes4.dex */
final class zzv extends zzbc {
    final /* synthetic */ long[] zza;
    final /* synthetic */ RemoteMediaClient zzb;

    public zzv(RemoteMediaClient r1, long[] r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
        super(r1, false);
    }

    @Override // com.google.android.gms.cast.framework.media.zzbc
    public final void zza() {
        this.zzb.zzt().zzt(zzb(), this.zza);
    }
}
