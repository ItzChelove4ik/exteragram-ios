package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import androidx.mediarouter.media.MediaRouter;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes4.dex */
public final class zzbl extends MediaRouter.Callback {
    private static final Logger zza = null;
    private final zzbg zzb;
    private final zzbx zzc;
    private final zzce zzd;

    static {
        zza = new Logger("MediaRouterCallback");
    }

    public zzbl(zzbg r1, zzbx r2, zzce r3) {
        this.zzb = (zzbg) Preconditions.checkNotNull(r1);
        this.zzc = r2;
        this.zzd = r3;
    }

    private final void zza(MediaRouter r1) {
        zzce r0 = this.zzd;
        if (r0 == null) goto L6;
        r0.zzf(r1);
        return;
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteAdded(MediaRouter r4, MediaRouter.RouteInfo r5) {
        this.zzb.zzf(r5.getId(), r5.getExtras());     // Catch: RemoteException -> L4
    L6:
        zza(r4);
        return;
    L4:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"onRouteAdded", zzbg.class.getSimpleName()});
        goto L6
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteChanged(MediaRouter r4, MediaRouter.RouteInfo r5) {
        if (r5.isSelected() == true) goto L11;
        return;
    L11:
        this.zzb.zzg(r5.getId(), r5.getExtras());     // Catch: RemoteException -> L7
    L9:
        zza(r4);
        return;
    L7:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"onRouteChanged", zzbg.class.getSimpleName()});
        goto L9
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteConnected(MediaRouter r4, MediaRouter.RouteInfo r5, MediaRouter.RouteInfo r6) {
        if (r5.getPlaybackType() == 1) goto L6;
        zza.i("ignore onRouteConnected for non-remote connected routeId: %s", new Object[]{r5.getId()});
        return;
    L6:
        zza.i("onRouteConnected with connectedRouteId = %s", new Object[]{r5.getId()});
        this.zzc.zzp(true);
        zzbg r3 = this.zzb;     // Catch: RemoteException -> L13
        if (r3.zze() < 251600000) goto L11;
        r3.zzl(r6.getId(), r5.getId(), r5.getExtras());     // Catch: RemoteException -> L13
        return;
    L11:
        r3.zzk(r6.getId(), r5.getId(), r5.getExtras());     // Catch: RemoteException -> L13
        return;
    L13:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"onRouteConnected", zzbg.class.getSimpleName()});
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteDisconnected(MediaRouter r5, MediaRouter.RouteInfo r6, MediaRouter.RouteInfo r7, int r8) {
        if (r6 != null) goto L5;
    L17:
        zza.i("ignore onRouteDisconnected for invalid or non-remote disconnected route", new Object[0]);
        return;
    L5:
        if (r6.getPlaybackType() != 1) goto L17;
        zza.i("onRouteDisconnected with disconnectedRouteId = %s, requestedRouteId = %s, reason = %d", new Object[]{((MediaRouter.RouteInfo) Preconditions.checkNotNull(r6)).getId(), r7.getId(), Integer.valueOf(r8)});
        this.zzc.zzp(false);
        zzbg r4 = this.zzb;     // Catch: RemoteException -> L14
        if (r4.zze() < 251600000) goto L12;
        r4.zzm(r7.getId(), r6.getId(), r6.getExtras(), r8);     // Catch: RemoteException -> L14
        return;
    L12:
        r4.zzj(r6.getId(), r6.getExtras(), r8);     // Catch: RemoteException -> L14
        return;
    L14:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"onRouteDisconnected", zzbg.class.getSimpleName()});
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteRemoved(MediaRouter r4, MediaRouter.RouteInfo r5) {
        this.zzb.zzh(r5.getId(), r5.getExtras());     // Catch: RemoteException -> L4
    L6:
        zza(r4);
        return;
    L4:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"onRouteRemoved", zzbg.class.getSimpleName()});
        goto L6
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteSelected(MediaRouter r3, MediaRouter.RouteInfo r4, int r5, MediaRouter.RouteInfo r6) {
        if (r4.getPlaybackType() == 1) goto L6;
        zza.i("ignore onRouteSelected for non-remote selected routeId: %s", new Object[]{r4.getId()});
        return;
    L6:
        zza.i("onRouteSelected with reason = %d, routeId = %s", new Object[]{Integer.valueOf(r5), r4.getId()});
        zzbg r52 = this.zzb;     // Catch: RemoteException -> L10
        if (r52.zze() < 220400000) goto L12;
        r52.zzk(r6.getId(), r4.getId(), r4.getExtras());     // Catch: RemoteException -> L10
    L17:
    L15:
        zza(r3);
        return;
    L12:
        r52.zzi(r6.getId(), r4.getExtras());     // Catch: RemoteException -> L10
    L10:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"onRouteSelected", zzbg.class.getSimpleName()});
        goto L15
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteUnselected(MediaRouter r4, MediaRouter.RouteInfo r5, int r6) {
        if (r5.getPlaybackType() == 1) goto L6;
        zza.i("ignore onRouteUnselected for non-remote routeId: %s", new Object[]{r5.getId()});
        return;
    L6:
        zza.i("onRouteUnselected with reason = %d, routeId = %s", new Object[]{Integer.valueOf(r6), r5.getId()});
        this.zzb.zzj(r5.getId(), r5.getExtras(), r6);     // Catch: RemoteException -> L9
    L11:
        zza(r4);
        return;
    L9:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"onRouteUnselected", zzbg.class.getSimpleName()});
        goto L11
    }
}
