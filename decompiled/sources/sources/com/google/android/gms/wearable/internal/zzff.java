package com.google.android.gms.wearable.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.util.Log;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes5.dex */
final class zzff {
    private final Map zza;

    public zzff() {
        this.zza = new HashMap();
    }

    public final void zzb(IBinder r9) {
        Map r0 = this.zza;
        monitor-enter(r0);
        if (r9 != null) goto L26;
        zzfb r2 = null;
    L13:
        zzib r92 = new zzib();     // Catch: Throwable -> L10
        Iterator r8 = this.zza.entrySet().iterator();     // Catch: Throwable -> L10
    L15:
        if (r8.hasNext() == false) goto L22;
        Map.Entry r3 = (Map.Entry) r8.next();     // Catch: Throwable -> L10
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r3.getValue());     // Catch: Throwable -> L10
        r2.zzd(r92, new zzd(null));     // Catch: Throwable -> L10 RemoteException -> L21
    L18:
        if (Log.isLoggable("WearableClient", 3) == false) goto L15;
        Log.d("WearableClient", "onPostInitHandler: added: " + String.valueOf(r3.getKey()) + "/null");     // Catch: Throwable -> L10 RemoteException -> L21
    L21:
        Log.w("WearableClient", "onPostInitHandler: Didn't add: " + String.valueOf(r3.getKey()) + "/null");     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        throw th;
    L22:
        monitor-exit(r0);     // Catch: Throwable -> L10
        return;
    L26:
        IInterface r22 = r9.queryLocalInterface("com.google.android.gms.wearable.internal.IWearableService");     // Catch: Throwable -> L10
        if ((r22 instanceof zzfb) == false) goto L12;
        r2 = (zzfb) r22;     // Catch: Throwable -> L10
        goto L13
    L12:
        r2 = new zzfb(r9);     // Catch: Throwable -> L10
        goto L13
    }
}
