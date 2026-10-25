package com.google.android.gms.cast.framework.media;

import java.util.List;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzao extends zzbc {
    final /* synthetic */ String zza;
    final /* synthetic */ RemoteMediaClient zzb;

    public zzao(RemoteMediaClient r1, boolean r2, String r3, List r4) {
        this.zza = r3;
        Objects.requireNonNull(r1);
        this.zzb = r1;
        super(r1, true);
    }

    @Override // com.google.android.gms.cast.framework.media.zzbc
    public final void zza() {
        this.zzb.zzt().zzK(this.zza, null);
    }
}
