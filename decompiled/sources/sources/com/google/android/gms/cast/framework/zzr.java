package com.google.android.gms.cast.framework;

import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzr extends RemoteMediaClient.Callback {
    final /* synthetic */ CastSession zza;

    public zzr(CastSession r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void onStatusUpdated() {
        CastSession r2 = this.zza;
        if (r2.zzk() == null) goto L5;
        MediaStatus r0 = r2.zzk().getMediaStatus();
    L7:
        if (r2.zzl() == null) goto L10;
        r2.zzl().zzc(r0);
        return;
    L10:
        return;
    L5:
        r0 = null;
        goto L7
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void zza(String r11, long r12, int r14, long r15, long r17) {
        CastSession r10 = this.zza;
        if (r10.zzl() == null) goto L6;
        r10.zzl().zzb(r11, r12, r14, r15, r17);
        return;
    }
}
