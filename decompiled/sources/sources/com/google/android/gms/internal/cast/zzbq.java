package com.google.android.gms.internal.cast;

import android.content.Context;
import android.os.Looper;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import com.google.android.gms.cast.CastMediaControlIntent;
import com.google.android.gms.cast.internal.Logger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes4.dex */
public final class zzbq extends MediaRouter.Callback {
    private static final Logger zzb = null;
    public final zzbn zza;
    private final Set zzc;
    private final Map zzd;
    private final LinkedHashSet zze;
    private final zzby zzf;

    static {
        zzb = new Logger("MRDiscoveryCallback");
    }

    public zzbq(Context r2) {
        this.zzf = new zzby(r2);
        this.zzd = Collections.synchronizedMap(new HashMap());
        this.zze = new LinkedHashSet();
        this.zzc = Collections.synchronizedSet(new LinkedHashSet());
        this.zza = new zzbn(this);
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteAdded(MediaRouter r3, MediaRouter.RouteInfo r4) {
        zzb.d("MediaRouterDiscoveryCallback.onRouteAdded.", new Object[0]);
        zza(r4, true);
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteChanged(MediaRouter r3, MediaRouter.RouteInfo r4) {
        zzb.d("MediaRouterDiscoveryCallback.onRouteChanged.", new Object[0]);
        zza(r4, true);
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteRemoved(MediaRouter r4, MediaRouter.RouteInfo r5) {
        zzb.d("MediaRouterDiscoveryCallback.onRouteRemoved.", new Object[0]);
        zza(r5, false);
    }

    public final void zza(MediaRouter.RouteInfo r17, boolean r18) {
        Logger r2 = zzb;
        r2.d("MediaRouterDiscoveryCallback.updateRouteToAppIds (add=%b) route %s", new Object[]{Boolean.valueOf(r18), r17});
        Map r3 = this.zzd;
        monitor-enter(r3);
        String r9 = String.valueOf(r3.keySet());     // Catch: Throwable -> L14
        StringBuilder r11 = new StringBuilder(r9.length() + 45);     // Catch: Throwable -> L14
        r11.append("appIdToRouteInfo has these appId route keys: ");     // Catch: Throwable -> L14
        r11.append(r9);     // Catch: Throwable -> L14
        r2.d(r11.toString(), new Object[0]);     // Catch: Throwable -> L14
        Iterator r8 = r3.entrySet().iterator();     // Catch: Throwable -> L14
        boolean r10 = false;
    L6:
        if (r8.hasNext() == false) goto L20;
        Map.Entry r112 = (Map.Entry) r8.next();     // Catch: Throwable -> L14
        String r12 = (String) r112.getKey();     // Catch: Throwable -> L14
        zzbm r113 = (zzbm) r112.getValue();     // Catch: Throwable -> L14
        if (r17.matchesSelector(r113.zzb) == false) goto L6;
        if (r18 == false) goto L17;
        StringBuilder r14 = new StringBuilder(String.valueOf(r12).length() + 32);     // Catch: Throwable -> L14
        r14.append("Adding/updating route for appId ");     // Catch: Throwable -> L14
        r14.append(r12);     // Catch: Throwable -> L14
        r2.d(r14.toString(), new Object[0]);     // Catch: Throwable -> L14
        boolean r114 = r113.zza.add(r17);     // Catch: Throwable -> L14
        if (r114 == true) goto L16;
        String r13 = String.valueOf(r17);     // Catch: Throwable -> L14
        StringBuilder r15 = new StringBuilder((r13.length() + 32) + String.valueOf(r12).length());     // Catch: Throwable -> L14
        r15.append("Route ");     // Catch: Throwable -> L14
        r15.append(r13);     // Catch: Throwable -> L14
        r15.append(" already exists for appId ");     // Catch: Throwable -> L14
        r15.append(r12);     // Catch: Throwable -> L14
        r2.w(r15.toString(), new Object[0]);     // Catch: Throwable -> L14
    L16:
        r10 = r114;
        goto L6
    L17:
        StringBuilder r142 = new StringBuilder(String.valueOf(r12).length() + 25);     // Catch: Throwable -> L14
        r142.append("Removing route for appId ");     // Catch: Throwable -> L14
        r142.append(r12);     // Catch: Throwable -> L14
        r2.d(r142.toString(), new Object[0]);     // Catch: Throwable -> L14
        r114 = r113.zza.remove(r17);     // Catch: Throwable -> L14
        if (r114 == true) goto L16;
        String r132 = String.valueOf(r17);     // Catch: Throwable -> L14
        StringBuilder r152 = new StringBuilder((r132.length() + 34) + String.valueOf(r12).length());     // Catch: Throwable -> L14
        r152.append("Route ");     // Catch: Throwable -> L14
        r152.append(r132);     // Catch: Throwable -> L14
        r152.append(" already removed from appId ");     // Catch: Throwable -> L14
        r152.append(r12);     // Catch: Throwable -> L14
        r2.w(r152.toString(), new Object[0]);     // Catch: Throwable -> L14
        goto L16
    L20:
        monitor-exit(r3);     // Catch: Throwable -> L14
        if (r10 == false) goto L51;
        zzb.d("Invoking callback.onRouteUpdated.", new Object[0]);
        Set r1 = this.zzc;
        monitor-enter(r1);
        HashMap r22 = new HashMap();     // Catch: Throwable -> L43
        Map r32 = this.zzd;     // Catch: Throwable -> L43
        monitor-enter(r32);     // Catch: Throwable -> L43
        Iterator r4 = r32.keySet().iterator();     // Catch: Throwable -> L32
    L28:
        if (r4.hasNext() == false) goto L38;
        String r5 = (String) r4.next();     // Catch: Throwable -> L32
        zzbm r6 = (zzbm) r32.get(zzhb.zza(r5));     // Catch: Throwable -> L32
        if (r6 != null) goto L34;
        zzhz r62 = zzhz.zzh();     // Catch: Throwable -> L32
    L36:
        if (r62.isEmpty() == true) goto L28;
        r22.put(r5, r62);     // Catch: Throwable -> L32
        goto L28
    L34:
        r62 = zzhz.zzj(r6.zza);     // Catch: Throwable -> L32
        goto L36
    L38:
        monitor-exit(r32);     // Catch: Throwable -> L32
        zzhy.zzb(r22.entrySet());     // Catch: Throwable -> L43
        Iterator r0 = this.zzc.iterator();     // Catch: Throwable -> L43
    L41:
        if (r0.hasNext() == false) goto L45;
        ((com.google.android.gms.cast.framework.zzbf) r0.next()).zza();     // Catch: Throwable -> L43
        goto L41
    L45:
        monitor-exit(r1);     // Catch: Throwable -> L43
        return;
    L32:
        th = move-exception;
        throw th;     // Catch: Throwable -> L43
    L43:
        th = move-exception;
        throw th;
    L51:
        return;
    L14:
        th = move-exception;
        throw th;
    }

    public final void zzb() {
        zzb.d("Stopping RouteDiscovery.", new Object[0]);
        this.zzd.clear();
        if (Looper.myLooper() != Looper.getMainLooper()) goto L6;
        this.zzf.zzc(this);
        return;
    L6:
        new zzfk(Looper.getMainLooper()).post(new zzbp(this));
    }

    public final void zzc() {
        this.zzf.zzc(this);
    }

    public final void zzd() {
        LinkedHashSet r0 = this.zze;
        Logger r1 = zzb;
        int r02 = r0.size();
        StringBuilder r3 = new StringBuilder(String.valueOf(r02).length() + 33);
        r3.append("Starting RouteDiscovery with ");
        r3.append(r02);
        r3.append(" IDs");
        r1.d(r3.toString(), new Object[0]);
        r1.d("appIdToRouteInfo has these appId route keys: ".concat(String.valueOf(this.zzd.keySet())), new Object[0]);
        if (Looper.myLooper() != Looper.getMainLooper()) goto L6;
        zze();
        return;
    L6:
        new zzfk(Looper.getMainLooper()).post(new zzbo(this));
    }

    public final void zze() {
        zzby r0 = this.zzf;
        r0.zzc(this);
        LinkedHashSet r1 = this.zze;
        monitor-enter(r1);
        Iterator r3 = r1.iterator();     // Catch: Throwable -> L10
    L6:
        if (r3.hasNext() == false) goto L13;
        String r4 = (String) r3.next();     // Catch: Throwable -> L10
        MediaRouteSelector r6 = new MediaRouteSelector.Builder().addControlCategory(CastMediaControlIntent.categoryForCast(r4)).build();     // Catch: Throwable -> L10
        Map r7 = this.zzd;     // Catch: Throwable -> L10
        if (((zzbm) r7.get(r4)) != null) goto L12;
        r7.put(r4, new zzbm(r6));     // Catch: Throwable -> L10
    L12:
        Logger r72 = zzb;     // Catch: Throwable -> L10
        String r42 = CastMediaControlIntent.categoryForCast(r4);     // Catch: Throwable -> L10
        StringBuilder r9 = new StringBuilder(r42.length() + 49);     // Catch: Throwable -> L10
        r9.append("Adding mediaRouter callback for control category ");     // Catch: Throwable -> L10
        r9.append(r42);     // Catch: Throwable -> L10
        r72.d(r9.toString(), new Object[0]);     // Catch: Throwable -> L10
        r0.zzb(r6, this, 4);     // Catch: Throwable -> L10
        goto L6
    L13:
        monitor-exit(r1);     // Catch: Throwable -> L10
        Map r10 = this.zzd;
        Logger r02 = zzb;
        String r102 = "appIdToRouteInfo has these appId route keys: ".concat(String.valueOf(r10.keySet()));
        r02.d(r102, new Object[0]);
        return;
    L10:
        th = move-exception;
        throw th;
    }

    public final void zzf(List r8) {
        Logger r0 = zzb;
        int r1 = r8.size();
        StringBuilder r3 = new StringBuilder(String.valueOf(r1).length() + 26);
        r3.append("SetRouteDiscovery for ");
        r3.append(r1);
        r3.append(" IDs");
        r0.d(r3.toString(), new Object[0]);
        LinkedHashSet r12 = new LinkedHashSet();
        Iterator r82 = r8.iterator();
    L4:
        if (r82.hasNext() == false) goto L6;
        r12.add(zzhb.zza((String) r82.next()));
        goto L4
    L6:
        Map r83 = this.zzd;
        String r32 = "resetting routes. appIdToRouteInfo has these appId route keys: ".concat(String.valueOf(r83.keySet()));
        r0.d(r32, new Object[0]);
        HashMap r33 = new HashMap();
        monitor-enter(r83);
        Iterator r4 = r12.iterator();     // Catch: Throwable -> L14
    L10:
        if (r4.hasNext() == false) goto L16;
        String r5 = (String) r4.next();     // Catch: Throwable -> L14
        zzbm r6 = (zzbm) r83.get(zzhb.zza(r5));     // Catch: Throwable -> L14
        if (r6 == null) goto L10;
        r33.put(r5, r6);     // Catch: Throwable -> L14
        goto L10
    L16:
        r83.clear();     // Catch: Throwable -> L14
        r83.putAll(r33);     // Catch: Throwable -> L14
        monitor-exit(r83);     // Catch: Throwable -> L14
        r0.d("Routes reset. appIdToRouteInfo has these appId route keys: ".concat(String.valueOf(r83.keySet())), new Object[0]);
        LinkedHashSet r02 = this.zze;
        monitor-enter(r02);
        r02.clear();     // Catch: Throwable -> L24
        r02.addAll(r12);     // Catch: Throwable -> L24
        monitor-exit(r02);     // Catch: Throwable -> L24
        zzd();
        return;
    L24:
        th = move-exception;
        throw th;
    L14:
        th = move-exception;
        throw th;
    }
}
