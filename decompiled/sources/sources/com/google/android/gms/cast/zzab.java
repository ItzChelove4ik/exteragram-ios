package com.google.android.gms.cast;

import androidx.mediarouter.media.MediaRouter;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzab extends MediaRouter.Callback {
    final /* synthetic */ CastRemoteDisplayLocalService zza;

    public zzab(CastRemoteDisplayLocalService r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteUnselected(MediaRouter r1, MediaRouter.RouteInfo r2) {
        CastRemoteDisplayLocalService r0 = this.zza;
        r0.zzg("onRouteUnselected");
        if (r0.zzm() != null) goto L6;
        r0.zzg("onRouteUnselected, no device was selected");
        return;
    L6:
        CastDevice r12 = CastDevice.getFromBundle(r2.getExtras());
        if (r12 == null) goto L13;
        CastDevice r22 = r0.zzm();
        if (r12.getDeviceId().equals(r22.getDeviceId()) == false) goto L13;
        CastRemoteDisplayLocalService.stopService();
        return;
    L13:
        r0.zzg("onRouteUnselected, device does not match");
    }
}
