package com.google.android.gms.cloudmessaging;

import android.util.Log;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class zzc extends ClassLoader {
    public zzc() {
    }

    @Override // java.lang.ClassLoader
    public final Class loadClass(String r2, boolean r3) {
        if (Objects.equals(r2, "com.google.android.gms.iid.MessengerCompat") == false) goto L11;
        if (Log.isLoggable("CloudMessengerCompat", 3) == false) goto L12;
        Log.d("CloudMessengerCompat", "Using renamed FirebaseIidMessengerCompat class");
        return zzd.class;
    L12:
        return zzd.class;
    L11:
        return super.loadClass(r2, r3);
    }
}
