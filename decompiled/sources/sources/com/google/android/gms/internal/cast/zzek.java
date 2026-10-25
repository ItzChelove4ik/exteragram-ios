package com.google.android.gms.internal.cast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.core.content.ContextCompat;
import com.google.android.gms.cast.internal.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes4.dex */
public final class zzek implements zzeg {
    private static final Logger zzb = null;
    public final Set zza;
    private final zzwo zzc;
    private final ConnectivityManager.NetworkCallback zzd;
    private final ConnectivityManager zze;
    private final Map zzf;
    private final List zzg;
    private boolean zzh;
    private final Context zzi;
    private final Object zzj;
    private final boolean zzk;
    private final BroadcastReceiver zzl;

    static {
        zzb = new Logger("ConnectivityMonitor");
    }

    public zzek(Context r3, zzwo r4) {
        this.zzd = new zzeh(this);
        this.zzf = new HashMap();
        this.zzg = new ArrayList();
        this.zzj = new Object();
        if (Build.VERSION.SDK_INT < 31) goto L5;
        boolean r0 = true;
    L6:
        this.zzk = r0;
        this.zzl = new zzei(this);
        this.zza = new HashSet();
        this.zzc = r4;
        this.zzi = r3;
        this.zze = (ConnectivityManager) r3.getSystemService("connectivity");
        return;
    L5:
        r0 = false;
        goto L6
    }

    private final void zzg(Network r5, LinkProperties r6) {
        Object r0 = this.zzj;
        monitor-enter(r0);
        zzb.d("a new network is available", new Object[0]);     // Catch: Throwable -> L7
        Map r1 = this.zzf;     // Catch: Throwable -> L7
        if (r1.containsKey(r5) == false) goto L9;
        this.zzg.remove(r5);     // Catch: Throwable -> L7
    L9:
        r1.put(r5, r6);     // Catch: Throwable -> L7
        this.zzg.add(r5);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        zzh();
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private final void zzh() {
        zzwo r0 = this.zzc;
        if (r0 != null) goto L5;
        return;
    L5:
        Set r1 = this.zza;
        monitor-enter(r1);
        Iterator r2 = r1.iterator();     // Catch: Throwable -> L13
    L9:
        if (r2.hasNext() == false) goto L15;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2.next());     // Catch: Throwable -> L13
        if (r0.isShutdown() == true) goto L9;
        r0.execute(new zzej(this, null));     // Catch: Throwable -> L13
        goto L9
    L15:
        monitor-exit(r1);     // Catch: Throwable -> L13
        return;
    L13:
        th = move-exception;
        throw th;
    }

    @Override // com.google.android.gms.internal.cast.zzeg
    public final void zza() {
        zzb.d("Start monitoring connectivity changes", new Object[0]);
        if (this.zzh == true) goto L22;
        ConnectivityManager r0 = this.zze;
        if (r0 == null) goto L20;
        Context r1 = this.zzi;
        if (ContextCompat.checkSelfPermission(r1, "android.permission.ACCESS_NETWORK_STATE") != 0) goto L21;
        Network r2 = r0.getActiveNetwork();
        if (r2 == null) goto L14;
        LinkProperties r3 = r0.getLinkProperties(r2);
        if (r3 == null) goto L14;
        zzg(r2, r3);
    L14:
        NetworkRequest.Builder r22 = new NetworkRequest.Builder().addTransportType(1);
        if (this.zzk == false) goto L17;
        r22.setIncludeOtherUidNetworks(true);
    L18:
        r0.registerNetworkCallback(r22.build(), this.zzd);
        this.zzh = true;
        return;
    L17:
        r1.registerReceiver(this.zzl, new IntentFilter("android.net.wifi.STATE_CHANGE"));
        goto L18
    L21:
        return;
    L20:
        return;
    }

    public final /* synthetic */ void zzb(zzef r3) {
        Object r0 = this.zzj;
        monitor-enter(r0);
        boolean r1 = this.zzg.isEmpty();     // Catch: Throwable -> L17
        monitor-exit(r0);     // Catch: Throwable -> L17
        if (r1 == false) goto L9;
    L15:
        r3.zza();
        return;
    L9:
        if (this.zzk == true) goto L15;
        ConnectivityManager r2 = this.zze;
        if (r2 == null) goto L15;
        NetworkInfo r22 = r2.getActiveNetworkInfo();
        if (r22 == null) goto L15;
        r22.isConnected();
    L17:
        th = move-exception;
        throw th;
    }

    public final /* synthetic */ void zzc(Network r1, LinkProperties r2) {
        zzg(r1, r2);
    }

    public final /* synthetic */ void zzd(Network r5) {
        Object r0 = this.zzj;
        monitor-enter(r0);
        zzb.d("the network is lost", new Object[0]);     // Catch: Throwable -> L7
        if (this.zzg.remove(r5) == false) goto L9;
        this.zzf.remove(r5);     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
        zzh();
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public final /* synthetic */ void zze() {
        Object r0 = this.zzj;
        monitor-enter(r0);
        zzb.d("all networks are unavailable.", new Object[0]);     // Catch: Throwable -> L8
        this.zzf.clear();     // Catch: Throwable -> L8
        this.zzg.clear();     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        zzh();
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public final /* synthetic */ void zzf() {
        zzh();
    }
}
