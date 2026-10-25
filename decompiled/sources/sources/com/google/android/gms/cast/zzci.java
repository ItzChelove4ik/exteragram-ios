package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes4.dex */
final class zzci extends zzdc {
    final /* synthetic */ int[] zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ JSONObject zzc;
    final /* synthetic */ RemoteMediaPlayer zzd;

    public zzci(RemoteMediaPlayer r1, GoogleApiClient r2, int[] r3, int r4, JSONObject r5) {
        this.zza = r3;
        this.zzb = r4;
        this.zzc = r5;
        Objects.requireNonNull(r1);
        this.zzd = r1;
        super(r1, r2);
    }

    @Override // com.google.android.gms.cast.zzdc
    public final void zza(com.google.android.gms.cast.internal.zzx r4) {
        this.zzd.zzg().zzG(zzb(), this.zza, this.zzb, this.zzc);
    }
}
