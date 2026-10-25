package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes4.dex */
final class zzcf extends zzdc {
    final /* synthetic */ MediaQueueItem zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ long zzc;
    final /* synthetic */ JSONObject zzd;
    final /* synthetic */ RemoteMediaPlayer zze;

    public zzcf(RemoteMediaPlayer r1, GoogleApiClient r2, MediaQueueItem r3, int r4, long r5, JSONObject r7) {
        this.zza = r3;
        this.zzb = r4;
        this.zzc = r5;
        this.zzd = r7;
        Objects.requireNonNull(r1);
        this.zze = r1;
        super(r1, r2);
    }

    @Override // com.google.android.gms.cast.zzdc
    public final void zza(com.google.android.gms.cast.internal.zzx r10) {
        MediaQueueItem r102 = this.zza;
        long r6 = this.zzc;
        JSONObject r8 = this.zzd;
        int r3 = this.zzb;
        com.google.android.gms.cast.internal.zzar r0 = this.zze.zzg();
        r0.zzD(zzb(), new MediaQueueItem[]{r102}, r3, 0, 0, r6, r8);
    }
}
