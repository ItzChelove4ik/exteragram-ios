package com.google.android.gms.internal.cast;

import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.mediarouter.media.MediaRouter;
import com.google.android.gms.cast.CastDevice;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzed extends MediaRouter.Callback {
    final /* synthetic */ zzee zza;

    public /* synthetic */ zzed(zzee r1, byte[] r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteAdded(MediaRouter r3, MediaRouter.RouteInfo r4) {
        zzee.zzh().d("RemoteConnectionMediaRouterCallback.onRouteAdded.", new Object[0]);
        this.zza.zzf(r4.getExtras());
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteChanged(MediaRouter r3, MediaRouter.RouteInfo r4) {
        zzee.zzh().d("RemoteConnectionMediaRouterCallback.onRouteChanged.", new Object[0]);
        this.zza.zzf(r4.getExtras());
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteRemoved(MediaRouter r3, MediaRouter.RouteInfo r4) {
        zzee.zzh().d("RemoteConnectionMediaRouterCallback.onRouteRemoved.", new Object[0]);
        Bundle r32 = r4.getExtras();
        if (r32 == null) goto L10;
        CastDevice r42 = CastDevice.getFromBundle(r32);
        if (r42 == null) goto L9;
        r32.getString("com.google.android.gms.cast.EXTRA_RUNNING_RECEIVER_APP_ID");
        zzee r2 = this.zza;
        String r33 = r42.getDeviceId();
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2.zzi().get(r33));
        r2.zzg(r42);
        return;
    L9:
        return;
    }
}
