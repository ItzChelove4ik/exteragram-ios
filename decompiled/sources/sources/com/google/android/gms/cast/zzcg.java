package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes4.dex */
final class zzcg extends zzdc {
    final /* synthetic */ MediaQueueItem[] zza;
    final /* synthetic */ JSONObject zzb;
    final /* synthetic */ RemoteMediaPlayer zzc;

    public zzcg(RemoteMediaPlayer r1, GoogleApiClient r2, MediaQueueItem[] r3, JSONObject r4) {
        this.zza = r3;
        this.zzb = r4;
        Objects.requireNonNull(r1);
        this.zzc = r1;
        super(r1, r2);
    }

    @Override // com.google.android.gms.cast.zzdc
    public final void zza(com.google.android.gms.cast.internal.zzx r11) {
        JSONObject r9 = this.zzb;
        MediaQueueItem[] r5 = this.zza;
        this.zzc.zzg().zzE(zzb(), 0, -1, r5, 0, null, null, r9);
    }
}
