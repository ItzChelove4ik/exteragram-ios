package com.google.android.gms.internal.cast;

import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.mediarouter.media.MediaRouteSelector;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.CastMediaControlIntent;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.internal.Logger;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public final class zzee {
    private static zzee zza;
    private static final Logger zzb = null;
    private final CastOptions zzc;
    private final zzax zzd;
    private final zzby zze;
    private final zzed zzf;
    private final Set zzg;
    private final zzeb zzh;
    private final Map zzi;
    private final Map zzj;
    private final PowerManager zzk;
    private final Object zzl;
    private final Object zzm;
    private boolean zzn;

    static {
        zzb = new Logger("RemoteConnectionManager");
    }

    private zzee(Context r2, CastOptions r3, zzax r4, zzby r5) {
        this.zzl = new Object();
        this.zzm = new Object();
        this.zzc = r3;
        this.zzg = Collections.newSetFromMap(new ConcurrentHashMap());
        this.zze = r5;
        this.zzf = new zzed(this, null);
        zzeb r32 = new zzeb(this, null);
        this.zzh = r32;
        this.zzd = r4;
        r4.zzf(r32);
        this.zzi = new ConcurrentHashMap();
        this.zzj = new ConcurrentHashMap();
        this.zzk = (PowerManager) r2.getSystemService("power");
        new zzec(this, null);
    }

    public static zzee zza(Context r2, CastOptions r3, zzax r4) {
        if (zza != null) goto L6;
        zza = new zzee(r2, r3, r4, new zzby(r2));
    L6:
        return zza;
    }

    public static /* synthetic */ Logger zzh() {
        return zzb;
    }

    private final void zzj(CastDevice r2) {
        if (((zzea) this.zzj.remove(r2.getDeviceId())) == null) goto L16;
        Object r22 = this.zzl;
        monitor-enter(r22);
        Iterator r1 = this.zzg.iterator();     // Catch: Throwable -> L10
        if (r1.hasNext() == true) goto L12;
        monitor-exit(r22);     // Catch: Throwable -> L10
        return;
    L12:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1.next());     // Catch: Throwable -> L10
        throw null;     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        throw th;
    }

    public final void zzb(com.google.android.gms.cast.framework.zzq r2) {
        Object r22 = this.zzm;
        monitor-enter(r22);
        Iterator r1 = this.zzi.entrySet().iterator();     // Catch: Throwable -> L8
        if (r1.hasNext() == true) goto L10;
        monitor-exit(r22);     // Catch: Throwable -> L8
        return;
    L10:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(((Map.Entry) r1.next()).getValue());     // Catch: Throwable -> L8
        throw null;     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;
    }

    public final /* synthetic */ void zzc() {
        Object r0 = this.zzm;
        monitor-enter(r0);
        Iterator r1 = this.zzi.values().iterator();     // Catch: Throwable -> L9
        if (r1.hasNext() == false) goto L13;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1.next());     // Catch: Throwable -> L9
        this.zzd.zze();     // Catch: Throwable -> L9
        PowerManager r3 = this.zzk;     // Catch: Throwable -> L9
        if (r3 == null) goto L12;
        r3.isInteractive();     // Catch: Throwable -> L9
    L12:
        throw null;     // Catch: Throwable -> L9
    L13:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L9:
        th = move-exception;
        throw th;
    }

    public final /* synthetic */ void zzd() {
        Object r0 = this.zzm;
        monitor-enter(r0);
        Iterator r2 = this.zzi.values().iterator();     // Catch: Throwable -> L8
        if (r2.hasNext() == true) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2.next());     // Catch: Throwable -> L8
        throw null;     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;
    }

    public final /* synthetic */ void zze() {
        boolean r0 = this.zzg.isEmpty();
        if (this.zzd.zze() == true) goto L18;
        if (r0 == true) goto L18;
        if (this.zzn == true) goto L22;
        Logger r02 = zzb;
        r02.d("Starting RemoteConnectionManager discovery.", new Object[0]);
        zzby r1 = this.zze;
        zzed r3 = this.zzf;
        r1.zzc(r3);
        String r4 = this.zzc.getReceiverApplicationId();
        if (r4.isEmpty() == false) goto L11;
        r02.d("Failed to create MediaRouteSelector. No target receiver app ID has been set.", new Object[0]);
        MediaRouteSelector r42 = null;
    L12:
        if (r42 != null) goto L15;
        r02.d("Skipping starting discovery. No target receiver app ID has been set.", new Object[0]);
        return;
    L15:
        this.zzn = true;
        String r6 = "Adding mediaRouter callback for control category ".concat(String.valueOf(r42.getControlCategories()));
        r02.d(r6, new Object[0]);
        r1.zzb(r42, r3, 4);
        return;
    L11:
        r42 = new MediaRouteSelector.Builder().addControlCategory(CastMediaControlIntent.categoryForCast(r4)).build();
        goto L12
    L22:
        return;
    L18:
        if (this.zzn == false) goto L21;
        this.zzn = false;
        zzb.d("Stopping RemoteConnectionManager discovery.", new Object[0]);
        this.zze.zzc(this.zzf);
        return;
    }

    public final /* synthetic */ void zzf(Bundle r5) {
        if (r5 == null) goto L39;
        CastDevice r0 = CastDevice.getFromBundle(r5);
        if (r0 == null) goto L37;
        String r52 = r5.getString("com.google.android.gms.cast.EXTRA_RUNNING_RECEIVER_APP_ID");
        Map r1 = this.zzi;
        if (r1.containsKey(r0.getDeviceId()) == false) goto L9;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1.get(r0.getDeviceId()));
    L9:
        String r12 = this.zzc.getReceiverApplicationId();
        if (r52 != null) goto L12;
    L34:
        zzj(r0);
        return;
    L12:
        if (r12.isEmpty() == true) goto L34;
        if (r52.equals(r12) == false) goto L34;
        Map r13 = this.zzj;
        if (r13.containsKey(r0.getDeviceId()) == false) goto L19;
        zzea r53 = (zzea) r13.get(r0.getDeviceId());
    L20:
        Set r54 = this.zzg;
        if (r54.isEmpty() == true) goto L38;
        Object r4 = this.zzl;
        monitor-enter(r4);
        Iterator r55 = r54.iterator();     // Catch: Throwable -> L28
        if (r55.hasNext() == true) goto L30;
        monitor-exit(r4);     // Catch: Throwable -> L28
        return;
    L30:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r55.next());     // Catch: Throwable -> L28
        throw null;     // Catch: Throwable -> L28
    L28:
        th = move-exception;
        throw th;
    L38:
        return;
    L19:
        zzea r2 = new zzea(r0, r52, null);
        r13.put(r0.getDeviceId(), r2);
        goto L20
    L37:
        return;
    }

    public final /* synthetic */ void zzg(CastDevice r1) {
        zzj(r1);
    }

    public final /* synthetic */ Map zzi() {
        return this.zzi;
    }
}
