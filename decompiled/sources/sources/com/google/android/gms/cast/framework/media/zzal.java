package com.google.android.gms.cast.framework.media;

import java.util.Objects;

/* loaded from: classes4.dex */
final class zzal extends zzbc {
    final /* synthetic */ int[] zza;
    final /* synthetic */ RemoteMediaClient zzb;

    public zzal(RemoteMediaClient r1, boolean r2, int[] r3) {
        this.zza = r3;
        Objects.requireNonNull(r1);
        this.zzb = r1;
        super(r1, true);
    }

    @Override // com.google.android.gms.cast.framework.media.zzbc
    public final void zza() {
        this.zzb.zzt().zzJ(zzb(), this.zza);
    }
}
