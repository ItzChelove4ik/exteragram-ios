package com.google.android.gms.cast.framework.media;

import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes4.dex */
final class zzag extends zzbc {
    final /* synthetic */ int zza;
    final /* synthetic */ JSONObject zzb;
    final /* synthetic */ RemoteMediaClient zzc;

    public zzag(RemoteMediaClient r1, int r2, JSONObject r3) {
        this.zza = r2;
        this.zzb = r3;
        Objects.requireNonNull(r1);
        this.zzc = r1;
        super(r1, false);
    }

    @Override // com.google.android.gms.cast.framework.media.zzbc
    public final void zza() {
        this.zzc.zzt().zzE(zzb(), 0, -1, null, 0, null, Integer.valueOf(this.zza), this.zzb);
    }
}
