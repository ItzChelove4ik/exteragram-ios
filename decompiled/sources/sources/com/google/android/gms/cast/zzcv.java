package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes4.dex */
final class zzcv extends zzdc {
    final /* synthetic */ double zza;
    final /* synthetic */ JSONObject zzb;
    final /* synthetic */ RemoteMediaPlayer zzc;

    public zzcv(RemoteMediaPlayer r1, GoogleApiClient r2, double r3, JSONObject r5) {
        this.zza = r3;
        this.zzb = r5;
        Objects.requireNonNull(r1);
        this.zzc = r1;
        super(r1, r2);
    }

    @Override // com.google.android.gms.cast.zzdc
    public final void zza(com.google.android.gms.cast.internal.zzx r4) {
        this.zzc.zzg().zzp(zzb(), this.zza, this.zzb);
    }
}
