package com.google.android.gms.internal.cast;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.media.session.MediaSessionCompat;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import androidx.mediarouter.media.MediaRouterParams;
import androidx.mediarouter.media.MediaTransferReceiver;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.SessionTransferCallback;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.tasks.Task;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes4.dex */
public final class zzbx extends zzbd {
    private static final Logger zza = null;
    private final MediaRouter zzb;
    private final CastOptions zzc;
    private final Map zzd;
    private zzce zze;
    private boolean zzf;
    private boolean zzg;
    private boolean zzh;
    private boolean zzi;
    private MediaRouterParams zzj;

    static {
        zza = new Logger("MediaRouterProxy");
    }

    public zzbx(Context r4, MediaRouter r5, CastOptions r6, com.google.android.gms.cast.internal.zzn r7) {
        this.zzd = new HashMap();
        this.zzb = r5;
        this.zzc = r6;
        if (PlatformVersion.isAtLeastT() == true) goto L5;
        return;
    L5:
        zza.d("Set up MediaRouterParams based on module flag and CastOptions for Android T or above", new Object[0]);
        this.zze = new zzce(r6);
        new Intent(r4, MediaTransferReceiver.class).setPackage(r4.getPackageName());
        this.zzf = !r4.getPackageManager().queryBroadcastReceivers(r5, 0).isEmpty();
        this.zzg = true;
        this.zzh = true;
        r7.zzb(new String[]{"com.google.android.gms.cast.FLAG_OUTPUT_SWITCHER_ENABLED", "com.google.android.gms.cast.FLAG_SHOW_SYSTEM_OUTPUT_SWITCHER_ON_CAST_ICON_CLICK"}).addOnCompleteListener(new zzbw(this));
    }

    private final void zzA(MediaRouteSelector r3) {
        Set r32 = (Set) this.zzd.get(r3);
        if (r32 == null) goto L9;
        Iterator r33 = r32.iterator();
    L7:
        if (r33.hasNext() == false) goto L11;
        MediaRouter.Callback r0 = (MediaRouter.Callback) r33.next();
        this.zzb.removeCallback(r0);
        goto L7
    L11:
        return;
    }

    private final void zzz(MediaRouteSelector r4, int r5) {
        Set r0 = (Set) this.zzd.get(r4);
        if (r0 == null) goto L9;
        Iterator r02 = r0.iterator();
    L7:
        if (r02.hasNext() == false) goto L11;
        MediaRouter.Callback r1 = (MediaRouter.Callback) r02.next();
        this.zzb.addCallback(r4, r1, r5);
        goto L7
    L11:
        return;
    }

    @Override // com.google.android.gms.internal.cast.zzbe
    public final void zzb(Bundle r3, zzbg r4) {
        MediaRouteSelector r32 = MediaRouteSelector.fromBundle(r3);
        if (r32 != null) goto L5;
        return;
    L5:
        Map r0 = this.zzd;
        if (r0.containsKey(r32) == true) goto L8;
        r0.put(r32, new HashSet());
    L8:
        ((Set) r0.get(r32)).add(new zzbl(r4, this, this.zze));
    }

    @Override // com.google.android.gms.internal.cast.zzbe
    public final void zzc(Bundle r3, int r4) {
        MediaRouteSelector r32 = MediaRouteSelector.fromBundle(r3);
        if (r32 != null) goto L6;
        return;
    L6:
        if (Looper.myLooper() != Looper.getMainLooper()) goto L9;
        zzz(r32, r4);
        return;
    L9:
        new zzfk(Looper.getMainLooper()).post(new zzbu(this, r32, r4));
    }

    @Override // com.google.android.gms.internal.cast.zzbe
    public final void zzd(Bundle r3) {
        MediaRouteSelector r32 = MediaRouteSelector.fromBundle(r3);
        if (r32 != null) goto L6;
        return;
    L6:
        if (Looper.myLooper() != Looper.getMainLooper()) goto L9;
        zzA(r32);
        return;
    L9:
        new zzfk(Looper.getMainLooper()).post(new zzbv(this, r32));
    }

    @Override // com.google.android.gms.internal.cast.zzbe
    public final boolean zze(Bundle r1, int r2) {
        MediaRouteSelector r12 = MediaRouteSelector.fromBundle(r1);
        if (r12 != null) goto L7;
        return false;
    L7:
        return this.zzb.isRouteAvailable(r12, r2);
    }

    @Override // com.google.android.gms.internal.cast.zzbe
    public final void zzf(String r5) {
        Logger r0 = zza;
        r0.d("select route with routeId = %s", new Object[]{r5});
        MediaRouter r4 = this.zzb;
        Iterator<MediaRouter.RouteInfo> r1 = r4.getRoutes().iterator();
    L4:
        if (r1.hasNext() == false) goto L12;
        MediaRouter.RouteInfo r2 = r1.next();
        if (r2.getId().equals(r5) == false) goto L4;
        r0.d("media route is found and selected", new Object[0]);
        r4.selectRoute(r2);
        return;
    }

