package com.google.android.gms.cast.framework.media;

import java.util.Objects;

/* loaded from: classes4.dex */
final class zzak extends zzbc {
    final /* synthetic */ RemoteMediaClient zza;

    public zzak(RemoteMediaClient r1, boolean r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        super(r1, true);
    }

    @Override // com.google.android.gms.cast.framework.media.zzbc
    public final void zza() {
        this.zza.zzt().zzH(zzb());
    }
}
