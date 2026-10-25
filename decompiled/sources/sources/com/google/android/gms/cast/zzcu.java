package com.google.android.gms.cast;

import com.google.android.gms.cast.MediaSeekOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes4.dex */
final class zzcu extends zzdc {
    final /* synthetic */ long zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ JSONObject zzc;
    final /* synthetic */ RemoteMediaPlayer zzd;

    public zzcu(RemoteMediaPlayer r1, GoogleApiClient r2, long r3, int r5, JSONObject r6) {
        this.zza = r3;
        this.zzb = r5;
        this.zzc = r6;
        Objects.requireNonNull(r1);
        this.zzd = r1;
        super(r1, r2);
    }

    @Override // com.google.android.gms.cast.zzdc
    public final void zza(com.google.android.gms.cast.internal.zzx r4) {
        com.google.android.gms.cast.internal.zzat r42 = zzb();
        MediaSeekOptions.Builder r0 = new MediaSeekOptions.Builder();
        r0.setPosition(this.zza);
        r0.setResumeState(this.zzb);
        r0.setCustomData(this.zzc);
        MediaSeekOptions r02 = r0.build();
        this.zzd.zzg().zzn(r42, r02);
    }
}
