package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes4.dex */
final class zzco extends zzdc {
    final /* synthetic */ int zza;
    final /* synthetic */ long zzb;
    final /* synthetic */ JSONObject zzc;
    final /* synthetic */ RemoteMediaPlayer zzd;

    public zzco(RemoteMediaPlayer r1, GoogleApiClient r2, int r3, long r4, JSONObject r6) {
        this.zza = r3;
        this.zzb = r4;
        this.zzc = r6;
        Objects.requireNonNull(r1);
        this.zzd = r1;
        super(r1, r2);
    }

    @Override // com.google.android.gms.cast.zzdc
    public final void zza(com.google.android.gms.cast.internal.zzx r11) {
        RemoteMediaPlayer r112 = this.zzd;
        int r2 = this.zza;
        if (r112.zza(r2) != (-1)) goto L6;
        setResult(new zzdb(this, new Status(0)));
        return;
    L6:
        r112.zzg().zzE(zzb(), r2, this.zzb, null, 0, null, null, this.zzc);
    }
}
