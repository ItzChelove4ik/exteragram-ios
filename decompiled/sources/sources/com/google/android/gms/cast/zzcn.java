package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes4.dex */
final class zzcn extends zzdc {
    final /* synthetic */ int zza;
    final /* synthetic */ JSONObject zzb;
    final /* synthetic */ RemoteMediaPlayer zzc;

    public zzcn(RemoteMediaPlayer r1, GoogleApiClient r2, int r3, JSONObject r4) {
        this.zza = r3;
        this.zzb = r4;
        Objects.requireNonNull(r1);
        this.zzc = r1;
        super(r1, r2);
    }

    @Override // com.google.android.gms.cast.zzdc
    public final void zza(com.google.android.gms.cast.internal.zzx r4) {
        RemoteMediaPlayer r42 = this.zzc;
        int r0 = this.zza;
        if (r42.zza(r0) != (-1)) goto L6;
        setResult(new zzdb(this, new Status(0)));
        return;
    L6:
        r42.zzg().zzF(zzb(), new int[]{r0}, this.zzb);
    }
}
