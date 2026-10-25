package com.google.android.gms.internal.cast;

import android.content.Context;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;

/* loaded from: classes4.dex */
public final class zzby {
    public MediaRouter zza;
    private final Context zzb;

    public zzby(Context r1) {
        this.zzb = r1;
    }

    public final MediaRouter zza() {
        if (this.zza != null) goto L6;
        this.zza = MediaRouter.getInstance(this.zzb);
    L6:
        return this.zza;
    }

    public final void zzb(MediaRouteSelector r1, MediaRouter.Callback r2, int r3) {
        zza().addCallback(r1, r2, 4);
    }

    public final void zzc(MediaRouter.Callback r1) {
        MediaRouter r0 = zza();
        if (r0 == null) goto L6;
        r0.removeCallback(r1);
        return;
    }
}
