package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes4.dex */
final class zzcr extends zzdc {
    final /* synthetic */ JSONObject zza;
    final /* synthetic */ RemoteMediaPlayer zzb;

    public zzcr(RemoteMediaPlayer r1, GoogleApiClient r2, JSONObject r3) {
        this.zza = r3;
        Objects.requireNonNull(r1);
        this.zzb = r1;
        super(r1, r2);
    }

    @Override // com.google.android.gms.cast.zzdc
    public final void zza(com.google.android.gms.cast.internal.zzx r2) {
        this.zzb.zzg().zzk(zzb(), this.zza);
    }
}