    @Override // com.google.android.gms.internal.cast.zzbe
    public final void zzg() {
        MediaRouter r1 = this.zzb;
        r1.selectRoute(r1.getDefaultRoute());
    }

    @Override // com.google.android.gms.internal.cast.zzbe
    public final boolean zzh() {
        MediaRouter r1 = this.zzb;
        MediaRouter.RouteInfo r0 = r1.getDefaultRoute();
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r1.getSelectedRoute().getId().equals(r0.getId()) == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // com.google.android.gms.internal.cast.zzbe
    public final Bundle zzi(String r3) {
        Iterator<MediaRouter.RouteInfo> r2 = this.zzb.getRoutes().iterator();
    L4:
        if (r2.hasNext() == false) goto L9;
        MediaRouter.RouteInfo r0 = r2.next();
        if (r0.getId().equals(r3) == false) goto L4;
        return r0.getExtras();
    L9:
        return null;
    }

    @Override // com.google.android.gms.internal.cast.zzbe
    public final String zzj() {
        return this.zzb.getSelectedRoute().getId();
    }

    @Override // com.google.android.gms.internal.cast.zzbe
    public final void zzk() {
        Map r0 = this.zzd;
        Iterator r1 = r0.values().iterator();
    L4:
        if (r1.hasNext() == false) goto L9;
        Iterator r2 = ((Set) r1.next()).iterator();
    L7:
        if (r2.hasNext() == false) goto L4;
        MediaRouter.Callback r3 = (MediaRouter.Callback) r2.next();
        this.zzb.removeCallback(r3);
        goto L7
    L9:
        r0.clear();
    }

    @Override // com.google.android.gms.internal.cast.zzbe
    public final boolean zzl() {
        MediaRouter r1 = this.zzb;
        MediaRouter.RouteInfo r0 = r1.getBluetoothRoute();
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r1.getSelectedRoute().getId().equals(r0.getId()) == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // com.google.android.gms.internal.cast.zzbe
    public final void zzm(int r1) {
        this.zzb.unselect(r1);
    }

    @Override // com.google.android.gms.internal.cast.zzbe
    public final void zzn(String r6) {
        MediaRouter r5 = this.zzb;
        Iterator<MediaRouter.GroupRouteInfo> r0 = r5.getConnectedGroupRoutes().iterator();
    L4:
        if (r0.hasNext() == false) goto L8;
        MediaRouter.GroupRouteInfo r1 = r0.next();
        if (r1.getId().equals(r6) == false) goto L4;
        zza.d("clean up the connectedGroupRoute = %s", new Object[]{r1});
        r1.disconnect();
        goto L4
    L8:
        MediaRouter.RouteInfo r02 = r5.getSelectedRoute();
        if (r02 != null) goto L11;
        return;
    L11:
        if (r02.isSystemRoute() == false) goto L13;
        return;
    L13:
        if (r02.getId().equals(r6) == false) goto L23;
        zza.d("clean up the selected route = %s", new Object[]{r02});
        r5.unselect(0);
        return;
    }

    public final boolean zzo() {
        if (this.zzf == true) goto L5;
        return false;
    L5:
        if (this.zzg == false) goto L14;
        CastOptions r1 = this.zzc;
        if (r1 != null) goto L9;
        return false;
    L9:
        if (r1.zzh() == false) goto L16;
        return true;
    L16:
        return false;
    L14:
        return false;
    }

    public final void zzp(boolean r1) {
        this.zzi = r1;
    }

    public final boolean zzq() {
        return this.zzi;
    }

    public final void zzr(Boolean r6, Boolean r7) {
        boolean r1 = false;
        if (PlatformVersion.isAtLeastT() == true) goto L6;
        zza.i("updateMediaRouterParams - not allowed on Android S and below", new Object[0]);
        return;
    L6:
        MediaRouter r0 = this.zzb;
        if (r0 == null) goto L32;
        MediaRouterParams r2 = this.zzj;
        if (r2 == null) goto L32;
        MediaRouterParams.Builder r3 = new MediaRouterParams.Builder(r2);
        boolean r22 = true;
        if (r6 != null) goto L14;
    L22:
        if (r7 == null) goto L28;
        MediaRouterParams r62 = this.zzj;
        if (r62 == null) goto L28;
        if (r62.isTransferToLocalEnabled() == r7.booleanValue()) goto L28;
        r3 = r3.setTransferToLocalEnabled(r7.booleanValue());
    L29:
        if (r22 == false) goto L38;
        MediaRouterParams r63 = r3.build();
        this.zzj = r63;
        r0.setRouterParams(r63);
        return;
    L38:
        return;
    L28:
        r22 = r1;
        goto L29
    L14:
        if (this.zzh == true) goto L16;
    L18:
        boolean r64 = false;
    L20:
        if (this.zzj.isOutputSwitcherEnabled() == r64) goto L22;
        r3 = r3.setOutputSwitcherEnabled(r64);
        r1 = true;
        goto L22
    L16:
        if (r6.booleanValue() == false) goto L18;
        r64 = true;
    L32:
        Logger r5 = zza;
        if (r0 != null) goto L35;
        String r65 = "mediaRouter";
    L36:
        r5.e("updateMediaRouterParams - %s must not be null", new Object[]{r65});
        return;
    L35:
        r65 = "routerParams";
        goto L36
    }

    public final void zzs(SessionTransferCallback r2) {
        zzce r0 = this.zze;
        if (r0 == null) goto L6;
        r0.zzc(r2);
        this.zzb.setOnPrepareTransferListener(new zzbt((zzce) Preconditions.checkNotNull(this.zze)));
        return;
    }

    public final void zzt(SessionTransferCallback r2) {
        zzce r0 = this.zze;
        if (r0 == null) goto L6;
        r0.zzd(r2);
        this.zzb.setOnPrepareTransferListener(null);
        return;
    }

    public final zzce zzu() {
        return this.zze;
    }

    public final void zzv(MediaSessionCompat r1) {
        this.zzb.setMediaSessionCompat(r1);
    }

    public final /* synthetic */ void zzw(Task r9) {
        if (r9.isSuccessful() == false) goto L13;
        Bundle r92 = (Bundle) r9.getResult();
        if (r92 != null) goto L7;
    L9:
        if (r92 == null) goto L13;
        if (r92.containsKey("com.google.android.gms.cast.FLAG_SHOW_SYSTEM_OUTPUT_SWITCHER_ON_CAST_ICON_CLICK") == false) goto L13;
        boolean r93 = r92.getBoolean("com.google.android.gms.cast.FLAG_SHOW_SYSTEM_OUTPUT_SWITCHER_ON_CAST_ICON_CLICK");
        this.zzh = r93;
        zza.d("The module-to-client show system output switcher on cast icon click flag value is %b", new Object[]{Boolean.valueOf(r93)});
        goto L13
    L7:
        if (r92.containsKey("com.google.android.gms.cast.FLAG_OUTPUT_SWITCHER_ENABLED") == false) goto L9;
        boolean r0 = r92.getBoolean("com.google.android.gms.cast.FLAG_OUTPUT_SWITCHER_ENABLED");
        this.zzg = r0;
        zza.d("The module-to-client output switcher flag value is %b", new Object[]{Boolean.valueOf(r0)});
    L13:
        boolean r94 = this.zzg;
        boolean r02 = this.zzh;
        MediaRouter r1 = this.zzb;
        if (r1 == null) goto L44;
        CastOptions r2 = this.zzc;
        if (r2 == null) goto L46;
        boolean r3 = r2.zzf();
        boolean r4 = true;
        if (r02 == true) goto L21;
    L23:
        boolean r03 = false;
    L24:
        if (r94 == true) goto L26;
    L28:
        boolean r95 = false;
    L29:
        MediaRouterParams r22 = new MediaRouterParams.Builder().setMediaTransferReceiverEnabled(r95).setTransferToLocalEnabled(r3).setOutputSwitcherEnabled(r03).setMediaTransferRestrictedToSelfProviders(r2.zzk()).build();
        this.zzj = r22;
        r1.setRouterParams(r22);
        zza.i("media transfer = %b, session transfer = %b, transfer to local = %b, in-app output switcher = %b", new Object[]{Boolean.valueOf(this.zzf), Boolean.valueOf(r95), Boolean.valueOf(r3), Boolean.valueOf(r03)});
        zzce r04 = this.zze;
        if (r04 == null) goto L38;
        if (this.zzf == false) goto L35;
        if (r95 == false) goto L35;
    L36:
        r04.zzb(r4);
    L35:
        r4 = false;
    L38:
        if (this.zzf == false) goto L41;
        if (r95 == false) goto L41;
        zzr.zzb(zzpm.zzJ);
    L41:
        if (r3 == false) goto L45;
        zzr.zzb(zzpm.zzK);
        return;
    L45:
        return;
    L26:
        if (r2.zzh() == false) goto L28;
        r95 = true;
        goto L29
    L21:
        if (r2.getShowSystemOutputSwitcherOnCastIconClick() == false) goto L23;
        r03 = true;
        goto L24
    L46:
        return;
    }

    public final /* synthetic */ void zzx(MediaRouteSelector r2, int r3) {
        Map r0 = this.zzd;
        monitor-enter(r0);
        zzz(r2, r3);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public final /* synthetic */ void zzy(MediaRouteSelector r1) {
        zzA(r1);
    }
}
