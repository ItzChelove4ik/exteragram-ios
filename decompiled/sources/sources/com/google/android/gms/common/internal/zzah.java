package com.google.android.gms.common.internal;

import android.app.PendingIntent;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;

/* loaded from: classes4.dex */
public abstract class zzah {
    private static final Uri zza = null;

    static {
        zza = new Uri.Builder().scheme("content").authority("com.google.android.gms.chimera").build();
    }

    public static Intent zza(Context r5, zzn r6) {
        String r1 = r6.zza();
        if (r1 == null) goto L5;
        Intent r3 = null;
        if (r6.zzd() == false) goto L34;
        Bundle r2 = new Bundle();
        r2.putString("serviceActionBundleKey", r1);
        ContentProviderClient r52 = r5.getContentResolver().acquireUnstableContentProviderClient(zza);     // Catch: IllegalArgumentException -> L13 RemoteException -> L15
        if (r52 == null) goto L21;
        Bundle r22 = r52.call("serviceIntentCall", null, r2);     // Catch: Throwable -> L17
        r52.release();     // Catch: IllegalArgumentException -> L13 RemoteException -> L15
    L23:
        if (r22 == null) goto L30;
        Intent r53 = (Intent) r22.getParcelable("serviceResponseIntentKey");
        if (r53 == null) goto L28;
        r3 = r53;
        goto L30
    L28:
        PendingIntent r54 = (PendingIntent) r22.getParcelable("serviceMissingResolutionIntentKey");
        if (r54 == null) goto L30;
        StringBuilder r23 = new StringBuilder(r1.length() + 72);
        r23.append("Dynamic lookup for intent failed for action ");
        r23.append(r1);
        r23.append(" but has possible resolution");
        Log.w("ServiceBindIntentUtils", r23.toString());
        throw new zzaf(new ConnectionResult(25, r54));
    L30:
        if (r3 != null) goto L34;
        Log.w("ServiceBindIntentUtils", "Dynamic lookup for intent failed for action: ".concat(r1));
    L17:
        th = move-exception;
        r52.release();     // Catch: IllegalArgumentException -> L13 RemoteException -> L15
        throw th;     // Catch: IllegalArgumentException -> L13 RemoteException -> L15
    L21:
        throw new RemoteException("Failed to acquire ContentProviderClient");     // Catch: IllegalArgumentException -> L13 RemoteException -> L15
    L15:
        e = e;
    L22:
        Log.w("ServiceBindIntentUtils", "Dynamic intent resolution failed: ".concat(e.toString()));
        r22 = null;
    L13:
        e = e;
    L34:
        if (r3 == null) goto L36;
        return r3;
    L36:
        return new Intent(r1).setPackage(r6.zzb());
    L5:
        return new Intent().setComponent(r6.zzc());
    }
}
