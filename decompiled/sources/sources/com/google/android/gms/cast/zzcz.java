package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import java.util.Objects;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class zzcz implements com.google.android.gms.cast.internal.zzas {
    final /* synthetic */ RemoteMediaPlayer zza;
    private GoogleApiClient zzb;
    private long zzc;

    public zzcz(RemoteMediaPlayer r3) {
        Objects.requireNonNull(r3);
        this.zza = r3;
        this.zzc = 0;
    }

    public final void zza(GoogleApiClient r1) {
        this.zzb = r1;
    }

    @Override // com.google.android.gms.cast.internal.zzas
    public final void zzb(String r2, String r3, long r4, String r6) {
        GoogleApiClient r62 = this.zzb;
        if (r62 == null) goto L6;
        Cast.CastApi.sendMessage(r62, r2, r3).setResultCallback(new zzcy(this, r4));
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("No GoogleApiClient available");
    }

    @Override // com.google.android.gms.cast.internal.zzas
    public final long zzc() {
        long r0 = this.zzc + 1;
        this.zzc = r0;
        return r0;
    }
}
