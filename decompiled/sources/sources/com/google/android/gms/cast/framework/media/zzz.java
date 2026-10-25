package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaQueueItem;
import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes4.dex */
final class zzz extends zzbc {
    final /* synthetic */ MediaQueueItem zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ long zzc;
    final /* synthetic */ JSONObject zzd;
    final /* synthetic */ RemoteMediaClient zze;

    public zzz(RemoteMediaClient r1, MediaQueueItem r2, int r3, long r4, JSONObject r6) {
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r6;
        Objects.requireNonNull(r1);
        this.zze = r1;
        super(r1, false);
    }

    @Override // com.google.android.gms.cast.framework.media.zzbc
    public final void zza() {
        MediaQueueItem r0 = this.zza;
        long r7 = this.zzc;
        JSONObject r9 = this.zzd;
        int r4 = this.zzb;
        com.google.android.gms.cast.internal.zzar r1 = this.zze.zzt();
        r1.zzD(zzb(), new MediaQueueItem[]{r0}, r4, 0, 0, r7, r9);
    }
}
