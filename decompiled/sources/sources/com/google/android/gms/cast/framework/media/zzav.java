package com.google.android.gms.cast.framework.media;

import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes4.dex */
final class zzav extends zzbc {
    final /* synthetic */ double zza;
    final /* synthetic */ JSONObject zzb;
    final /* synthetic */ RemoteMediaClient zzc;

    public zzav(RemoteMediaClient r1, double r2, JSONObject r4) {
        this.zza = r2;
        this.zzb = r4;
        Objects.requireNonNull(r1);
        this.zzc = r1;
        super(r1, false);
    }

    @Override // com.google.android.gms.cast.framework.media.zzbc
    public final void zza() {
        this.zzc.zzt().zzr(zzb(), this.zza, this.zzb);
    }
}
