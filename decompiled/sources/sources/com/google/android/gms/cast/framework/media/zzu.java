package com.google.android.gms.cast.framework.media;

import java.util.Objects;

/* loaded from: classes4.dex */
final class zzu extends zzbc {
    final /* synthetic */ RemoteMediaClient zza;

    public zzu(RemoteMediaClient r2) {
        Objects.requireNonNull(r2);
        this.zza = r2;
        super(r2, false);
    }

    @Override // com.google.android.gms.cast.framework.media.zzbc
    public final void zza() {
        this.zza.zzt().zzs(zzb());
    }
}
