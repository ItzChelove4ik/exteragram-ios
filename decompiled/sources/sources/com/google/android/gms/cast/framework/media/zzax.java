package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.internal.CastUtils;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import okhttp3.internal.ws.WebSocketProtocol;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class zzax implements com.google.android.gms.cast.internal.zzas {
    final /* synthetic */ RemoteMediaClient zza;
    private com.google.android.gms.cast.zzq zzb;
    private final AtomicLong zzc;

    public zzax(RemoteMediaClient r5) {
        Objects.requireNonNull(r5);
        this.zza = r5;
        this.zzc = new AtomicLong((CastUtils.zzb() & WebSocketProtocol.PAYLOAD_SHORT_MAX) * NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
    }

    public final void zza(com.google.android.gms.cast.zzq r1) {
        this.zzb = r1;
    }

    @Override // com.google.android.gms.cast.internal.zzas
    public final void zzb(String r1, String r2, long r3, String r5) {
        com.google.android.gms.cast.zzq r52 = this.zzb;
        if (r52 == null) goto L6;
        r52.zzf(r1, r2).addOnFailureListener(new zzaw(this, r3));
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("Device is not connected");
    }

    @Override // com.google.android.gms.cast.internal.zzas
    public final long zzc() {
        return this.zzc.getAndIncrement();
    }
}
