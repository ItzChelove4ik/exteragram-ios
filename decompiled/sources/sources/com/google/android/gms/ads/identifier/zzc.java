package com.google.android.gms.ads.identifier;

import android.util.Log;
import androidx.mediarouter.media.GlobalMediaRouter;
import com.google.android.gms.internal.ads_identifier.zzi;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: classes4.dex */
public abstract class zzc {
    public static final void zza(String r6) {
        zzi.zzb(GlobalMediaRouter.CallbackHandler.MSG_ROUTE_UNSELECTED);     // Catch: RuntimeException -> L12 IOException -> L14 IndexOutOfBoundsException -> L16 Throwable -> L27
        HttpURLConnection r2 = (HttpURLConnection) new URL(r6).openConnection();     // Catch: RuntimeException -> L12 IOException -> L14 IndexOutOfBoundsException -> L16 Throwable -> L27
        int r3 = r2.getResponseCode();     // Catch: Throwable -> L18
        if (r3 >= 200) goto L7;
    L8:
        StringBuilder r5 = new StringBuilder(String.valueOf(r6).length() + 65);     // Catch: Throwable -> L18
        r5.append("Received non-success response code ");     // Catch: Throwable -> L18
        r5.append(r3);     // Catch: Throwable -> L18
        r5.append(" from pinging URL: ");     // Catch: Throwable -> L18
        r5.append(r6);     // Catch: Throwable -> L18
        Log.w("HttpUrlPinger", r5.toString());     // Catch: Throwable -> L18
    L9:
        r2.disconnect();     // Catch: RuntimeException -> L12 IOException -> L14 IndexOutOfBoundsException -> L16 Throwable -> L27
        zzi.zza();
        return;
    L7:
        if (r3 < 300) goto L9;
    L18:
        th = move-exception;
        r2.disconnect();     // Catch: RuntimeException -> L12 IOException -> L14 IndexOutOfBoundsException -> L16 Throwable -> L27
        throw th;     // Catch: RuntimeException -> L12 IOException -> L14 IndexOutOfBoundsException -> L16 Throwable -> L27
    L27:
        th = move-exception;
        zzi.zza();
        throw th;
    L14:
        e = e;
    L21:
        String r32 = e.getMessage();     // Catch: Throwable -> L27
        StringBuilder r52 = new StringBuilder((String.valueOf(r6).length() + 27) + String.valueOf(r32).length());     // Catch: Throwable -> L27
        r52.append("Error while pinging URL: ");     // Catch: Throwable -> L27
        r52.append(r6);     // Catch: Throwable -> L27
        r52.append(". ");     // Catch: Throwable -> L27
        r52.append(r32);     // Catch: Throwable -> L27
        Log.w("HttpUrlPinger", r52.toString(), e);     // Catch: Throwable -> L27
        zzi.zza();
        return;
    L16:
        e = move-exception;
        String r33 = e.getMessage();     // Catch: Throwable -> L27
        StringBuilder r53 = new StringBuilder((String.valueOf(r6).length() + 32) + String.valueOf(r33).length());     // Catch: Throwable -> L27
        r53.append("Error while parsing ping URL: ");     // Catch: Throwable -> L27
        r53.append(r6);     // Catch: Throwable -> L27
        r53.append(". ");     // Catch: Throwable -> L27
        r53.append(r33);     // Catch: Throwable -> L27
        Log.w("HttpUrlPinger", r53.toString(), e);     // Catch: Throwable -> L27
        zzi.zza();
        return;
    L12:
        e = e;
        goto L21
    }
}
