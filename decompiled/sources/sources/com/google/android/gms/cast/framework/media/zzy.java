package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaQueueItem;
import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes4.dex */
final class zzy extends zzbc {
    final /* synthetic */ MediaQueueItem[] zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ JSONObject zzc;
    final /* synthetic */ RemoteMediaClient zzd;

    public zzy(RemoteMediaClient r1, MediaQueueItem[] r2, int r3, JSONObject r4) {
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r4;
        Objects.requireNonNull(r1);
        this.zzd = r1;
        super(r1, false);
    }

    @Override // com.google.android.gms.cast.framework.media.zzbc
    public final void zza() {
        JSONObject r8 = this.zzc;
        this.zzd.zzt().zzD(zzb(), this.zza, this.zzb, 0, -1, -1, r8);
    }
}
