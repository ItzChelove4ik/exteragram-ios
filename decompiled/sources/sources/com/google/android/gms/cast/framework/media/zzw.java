package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.TextTrackStyle;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzw extends zzbc {
    final /* synthetic */ TextTrackStyle zza;
    final /* synthetic */ RemoteMediaClient zzb;

    public zzw(RemoteMediaClient r1, TextTrackStyle r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
        super(r1, false);
    }

    @Override // com.google.android.gms.cast.framework.media.zzbc
    public final void zza() {
        this.zzb.zzt().zzu(zzb(), this.zza);
    }
}
