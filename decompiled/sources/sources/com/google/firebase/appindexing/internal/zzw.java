package com.google.firebase.appindexing.internal;

import android.util.Log;

/* loaded from: classes5.dex */
public abstract class zzw {
    public static int zza(String r1) {
        if (zzb(3) == true) goto L5;
        return 0;
    L5:
        return Log.d("FirebaseAppIndex", r1);
    }

    public static boolean zzb(int r2) {
        if (Log.isLoggable("FirebaseAppIndex", r2) == false) goto L7;
        return true;
    L7:
        return Log.isLoggable("FirebaseAppIndex", r2);
    }
}
