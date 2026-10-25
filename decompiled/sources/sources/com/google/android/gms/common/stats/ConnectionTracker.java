package com.google.android.gms.common.stats;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import org.telegram.tgnet.TLObject;

/* loaded from: classes.dex */
public class ConnectionTracker {
    private static final Object zzb = null;
    private static volatile ConnectionTracker zzc;
    public final ConcurrentHashMap zza;

    static {
        zzb = new Object();
    }

    private ConnectionTracker() {
        this.zza = new ConcurrentHashMap();
    }

    public static ConnectionTracker getInstance() {
        if (zzc != null) goto L15;
        Object r0 = zzb;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L7:
        if (zzc != null) goto L11;
        zzc = new ConnectionTracker();     // Catch: Throwable -> L9
    L11:
        monitor-exit(r0);     // Catch: Throwable -> L9
    L15:
        ConnectionTracker r02 = zzc;
        Preconditions.checkNotNull(r02);
        return r02;
    }

    private final boolean zzb(Context r4, String r5, Intent r6, ServiceConnection r7, int r8, boolean r9, Executor r10) {
        ComponentName r92 = r6.getComponent();
        if (r92 == null) goto L12;
        String r93 = r92.getPackageName();
        "com.google.android.gms".equals(r93);
        if ((Wrappers.packageManager(r4).getApplicationInfo(r93, 0).flags & TLObject.FLAG_21) == 0) goto L12;
        Log.w("ConnectionTracker", "Attempted to bind to a service in a STOPPED package.");
        return false;
    L12:
        if (zzc(r7) == false) goto L26;
        ServiceConnection r94 = (ServiceConnection) this.zza.putIfAbsent(r7, r7);
        if (r94 == null) goto L30;
        if (r7 == r94) goto L30;
        Log.w("ConnectionTracker", String.format("Duplicate binding with the same ServiceConnection: %s, %s, %s.", new Object[]{r7, r5, r6.getAction()}));
    L30:
        boolean r42 = zze(r4, r6, r7, r8, r10);     // Catch: Throwable -> L22
        if (r42 == true) goto L32;
        this.zza.remove(r7, r7);
        return false;
    L32:
        return r42;
    L22:
        th = move-exception;
        this.zza.remove(r7, r7);
        throw th;
    L26:
        return zze(r4, r6, r7, r8, r10);
    }

    private static boolean zzc(ServiceConnection r0) {
        if ((r0 instanceof zzr) == true) goto L6;
        return true;
    L6:
        return false;
    }

    private static void zzd(Context r0, ServiceConnection r1) {
        r0.unbindService(r1);     // Catch: Throwable -> L4
        return;
    }

    private static final boolean zze(Context r1, Intent r2, ServiceConnection r3, int r4, Executor r5) {
        if (r5 != null) goto L5;
        r5 = null;
    L5:
        if (PlatformVersion.isAtLeastQ() == false) goto L10;
        if (r5 == null) goto L10;
        return r1.bindService(r2, r4, r5, r3);
    L10:
        return r1.bindService(r2, r3, r4);
    }

    public boolean bindService(Context r10, Intent r11, ServiceConnection r12, int r13) {
        return zzb(r10, r10.getClass().getName(), r11, r12, r13, true, null);
    }

    public void unbindService(Context r3, ServiceConnection r4) {
        if (zzc(r4) == false) goto L12;
        ConcurrentHashMap r0 = this.zza;
        if (r0.containsKey(r4) == false) goto L12;
        zzd(r3, (ServiceConnection) r0.get(r4));     // Catch: Throwable -> L9
        this.zza.remove(r4);
        return;
    L9:
        th = move-exception;
        this.zza.remove(r4);
        throw th;
    L12:
        zzd(r3, r4);
    }

    public final boolean zza(Context r9, String r10, Intent r11, ServiceConnection r12, int r13, Executor r14) {
        return zzb(r9, r10, r11, r12, 4225, true, r14);
    }
}
