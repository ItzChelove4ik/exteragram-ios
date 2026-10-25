package com.google.android.gms.cast.framework.media;

import java.util.Objects;

/* loaded from: classes4.dex */
final class zzam extends zzbc {
    final /* synthetic */ int zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ int zzc;
    final /* synthetic */ RemoteMediaClient zzd;

    public zzam(RemoteMediaClient r1, boolean r2, int r3, int r4, int r5) {
        this.zza = r3;
        this.zzb = r4;
        this.zzc = r5;
        Objects.requireNonNull(r1);
        this.zzd = r1;
        super(r1, true);
    }

    @Override // com.google.android.gms.cast.framework.media.zzbc
    public final void zza() {
        this.zzd.zzt().zzI(zzb(), this.zza, this.zzb, this.zzc);
    }
}
