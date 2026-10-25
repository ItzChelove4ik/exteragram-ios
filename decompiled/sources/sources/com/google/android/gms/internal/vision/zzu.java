package com.google.android.gms.internal.vision;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

/* loaded from: classes5.dex */
public abstract class zzu {
    public static boolean zza(Context r1, String r2) {
        if (DynamiteModule.getLocalVersion(r1, r2) <= DynamiteModule.getRemoteVersion(r1, "com.google.android.gms.vision.dynamite")) goto L6;
        return true;
    L6:
        return false;
    }
}
