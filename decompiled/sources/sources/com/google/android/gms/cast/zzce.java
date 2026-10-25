package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes4.dex */
final class zzce extends zzdc {
    final /* synthetic */ MediaQueueItem[] zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ JSONObject zzc;
    final /* synthetic */ RemoteMediaPlayer zzd;

    public zzce(RemoteMediaPlayer r1, GoogleApiClient r2, MediaQueueItem[] r3, int r4, JSONObject r5) {
        this.zza = r3;
        this.zzb = r4;
        this.zzc = r5;
        Objects.requireNonNull(r1);
        this.zzd = r1;
        super(r1, r2);
    }

    @Override // com.google.android.gms.cast.zzdc
    public final void zza(com.google.android.gms.cast.internal.zzx r10) {
        JSONObject r8 = this.zzc;
        this.zzd.zzg().zzD(zzb(), this.zza, this.zzb, 0, -1, -1, r8);
    }
}
