package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes4.dex */
final class zzcm extends zzdc {
    final /* synthetic */ int zza;
    final /* synthetic */ JSONObject zzb;
    final /* synthetic */ RemoteMediaPlayer zzc;

    public zzcm(RemoteMediaPlayer r1, GoogleApiClient r2, int r3, JSONObject r4) {
        this.zza = r3;
        this.zzb = r4;
        Objects.requireNonNull(r1);
        this.zzc = r1;
        super(r1, r2);
    }

    @Override // com.google.android.gms.cast.zzdc
    public final void zza(com.google.android.gms.cast.internal.zzx r11) {
        this.zzc.zzg().zzE(zzb(), 0, -1, null, 0, null, Integer.valueOf(this.zza), this.zzb);
    }
}
