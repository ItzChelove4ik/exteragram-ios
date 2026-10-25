package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes4.dex */
final class zzcd extends zzdc {
    final /* synthetic */ MediaQueueItem[] zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ int zzc;
    final /* synthetic */ long zzd;
    final /* synthetic */ JSONObject zze;
    final /* synthetic */ RemoteMediaPlayer zzf;

    public zzcd(RemoteMediaPlayer r1, GoogleApiClient r2, MediaQueueItem[] r3, int r4, int r5, long r6, JSONObject r8) {
        this.zza = r3;
        this.zzb = r4;
        this.zzc = r5;
        this.zzd = r6;
        this.zze = r8;
        Objects.requireNonNull(r1);
        this.zzf = r1;
        super(r1, r2);
    }

    @Override // com.google.android.gms.cast.zzdc
    public final void zza(com.google.android.gms.cast.internal.zzx r9) {
        this.zzf.zzg().zzC(zzb(), this.zza, this.zzb, this.zzc, this.zzd, this.zze);
    }
}
