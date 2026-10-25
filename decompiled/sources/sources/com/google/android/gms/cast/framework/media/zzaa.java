package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaQueueItem;
import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes4.dex */
final class zzaa extends zzbc {
    final /* synthetic */ MediaQueueItem[] zza;
    final /* synthetic */ JSONObject zzb;
    final /* synthetic */ RemoteMediaClient zzc;

    public zzaa(RemoteMediaClient r1, MediaQueueItem[] r2, JSONObject r3) {
        this.zza = r2;
        this.zzb = r3;
        Objects.requireNonNull(r1);
        this.zzc = r1;
        super(r1, false);
    }

    @Override // com.google.android.gms.cast.framework.media.zzbc
    public final void zza() {
        JSONObject r9 = this.zzb;
        MediaQueueItem[] r5 = this.zza;
        this.zzc.zzt().zzE(zzb(), 0, -1, r5, 0, null, null, r9);
    }
}
