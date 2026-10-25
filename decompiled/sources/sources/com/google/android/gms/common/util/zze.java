package com.google.android.gms.common.util;

import android.os.Looper;

/* loaded from: classes.dex */
public abstract class zze {
    public static boolean zza() {
        if (Looper.getMainLooper() != Looper.myLooper()) goto L6;
        return true;
    L6:
        return false;
    }
}
