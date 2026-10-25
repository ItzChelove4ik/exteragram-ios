package com.google.android.gms.cast;

import com.google.android.gms.cast.MediaLoadRequestData;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes4.dex */
final class zzcq extends zzdc {
    final /* synthetic */ MediaInfo zza;
    final /* synthetic */ boolean zzb;
    final /* synthetic */ long zzc;
    final /* synthetic */ long[] zzd;
    final /* synthetic */ JSONObject zze;
    final /* synthetic */ RemoteMediaPlayer zzf;

    public zzcq(RemoteMediaPlayer r1, GoogleApiClient r2, MediaInfo r3, boolean r4, long r5, long[] r7, JSONObject r8) {
        this.zza = r3;
        this.zzb = r4;
        this.zzc = r5;
        this.zzd = r7;
        this.zze = r8;
        Objects.requireNonNull(r1);
        this.zzf = r1;
        super(r1, r2);
    }

    @Override // com.google.android.gms.cast.zzdc
    public final void zza(com.google.android.gms.cast.internal.zzx r6) {
        RemoteMediaPlayer r62 = this.zzf;
        Object r0 = r62.zzf();
        monitor-enter(r0);
        com.google.android.gms.cast.internal.zzar r63 = r62.zzg();     // Catch: Throwable -> L7
        com.google.android.gms.cast.internal.zzat r1 = zzb();     // Catch: Throwable -> L7
        MediaLoadRequestData.Builder r2 = new MediaLoadRequestData.Builder();     // Catch: Throwable -> L7
        r2.setMediaInfo(this.zza);     // Catch: Throwable -> L7
        r2.setAutoplay(Boolean.valueOf(this.zzb));     // Catch: Throwable -> L7
        r2.setCurrentTime(this.zzc);     // Catch: Throwable -> L7
        r2.setActiveTrackIds(this.zzd);     // Catch: Throwable -> L7
        r2.setCustomData(this.zze);     // Catch: Throwable -> L7
        r63.zzj(r1, r2.build());     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
