package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaQueueItem;
import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes4.dex */
final class zzx extends zzbc {
    final /* synthetic */ MediaQueueItem[] zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ int zzc;
    final /* synthetic */ long zzd;
    final /* synthetic */ JSONObject zze;
    final /* synthetic */ RemoteMediaClient zzf;

    public zzx(RemoteMediaClient r1, MediaQueueItem[] r2, int r3, int r4, long r5, JSONObject r7) {
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r5;
        this.zze = r7;
        Objects.requireNonNull(r1);
        this.zzf = r1;
        super(r1, false);
    }

    @Override // com.google.android.gms.cast.framework.media.zzbc
    public final void zza() {
        this.zzf.zzt().zzC(zzb(), this.zza, this.zzb, this.zzc, this.zzd, this.zze);
    }
}
