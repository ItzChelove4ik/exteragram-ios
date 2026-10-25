package com.google.android.gms.cast.framework.media;

import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes4.dex */
final class zzai extends zzbc {
    final /* synthetic */ int zza;
    final /* synthetic */ long zzb;
    final /* synthetic */ JSONObject zzc;
    final /* synthetic */ RemoteMediaClient zzd;

    public zzai(RemoteMediaClient r1, int r2, long r3, JSONObject r5) {
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r5;
        Objects.requireNonNull(r1);
        this.zzd = r1;
        super(r1, false);
    }

    @Override // com.google.android.gms.cast.framework.media.zzbc
    public final void zza() {
        this.zzd.zzt().zzE(zzb(), this.zza, this.zzb, null, 0, null, null, this.zzc);
    }
}
